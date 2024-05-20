package org.tinygame.herostory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinygame.herostory.cmdhandler.CmdHandlerFactory;
=======
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6

/**
 * 服务器入口类
 */
public class ServerMain {
    /**
     * 日志对象
     */
    static private final Logger LOGGER = LoggerFactory.getLogger(ServerMain.class);

    /**
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
     * 应用主函数
     *
     * @param argArray 参数数组
     */
    static public void main(String[] argArray) {
        // 设置 log4j 属性文件
        PropertyConfigurator.configure(ServerMain.class.getClassLoader().getResourceAsStream("log4j.properties"));
        
        CmdHandlerFactory.init();
        GameMsgRecognizer.init();
=======
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
     * 服务器端口号
     */
    static private final int SERVER_PORT = 12345;

    /**
     * 应用主函数
     *
     * @param argvArray 命令行参数数组
     */
    static public void main(String[] argvArray) {
        // 设置 log4j 属性文件
        PropertyConfigurator.configure(ServerMain.class.getClassLoader().getResourceAsStream("log4j.properties"));
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6

        EventLoopGroup bossGroup = new NioEventLoopGroup();   // 拉客的, 也就是故事中的美女
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 干活的, 也就是故事中的服务生

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class); // 服务器信道的处理方式
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(
                    new HttpServerCodec(), // Http 服务器编解码器
                    new HttpObjectAggregator(65535), // 内容长度限制
                    new WebSocketServerProtocolHandler("/websocket"), // WebSocket 协议处理器, 在这里处理握手、ping、pong 等消息
<<<<<<< HEAD
                    new GameMsgDecoder(), // 自定义的消息解码器
                    new GameMsgEncoder(), // 自定义的消息编码器
=======
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                    new GameMsgDecoder(), // 自定义的消息解码器
                    new GameMsgEncoder(), // 自定义的消息编码器
=======
>>>>>>> 2869db01f865cc0f88d906db03447f609d2e3fc7
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> 2869db01f865cc0f88d906db03447f609d2e3fc7
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> 2869db01f865cc0f88d906db03447f609d2e3fc7
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> 2869db01f865cc0f88d906db03447f609d2e3fc7
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
                    new GameMsgHandler() // 自定义的消息处理器
                );
            }
        });

        try {
            // 绑定 12345 端口,
<<<<<<< HEAD
            // 注意: 实际项目中会使用 argArray 中的参数来指定端口号
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            ChannelFuture f = b.bind(12345).sync();
=======
<<<<<<< HEAD
            // 注意: 实际项目中会使用 argArray 中的参数来指定端口号
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
            // 注意: 实际项目中会使用 argvArray 中的参数来指定端口号
>>>>>>> 2869db01f865cc0f88d906db03447f609d2e3fc7
            ChannelFuture f = b.bind(SERVER_PORT).sync();
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6

            if (f.isSuccess()) {
                LOGGER.info("服务器启动成功!");
            }

            // 等待服务器信道关闭,
            // 也就是不要立即退出应用程序, 让应用程序可以一直提供服务
            f.channel().closeFuture().sync();
        } catch (Exception ex) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            ex.printStackTrace();
=======
<<<<<<< HEAD
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
            // 如果遇到异常, 打印详细信息...
=======
            // 记录错误日志
>>>>>>> 2869db01f865cc0f88d906db03447f609d2e3fc7
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            // 关闭服务器, 大家都歇了吧
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
        }
    }
}
