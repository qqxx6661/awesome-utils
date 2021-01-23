package com.rude3knife.commonutils;

import com.rude3knife.commonutils.utils.DateTimeUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateTimeUtilTests {

    @Test
    public void formatCallDuration() {
        assertEquals("00:30", DateTimeUtil.formatCallDuration(30));
        assertEquals("02:30", DateTimeUtil.formatCallDuration(150));
        assertEquals("01:00:50", DateTimeUtil.formatCallDuration(3650));

    }

}
