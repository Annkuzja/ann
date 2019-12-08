package by.bstu.project.dao;

import by.bstu.project.entity.Driver;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DriverDaoImpl implements DriverDao {

    private static final String URL = "jdbc:mysql://localhost:3306/spring_security_app";
    private static final String USER = "sergio";
    private static final String PASSWORD = "Kol21010801";

    public DriverDaoImpl() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Driver insert(Driver driver) throws Exception {
        PreparedStatement statement = getCreateStatement("INSERT INTO drivers(name, surname) VALUES(?, ?)", "id");
        statement.setString(1, driver.getName());
        statement.setString(2, driver.getSurname());
        if (statement.executeUpdate() > 0) {
            ResultSet generatedKeys = statement.getGeneratedKeys();
            boolean next = generatedKeys.next();
            int driverId = next ? generatedKeys.getInt(1) : -1;
            if (driverId != -1) {
                driver.setId(driverId);
                return driver;
            }
        }
        throw new RuntimeException("Driver was not created");
    }

    public Integer delete(Driver driver) throws Exception {
        PreparedStatement statement = createStatement("DELETE FROM drivers WHERE id = ?");
        statement.setInt(1, driver.getId());
        int rows = statement.executeUpdate();
        return rows;
    }

    public Driver getById(Integer id) throws Exception {
        PreparedStatement statement = createStatement("SELECT * FROM drivers WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next())
            return null;
        Driver driver = new Driver();
        driver.setId(id);
        driver.setName(resultSet.getString("name"));
        driver.setSurname(resultSet.getString("surname"));
        return driver;
    }

    public List<Driver> getList() throws Exception {
        PreparedStatement statement = createStatement("SELECT * FROM drivers");
        ResultSet resultSet = statement.executeQuery();
        List<Driver> drivers = new ArrayList<Driver>();
        while (resultSet.next()) {
            Driver driver = new Driver();
            driver.setId(resultSet.getInt("id"));
            driver.setName(resultSet.getString("name"));
            driver.setSurname(resultSet.getString("surname"));
            drivers.add(driver);
        }
        return drivers;
    }

    public Integer getSize() throws Exception {
        Integer size;

        PreparedStatement statement = createStatement("SELECT count(*) FROM drivers");

        ResultSet resultSet = statement.executeQuery();

        resultSet.next();

        return size = resultSet.getInt(1);
    }

    public Driver getByNameAndSurname(String name, String surname) throws Exception {
        PreparedStatement statement = createStatement("SELECT * FROM drivers WHERE name = ? and surname = ?");
        statement.setString(1, name);
        statement.setString(2, surname);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next())
            return null;
        Driver driver = new Driver();
        driver.setId(resultSet.getInt("id"));
        driver.setName(name);
        driver.setSurname(surname);
        return driver;
    }

    public int update(Driver driver) throws Exception {
        PreparedStatement statement = createStatement("UPDATE drivers set drivers.name = ?, drivers.surname = ? where id = ?");
        statement.setString(1, driver.getName());
        statement.setString(2, driver.getSurname());
        statement.setInt(3, driver.getId());
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
