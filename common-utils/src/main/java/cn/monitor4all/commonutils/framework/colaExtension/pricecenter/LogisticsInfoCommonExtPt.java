package cn.monitor4all.commonutils.framework.colaExtension.pricecenter;

import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangzhendong
 */
@Slf4j
@Extension(bizId = "ICBU", useCase = "logisticsInfo")
public class LogisticsInfoCommonExtPt implements LogisticsInfoExtPt {

    @Override
    public String getProductLogisticsPrice(String productId) {
        return "logisticsPrice:10";
    }
}
