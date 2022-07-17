package cn.monitor4all.commonutils.framework.springStateMachine;

import lombok.Data;

@Data
public class Order {

    private int id;
    private OrderStatus status;
}
