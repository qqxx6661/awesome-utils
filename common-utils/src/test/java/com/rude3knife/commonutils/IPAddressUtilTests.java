package com.rude3knife.commonutils;

import com.rude3knife.commonutils.utils.IPAddressUtil;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IPAddressUtilTests {

    @Test
    public void isInRange() {
        assertTrue(IPAddressUtil.isInRange("192.168.1.5", "192.168.0.0/23"));
        assertTrue(IPAddressUtil.isInRange("192.168.1.5", "192.168.1.0/24"));
        assertFalse(IPAddressUtil.isInRange("192.168.1.5", "192.168.1.0/32"));
        assertTrue(IPAddressUtil.isInRange("10.249.30.53", "10.249.0.0/16"));
        assertFalse(IPAddressUtil.isInRange("10.249.30.53", "10.249.0.0/24"));
        assertFalse(IPAddressUtil.isInRange("10.249.30.53", "10.249.0.0/32"));
    }

}
