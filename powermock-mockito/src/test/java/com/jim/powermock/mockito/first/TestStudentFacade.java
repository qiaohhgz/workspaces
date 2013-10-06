package com.jim.powermock.mockito.first;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/13/13
 * Time: 2:26 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({StudentDao.class, StudentBean.class})
@PowerMockIgnore({"org.apache.log4j.*"})
public class TestStudentFacade {
    private StudentFacade facade = new StudentFacade();
    private StudentDao dao;

    private StudentFacade getFacade() {
        return facade;
    }

    @Before
    public void doSetUp() throws Exception {
//        this.dao = mock(StudentDao.class);
//        when(this.dao.add(any(StudentBean.class))).thenReturn(new StudentBean());
//        given(facade.getDao()).willReturn(this.dao);

    }

    @Test
    public void testAddStudent() throws Exception {
        //mock
        this.dao = mock(StudentDao.class);

//        when(this.dao.add(any(StudentBean.class))).thenReturn(new StudentBean());
        when(getFacade().getDao()).thenReturn(this.dao);
//        given(getFacade().getDao()).willReturn(dao);

        //prepare
        StudentBean expected = new StudentBean();
        expected.setId(1000);
        expected.setName("Jim");

        //mock
//        this.dao = mock(StudentDao.class);
//        when(this.dao.add(expected)).thenReturn(expected);
//        given(facade.getDao()).willReturn(dao);

        //action
        StudentBean actual = getFacade().add(expected);

        //assert
        assertNotNull("student bean is not null", actual);
        assertEquals("id ok", expected.getId(), actual.getId());
        assertEquals("name ok", expected.getName(), actual.getName());
    }

    @Test
    public void testList() throws Exception {
        //mock creation
        List mockedList = mock(List.class);
        //using mock object
        mockedList.add("one");
        mockedList.clear();
        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test(expected = RuntimeException.class)
    public void testSomeStubbing() throws Exception {
        LinkedList mockedList = mock(LinkedList.class);
        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());
        //following prints "first"
        System.out.println(mockedList.get(0));
        //following throws runtime exception
        System.out.println(mockedList.get(1));
        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));
        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
        //If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
    }

    @Test
    public void testVoidMethod() throws Exception {
        ArrayList array = Mockito.mock(ArrayList.class);
        doNothing().when(array);
    }
}
