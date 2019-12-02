package by.bstu.project.dao;

import by.bstu.project.entity.Driver;

public interface DriverDao extends IDao<Driver> {
    Driver getByNameAndSurname(String name, String surname) throws Exception;
}
