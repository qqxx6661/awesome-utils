package cn.monitor4all.commonutils.framework.colaExtension;

import cn.monitor4all.commonutils.framework.colaExtension.pricecenter.LogisticsInfoExtPt;
import cn.monitor4all.commonutils.framework.colaExtension.pricecenter.PriceCalculateExtPt;
import cn.monitor4all.commonutils.framework.colaExtension.pricecenter.ProductInfoExtPt;
import cn.monitor4all.commonutils.framework.colaExtension.pricecenter.PromotionInfoExtPt;
import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.extension.ExtensionExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(path = "/extension")
public class ColaExtensionController {

    @Resource
    private ExtensionExecutor extensionExecutor;

    @GetMapping("/testSuccess")
    public String testSuccess(String bizId, String orderId) throws Exception {
        BizScenario bizScenario = BizScenario.valueOf(bizId);
        boolean result = extensionExecutor.execute(SalesOrderBizExtPt.class, bizScenario, extension -> extension.addSalesOrder(orderId));
        return String.valueOf(result);
    }

    @GetMapping("/priceCenter")
    public String priceCenter(String bizId, String scenario) throws Exception {

        // 商品营销价
        BizScenario bizScenario = BizScenario.valueOf(bizId, "promotionInfo", scenario);
        String promotionPrice = extensionExecutor.execute(PromotionInfoExtPt.class, bizScenario, extension -> extension.getProductPromotionInfo("718"));

        // 商品原价
        bizScenario = BizScenario.valueOf(bizId, "productInfo", scenario);
        String productPrice = extensionExecutor.execute(ProductInfoExtPt.class, bizScenario, extension -> extension.getProductPriceInfo("718"));

        // 物流价
        bizScenario = BizScenario.valueOf(bizId, "logisticsInfo", scenario);
        String logisticsPrice = extensionExecutor.execute(LogisticsInfoExtPt.class, bizScenario, extension -> extension.getProductLogisticsPrice("718"));

        // 价格计算
        bizScenario = BizScenario.valueOf(bizId, "priceCalculate", scenario);
        String finalProductPrice = extensionExecutor.execute(PriceCalculateExtPt.class, bizScenario, extension ->
                extension.calOriginPrice(productPrice, promotionPrice, logisticsPrice));

        String finalPromotionPrice = extensionExecutor.execute(PriceCalculateExtPt.class, bizScenario, extension ->
                extension.calPromotionPrice(productPrice, promotionPrice, logisticsPrice));

        String finalPerpiecePrice = extensionExecutor.execute(PriceCalculateExtPt.class, bizScenario, extension ->
                extension.calPerpiecePrice(productPrice, promotionPrice, logisticsPrice));

        return finalProductPrice + "    " + finalPromotionPrice + "    " + finalPerpiecePrice;
    }

}
