package com.rude3knife.commonutils;

import com.rude3knife.commonutils.utils.PageUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PageUtilTests {

    @Test
    public void getListPage() {
        int pageSize = 10;
        List<Integer> test = new ArrayList<>();
        for (int i = 1; i <= 100 ; i++) {
            test.add(i);
        }
        for (int i = 1; i <= PageUtil.getListTotalPage(pageSize, test); i++) {
            System.out.println(PageUtil.getListPageContent(i, pageSize, test));
        }
    }


}
