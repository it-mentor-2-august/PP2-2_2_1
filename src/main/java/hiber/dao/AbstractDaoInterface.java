package hiber.dao;

import java.util.List;

public interface AbstractDaoInterface<T, E> {
    T getById(final E id);
    List<T> findAll();
    void add(final T entity);
}
