package rpc.xp.common;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ResponseDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		if (in.readableBytes() < 4) {
			return;
		}
		int index = in.readerIndex();
		int readInt = in.readInt();
		if (in.readableBytes() < readInt) {
			in.readerIndex(index);
			return;
		}
		byte[] bytes = new byte[readInt];
		in.readBytes(bytes);
		RpcResponse response = SerializationUtil.deserializeFromByte(bytes, RpcResponse.class);
		out.add(response);
	}

}
