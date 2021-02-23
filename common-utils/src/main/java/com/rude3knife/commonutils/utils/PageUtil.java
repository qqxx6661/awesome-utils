package com.rude3knife.commonutils.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页相关工具类
 */
public class PageUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageUtil.class);

    /**
     * List分页
     * @param page 当前页数
     * @param pageSize 每页得大小
     * @param list 分页的对象
     * @return
     */
    public static List<?> getListPageContent(int page, int pageSize, List<?> list) {
        if (list == null || list.size() == 0) {
            throw new RuntimeException("list本身不能为空!");
        }
        LOGGER.info("当前页：" + page + " 每页数量:" + pageSize + " List总大小:" + list.size());
        int totalCount = list.size();
        page = page - 1;
        int fromIndex = page * pageSize;
        //分页不能大于总数
        if(fromIndex>=totalCount) {
            throw new RuntimeException("页数或分页大小不正确!");
        }
        int toIndex = ((page + 1) * pageSize);
        if (toIndex > totalCount) {
            toIndex = totalCount;
        }
        return list.subList(fromIndex, toIndex);
    }

    /**
     * 根据分页大小和List获取总页数
     * @param pageSize
     * @param list
     * @return
     */
    public static int getListTotalPage(int pageSize, List<?> list) {
        if (list.size() % pageSize == 0) {
            return list.size() / pageSize;
        } else {
            return list.size() / pageSize + 1;
        }
    }

}
