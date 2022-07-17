package cn.monitor4all.commonutils.framework.springStateMachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangzhendong
 */
@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;
    @Autowired
    private StateMachinePersister<OrderStatus, OrderStatusChangeEvent, Order> persister;

    private int id = 1;
    private Map<Integer, Order> orders = new HashMap<>();

    @Override
    public Order create() {
        Order order = new Order();
        order.setStatus(OrderStatus.WAIT_PAYMENT);
        order.setId(id++);
        orders.put(order.getId(), order);
        log.info("创建新订单 [{}]", order);
        return order;
    }

    @Override
    public Order pay(int id) {
        Order order = orders.get(id);
        log.info("线程名称：" + Thread.currentThread().getName() + " 进行支付，订单号：" + id);
        Message<OrderStatusChangeEvent> message = MessageBuilder.withPayload(OrderStatusChangeEvent.PAYED).setHeader("order", order).build();
        if (!sendEvent(message, order)) {
            log.info("线程名称：" + Thread.currentThread().getName() + " 支付失败, 状态异常，订单号：" + id);
        }
        return orders.get(id);
    }

    @Override
    public Order deliver(int id) {
        Order order = orders.get(id);
        log.info("线程名称：" + Thread.currentThread().getName() + " 尝试发货，订单号：" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvent.DELIVERY).setHeader("order", order).build(), orders.get(id))) {
            log.info("线程名称：" + Thread.currentThread().getName() + " 发货失败，状态异常，订单号：" + id);
        }
        return orders.get(id);
    }

    @Override
    public Order receive(int id) {
        Order order = orders.get(id);
        log.info("线程名称：" + Thread.currentThread().getName() + " 尝试收货，订单号：" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderStatusChangeEvent.RECEIVED).setHeader("order", order).build(), orders.get(id))) {
            log.info("线程名称：" + Thread.currentThread().getName() + " 收货失败，状态异常，订单号：" + id);
        }
        return orders.get(id);
    }

    @Override
    public Map<Integer, Order> getOrders() {
        return orders;
    }
    
    /**
     * 发送订单状态转换事件
     *
     * @param message
     * @param order
     * @return
     */
    private synchronized boolean sendEvent(Message<OrderStatusChangeEvent> message, Order order) {
        boolean result = false;
        try {
            orderStateMachine.start();
            //尝试恢复状态机状态
            persister.restore(orderStateMachine, order);
            //添加延迟用于线程安全测试
            Thread.sleep(1000);
            result = orderStateMachine.sendEvent(message);
            log.info("sendEvent [{}]", result);
            //持久化状态机状态
            persister.persist(orderStateMachine, (Order) message.getHeaders().get("order"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stop();
        }
        return result;
    }
}
