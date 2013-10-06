package com.jim.ssh;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/10/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractHibernateBindSession {
    private boolean boundSession;
    private List<SessionFactory> sessionFactoryList;
    private ApplicationContext applicationContext;

    public abstract ApplicationContext getApplicationContext();

    protected void info(String msg) {
        System.out.println(msg);
    }

    private void initSessionFactoryList() {
        this.applicationContext = getApplicationContext();
        sessionFactoryList = new ArrayList<SessionFactory>();
        Map<String, SessionFactory> sessionFactoryMap = applicationContext.getBeansOfType(SessionFactory.class);
        Set<String> sfNames = sessionFactoryMap.keySet();
        info("=================== Begin Init Session Factory ===================");
        for (String name : sfNames) {
            SessionFactory sessionFactory = sessionFactoryMap.get(name);
            addSessionFactory(sessionFactory);
        }
        info("=================== End Init Session Factory ===================");
    }

    protected void bindResource() throws Exception {
        initSessionFactoryList();
        for (SessionFactory sessionFactory : getSessionFactoryList()) {
            info("Opening Hibernate Session in HibernateJUnit4TestBase");
            Session session = SessionFactoryUtils.getSession(sessionFactory, true);
            TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
        }
        boundSession = true;
    }

    protected void releaseSession() throws Exception {
        if (isBoundSession()) {
            for (SessionFactory sessionFactory : getSessionFactoryList()) {
                info("Closing Hibernate Session in HibernateJUnit4TestBase");
                SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
                SessionFactoryUtils.releaseSession(sessionHolder.getSession(), sessionFactory);
            }
            boundSession = false;
        }
    }

    private List<SessionFactory> getSessionFactoryList() {
        return sessionFactoryList;
    }

    private void addSessionFactory(SessionFactory sessionFactory) {
        getSessionFactoryList().add(sessionFactory);
    }

    private boolean isBoundSession() {
        return this.boundSession;
    }
}
