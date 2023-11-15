package com.essabir.exam.dao;

import java.util.List;

public interface IDoa<T>{
    T create (T o);
    boolean  delete(T o);
    T update(T o);
    List<T> findAll();
    T findById (Long id);
}
