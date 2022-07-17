package cn.monitor4all.commonutils.thread.reactor;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ReactorTest {

    private static final int MAX_POOL_SIZE = 32;
    private static final int CORE_POOL_SIZE = MAX_POOL_SIZE;
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactoryBuilder().setNamePrefix("query-product-pool-").build();
    private static final ExecutorService QUERY_PRODUCT_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), THREAD_FACTORY);

    private static Mono<String> doQuery(String str) {
        return Mono.<String>create(sink -> {
            try {
                Thread.sleep(5000);
                sink.success(str);
                log.info("success");
            } catch (Exception e) {
                sink.error(e);
            }
        })
        .subscribeOn(Schedulers.fromExecutorService(QUERY_PRODUCT_POOL_EXECUTOR));
    }


    public static void main(String[] args) {

        List<String> list = new ArrayList<String>() {
            {
                add("1");
                add("2");
                add("3");
                add("4");
                add("5");
            }
        };

        Flux.fromStream(list.stream())
                .flatMap(ReactorTest::doQuery)
                .collectList()
                .block()
                .forEach(log::info);

    }

}
