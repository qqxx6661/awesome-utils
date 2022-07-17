package cn.monitor4all.commonutils.framework.springStateMachine;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

/**
 * @author yangzhendong
 */
@Slf4j
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvent> {

    /**
     * 配置状态
     *
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderStatus.WAIT_PAYMENT)
                .choice(OrderStatus.WAIT_DELIVER)
                .states(EnumSet.allOf(OrderStatus.class));

    }
    /**
     * 配置状态转换事件关系
     *
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvent> transitions) throws Exception {
        transitions
                .withExternal().source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER).event(OrderStatusChangeEvent.PAYED)
                .and()
                .withChoice().source(OrderStatus.WAIT_DELIVER).first(OrderStatus.WAIT_RECEIVE, tasksChoiceGuard(), actionDelivery()).last(OrderStatus.CANCELED, actionCancel())
                .and()
                .withExternal().source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH).event(OrderStatusChangeEvent.RECEIVED);
    }
    /**
     * 持久化配置
     * 实际使用中，可以配合redis等，进行持久化操作
     *
     * @return
     */
    @Bean
    public DefaultStateMachinePersister persister() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<Object, Object, Order>() {
            @Override
            public void write(StateMachineContext<Object, Object> context, Order order) throws Exception {
                log.info("进行Order的状态写入 context [{}] order [{}]", context.getState(), order);
                //此处并没有进行持久化操作
            }
            @Override
            public StateMachineContext<Object, Object> read(Order order) throws Exception {
                //此处直接获取order中的状态，其实并没有进行持久化读取操作
                log.info("进行Order的状态读取 [{}]", order);
                return new DefaultStateMachineContext(order.getStatus(), null, null, null);
            }
        });
    }

    @Bean
    public Guard<OrderStatus, OrderStatusChangeEvent> tasksChoiceGuard() {
        return context -> {
            Order order = (Order) context.getMessage().getHeaders().get("order");
            log.info("tasksChoiceGuard Order [{}]", order);
            return order.getId() == 2;
        };
    }

    @Bean
    public Action<OrderStatus, OrderStatusChangeEvent> actionCancel() {
        return new Action<OrderStatus, OrderStatusChangeEvent>() {
            @Override
            public void execute(StateContext<OrderStatus, OrderStatusChangeEvent> context) {
                Order order = (Order) context.getMessage().getHeaders().get("order");
                order.setStatus(OrderStatus.CANCELED);
                log.info("[状态流转逻辑处理]商家取消订单：" + order);
            }
        };
    }

    @Bean
    public Action<OrderStatus, OrderStatusChangeEvent> actionDelivery() {
        return new Action<OrderStatus, OrderStatusChangeEvent>() {
            @Override
            public void execute(StateContext<OrderStatus, OrderStatusChangeEvent> context) {
                Order order = (Order) context.getMessage().getHeaders().get("order");
                order.setStatus(OrderStatus.WAIT_RECEIVE);
                log.info("[状态流转逻辑处理]商家发货：" + order);
            }
        };
    }
}
