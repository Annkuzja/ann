package by.bstu.project.dao;

import by.bstu.project.entity.Bus;

public interface BusDao extends IDao<Bus> {
    Bus getByName(String name) throws Exception;
}
