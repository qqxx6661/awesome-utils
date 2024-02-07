package cn.monitor4all.commonutils.framework.colaExtension.pricecenter;

import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author yangzhendong
 */
@Slf4j
@Extension(bizId = "ICBU", useCase = "priceCalculate", scenario = "cart")
public class PriceCalculateCartExtPt implements PriceCalculateExtPt {

    @Override
    public String calOriginPrice(String productPrice, String promotionPrice, String logisticsPrice) {
        return "cart--" + productPrice;
    }

    @Override
    public String calPromotionPrice(String productPrice, String promotionPrice, String logisticsPrice) {
        return "cart--"  + promotionPrice;
    }

    @Override
    public String calPerpiecePrice(String productPrice, String promotionPrice, String logisticsPrice) {
        return "cart--" + productPrice + promotionPrice + logisticsPrice;
    }
}
