package com.jim.ssh;

import org.springframework.context.ApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/10/13
 * Time: 11:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractHibernateBindSessionDelegate extends AbstractHibernateBindSession {
    private AbstractHibernateBindSession target;
    private static AbstractHibernateBindSessionDelegate delegate = new AbstractHibernateBindSessionDelegate();
    private ApplicationContext applicationContext;

    private AbstractHibernateBindSessionDelegate() {
        target = this;
    }

    public synchronized static AbstractHibernateBindSessionDelegate getInstance(ApplicationContext applicationContext) {
        delegate.setApplicationContext(applicationContext);
        return delegate;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
