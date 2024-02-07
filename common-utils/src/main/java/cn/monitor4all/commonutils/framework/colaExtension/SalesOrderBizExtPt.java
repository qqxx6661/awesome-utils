package cn.monitor4all.commonutils.framework.colaExtension;

import com.alibaba.cola.extension.ExtensionPointI;

public interface SalesOrderBizExtPt extends ExtensionPointI {

    boolean addSalesOrder(String salesOrderId);
}
