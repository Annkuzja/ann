package by.bstu.project.dao;

import by.bstu.project.entity.Route;

public interface RouteDao extends IDao<Route> {
    Route getByDriverAndBusIds(Integer busId, Integer driverId) throws Exception;
}
