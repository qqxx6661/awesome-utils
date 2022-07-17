/**
 * sinture.com Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package cn.monitor4all.commonutils.io.socket.TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yzd
 * @version Id: ServerDemo.java, v 0.1 2018年07月10日 12:36 yzd Exp $
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        // 服务端在20006端口监听客户端请求的TCP连接
        ServerSocket server = new ServerSocket(20000);
        Socket client = null;
        boolean f = true;
        while(f){
            // 等待客户端的连接，如果没有获取连接
            client = server.accept();
            System.out.println("与客户端连接成功！");
            System.out.println("Port是：" + client.getPort());
            System.out.println("LocalPort是：" + client.getLocalPort());
            System.out.println("LocalAddress是：" + client.getLocalAddress());
            System.out.println("InetAddress是：" + client.getInetAddress());
            System.out.println("KeepAlive是：" + client.getKeepAlive());
            // 为每个客户端连接开启一个线程
            new Thread(new ServerThread(client)).start();

        }
    }
}