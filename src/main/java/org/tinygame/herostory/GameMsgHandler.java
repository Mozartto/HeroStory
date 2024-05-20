package org.tinygame.herostory;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.google.protobuf.GeneratedMessageV3;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinygame.herostory.cmdhandler.CmdHandlerFactory;
import org.tinygame.herostory.cmdhandler.ICmdHandler;
import org.tinygame.herostory.model.UserManager;
import org.tinygame.herostory.msg.GameMsgProtocol;

=======
<<<<<<< HEAD
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinygame.herostory.msg.GameMsgProtocol;

import java.util.HashMap;
import java.util.Map;
=======
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> 2869db01f865cc0f88d906db03447f609d2e3fc7

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
/**
 * 自定义的消息处理器
 */
public class GameMsgHandler extends SimpleChannelInboundHandler<Object> {
    /**
     * 日志对象
     */
    static private final Logger LOGGER = LoggerFactory.getLogger(GameMsgHandler.class);

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        // 添加客户端信道
        Broadcaster.addChannel(ctx.channel());
=======
<<<<<<< HEAD
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
    /**
     * 客户端信道数组, 一定要使用 static,
     * 否则无法实现群发
     * （实现消息的广播，否则用户登录之后看不到对方）
     * 因为如果不用static，在你每个客户端连进来的时候都会新建一个GameMsgHandler，无法实现消息的群发
     */
    static private final ChannelGroup _channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 用户字典
     */
    static private final Map<Integer, User> _userMap = new HashMap<>();

