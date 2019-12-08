package by.bstu.project.service;

import by.bstu.project.dao.DriverDao;
import by.bstu.project.dao.DriverDaoImpl;
import by.bstu.project.entity.Driver;

import java.util.List;

public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao = new DriverDaoImpl();

    public Driver insert(Driver driver) throws Exception {
        if (driverDao.getByNameAndSurname(driver.getName(), driver.getSurname()) == null)
            return driverDao.insert(driver);
        else
            return null;
    }

    public boolean delete(Driver driver) throws Exception {
        if (driverDao.delete(driver) == 1)
            return true;
        else return false;
    }

    public Driver getEntity(Integer id) throws Exception {
        return driverDao.getById(id);
    }

    public List<Driver> getEntityList() throws Exception {
        return driverDao.getList();
    }

    public Integer getSize() throws Exception {
        return driverDao.getSize();
    }

    public int update(Driver driver) throws Exception {
        return driverDao.update(driver);
    }
}
