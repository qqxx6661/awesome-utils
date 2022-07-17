package cn.monitor4all.commonutils.framework.colaExtension;

import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yangzhendong
 */
@Slf4j
@Extension(bizId = "SalesOrderFba")
public class SalesOrderFbaExtPt implements SalesOrderBizExtPt{
    @Override
    public boolean addSalesOrderBiz(String salesOrderId) {
        log.info("SalesOrderFbaExtPt addSalesOrderBiz [{}]", salesOrderId);
        return true;
    }
}
