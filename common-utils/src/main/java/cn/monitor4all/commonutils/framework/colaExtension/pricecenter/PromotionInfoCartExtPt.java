package cn.monitor4all.commonutils.framework.colaExtension.pricecenter;

import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangzhendong
 */
@Slf4j
@Extension(bizId = "ICBU", useCase = "promotionInfo", scenario = "cart")
public class PromotionInfoCartExtPt implements PromotionInfoExtPt {

    @Override
    public String getProductPromotionInfo(String productId) {
        return "promotionPrice:80";
    }
}
