package by.bstu.project.service;

import by.bstu.project.entity.RouteVO;

import java.util.List;

public interface RouteVOService {
    RouteVO getRouteVOByNumber(Integer number) throws Exception;

    List<RouteVO> getFullList() throws Exception;

    List<RouteVO> findRoutes(String source, String destination) throws Exception;

}
