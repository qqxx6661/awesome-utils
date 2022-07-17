package cn.monitor4all.commonutils.framework.eventbus;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataObserver1 {

    /**
     * 只有通过@Subscribe注解的方法才会被注册进EventBus
     * 而且方法有且只能有1个参数
     *
     * @param msg
     */
    @Subscribe
    public void func(String msg) {
        log.info("String msg: " + msg);
    }
}
