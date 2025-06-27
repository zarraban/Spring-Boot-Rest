package io.github.zarraban.Spring_Boot_Rest.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T,T1> {

    T create(T1 request);

    T readById(Long id);

    boolean deleteById(Long id);

    T updateById(Long id, T1 request);

    List<T> readAll();



}
