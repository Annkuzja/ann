package by.bstu.project.service;

import by.bstu.project.dao.RouteVODao;
import by.bstu.project.dao.RouteVODaoImpl;
import by.bstu.project.entity.RouteVO;

import java.util.List;

public class RouteVOServiceImpl implements RouteVOService {

    RouteVODao routeVODao = new RouteVODaoImpl();

    public RouteVO getRouteVOByNumber(Integer number) throws Exception {
        return routeVODao.getEntity(number);
    }

    public List<RouteVO> getFullList() throws Exception {
        return routeVODao.getList();
    }
}
