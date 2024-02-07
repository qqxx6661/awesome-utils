package cn.monitor4all.commonutils.framework.colaExtension.pricecenter;

import com.alibaba.cola.extension.ExtensionPointI;

public interface PromotionInfoExtPt extends ExtensionPointI {

    String getProductPromotionInfo(String productId);
}
