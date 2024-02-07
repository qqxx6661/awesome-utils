package cn.monitor4all.commonutils.framework.colaExtension.pricecenter;

import com.alibaba.cola.extension.ExtensionPointI;

public interface LogisticsInfoExtPt extends ExtensionPointI {

    String getProductLogisticsPrice(String productId);
}
