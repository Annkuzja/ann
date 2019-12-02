package by.bstu.project.service;

import java.util.List;

public interface IService<T> {
    T insert(T entity) throws Exception;

    boolean delete(T entity) throws Exception;

    T getEntity(Integer id) throws Exception;

    List<T> getEntityList() throws Exception;

    Integer getSize() throws Exception;
}
