package by.bstu.project.dao.mapper;

import by.bstu.project.entity.Driver;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DriverRowMapper implements RowMapper {
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Driver driver = new Driver();
        driver.setId(resultSet.getInt("id"));
        driver.setName("name");
        driver.setSurname("surname");
        return driver;
    }
}
