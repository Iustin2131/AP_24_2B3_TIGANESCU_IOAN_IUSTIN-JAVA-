package org.example;

public interface AbstractRepository<T, ID> {
    void save(T entity);
    T findById(ID id);
    void delete(T entity);
}
