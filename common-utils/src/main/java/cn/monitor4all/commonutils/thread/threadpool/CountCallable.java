package cn.monitor4all.commonutils.thread.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountCallable  implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        log.info("running thread [{}]", Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
        return 1;
    }
}
