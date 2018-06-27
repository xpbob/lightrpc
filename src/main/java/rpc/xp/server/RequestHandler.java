package rpc.xp.server;

import java.lang.reflect.Method;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import rpc.xp.common.RpcRequest;
import rpc.xp.common.RpcResponse;

public class RequestHandler extends SimpleChannelInboundHandler<RpcRequest> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
		Object service = Invoker.getService(msg.getClassName());
		Method declaredMethod = service.getClass().getDeclaredMethod(msg.getMethodName(), msg.getParameterTypes());
		Object invoke = declaredMethod.invoke(service, msg.getArgs());
		RpcResponse response = new RpcResponse();
		response.setId(msg.getId());
		response.setResult(invoke);
		ctx.channel().writeAndFlush(response);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.channel().close();
	}

	
}
