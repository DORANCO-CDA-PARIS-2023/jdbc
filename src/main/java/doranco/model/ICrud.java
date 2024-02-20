package doranco.model;

import doranco.entity.Student;

import java.util.Set;

public interface ICrud <T> {

    // CREATE
    int add(T entity) throws Exception;

    // READ
    Set<T> get() throws Exception;
    T getById(int id) throws Exception;


    // UPDATE
    void update(T entity) throws Exception;

    // DELETE
    void delete(T entity) throws Exception;
    void deleteById(int id) throws Exception;

}
