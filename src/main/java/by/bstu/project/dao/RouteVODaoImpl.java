package by.bstu.project.dao;

import by.bstu.project.entity.RouteVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteVODaoImpl implements RouteVODao {

    private static final String URL = "jdbc:mysql://localhost:3306/spring_security_app";
    private static final String USER = "sergio";
    private static final String PASSWORD = "Kol21010801";

    private static final String SELECT_SQL = "SELECT ROUTE.id, " +
            "ROUTE.destination, " +
            "ROUTE.source, " +
            "DR.id, " +
            "BUS.id, " +
            "DR.name, " +
            "DR.surname, " +
            "BUS.name, " +
            "BUS.horse_power, " +
            "BUS.number_of_passengers " +
            "FROM route AS ROUTE " +
            "INNER JOIN drivers AS DR ON ROUTE.driver_id = DR.id " +
            "INNER JOIN bus BUS ON ROUTE.bus_id = BUS.id " +
            "WHERE ROUTE.id = ?";

    private static final String SELECT_ALL_SQL = "SELECT ROUTE.id, " +
            "ROUTE.destination, " +
            "ROUTE.source, " +
            "DR.id, " +
            "BUS.id, " +
            "DR.name, " +
            "DR.surname, " +
            "BUS.name, " +
            "BUS.horse_power, " +
            "BUS.number_of_passengers " +
            "FROM route AS ROUTE " +
            "INNER JOIN drivers AS DR ON ROUTE.driver_id = DR.id " +
            "INNER JOIN bus BUS ON ROUTE.bus_id = BUS.id";


    public RouteVO getEntity(Integer id) throws Exception {
        PreparedStatement statement = createStatement(SELECT_SQL);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next())
            return null;
        RouteVO routeVO = new RouteVO();
        routeVO.setRouteId(id);
        routeVO.setDestination(resultSet.getString("ROUTE.destination"));
        routeVO.setSource(resultSet.getString("ROUTE.source"));
        routeVO.setBusId(resultSet.getInt("BUS.id"));
        routeVO.setDriverId(resultSet.getInt("DR.id"));
        routeVO.setDriverName(resultSet.getString("DR.name"));
        routeVO.setDriverSurname(resultSet.getString("DR.surname"));
        routeVO.setMarkOfBus(resultSet.getString("BUS.name"));
        routeVO.setHorsePower(resultSet.getInt("BUS.horse_power"));
        routeVO.setNumberOfPassenger(resultSet.getInt("BUS.number_of_passengers"));
        return routeVO;
    }

    public List<RouteVO> getList() throws Exception {
        PreparedStatement statement = createStatement(SELECT_ALL_SQL);
        ResultSet resultSet = statement.executeQuery();
        List<RouteVO> routesVO = new ArrayList<RouteVO>();
        while (resultSet.next()) {
            RouteVO routeVO = new RouteVO();
            routeVO.setRouteId(resultSet.getInt("ROUTE.id"));
            routeVO.setDestination(resultSet.getString("ROUTE.destination"));
            routeVO.setSource(resultSet.getString("ROUTE.source"));
            routeVO.setBusId(resultSet.getInt("BUS.id"));
            routeVO.setDriverId(resultSet.getInt("DR.id"));
            routeVO.setDriverName(resultSet.getString("DR.name"));
            routeVO.setDriverSurname(resultSet.getString("DR.surname"));
            routeVO.setMarkOfBus(resultSet.getString("BUS.name"));
            routeVO.setHorsePower(resultSet.getInt("BUS.horse_power"));
            routeVO.setNumberOfPassenger(resultSet.getInt("BUS.number_of_passengers"));
            routesVO.add(routeVO);
        }
        return routesVO;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static PreparedStatement createStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }
}
