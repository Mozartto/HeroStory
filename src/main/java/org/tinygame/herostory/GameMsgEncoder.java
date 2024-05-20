package org.tinygame.herostory;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.tinygame.herostory.msg.GameMsgProtocol;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
import org.tinygame.herostory.msg.GameMsgProtocol;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
import org.tinygame.herostory.msg.GameMsgProtocol;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
import org.tinygame.herostory.msg.GameMsgProtocol;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6

/**
 * 消息编码器
 */
public class GameMsgEncoder extends ChannelOutboundHandlerAdapter {
    /**
     * 日志对象
     */
    static private final Logger LOGGER = LoggerFactory.getLogger(GameMsgEncoder.class);

    @Override
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (null == msg ||
            !(msg instanceof GeneratedMessageV3)) {
            super.write(ctx, msg, promise);
            return;
        }

        // 获取消息类
        Class<?> msgClazz = msg.getClass();

        // 获取消息编码
        int msgCode = GameMsgRecognizer.getMsgCodeByMsgClazz(msgClazz);
        if (msgCode <= -1) {
            LOGGER.error(
                "无法识别的消息, msgClazz = {}",
                msgClazz.getName()
            );
            return;
        }

        // 获取消息体字节数组
        byte[] msgBody = ((GeneratedMessageV3) msg).toByteArray();

        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeShort((short) msgBody.length); // 写出消息长度
        byteBuf.writeShort((short) msgCode); // 写出消息编号
        byteBuf.writeBytes(msgBody); // 写出消息体

        BinaryWebSocketFrame frame = new BinaryWebSocketFrame(byteBuf);
        super.write(ctx, frame, promise);
=======
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        if (null == ctx ||
            null == msg) {
            return;
        }

        try {
            if (!(msg instanceof GeneratedMessageV3)) {
                super.write(ctx, msg, promise);
                return;
            }

            int msgCode = -1;

            if (msg instanceof GameMsgProtocol.UserEntryResult) {
                msgCode = GameMsgProtocol.MsgCode.USER_ENTRY_RESULT_VALUE;
            } else if (msg instanceof GameMsgProtocol.WhoElseIsHereResult) {
                msgCode = GameMsgProtocol.MsgCode.WHO_ELSE_IS_HERE_RESULT_VALUE;
            } else if (msg instanceof GameMsgProtocol.UserMoveToResult) {
                msgCode = GameMsgProtocol.MsgCode.USER_MOVE_TO_RESULT_VALUE;
            } else if (msg instanceof GameMsgProtocol.UserQuitResult) {
                msgCode = GameMsgProtocol.MsgCode.USER_QUIT_RESULT_VALUE;
            } else {
                LOGGER.error(
                    "无法识别的消息类型, msgClazz = {}",
                    msg.getClass().getName()
                );
                super.write(ctx, msg, promise);
                return;
            }

            byte[] msgBody = ((GeneratedMessageV3) msg).toByteArray();

            // 借助netty的ByteBuf类来写出消息
            ByteBuf byteBuf = ctx.alloc().buffer();
            byteBuf.writeShort((short) msgBody.length); // 写出消息长度
            byteBuf.writeShort((short) msgCode); // 写出消息编号
            byteBuf.writeBytes(msgBody); // 写出消息体

            // 解码时用的是这个对象，编码时也要用这个对象
            BinaryWebSocketFrame frame = new BinaryWebSocketFrame(byteBuf);
            super.write(ctx, frame, promise);
        } catch (Exception ex) {
            // 记录错误日志
            LOGGER.error(ex.getMessage(), ex);
        }
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
