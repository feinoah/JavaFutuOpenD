// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Common.proto

package Common;

public final class Common {
  private Common() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * <pre>
   *返回结果
   * </pre>
   *
   * Protobuf enum {@code Common.RetType}
   */
  public enum RetType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     *成功
     * </pre>
     *
     * <code>RetType_Succeed = 0;</code>
     */
    RetType_Succeed(0),
    /**
     * <pre>
     *失败
     * </pre>
     *
     * <code>RetType_Failed = -1;</code>
     */
    RetType_Failed(-1),
    /**
     * <pre>
     *超时
     * </pre>
     *
     * <code>RetType_TimeOut = -100;</code>
     */
    RetType_TimeOut(-100),
    /**
     * <pre>
     *未知结果
     * </pre>
     *
     * <code>RetType_Unknown = -400;</code>
     */
    RetType_Unknown(-400),
    ;

    /**
     * <pre>
     *成功
     * </pre>
     *
     * <code>RetType_Succeed = 0;</code>
     */
    public static final int RetType_Succeed_VALUE = 0;
    /**
     * <pre>
     *失败
     * </pre>
     *
     * <code>RetType_Failed = -1;</code>
     */
    public static final int RetType_Failed_VALUE = -1;
    /**
     * <pre>
     *超时
     * </pre>
     *
     * <code>RetType_TimeOut = -100;</code>
     */
    public static final int RetType_TimeOut_VALUE = -100;
    /**
     * <pre>
     *未知结果
     * </pre>
     *
     * <code>RetType_Unknown = -400;</code>
     */
    public static final int RetType_Unknown_VALUE = -400;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static RetType valueOf(int value) {
      return forNumber(value);
    }

