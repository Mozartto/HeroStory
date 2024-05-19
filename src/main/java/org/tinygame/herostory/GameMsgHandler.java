package org.tinygame.herostory;

<<<<<<< HEAD
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

/**
 * 自定义的消息处理器
 */
public class GameMsgHandler extends SimpleChannelInboundHandler<Object> {
    /**
     * 日志对象
     */
    static private final Logger LOGGER = LoggerFactory.getLogger(GameMsgHandler.class);

<<<<<<< HEAD
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
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (null == ctx) {
            return;
        }

        super.handlerRemoved(ctx);
        _channelGroup.remove(ctx.channel());

        // 先拿到用户 Id
        Integer userId = (Integer) ctx.channel().attr(AttributeKey.valueOf("userId")).get();

        if (null == userId) {
            return;
        }

        _userMap.remove(userId);

        GameMsgProtocol.UserQuitResult.Builder resultBuilder = GameMsgProtocol.UserQuitResult.newBuilder();
        resultBuilder.setQuitUserId(userId);

        GameMsgProtocol.UserQuitResult newResult = resultBuilder.build();
        _channelGroup.writeAndFlush(newResult);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
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
    }
}
