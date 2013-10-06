package com.jim.powermock;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 9/13/13
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CRUD<T> {
    T add(T entity);

    T delete(T entity);

    T deleteById(int id);

    T update(T entity);

    T getById(int id);

    List<T> getList();
}
