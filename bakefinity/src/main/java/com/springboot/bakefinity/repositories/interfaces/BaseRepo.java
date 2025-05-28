package com.springboot.bakefinity.repositories.interfaces;

import java.util.List;

public interface BaseRepo<T> {

    T get(int id) throws Exception;
    List<T> getAll() throws Exception;
    void add(T t) throws Exception;
    void update(T t) throws Exception;
    void delete(int id) throws Exception;

}