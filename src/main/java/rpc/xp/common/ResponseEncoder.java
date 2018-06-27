package rpc.xp.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ResponseEncoder extends MessageToByteEncoder<RpcResponse> {

	@Override
	protected void encode(ChannelHandlerContext ctx, RpcResponse msg, ByteBuf out) throws Exception {
		byte[] serializeToByte = SerializationUtil.serializeToByte(msg);
		out.writeInt(serializeToByte.length);
		out.writeBytes(serializeToByte);

	}

}
