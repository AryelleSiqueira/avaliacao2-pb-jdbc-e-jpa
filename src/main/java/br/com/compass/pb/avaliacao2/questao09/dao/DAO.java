package br.com.compass.pb.avaliacao2.questao09.dao;

import java.util.List;

public interface DAO<T> {

    void save(T t);

    void update(T t);

    void delete(int id);

    T get(int id);

    List<T> listAll();
}
