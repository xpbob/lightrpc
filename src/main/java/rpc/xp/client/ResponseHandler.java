package rpc.xp.client;

import java.util.concurrent.SynchronousQueue;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import rpc.xp.common.RpcResponse;

public class ResponseHandler extends SimpleChannelInboundHandler<RpcResponse> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, RpcResponse msg) throws Exception {
		String id = msg.getId();
		SynchronousQueue synchronousQueue = ResultInfo.getSynchronousQueue(id);
		synchronousQueue.put(msg.getResult());
		ResultInfo.removeById(id);
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
