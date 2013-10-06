package com.jim.ssh;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 6/24/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMultiBeforeAnnotation {
    private static final Logger log = Logger.getLogger(TestMultiBeforeAnnotation.class);

    @Before
    public void testBeforeOne() throws Exception {
        log.info("before one");
    }

    @Before
    public void testBeforeTwo() throws Exception {
        log.info("before two");
    }

    @Test
    public void testName() throws Exception {
        log.info("test main");
    }

    @After
    public void testAfterOne() throws Exception {
        log.info("After one");
    }

    @After
    public void testAfterTwo() throws Exception {
        log.info("After two");
    }
}
