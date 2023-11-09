package cn.monitor4all.commonutils.framework.colaExtension;

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
        boolean result = extensionExecutor.execute(SalesOrderBizExtPt.class, bizScenario, extension -> extension.addSalesOrderBiz(orderId));
        return String.valueOf(result);
    }

}
