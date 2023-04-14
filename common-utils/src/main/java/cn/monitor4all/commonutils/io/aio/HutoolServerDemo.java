package cn.monitor4all.commonutils.io.aio;

import cn.hutool.core.io.BufferUtil;
import cn.hutool.socket.aio.AioServer;
import cn.hutool.socket.aio.AioSession;
import cn.hutool.socket.aio.SimpleIoAction;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * AIO 服务端
 */
@Slf4j
public class HutoolServerDemo {

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        AioServer aioServer = new AioServer(7187);
        aioServer.setIoAction(new SimpleIoAction() {

            @Override
            public void accept(AioSession session) {
                log.info("客户端 [{}] 连接成功", session.getRemoteAddress());
            }

            @Override
            public void doAction(AioSession session, ByteBuffer data) {

                byte[] bytes = new byte[data.limit()];
                data.get(bytes);
                String str = new String(bytes, StandardCharsets.UTF_8);
                log.info("客户端 [{}] ByteBuffer [{}] 发来消息 [{}]", session.getRemoteAddress(), data, str);

                if(!data.hasRemaining()) {
                    String response = "{\"code\":\"success\"}";
                    session.write(BufferUtil.createUtf8(response));
                }else {
                    session.read();
                }
            }

        }).start(true);
    }
}
