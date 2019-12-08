package by.bstu.project.service;

import by.bstu.project.dao.RouteDao;
import by.bstu.project.dao.RouteDaoImpl;
import by.bstu.project.entity.Route;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();

    public Route insert(Route route) throws Exception {

        if (routeDao.getByDriverAndBusIds(route.getBusId(), route.getDriverId()) == null)
            return routeDao.insert(route);
        else return null;
    }

    public boolean delete(Route route) throws Exception {
        return routeDao.delete(route) == 1;
    }

    public Route getEntity(Integer id) throws Exception {
        return routeDao.getById(id);
    }

    public List<Route> getEntityList() throws Exception {
        return routeDao.getList();
    }

    public Integer getSize() throws Exception {
        return routeDao.getSize();
    }

    public int update(Route route) throws Exception {
        return routeDao.update(route);
    }
}
