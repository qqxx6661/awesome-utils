package cn.monitor4all.commonutils.framework.springStateMachine;

public enum OrderStatus {
    WAIT_PAYMENT,
    WAIT_DELIVER,
    WAIT_RECEIVE,
    FINISH,
    CANCELED;
}
