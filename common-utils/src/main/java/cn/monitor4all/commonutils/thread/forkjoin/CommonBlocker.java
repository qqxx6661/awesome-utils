package cn.monitor4all.commonutils.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class CommonBlocker implements ForkJoinPool.ManagedBlocker {
    public static ForkJoinPool.ManagedBlocker commonBlocker = new CommonBlocker();
    @Override
    public boolean block() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
        return true;
    }

    @Override
    public boolean isReleasable() {
        return false;
    }
}
