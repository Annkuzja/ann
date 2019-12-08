package by.bstu.project.dao;

import by.bstu.project.entity.Route;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RouteDaoImpl implements RouteDao {

    private static final String URL = "jdbc:mysql://localhost:3306/spring_security_app";
    private static final String USER = "sergio";
    private static final String PASSWORD = "Kol21010801";

    public RouteDaoImpl() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Route insert(Route route) throws Exception {
        PreparedStatement statement = getCreateStatement("INSERT INTO route(bus_id, driver_id, destination, source) VALUES(?, ?, ?, ?)", "id");
        statement.setInt(1, route.getBusId());
        statement.setInt(2, route.getDriverId());
        statement.setString(3, route.getDestination());
        statement.setString(4, route.getSource());

        if (statement.executeUpdate() > 0) {
            ResultSet generatedKeys = statement.getGeneratedKeys();
            boolean next = generatedKeys.next();
            int routeId = next ? generatedKeys.getInt(1) : -1;
            if (routeId != -1) {
                route.setId(routeId);
                return route;
            }
        }
        throw new RuntimeException("Route was not created");
    }

    public Integer delete(Route route) throws Exception {
        PreparedStatement statement = createStatement("DELETE FROM route WHERE id = ?");
        statement.setInt(1, route.getId());
        int rows = statement.executeUpdate();
        return rows;
    }

    public Route getById(Integer id) throws Exception {
        PreparedStatement statement = createStatement("SELECT * FROM route WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next())
            return null;
        Route route = new Route();
        route.setId(id);
        route.setDriverId(resultSet.getInt("driver_id"));
        route.setBusId(resultSet.getInt("bus_id"));
        route.setDestination(resultSet.getString("destination"));
        route.setSource(resultSet.getString("source"));
        return route;
    }

    public List<Route> getList() throws Exception {
        PreparedStatement statement = createStatement("SELECT * FROM route");
        ResultSet resultSet = statement.executeQuery();
        List<Route> routes = new ArrayList<Route>();
        while (resultSet.next()) {
            Route route = new Route();
            route.setId(resultSet.getInt("id"));
            route.setDriverId(resultSet.getInt("driver_id"));
            route.setBusId(resultSet.getInt("bus_id"));
            route.setDestination(resultSet.getString("destination"));
            route.setSource(resultSet.getString("source"));
            routes.add(route);
        }
        return routes;
    }

    public Integer getSize() throws Exception {
        Integer size;

        PreparedStatement statement = createStatement("SELECT count(*) FROM route");

        ResultSet resultSet = statement.executeQuery();

        resultSet.next();

        return size = resultSet.getInt(1);
    }

    public Route getByDriverAndBusIds(Integer busId, Integer driverId) throws Exception {
        PreparedStatement statement = createStatement("SELECT * FROM route WHERE bus_id = ? and driver_id = ?");
        statement.setInt(1, busId);
        statement.setInt(2, driverId);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next())
            return null;
        Route route = new Route();
        route.setId(resultSet.getInt("id"));
        route.setDriverId(driverId);
        route.setBusId(busId);
        return route;
    }

    public int update(Route route) throws Exception {
        PreparedStatement statement = createStatement("UPDATE route set route.bus_id = ?, route.driver_id = ?, route.destination = ?, route.source = ? where id = ?");
        statement.setInt(1, route.getBusId());
        statement.setInt(2, route.getDriverId());
        statement.setString(3, route.getDestination());
        statement.setString(4, route.getSource());
        statement.setInt(5, route.getId());
        return statement.executeUpdate();
    }

    private PreparedStatement getCreateStatement(String sql, String idFieldName) throws SQLException {
        return getConnection().prepareStatement(sql, new String[]{idFieldName});
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static PreparedStatement createStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }
}
