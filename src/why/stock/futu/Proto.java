package why.stock.futu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Parser;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;

import Common.Common.PacketID;
import Qot_Common.QotCommon.*;
import Trd_Common.TrdCommon.*;

/**
 * @author why
 *  @date 2019/04/13 10:09 created
 */
public class Proto {
	
	private Socket s;
	
	private long connID, userID, milliSeconds, keepAliveInterval;
	
	private int nSerialNo = 2, timeOut = 30; //tenth of second

	private static MessageDigest sha1MsgDigest, md5MsgDigest;
	
	static{
		try {
			sha1MsgDigest = MessageDigest.getInstance("SHA1");
			md5MsgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(final String... args) throws Exception{
		
		String code = "00700";
		GeneratedMessageV3 response;
		QotMarket market = QotMarket.QotMarket_HK_Security;
		Proto ctx = OpenContext("127.0.0.1", 11111, false, false, "Tester");
		
		Thread.sleep(3000);
		System.out.println(response = ctx.subscribe(market, SubType.SubType_Basic, Arrays.asList(code), true));
		Thread.sleep(3000);
		System.out.println(response = ctx.get_stock_quote(market, Arrays.asList(code)));
		Thread.sleep(3000);
		System.out.println(new Date((long)((Qot_GetBasicQot.QotGetBasicQot.Response)response).getS2C().getBasicQotList(0).getUpdateTimestamp()*1000));
		
		Thread.sleep(3000);
		System.out.println(response = ctx.subscribe(market, SubType.SubType_Ticker, Arrays.asList(code), true));
		response = ctx.get_rt_ticker(market, code, 1000);
		System.out.println("Ticker " + ((Qot_GetTicker.QotGetTicker.Response)response).getS2C().getTickerListCount());
		
		System.out.println(response = ctx.subscribe(market, SubType.SubType_OrderBook, Arrays.asList(code), true));
		System.out.println(response = ctx.get_order_book(market, code, 10));
		
		System.out.println(response = ctx.subscribe(market, SubType.SubType_Broker, Arrays.asList(code), true));
		System.out.println(response = ctx.get_broker_queue(market, code));
		
		System.out.println(response = ctx.get_acc_list());
		List<TrdAcc> trdAccs = ((Trd_GetAccList.TrdGetAccList.Response)response).getS2C().getAccListList();
		TrdAcc trdAcc = Baser.firstAcc(trdAccs, TrdMarket.TrdMarket_US, true);
		TrdHeader trdHeader = Baser.trdAcc2Header(trdAcc);
		
		System.out.println(response = ctx.unlock_trade("", true));
		System.out.println(response = ctx.accinfo_query(trdHeader));
		System.out.println(response = ctx.position_list_query(trdHeader));
		System.out.println(response = ctx.order_list_query(trdHeader));
//		System.out.println(response = ctx.place_order(trdHeader, 400, 100, code, TrdSide.TrdSide_Buy, OrderType.OrderType_Normal));
//		System.out.println(response = ctx.modify_order(trdHeader, ModifyOrderOp.ModifyOrderOp_Normal, ((Trd_PlaceOrder.TrdPlaceOrder.Response)response).getS2C().getOrderID(), 399, 100));
		
//		System.out.println(response = quote_ctx.subscribe(market, SubType.SubType_Ticker, Arrays.asList(code), false));
		ctx.close();
	}
	
	private Proto(Socket s, long connID, long userID, int keepAliveInterval){
		this.s = s;
		this.connID = connID;
		this.userID = userID;
		this.keepAliveInterval = keepAliveInterval * 1000;
		this.milliSeconds = System.currentTimeMillis();
	}
	
	public void close() throws IOException{
		s.close();
	}
	
	public void setTimeOut(int timeOut) { //millisecond
		this.timeOut = timeOut/100;
	}
	
	@SuppressWarnings("deprecation")
	public static Proto OpenContext(String host, int port, boolean recNotify, boolean encrypt, String client) throws IOException, InterruptedException{
		Socket s = new Socket(host, port);
		InitConnect.InitConnect.C2S.Builder cb = InitConnect.InitConnect.C2S.newBuilder();
		cb.setClientVer(300);
		cb.setClientID("Java" + client);
		cb.setRecvNotify(recNotify);
		cb.setPacketEncAlgo(encrypt ? 4 : 0);
		InitConnect.InitConnect.Request.Builder rb = InitConnect.InitConnect.Request.newBuilder();
		rb.setC2S(cb.build());
		InitConnect.InitConnect.Response response = tcp(s, 1001, rb.build(), InitConnect.InitConnect.Response.PARSER, 1, 30);
		if(response==null){
			s.close();
			return null;
		}else{
//			System.out.println(response);
			InitConnect.InitConnect.S2C s2c = response.getS2C();
			return new Proto(s, s2c.getConnID(), s2c.getLoginUserID(), s2c.getKeepAliveInterval());
		} 
	}

	@SuppressWarnings("deprecation")
	private KeepAlive.KeepAlive.Response keep_alive() throws IOException, InterruptedException{
		KeepAlive.KeepAlive.C2S.Builder cb = KeepAlive.KeepAlive.C2S.newBuilder();
		cb.setTime(new Date().getTime()/1000);
		KeepAlive.KeepAlive.Request.Builder rb = KeepAlive.KeepAlive.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(s, 1004, rb.build(), KeepAlive.KeepAlive.Response.PARSER, nSerialNo++, timeOut);
	}
	
	@SuppressWarnings("deprecation")
	public Qot_Sub.QotSub.Response subscribe(QotMarket market, SubType type, List<String> codes, boolean subIfTrueElseUnscribe) throws IOException, InterruptedException{
		int mkt = market.getNumber();
		int subType = type.getNumber();
		boolean sz = market == QotMarket.QotMarket_CNSZ_Security;
		
		Qot_Sub.QotSub.C2S.Builder cb = Qot_Sub.QotSub.C2S.newBuilder();
		cb.setIsSubOrUnSub(subIfTrueElseUnscribe);
		for(String code : codes){
			Security.Builder sb  =  Security.newBuilder();
			sb.setMarket(sz && code.charAt(0)=='6' ? QotMarket.QotMarket_CNSH_Security_VALUE : mkt);
			sb.setCode(code);
			cb.addSecurityList(sb.build());
			cb.addSubTypeList(subType);
		}
		Qot_Sub.QotSub.Request.Builder rb = Qot_Sub.QotSub.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(3001, rb.build(), Qot_Sub.QotSub.Response.PARSER);
	}
	
	@SuppressWarnings("deprecation")
	public Qot_GetTicker.QotGetTicker.Response get_rt_ticker(QotMarket market, String code, int max) throws IOException, InterruptedException{
		int mkt = market.getNumber();
		Security.Builder sb  =  Security.newBuilder();
		sb.setMarket(mkt);
		sb.setCode(code);
		Qot_GetTicker.QotGetTicker.C2S.Builder cb = Qot_GetTicker.QotGetTicker.C2S.newBuilder();
		cb.setSecurity(sb.build());
		cb.setMaxRetNum(max);
		Qot_GetTicker.QotGetTicker.Request.Builder rb = Qot_GetTicker.QotGetTicker.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(3010, rb.build(), Qot_GetTicker.QotGetTicker.Response.PARSER);
	}
	
	@SuppressWarnings("deprecation")
	public Qot_GetBasicQot.QotGetBasicQot.Response get_stock_quote(QotMarket market, List<String> codes) throws IOException, InterruptedException{
		int mkt = market.getNumber();
		boolean sz = market == QotMarket.QotMarket_CNSZ_Security;
		
		Qot_GetBasicQot.QotGetBasicQot.C2S.Builder cb = Qot_GetBasicQot.QotGetBasicQot.C2S.newBuilder();
		for(String code : codes){
			Security.Builder sb  =  Security.newBuilder();
			sb.setMarket(sz && code.charAt(0)=='6' ? QotMarket.QotMarket_CNSH_Security_VALUE : mkt);
			sb.setCode(code);
			cb.addSecurityList(sb.build());
		}
		Qot_GetBasicQot.QotGetBasicQot.Request.Builder rb = Qot_GetBasicQot.QotGetBasicQot.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(3004, rb.build(), Qot_GetBasicQot.QotGetBasicQot.Response.PARSER);
	}
	
	@SuppressWarnings("deprecation")
	public Qot_GetOrderBook.QotGetOrderBook.Response get_order_book(QotMarket market, String code, int num) throws IOException, InterruptedException{
		int mkt = market.getNumber();
		Security.Builder sb = Security.newBuilder();
		sb.setMarket(mkt);
		sb.setCode(code);
		Qot_GetOrderBook.QotGetOrderBook.C2S.Builder cb = Qot_GetOrderBook.QotGetOrderBook.C2S.newBuilder();
		cb.setSecurity(sb.build());
		cb.setNum(num);
		Qot_GetOrderBook.QotGetOrderBook.Request.Builder rb = Qot_GetOrderBook.QotGetOrderBook.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(3012, rb.build(), Qot_GetOrderBook.QotGetOrderBook.Response.PARSER);
	}
	
	@SuppressWarnings("deprecation")
	public Qot_GetBroker.QotGetBroker.Response get_broker_queue(QotMarket market, String code) throws IOException, InterruptedException{
		int mkt = market.getNumber();
		Security.Builder sb = Security.newBuilder();
		sb.setMarket(mkt);
		sb.setCode(code);
		Qot_GetBroker.QotGetBroker.C2S.Builder cb = Qot_GetBroker.QotGetBroker.C2S.newBuilder();
		cb.setSecurity(sb.build());
		Qot_GetBroker.QotGetBroker.Request.Builder rb = Qot_GetBroker.QotGetBroker.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(3014, rb.build(), Qot_GetBroker.QotGetBroker.Response.PARSER);
	}
	
	@SuppressWarnings("deprecation")
	public Trd_GetAccList.TrdGetAccList.Response get_acc_list() throws IOException, InterruptedException{
		Trd_GetAccList.TrdGetAccList.C2S.Builder cb = Trd_GetAccList.TrdGetAccList.C2S.newBuilder();
		cb.setUserID(userID);
		Trd_GetAccList.TrdGetAccList.Request.Builder rb = Trd_GetAccList.TrdGetAccList.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(2001, rb.build(), Trd_GetAccList.TrdGetAccList.Response.PARSER);
	}
	
	@SuppressWarnings("deprecation")
	public Trd_UnlockTrade.TrdUnlockTrade.Response unlock_trade(String password, boolean is_unlock) throws IOException, InterruptedException{
		Trd_UnlockTrade.TrdUnlockTrade.C2S.Builder cb = Trd_UnlockTrade.TrdUnlockTrade.C2S.newBuilder();
		cb.setUnlock(is_unlock);
		if(password!=null){
			md5MsgDigest.update(password.getBytes());
			cb.setPwdMD5(Baser.bytesToHex(md5MsgDigest.digest()));
		}
		Trd_UnlockTrade.TrdUnlockTrade.Request.Builder rb = Trd_UnlockTrade.TrdUnlockTrade.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(2005, rb.build(), Trd_UnlockTrade.TrdUnlockTrade.Response.PARSER);
	}
	
	@SuppressWarnings("deprecation")
	public Trd_GetFunds.TrdGetFunds.Response accinfo_query(TrdHeader trdHeader) throws IOException, InterruptedException{
		Trd_GetFunds.TrdGetFunds.C2S.Builder cb = Trd_GetFunds.TrdGetFunds.C2S.newBuilder();
		cb.setHeader(trdHeader);
		Trd_GetFunds.TrdGetFunds.Request.Builder rb = Trd_GetFunds.TrdGetFunds.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(2101, rb.build(), Trd_GetFunds.TrdGetFunds.Response.PARSER);
	}
	
	@SuppressWarnings("deprecation")
	public Trd_GetPositionList.TrdGetPositionList.Response position_list_query(TrdHeader trdHeader) throws IOException, InterruptedException{
		Trd_GetPositionList.TrdGetPositionList.C2S.Builder cb = Trd_GetPositionList.TrdGetPositionList.C2S.newBuilder();
		cb.setHeader(trdHeader);
		Trd_GetPositionList.TrdGetPositionList.Request.Builder rb = Trd_GetPositionList.TrdGetPositionList.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(2102, rb.build(), Trd_GetPositionList.TrdGetPositionList.Response.PARSER);
	}
	
	@SuppressWarnings("deprecation")
	public Trd_GetOrderList.TrdGetOrderList.Response order_list_query(TrdHeader trdHeader) throws IOException, InterruptedException{
		Trd_GetOrderList.TrdGetOrderList.C2S.Builder cb = Trd_GetOrderList.TrdGetOrderList.C2S.newBuilder();
		cb.setHeader(trdHeader);
		Trd_GetOrderList.TrdGetOrderList.Request.Builder rb = Trd_GetOrderList.TrdGetOrderList.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(2201, rb.build(), Trd_GetOrderList.TrdGetOrderList.Response.PARSER);
	}
	
	@SuppressWarnings("deprecation")
	public Trd_PlaceOrder.TrdPlaceOrder.Response place_order(TrdHeader trdHeader, double price, long qty, String code, TrdSide trd_side, OrderType order_type) throws IOException, InterruptedException{
		Trd_PlaceOrder.TrdPlaceOrder.C2S.Builder cb = Trd_PlaceOrder.TrdPlaceOrder.C2S.newBuilder();
		cb.setHeader(trdHeader);
		cb.setCode(code);
		cb.setOrderType(order_type.getNumber());
		PacketID.Builder pb = PacketID.newBuilder();
		pb.setConnID(connID);
		pb.setSerialNo(nSerialNo);
		cb.setPacketID(pb.build());
		cb.setPrice(price);
		cb.setQty(qty);
		cb.setTrdSide(trd_side.getNumber());
		
		Trd_PlaceOrder.TrdPlaceOrder.Request.Builder rb = Trd_PlaceOrder.TrdPlaceOrder.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(2202, rb.build(), Trd_PlaceOrder.TrdPlaceOrder.Response.PARSER);
	}
	
	@SuppressWarnings("deprecation")
	public Trd_ModifyOrder.TrdModifyOrder.Response modify_order(TrdHeader trdHeader, ModifyOrderOp modify_order_op, long order_id, double price, long qty) throws IOException, InterruptedException{
		Trd_ModifyOrder.TrdModifyOrder.C2S.Builder cb = Trd_ModifyOrder.TrdModifyOrder.C2S.newBuilder();
		cb.setHeader(trdHeader);
		PacketID.Builder pb = PacketID.newBuilder();
		pb.setConnID(connID);
		pb.setSerialNo(nSerialNo);
		cb.setPacketID(pb.build());
		cb.setPrice(price);
		cb.setQty(qty);
		cb.setModifyOrderOp(modify_order_op.getNumber());
		cb.setOrderID(order_id);
		
		Trd_ModifyOrder.TrdModifyOrder.Request.Builder rb = Trd_ModifyOrder.TrdModifyOrder.Request.newBuilder();
		rb.setC2S(cb.build());
		return tcp(2205, rb.build(), Trd_ModifyOrder.TrdModifyOrder.Response.PARSER);
	}
	
	private <T extends GeneratedMessageV3> T tcp(int nProtoID, GeneratedMessageV3 in, Parser<T> PARSER) throws IOException, InterruptedException{
		long curMilliSeconds = System.currentTimeMillis();
//		System.out.println("runing " + in.getClass() + " at " + (curMilliSeconds-milliSeconds)/1000);
		if(curMilliSeconds-milliSeconds>keepAliveInterval){
			this.keep_alive();
			this.milliSeconds = System.currentTimeMillis();
		}
		T t = tcp(s, nProtoID, in, PARSER, nSerialNo++, timeOut);
		return t;
	}
	private static synchronized <T extends GeneratedMessageV3> T tcp(Socket s, int nProtoID, GeneratedMessageV3 in, Parser<T> PARSER, int nSerialNo, int timeOut) 
			throws IOException, InterruptedException//, ReflectiveOperationException, IllegalArgumentException, SecurityException 
	{        
        byte[] to = in.toByteArray();
        APIProtoHeader header = new APIProtoHeader(nProtoID, nSerialNo, to);
        header.write();
        int hsize = header.size();
        OutputStream os = s.getOutputStream();
        os.write(header.getPointer().getByteArray(0, hsize));
        os.write(to);
        os.flush();
        
        int b, j=0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = s.getInputStream();
        while((b=is.available())==0 && j++<timeOut)
        	Thread.sleep(100);
        do{
        	for(int i=0; i<b; i++)
        		baos.write(is.read());
        }while((b=is.available())>0);
        
        byte[] datas = baos.toByteArray();
        if(datas.length<hsize)
        	return null;
        
        Pointer p = new Memory(hsize);
        p.write(0, datas, 0, hsize);
        APIProtoHeader head = new APIProtoHeader(p);
        head.read();
        if(head.nProtoID!=header.nProtoID || head.nProtoVer!=header.nProtoVer || head.nProtoFmtType!=header.nProtoFmtType 
        		|| head.szHeaderFlag[0]!=header.szHeaderFlag[0] || head.szHeaderFlag[1]!=header.szHeaderFlag[1] || head.nSerialNo!=header.nSerialNo)
        	return null;
        
        T response = PARSER.parseFrom(datas = Arrays.copyOfRange(datas, hsize, datas.length));
//        if((Integer)response.getClass().getMethod("getRetType").invoke(response)!=0){
//        	System.err.println("send: " + Baser.bytesToHex(to));
//        	System.err.println("response: " + Baser.bytesToHex(datas));
//        }
        return response;
	}
	
//	Э�����
//	Э�����ݰ���Э��ͷ�Լ�Э���壬Э��ͷ�̶��ֶΣ�Э������ݾ���Э�����

	//	Э��ͷ�ṹ
//	szHeaderFlag	��ͷ��ʼ��־���̶�Ϊ��FT��
//	nProtoID	Э��ID
//	nProtoFmtType	Э���ʽ���ͣ�0ΪProtobuf��ʽ��1ΪJson��ʽ
//	nProtoVer	Э��汾�����ڵ�������, Ŀǰ��0
//	nSerialNo	�����кţ����ڶ�Ӧ������ͻذ�, Ҫ�����
//	nBodyLen	���峤��
//	arrBodySHA1	����ԭʼ����(���ܺ�)��SHA1��ϣֵ
//	arrReserved	����8�ֽ���չ
	public static class APIProtoHeader extends Structure
	{
		public byte szHeaderFlag[] = {'F', 'T'};
		public int nProtoID;
		public byte nProtoFmtType;
		public byte nProtoVer;
		public int nSerialNo;
		public int nBodyLen;
		public byte arrBodySHA1[] = new byte[20];
		public byte arrReserved[] = {0, 0, 0, 0, 0, 0, 0, 0}; //= new byte[8];
	    
	    public APIProtoHeader(int nProtoID, int nSerialNo, byte[] bodys){
	    	this.nProtoID = nProtoID;
	    	this.nSerialNo = nSerialNo;
	    	this.nBodyLen = bodys.length;
	    	sha1MsgDigest.update(bodys);
	    	this.arrBodySHA1 = sha1MsgDigest.digest();
	    }
	    
	    public APIProtoHeader(Pointer p){
			super(p);
		}
	}
//	FutuOpenD�ڲ�����ʹ��Protobuf�����Э���ʽ����ʹ��Protobuf������Jsonת������
//	nProtoFmtType�ֶ�ָ���˰�����������ͣ��ذ���ض�Ӧ���͵����ݣ�����Э������������FutuOpenD�����ļ�ָ��
//	arrBodySHA1����У���������������紫��ǰ���һ���ԣ�������ȷ����
//	Э��ͷ�Ķ�������ʹ�õ���С���ֽ��򣬼�һ�㲻��Ҫʹ��ntohl����غ���ת������
	
//	Э����ṹ
//	ProtobufЭ���������ṹ
//
//	message C2S
//	{
//	    required int64 req = 1;
//	}
//
//	message Request
//	{
//	    required C2S c2s = 1;
//	}
//	ProtobufЭ���Ӧ����ṹ
//
//	message S2C
//	{
//	    required int64 data = 1;
//	}
//
//	message Response
//	{
//	    required int32 retType = 1 [default = -400]; //RetType,���ؽ��
//	    optional string retMsg = 2;
//	    optional int32 errCode = 3;
//	    optional S2C s2c = 4;
//	}
	
//	JsonЭ���������ṹ
//
//	{
//	    "c2s":
//	    {
//	         "req": 0
//	    }
//	}
//	JsonЭ���Ӧ����ṹ
//
//	{
//	    "retType" : 0
//	    "retMsg" : ""
//	    "errCode" : 0
//	    "s2c":
//	    {
//	        "data": 0
//	    }
//	}
//	�ֶ�	˵��
//	c2s	��������ṹ
//	req	���������ʵ�ʸ���Э�鶨��
//	retType	������
//	retMsg	������ʧ�ܣ�˵��ʧ��ԭ��
//	errCode	������ʧ�ܶ�Ӧ������
//	s2c	��Ӧ���ݽṹ������Э�鲻�����������޸��ֶ�
//	data	��Ӧ���ݣ�ʵ�ʸ���Э�鶨��
}
