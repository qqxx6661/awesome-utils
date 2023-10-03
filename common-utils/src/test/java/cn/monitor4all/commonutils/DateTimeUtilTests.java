package cn.monitor4all.commonutils;

import cn.monitor4all.commonutils.utils.DateTimeUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateTimeUtilTests {

    @Test
    public void formatDuration() {
        assertEquals("00:30", DateTimeUtil.formatDuration(30));
        assertEquals("02:30", DateTimeUtil.formatDuration(150));
        assertEquals("01:00:50", DateTimeUtil.formatDuration(3650));
    }
    // this is a test comment

}
