package com.jim.ssh;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 5/7/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestUUID {
    @Test
    public void test1() throws Exception {
        int size = 1000;
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < size; i++) {
            set.add(UUID.randomUUID().toString());
        }
        assert set.size() == size;
    }

    @Test
    public void test2() throws Exception {
        int size = 1000;
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < size; i++) {
            set.add("jim");
        }
        assert set.size() != size;
    }
}
