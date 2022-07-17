package cn.monitor4all.commonutils.framework.springStateMachine;

import java.util.Map;

public interface OrderService {

    //创建订单
    Order create();

    //发起支付
    Order pay(int id);

    //订单发货
    Order deliver(int id);

    //订单收货
    Order receive(int id);

    //获取所有订单信息
    Map<Integer, Order> getOrders();
}
