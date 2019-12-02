package by.bstu.project.dao;

import by.bstu.project.entity.RouteVO;

import java.util.List;

public interface RouteVODao {
    RouteVO getEntity(Integer id) throws Exception;
    List<RouteVO> getList() throws Exception;
}
