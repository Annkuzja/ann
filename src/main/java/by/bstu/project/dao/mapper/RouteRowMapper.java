package by.bstu.project.dao.mapper;

import by.bstu.project.entity.Route;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RouteRowMapper implements RowMapper<Route> {
    public Route mapRow(ResultSet resultSet, int i) throws SQLException {
        Route route = new Route();
        return route.builder()
                .id(resultSet.getInt("id"))
                .busId(resultSet.getInt("bus_id"))
                .driverId(resultSet.getInt("driver_id"))
                .build();
    }
}
