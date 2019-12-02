package by.bstu.project.dao.mapper;

import by.bstu.project.entity.Bus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BusRowMapper implements RowMapper<Bus> {
    public Bus mapRow(ResultSet resultSet, int i) throws SQLException {
        Bus bus = new Bus();
        bus.setId(resultSet.getInt("id"));
        bus.setMark(resultSet.getString("name"));
        bus.setHorsePower(resultSet.getInt("horse_power"));
        bus.setNumberOfPassenger(resultSet.getInt("number_of_passangers"));
        return bus;
    }
}
