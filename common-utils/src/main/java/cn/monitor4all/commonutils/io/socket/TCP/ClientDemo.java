/**
 * sinture.com Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package cn.monitor4all.commonutils.io.socket.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * @author yzd
 * @version Id: ClientDemo.java, v 0.1 2018年07月10日 14:05 yzd Exp $
 */
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        //客户端请求与本机在20006端口建立TCP连接
        Socket client = new Socket("127.0.0.1", 20000);
        client.setSoTimeout(10000);
        //获取键盘输入
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //获取Socket的输出流，用来发送数据到服务端
        PrintStream out = new PrintStream(client.getOutputStream());
        //获取Socket的输入流，用来接收从服务端发送过来的数据
        BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream()));
        boolean flag = true;
        while(flag){
            System.out.print("输入信息：");
            String str = input.readLine();
            //发送数据到服务端
            out.println(str);
            if("bye".equals(str)){
                flag = false;
            }else{
                try{
                    //从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
                    String echo = buf.readLine();
                    System.out.println(echo);
                }catch(SocketTimeoutException e){
                    System.out.println("Time out, No response");
                }
            }
        }

        input.close();
        if(client != null){
            //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭
            client.close();	//只关闭socket，其关联的输入输出流也会被关闭
        }

    }
}