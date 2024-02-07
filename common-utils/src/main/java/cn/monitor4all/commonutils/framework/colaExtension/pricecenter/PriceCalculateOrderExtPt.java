package cn.monitor4all.commonutils.framework.colaExtension.pricecenter;

import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangzhendong
 */
@Slf4j
@Extension(bizId = "ICBU", useCase = "priceCalculate", scenario = "order")
public class PriceCalculateOrderExtPt implements PriceCalculateExtPt {

    @Override
    public String calOriginPrice(String productPrice, String promotionPrice, String logisticsPrice) {
        return "order--" + productPrice;
    }

    @Override
    public String calPromotionPrice(String productPrice, String promotionPrice, String logisticsPrice) {
        return "order--"  + promotionPrice;
    }

    @Override
    public String calPerpiecePrice(String productPrice, String promotionPrice, String logisticsPrice) {
        return "order--" + productPrice + promotionPrice + logisticsPrice;
    }
}
