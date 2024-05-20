package org.tinygame.herostory;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.google.protobuf.Message;
=======
import com.google.protobuf.GeneratedMessageV3;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
import com.google.protobuf.GeneratedMessageV3;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
import com.google.protobuf.GeneratedMessageV3;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
import com.google.protobuf.GeneratedMessageV3;
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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
 * 消息解码器
 */
public class GameMsgDecoder extends ChannelInboundHandlerAdapter {
    /**
     * 日志对象
     */
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    static private Logger LOGGER = LoggerFactory.getLogger(GameMsgDecoder.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
=======
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
    static private final Logger LOGGER = LoggerFactory.getLogger(GameMsgDecoder.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (null == ctx ||
            null == msg) {
            return;
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
        if (!(msg instanceof BinaryWebSocketFrame)) {
            return;
        }

        // WebSocket 二进制消息会通过 HttpServerCodec 解码成 BinaryWebSocketFrame 类对象
        BinaryWebSocketFrame frame = (BinaryWebSocketFrame) msg;
        ByteBuf byteBuf = frame.content();

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        byteBuf.readShort(); // 读取消息的长度
        int msgCode = byteBuf.readShort(); // 读取消息的编号

        // 获取消息构建者
        Message.Builder msgBuilder = GameMsgRecognizer.getMsgBuilderByMsgCode(msgCode);
        if (null == msgBuilder) {
            LOGGER.error("无法识别的消息, msgCode = {}", msgCode);
            return;
        }

        // 拿到消息体
        byte[] msgBody = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(msgBody);

        msgBuilder.clear();
        msgBuilder.mergeFrom(msgBody);

        // 构建消息
        Message newMsg = msgBuilder.build();

        if (null != newMsg) {
            ctx.fireChannelRead(newMsg);
=======
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
=======
>>>>>>> d109986482a7aa42c6d828e9243651a01462dbb6
        try {
            byteBuf.readShort(); // 读取消息的长度
            int msgCode = byteBuf.readShort(); // 读取消息的编号

            // 拿到消息体
            byte[] msgBody = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(msgBody);

            GeneratedMessageV3 cmd = null;

            switch (msgCode) {
                case GameMsgProtocol.MsgCode.USER_ENTRY_CMD_VALUE:
                    cmd = GameMsgProtocol.UserEntryCmd.parseFrom(msgBody);
                    break;

                case GameMsgProtocol.MsgCode.WHO_ELSE_IS_HERE_CMD_VALUE:
                    cmd = GameMsgProtocol.WhoElseIsHereCmd.parseFrom(msgBody);
                    break;

                case GameMsgProtocol.MsgCode.USER_MOVE_TO_CMD_VALUE:
                    cmd = GameMsgProtocol.UserMoveToCmd.parseFrom(msgBody);
                    break;
            }

            if (null != cmd) {
                ctx.fireChannelRead(cmd);
            }
        } catch (Exception ex) {
            // 记录错误日志
            LOGGER.error(ex.getMessage(), ex);
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
