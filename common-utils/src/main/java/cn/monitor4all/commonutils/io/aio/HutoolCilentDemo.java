package cn.monitor4all.commonutils.io.aio;

import cn.hutool.socket.aio.AioClient;
import cn.hutool.socket.aio.AioSession;
import cn.hutool.socket.aio.SimpleIoAction;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * AIO 客户端
 */
@Slf4j
public class HutoolCilentDemo {

    public static void main(String[] args) throws IOException {

        AioClient client = new AioClient(new InetSocketAddress("localhost", 7187), new SimpleIoAction() {

            @Override
            public void doAction(AioSession session, ByteBuffer data) {
                byte[] bytes = new byte[data.limit()];
                data.get(bytes);
                String str = new String(bytes, StandardCharsets.UTF_8);
                log.info("服务端 [{}] ByteBuffer [{}] 发来消息 [{}]", session.getRemoteAddress(), data, str);

                if(data.hasRemaining()) {
                    session.read();
                }
            }
        });


        client.write(ByteBuffer.wrap("{\"data\":\"123\"}".getBytes()));


        client.read();

        client.close();
    }
}
