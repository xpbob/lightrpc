package rpc.xp.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RequestEncoder extends MessageToByteEncoder<RpcRequest> {

	@Override
	protected void encode(ChannelHandlerContext ctx, RpcRequest msg, ByteBuf out) throws Exception {
		byte[] serializeToByte = SerializationUtil.serializeToByte(msg);
		out.writeInt(serializeToByte.length);
		out.writeBytes(serializeToByte);

	}

}