    /**
     * @param ctx
     * @throws Exception
     *
     * 这里是使当前的channel加入到其他用户的channel中，实现用户之间能看到的功能
     * 一个新用户连接之后会被加入到channelGroup中
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (null == ctx) {
            return;
        }

        super.channelActive(ctx);
        _channelGroup.add(ctx.channel());
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

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        super.handlerRemoved(ctx);

        // 移除客户端信道
        Broadcaster.removeChannel(ctx.channel());

        // 拿到用户 Id
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();
=======
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
        if (null == ctx) {
            return;
        }

        super.handlerRemoved(ctx);
        _channelGroup.remove(ctx.channel());

        // 先拿到用户 Id
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();

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
        if (null == userId) {
            return;
        }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        // 移除用户
        UserManager.removeUserById(userId);

        // 广播用户离场的消息
=======
        _userMap.remove(userId);

>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
        _userMap.remove(userId);

>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
        _userMap.remove(userId);

>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
        _userMap.remove(userId);

>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
        GameMsgProtocol.UserQuitResult.Builder resultBuilder = GameMsgProtocol.UserQuitResult.newBuilder();
        resultBuilder.setQuitUserId(userId);

        GameMsgProtocol.UserQuitResult newResult = resultBuilder.build();
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        Broadcaster.broadcast(newResult);
=======
        _channelGroup.writeAndFlush(newResult);
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
        _channelGroup.writeAndFlush(newResult);
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
        _channelGroup.writeAndFlush(newResult);
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
        _channelGroup.writeAndFlush(newResult);
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        // 获取消息类
        Class<?> msgClazz = msg.getClass();

        LOGGER.info(
            "收到客户端消息, msgClazz = {}, msg = {}",
            msgClazz.getName(),
            msg
        );

        // 获取指令处理器
        ICmdHandler<? extends GeneratedMessageV3>
            cmdHandler = CmdHandlerFactory.create(msgClazz);

        if (null == cmdHandler) {
            LOGGER.error(
                "未找到相对应的指令处理器, msgClazz = {}",
                msgClazz.getName()
            );
            return;
        }

        // 处理指令
        cmdHandler.handle(ctx, cast(msg));
    }

    /**
     * 转型消息对象
     *
     * @param msg    消息对象
     * @param <TCmd> 指令类型
     * @return 指令对象
     */
    static private <TCmd extends GeneratedMessageV3> TCmd cast(Object msg) {
        if (null == msg ||
            !(msg instanceof GeneratedMessageV3)) {
            return null;
        } else {
            return (TCmd) msg;
        }
=======
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
        if (null == ctx ||
            null == msg) {
            return;
        }

        LOGGER.info(
            "收到客户端消息, msgClazz = {}, msgObj = {}",
            msg.getClass().getName(),
            msg
        );

        if (msg instanceof GameMsgProtocol.UserEntryCmd) {
            // 从指令对象中获取用户 Id 和英雄形象
            GameMsgProtocol.UserEntryCmd cmd = (GameMsgProtocol.UserEntryCmd) msg;
            int userId = cmd.getUserId();
            String heroAvatar = cmd.getHeroAvatar();

            // 通过protocol中的result类型构建返回的消息结果
            GameMsgProtocol.UserEntryResult.Builder resultBuilder = GameMsgProtocol.UserEntryResult.newBuilder();
            resultBuilder.setUserId(userId);
            resultBuilder.setHeroAvatar(heroAvatar);

            // 将用户加入字典
            User newUser = new User();
            newUser.userId = userId;
            newUser.heroAvatar = heroAvatar;
            _userMap.put(newUser.userId, newUser);

            // 将用户 Id 附着到 Channel
            ctx.channel().attr(AttributeKey.valueOf("userId")).set(userId);

            // 构建结果并发送
            GameMsgProtocol.UserEntryResult newResult = resultBuilder.build();
            _channelGroup.writeAndFlush(newResult);
        } else if (msg instanceof GameMsgProtocol.WhoElseIsHereCmd) {
            GameMsgProtocol.WhoElseIsHereResult.Builder resultBuilder = GameMsgProtocol.WhoElseIsHereResult.newBuilder();

            for (User currUser : _userMap.values()) {
                if (null == currUser) {
                    continue;
                }

                GameMsgProtocol.WhoElseIsHereResult.UserInfo.Builder userInfoBuilder = GameMsgProtocol.WhoElseIsHereResult.UserInfo.newBuilder();
                userInfoBuilder.setUserId(currUser.userId);
                userInfoBuilder.setHeroAvatar(currUser.heroAvatar);
                resultBuilder.addUserInfo(userInfoBuilder);
            }

            GameMsgProtocol.WhoElseIsHereResult newResult = resultBuilder.build();
            ctx.writeAndFlush(newResult);
        } else if (msg instanceof GameMsgProtocol.UserMoveToCmd) {
            Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();

            if (null == userId) {
                return;
            }

            GameMsgProtocol.UserMoveToCmd cmd = (GameMsgProtocol.UserMoveToCmd) msg;

            GameMsgProtocol.UserMoveToResult.Builder resultBuilder = GameMsgProtocol.UserMoveToResult.newBuilder();
            resultBuilder.setMoveUserId(userId);
            resultBuilder.setMoveToPosX(cmd.getMoveToPosX());
            resultBuilder.setMoveToPosY(cmd.getMoveToPosY());

            GameMsgProtocol.UserMoveToResult newResult = resultBuilder.build();
            _channelGroup.writeAndFlush(newResult);
        }
=======
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        LOGGER.info("收到客户端消息, msg = {}", msg);

        // WebSocket 二进制消息会通过 HttpServerCodec 解码成 BinaryWebSocketFrame 类对象
        BinaryWebSocketFrame frame = (BinaryWebSocketFrame)msg;
        ByteBuf byteBuf = frame.content();

        // 拿到真实的字节数组 并打印
        byte[] byteArray = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(byteArray);

        StringBuffer sb = new StringBuffer();
        sb.append("收到的字节 = ");

        for (byte b : byteArray) {
            sb.append(b).append(", ");
        }

        LOGGER.info(sb.toString());
>>>>>>> 2869db01f865cc0f88d906db03447f609d2e3fc7
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
