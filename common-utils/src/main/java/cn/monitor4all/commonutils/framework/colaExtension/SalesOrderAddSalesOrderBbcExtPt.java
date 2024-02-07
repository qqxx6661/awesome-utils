package cn.monitor4all.commonutils.framework.colaExtension;

import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangzhendong
 */
@Slf4j
@Extension(bizId = "BBC", useCase = "addSalesOrder", scenario = "SalesOrder")
public class SalesOrderAddSalesOrderBbcExtPt implements SalesOrderBizExtPt {
    @Override
    public boolean addSalesOrder(String salesOrderId) {
        log.info("SalesOrderAddSalesOrderBbcExtPt [{}]", salesOrderId);
        return true;
    }
}
