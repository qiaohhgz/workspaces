package com.jim.powermock.mockito.first;

import com.jim.powermock.CRUD;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/13/13
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class StudentFacade implements CRUD<StudentBean> {
    protected static Logger logger = Logger.getLogger(StudentFacade.class);
    private StudentDao dao = new StudentDao();

    @Override
    public StudentBean add(StudentBean entity) {
        logger.info("add student by Facade");
        logger.info(entity.toString());
        return getDao().add(entity);
    }

    @Override
    public StudentBean delete(StudentBean entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StudentBean deleteById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StudentBean update(StudentBean entity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public StudentBean getById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<StudentBean> getList() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public StudentDao getDao() {
        return dao;
    }

    public void setDao(StudentDao dao) {
        this.dao = dao;
    }
}
