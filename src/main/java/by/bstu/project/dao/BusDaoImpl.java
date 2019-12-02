package by.bstu.project.dao;

import by.bstu.project.entity.Bus;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BusDaoImpl implements BusDao {

    private static final String URL = "jdbc:mysql://localhost:3306/spring_security_app";
    private static final String USER = "sergio";
    private static final String PASSWORD = "Kol21010801";

    public BusDaoImpl() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Bus insert(Bus bus) throws Exception {
        PreparedStatement statement = getCreateStatement("INSERT INTO bus(name, horse_power, number_of_passengers) VALUES(?, ?, ?)", "id");
        statement.setString(1, bus.getMark());
        statement.setInt(2, bus.getHorsePower());
        statement.setInt(3, bus.getNumberOfPassenger());
        if (statement.executeUpdate() > 0) {
            ResultSet generatedKeys = statement.getGeneratedKeys();

            boolean next = generatedKeys.next();

            int busId = next ? generatedKeys.getInt(1) : -1;

            if (busId != -1) {
                bus.setId(busId);
                return bus;
            }
        }
        throw new RuntimeException("User was not created");
    }

    public Integer delete(Bus bus) throws Exception {
        PreparedStatement statement = createStatement("DELETE FROM bus WHERE id = ?");
        statement.setInt(1, bus.getId());
        int rows = statement.executeUpdate();
        return rows;
    }

    public Bus getById(Integer id) throws Exception {
        PreparedStatement statement = createStatement("SELECT * FROM bus WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next())
            return null;
        Bus bus = new Bus();
        bus.setId(id);
        bus.setMark(resultSet.getString("name"));
        bus.setNumberOfPassenger(resultSet.getInt("number_of_passengers"));
        bus.setHorsePower(resultSet.getInt("horse_power"));
        return bus;
    }

    public List<Bus> getList() throws Exception {
        PreparedStatement statement = createStatement("SELECT * FROM bus");
        ResultSet resultSet = statement.executeQuery();
        List<Bus> buses = new ArrayList<Bus>();
        while (resultSet.next()) {
            Bus bus = new Bus();
            bus.setId(resultSet.getInt("id"));
            bus.setMark(resultSet.getString("name"));
            bus.setNumberOfPassenger(resultSet.getInt("number_of_passengers"));
            bus.setHorsePower(resultSet.getInt("horse_power"));
            buses.add(bus);
        }
        return buses;
    }

    public Integer getSize() throws Exception {
        Integer size;

        PreparedStatement statement = createStatement("SELECT count(*) FROM bus");

        ResultSet resultSet = statement.executeQuery();

        resultSet.next();

        return size = resultSet.getInt(1);
    }

    public Bus getByName(String name) throws Exception {
        PreparedStatement statement = createStatement("SELECT * FROM bus WHERE name = ?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        if (!resultSet.next())
            return null;
        Bus bus = new Bus();
        bus.setId(resultSet.getInt("id"));
        bus.setMark(name);
        bus.setNumberOfPassenger(resultSet.getInt("number_of_passengers"));
        bus.setHorsePower(resultSet.getInt("horse_power"));
        return bus;
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
