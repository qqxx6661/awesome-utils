package cn.monitor4all.commonutils.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);

        List<Future<Integer>> futureList = new ArrayList<>();
        for(int i = 0;i < 20;i++){
            CountCallable c = new CountCallable();
            Future<Integer> f =  pool.submit(c);
            futureList.add(f);
        }

        int count = 0;

        for(Future<Integer> a: futureList){
            try{
                count += a.get();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println(count);
    }
}
