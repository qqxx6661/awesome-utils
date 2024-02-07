package cn.monitor4all.commonutils.framework.colaExtension.pricecenter;

import cn.monitor4all.commonutils.framework.colaExtension.SalesOrderBizExtPt;
import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangzhendong
 */
@Slf4j
@Extension(bizId = "ICBU", useCase = "productInfo")
public class ProductInfoCommonExtPt implements ProductInfoExtPt {

    @Override
    public String getProductPriceInfo(String productId) {
        return "productPrice:100";
    }
}
