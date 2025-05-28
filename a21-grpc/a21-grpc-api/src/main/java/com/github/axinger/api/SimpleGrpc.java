package com.github.axinger.api;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.53.0)",
    comments = "Source: Simple.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SimpleGrpc {

  private SimpleGrpc() {}

  public static final String SERVICE_NAME = "Simple";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<MyRequest,
      MyResponse> getOneToOneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OneToOne",
      requestType = MyRequest.class,
      responseType = MyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MyRequest,
      MyResponse> getOneToOneMethod() {
    io.grpc.MethodDescriptor<MyRequest, MyResponse> getOneToOneMethod;
    if ((getOneToOneMethod = SimpleGrpc.getOneToOneMethod) == null) {
      synchronized (SimpleGrpc.class) {
        if ((getOneToOneMethod = SimpleGrpc.getOneToOneMethod) == null) {
          SimpleGrpc.getOneToOneMethod = getOneToOneMethod =
              io.grpc.MethodDescriptor.<MyRequest, MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OneToOne"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SimpleMethodDescriptorSupplier("OneToOne"))
              .build();
        }
      }
    }
    return getOneToOneMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
      com.google.protobuf.StringValue> getGet1Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get1",
      requestType = com.google.protobuf.StringValue.class,
      responseType = com.google.protobuf.StringValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
      com.google.protobuf.StringValue> getGet1Method() {
    io.grpc.MethodDescriptor<com.google.protobuf.StringValue, com.google.protobuf.StringValue> getGet1Method;
    if ((getGet1Method = SimpleGrpc.getGet1Method) == null) {
      synchronized (SimpleGrpc.class) {
        if ((getGet1Method = SimpleGrpc.getGet1Method) == null) {
          SimpleGrpc.getGet1Method = getGet1Method =
              io.grpc.MethodDescriptor.<com.google.protobuf.StringValue, com.google.protobuf.StringValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Get1"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.StringValue.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.StringValue.getDefaultInstance()))
              .setSchemaDescriptor(new SimpleMethodDescriptorSupplier("Get1"))
              .build();
        }
      }
    }
    return getGet1Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
      MyStructDto> getGet2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get2",
      requestType = com.google.protobuf.StringValue.class,
      responseType = MyStructDto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
      MyStructDto> getGet2Method() {
    io.grpc.MethodDescriptor<com.google.protobuf.StringValue, MyStructDto> getGet2Method;
    if ((getGet2Method = SimpleGrpc.getGet2Method) == null) {
      synchronized (SimpleGrpc.class) {
        if ((getGet2Method = SimpleGrpc.getGet2Method) == null) {
          SimpleGrpc.getGet2Method = getGet2Method =
              io.grpc.MethodDescriptor.<com.google.protobuf.StringValue, MyStructDto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Get2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.StringValue.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MyStructDto.getDefaultInstance()))
              .setSchemaDescriptor(new SimpleMethodDescriptorSupplier("Get2"))
              .build();
        }
      }
    }
    return getGet2Method;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SimpleStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SimpleStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SimpleStub>() {
        @Override
        public SimpleStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SimpleStub(channel, callOptions);
        }
      };
    return SimpleStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SimpleBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SimpleBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SimpleBlockingStub>() {
        @Override
        public SimpleBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SimpleBlockingStub(channel, callOptions);
        }
      };
    return SimpleBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SimpleFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SimpleFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SimpleFutureStub>() {
        @Override
        public SimpleFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SimpleFutureStub(channel, callOptions);
        }
      };
    return SimpleFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SimpleImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 简单gRPC
     * </pre>
     */
    public void oneToOne(MyRequest request,
                         io.grpc.stub.StreamObserver<MyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOneToOneMethod(), responseObserver);
    }

    /**
     */
    public void get1(com.google.protobuf.StringValue request,
        io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGet1Method(), responseObserver);
    }

    /**
     */
    public void get2(com.google.protobuf.StringValue request,
        io.grpc.stub.StreamObserver<MyStructDto> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGet2Method(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOneToOneMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                MyRequest,
                MyResponse>(
                  this, METHODID_ONE_TO_ONE)))
          .addMethod(
            getGet1Method(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.StringValue,
                com.google.protobuf.StringValue>(
                  this, METHODID_GET1)))
          .addMethod(
            getGet2Method(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.StringValue,
                MyStructDto>(
                  this, METHODID_GET2)))
          .build();
    }
  }

  /**
   */
  public static final class SimpleStub extends io.grpc.stub.AbstractAsyncStub<SimpleStub> {
    private SimpleStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SimpleStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SimpleStub(channel, callOptions);
    }

    /**
     * <pre>
     * 简单gRPC
     * </pre>
     */
    public void oneToOne(MyRequest request,
                         io.grpc.stub.StreamObserver<MyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOneToOneMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get1(com.google.protobuf.StringValue request,
        io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGet1Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get2(com.google.protobuf.StringValue request,
        io.grpc.stub.StreamObserver<MyStructDto> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGet2Method(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SimpleBlockingStub extends io.grpc.stub.AbstractBlockingStub<SimpleBlockingStub> {
    private SimpleBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SimpleBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SimpleBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 简单gRPC
     * </pre>
     */
    public MyResponse oneToOne(MyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOneToOneMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.StringValue get1(com.google.protobuf.StringValue request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGet1Method(), getCallOptions(), request);
    }

    /**
     */
    public MyStructDto get2(com.google.protobuf.StringValue request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGet2Method(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SimpleFutureStub extends io.grpc.stub.AbstractFutureStub<SimpleFutureStub> {
    private SimpleFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected SimpleFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SimpleFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 简单gRPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<MyResponse> oneToOne(
        MyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOneToOneMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.StringValue> get1(
        com.google.protobuf.StringValue request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGet1Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MyStructDto> get2(
        com.google.protobuf.StringValue request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGet2Method(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ONE_TO_ONE = 0;
  private static final int METHODID_GET1 = 1;
  private static final int METHODID_GET2 = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SimpleImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SimpleImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ONE_TO_ONE:
          serviceImpl.oneToOne((MyRequest) request,
              (io.grpc.stub.StreamObserver<MyResponse>) responseObserver);
          break;
        case METHODID_GET1:
          serviceImpl.get1((com.google.protobuf.StringValue) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.StringValue>) responseObserver);
          break;
        case METHODID_GET2:
          serviceImpl.get2((com.google.protobuf.StringValue) request,
              (io.grpc.stub.StreamObserver<MyStructDto>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SimpleBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SimpleBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return SimpleProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Simple");
    }
  }

  private static final class SimpleFileDescriptorSupplier
      extends SimpleBaseDescriptorSupplier {
    SimpleFileDescriptorSupplier() {}
  }

  private static final class SimpleMethodDescriptorSupplier
      extends SimpleBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SimpleMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SimpleGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SimpleFileDescriptorSupplier())
              .addMethod(getOneToOneMethod())
              .addMethod(getGet1Method())
              .addMethod(getGet2Method())
              .build();
        }
      }
    }
    return result;
  }
}
