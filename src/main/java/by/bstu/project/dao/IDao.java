package by.bstu.project.dao;

import java.util.List;

public interface IDao<T> {

    T insert(T entity) throws Exception;

    Integer delete(T entity) throws Exception;

    T getById(Integer id) throws Exception;

    List<T> getList() throws Exception;

    Integer getSize() throws Exception;

}