    public static RetType forNumber(int value) {
      switch (value) {
        case 0: return RetType_Succeed;
        case -1: return RetType_Failed;
        case -100: return RetType_TimeOut;
        case -400: return RetType_Unknown;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<RetType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        RetType> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<RetType>() {
            public RetType findValueByNumber(int number) {
              return RetType.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return Common.getDescriptor().getEnumTypes().get(0);
    }

    private static final RetType[] VALUES = values();

    public static RetType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private RetType(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:Common.RetType)
  }

  /**
   * <pre>
   *包加密算法
   * </pre>
   *
   * Protobuf enum {@code Common.PacketEncAlgo}
   */
  public enum PacketEncAlgo
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     *富途修改过的AES加密的ECB模式
     * </pre>
     *
     * <code>PacketEncAlgo_FTAES_ECB = 0;</code>
     */
    PacketEncAlgo_FTAES_ECB(0),
    /**
     * <pre>
     *不加密
     * </pre>
     *
     * <code>PacketEncAlgo_None = -1;</code>
     */
    PacketEncAlgo_None(-1),
    /**
     * <pre>
     *标准的AES加密的ECB模式
     * </pre>
     *
     * <code>PacketEncAlgo_AES_ECB = 1;</code>
     */
    PacketEncAlgo_AES_ECB(1),
    ;

    /**
     * <pre>
     *富途修改过的AES加密的ECB模式
     * </pre>
     *
     * <code>PacketEncAlgo_FTAES_ECB = 0;</code>
     */
    public static final int PacketEncAlgo_FTAES_ECB_VALUE = 0;
    /**
     * <pre>
     *不加密
     * </pre>
     *
     * <code>PacketEncAlgo_None = -1;</code>
     */
    public static final int PacketEncAlgo_None_VALUE = -1;
    /**
     * <pre>
     *标准的AES加密的ECB模式
     * </pre>
     *
     * <code>PacketEncAlgo_AES_ECB = 1;</code>
     */
    public static final int PacketEncAlgo_AES_ECB_VALUE = 1;


    public final int getNumber() {
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static PacketEncAlgo valueOf(int value) {
      return forNumber(value);
    }

    public static PacketEncAlgo forNumber(int value) {
      switch (value) {
        case 0: return PacketEncAlgo_FTAES_ECB;
        case -1: return PacketEncAlgo_None;
        case 1: return PacketEncAlgo_AES_ECB;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<PacketEncAlgo>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        PacketEncAlgo> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<PacketEncAlgo>() {
            public PacketEncAlgo findValueByNumber(int number) {
              return PacketEncAlgo.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return Common.getDescriptor().getEnumTypes().get(1);
    }

    private static final PacketEncAlgo[] VALUES = values();

    public static PacketEncAlgo valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private PacketEncAlgo(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:Common.PacketEncAlgo)
  }

  public interface PacketIDOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Common.PacketID)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     *当前TCP连接的连接ID，一条连接的唯一标识，InitConnect协议会返回
     * </pre>
     *
     * <code>required uint64 connID = 1;</code>
     */
    boolean hasConnID();
    /**
     * <pre>
     *当前TCP连接的连接ID，一条连接的唯一标识，InitConnect协议会返回
     * </pre>
     *
     * <code>required uint64 connID = 1;</code>
     */
    long getConnID();

    /**
     * <pre>
     *包头中的包自增序列号
     * </pre>
     *
     * <code>required uint32 serialNo = 2;</code>
     */
    boolean hasSerialNo();
    /**
     * <pre>
     *包头中的包自增序列号
     * </pre>
     *
     * <code>required uint32 serialNo = 2;</code>
     */
    int getSerialNo();
  }
  /**
   * <pre>
   *包的唯一标识，用于回放攻击的识别和保护
   * </pre>
   *
   * Protobuf type {@code Common.PacketID}
   */
  public  static final class PacketID extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Common.PacketID)
      PacketIDOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use PacketID.newBuilder() to construct.
    private PacketID(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private PacketID() {
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private PacketID(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              bitField0_ |= 0x00000001;
              connID_ = input.readUInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              serialNo_ = input.readUInt32();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Common.internal_static_Common_PacketID_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Common.internal_static_Common_PacketID_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Common.PacketID.class, Common.PacketID.Builder.class);
    }

    private int bitField0_;
    public static final int CONNID_FIELD_NUMBER = 1;
    private long connID_;
    /**
     * <pre>
     *当前TCP连接的连接ID，一条连接的唯一标识，InitConnect协议会返回
     * </pre>
     *
     * <code>required uint64 connID = 1;</code>
     */
    public boolean hasConnID() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <pre>
     *当前TCP连接的连接ID，一条连接的唯一标识，InitConnect协议会返回
     * </pre>
     *
     * <code>required uint64 connID = 1;</code>
     */
    public long getConnID() {
      return connID_;
    }

    public static final int SERIALNO_FIELD_NUMBER = 2;
    private int serialNo_;
    /**
     * <pre>
     *包头中的包自增序列号
     * </pre>
     *
     * <code>required uint32 serialNo = 2;</code>
     */
    public boolean hasSerialNo() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <pre>
     *包头中的包自增序列号
     * </pre>
     *
     * <code>required uint32 serialNo = 2;</code>
     */
    public int getSerialNo() {
      return serialNo_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasConnID()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasSerialNo()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) != 0)) {
        output.writeUInt64(1, connID_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        output.writeUInt32(2, serialNo_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(1, connID_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, serialNo_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof Common.PacketID)) {
        return super.equals(obj);
      }
      Common.PacketID other = (Common.PacketID) obj;

      if (hasConnID() != other.hasConnID()) return false;
      if (hasConnID()) {
        if (getConnID()
            != other.getConnID()) return false;
      }
      if (hasSerialNo() != other.hasSerialNo()) return false;
      if (hasSerialNo()) {
        if (getSerialNo()
            != other.getSerialNo()) return false;
      }
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasConnID()) {
        hash = (37 * hash) + CONNID_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            getConnID());
      }
      if (hasSerialNo()) {
        hash = (37 * hash) + SERIALNO_FIELD_NUMBER;
        hash = (53 * hash) + getSerialNo();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static Common.PacketID parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Common.PacketID parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Common.PacketID parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Common.PacketID parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Common.PacketID parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Common.PacketID parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Common.PacketID parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static Common.PacketID parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static Common.PacketID parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static Common.PacketID parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static Common.PacketID parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static Common.PacketID parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(Common.PacketID prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     *包的唯一标识，用于回放攻击的识别和保护
     * </pre>
     *
     * Protobuf type {@code Common.PacketID}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Common.PacketID)
        Common.PacketIDOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return Common.internal_static_Common_PacketID_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return Common.internal_static_Common_PacketID_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                Common.PacketID.class, Common.PacketID.Builder.class);
      }

