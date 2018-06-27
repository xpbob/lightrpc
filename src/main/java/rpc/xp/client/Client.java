package rpc.xp.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import rpc.xp.common.RequestEncoder;
import rpc.xp.common.ResponseDecoder;

public class Client {
	private static Channel channel;

	public static void connect(String ip, int port) throws InterruptedException {
		EventLoopGroup worker = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(worker).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast(new RequestEncoder());
				pipeline.addLast(new ResponseDecoder());
				pipeline.addLast(new ResponseHandler());
			}

		});

		ChannelFuture sync = b.connect(ip, port).sync();
		channel = sync.channel();

	}

	public static void write(Object obj) {
		channel.writeAndFlush(obj);
	}
}
