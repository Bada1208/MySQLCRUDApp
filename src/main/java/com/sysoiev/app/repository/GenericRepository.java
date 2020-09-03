package com.sysoiev.app.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T getById(ID id);

    void deleteById(ID id) ;

    void update(T item) ;

    T save(T item);

    List<T> getAll();

}