      // Construct using Common.PacketID.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        connID_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        serialNo_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return Common.internal_static_Common_PacketID_descriptor;
      }

      @java.lang.Override
      public Common.PacketID getDefaultInstanceForType() {
        return Common.PacketID.getDefaultInstance();
      }

      @java.lang.Override
      public Common.PacketID build() {
        Common.PacketID result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public Common.PacketID buildPartial() {
        Common.PacketID result = new Common.PacketID(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.connID_ = connID_;
          to_bitField0_ |= 0x00000001;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.serialNo_ = serialNo_;
          to_bitField0_ |= 0x00000002;
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof Common.PacketID) {
          return mergeFrom((Common.PacketID)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(Common.PacketID other) {
        if (other == Common.PacketID.getDefaultInstance()) return this;
        if (other.hasConnID()) {
          setConnID(other.getConnID());
        }
        if (other.hasSerialNo()) {
          setSerialNo(other.getSerialNo());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        if (!hasConnID()) {
          return false;
        }
        if (!hasSerialNo()) {
          return false;
        }
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        Common.PacketID parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (Common.PacketID) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long connID_ ;
      /**
       * <pre>
       *当前TCP连接的连接ID，一条连接的唯一标识，InitConnect协议会返回
       * </pre>
       *
       * <code>required uint64 connID = 1;</code>
       */
      public boolean hasConnID() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <pre>
       *当前TCP连接的连接ID，一条连接的唯一标识，InitConnect协议会返回
       * </pre>
       *
       * <code>required uint64 connID = 1;</code>
       */
      public long getConnID() {
        return connID_;
      }
      /**
       * <pre>
       *当前TCP连接的连接ID，一条连接的唯一标识，InitConnect协议会返回
       * </pre>
       *
       * <code>required uint64 connID = 1;</code>
       */
      public Builder setConnID(long value) {
        bitField0_ |= 0x00000001;
        connID_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *当前TCP连接的连接ID，一条连接的唯一标识，InitConnect协议会返回
       * </pre>
       *
       * <code>required uint64 connID = 1;</code>
       */
      public Builder clearConnID() {
        bitField0_ = (bitField0_ & ~0x00000001);
        connID_ = 0L;
        onChanged();
        return this;
      }

      private int serialNo_ ;
      /**
       * <pre>
       *包头中的包自增序列号
       * </pre>
       *
       * <code>required uint32 serialNo = 2;</code>
       */
      public boolean hasSerialNo() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <pre>
       *包头中的包自增序列号
       * </pre>
       *
       * <code>required uint32 serialNo = 2;</code>
       */
      public int getSerialNo() {
        return serialNo_;
      }
      /**
       * <pre>
       *包头中的包自增序列号
       * </pre>
       *
       * <code>required uint32 serialNo = 2;</code>
       */
      public Builder setSerialNo(int value) {
        bitField0_ |= 0x00000002;
        serialNo_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *包头中的包自增序列号
       * </pre>
       *
       * <code>required uint32 serialNo = 2;</code>
       */
      public Builder clearSerialNo() {
        bitField0_ = (bitField0_ & ~0x00000002);
        serialNo_ = 0;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:Common.PacketID)
    }

    // @@protoc_insertion_point(class_scope:Common.PacketID)
    private static final Common.PacketID DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new Common.PacketID();
    }

    public static Common.PacketID getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<PacketID>
        PARSER = new com.google.protobuf.AbstractParser<PacketID>() {
      @java.lang.Override
      public PacketID parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PacketID(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<PacketID> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<PacketID> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public Common.PacketID getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Common_PacketID_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Common_PacketID_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014Common.proto\022\006Common\",\n\010PacketID\022\016\n\006co" +
      "nnID\030\001 \002(\004\022\020\n\010serialNo\030\002 \002(\r*w\n\007RetType\022" +
      "\023\n\017RetType_Succeed\020\000\022\033\n\016RetType_Failed\020\377" +
      "\377\377\377\377\377\377\377\377\001\022\034\n\017RetType_TimeOut\020\234\377\377\377\377\377\377\377\377\001\022" +
      "\034\n\017RetType_Unknown\020\360\374\377\377\377\377\377\377\377\001*h\n\rPacketE" +
      "ncAlgo\022\033\n\027PacketEncAlgo_FTAES_ECB\020\000\022\037\n\022P" +
      "acketEncAlgo_None\020\377\377\377\377\377\377\377\377\377\001\022\031\n\025PacketEn" +
      "cAlgo_AES_ECB\020\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_Common_PacketID_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Common_PacketID_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Common_PacketID_descriptor,
        new java.lang.String[] { "ConnID", "SerialNo", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
