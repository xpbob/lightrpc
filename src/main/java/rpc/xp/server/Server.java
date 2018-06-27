package rpc.xp.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import rpc.xp.common.RequestDecoder;
import rpc.xp.common.ResponseEncoder;

public class Server {
	public static void bind(int port) throws InterruptedException {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();

		ServerBootstrap server = new ServerBootstrap();
		server.group(boss, worker).channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel channel) throws Exception {
						ChannelPipeline pipeline = channel.pipeline();
						pipeline.addLast(new RequestDecoder());
						pipeline.addLast(new ResponseEncoder());
						pipeline.addLast(new RequestHandler());
					}

				});
		ChannelFuture sync = server.bind(port).sync();
		sync.channel().closeFuture().sync();
	}
}
