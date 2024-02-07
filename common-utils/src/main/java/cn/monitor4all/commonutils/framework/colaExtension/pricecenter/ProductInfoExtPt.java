package cn.monitor4all.commonutils.framework.colaExtension.pricecenter;

import com.alibaba.cola.extension.ExtensionPointI;

public interface ProductInfoExtPt extends ExtensionPointI {

    String getProductPriceInfo(String productId);
}
