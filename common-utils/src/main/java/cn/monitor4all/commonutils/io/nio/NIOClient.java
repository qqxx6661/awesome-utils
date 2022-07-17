package cn.monitor4all.commonutils.io.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class NIOClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 7188);
        OutputStream out = socket.getOutputStream();
        String s = "hello world";
        out.write(s.getBytes());
        out.close();
    }
}
