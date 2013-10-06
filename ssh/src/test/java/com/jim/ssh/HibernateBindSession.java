package com.jim.ssh;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/10/13
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateBindSession {
    private AbstractHibernateBindSessionDelegate hibernateBindSessionDelegate;

    public void test() throws Exception {
        AbstractHibernateBindSessionDelegate.getInstance(null).bindResource();
    }
}
