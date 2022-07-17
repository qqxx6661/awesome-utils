package cn.monitor4all.commonutils.framework.springStateMachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * @author yangzhendong
 */
@Slf4j
@Component("orderStateListener")
@WithStateMachine(name = "orderStateMachine")
public class OrderStateListenerImpl {

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public void payTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatus.WAIT_DELIVER);
        log.info("[状态流转逻辑处理]客户完成支付，状态机反馈信息：" + message.getHeaders());
    }
    
//    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
//    public void deliverTransition(Message<OrderStatusChangeEvent> message) throws Exception {
//        Order order = (Order) message.getHeaders().get("order");
//        order.setStatus(OrderStatus.WAIT_RECEIVE);
//        log.info("[状态流转逻辑处理]商家完成发货，状态机反馈信息：" + message.getHeaders());
//    }
    
    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public void receiveTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setStatus(OrderStatus.FINISH);
        log.info("[状态流转逻辑处理]客户完成收货，状态机反馈信息：" + message.getHeaders());
    }

//    @OnTransition(source = "WAIT_DELIVER", target = "CANCELED")
//    public void cancelTransition(Message<OrderStatusChangeEvent> message) throws Exception {
//        Order order = (Order) message.getHeaders().get("order");
//        order.setStatus(OrderStatus.CANCELED);
//        log.info("[状态流转逻辑处理]商家取消订单，状态机反馈信息：" + message.getHeaders());
//    }

}
