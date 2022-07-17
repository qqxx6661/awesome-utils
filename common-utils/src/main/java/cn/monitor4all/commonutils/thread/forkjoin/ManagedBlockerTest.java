package cn.monitor4all.commonutils.thread.forkjoin;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ManagedBlockerTest {

    static String threadDateTimeInfo() {
        return DateTimeFormatter.ISO_TIME.format(LocalTime.now()) + Thread.currentThread().getName();
    }
    static void test3() {
        List<RecursiveTask<String>> tasks = Stream.generate(() -> new RecursiveTask<String>() {
            @Override
            protected String compute() {
                System.out.println(threadDateTimeInfo() + ":simulate io task blocking for 2 seconds···");
                try {
                    ForkJoinPool.managedBlock(CommonBlocker.commonBlocker);
                    //线程休眠2秒模拟IO调用阻塞
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new Error(e);
                }
                return threadDateTimeInfo() + ": io blocking task returns successfully";
            }
        }).limit(50).collect(Collectors.toList());

        tasks.forEach(ForkJoinTask::fork);

        tasks.forEach(e -> {
            try {
                System.out.println(e.get());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
    public static void main(String[] args) {
        test3();
    }
}
