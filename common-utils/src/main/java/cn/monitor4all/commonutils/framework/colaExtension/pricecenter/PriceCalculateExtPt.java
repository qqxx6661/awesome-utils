package cn.monitor4all.commonutils.framework.colaExtension.pricecenter;

import com.alibaba.cola.extension.ExtensionPointI;

import java.math.BigDecimal;

public interface PriceCalculateExtPt extends ExtensionPointI {

    String calOriginPrice(String productPrice, String promotionPrice, String logisticsPrice);
    String calPromotionPrice(String productPrice, String promotionPrice, String logisticsPrice);
    String calPerpiecePrice(String productPrice, String promotionPrice, String logisticsPrice);
}
