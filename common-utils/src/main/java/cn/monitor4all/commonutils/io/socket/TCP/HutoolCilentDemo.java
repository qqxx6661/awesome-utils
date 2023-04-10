package cn.monitor4all.commonutils.io.socket.TCP;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.socket.aio.AioClient;
import cn.hutool.socket.aio.AioSession;
import cn.hutool.socket.aio.SimpleIoAction;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class HutoolCilentDemo {

    public static void main(String[] args) throws IOException {

        AioClient client = new AioClient(new InetSocketAddress("localhost", 7187), new SimpleIoAction() {

            @Override
            public void doAction(AioSession session, ByteBuffer data) {
                if(data.hasRemaining()) {
                    Console.log(StrUtil.utf8Str(data));
                    session.read();
                }
                Console.log("OK");
            }
        });

        client.write(ByteBuffer.wrap("Hello".getBytes()));
        client.read();

        client.close();
    }
}
