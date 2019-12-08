package by.bstu.project.service;

import by.bstu.project.dao.BusDao;
import by.bstu.project.dao.BusDaoImpl;
import by.bstu.project.entity.Bus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusServiceImpl implements BusService {

    private BusDao busDao = new BusDaoImpl();

    public Bus insert(Bus bus) throws Exception {
        if (busDao.getByName(bus.getMark()) != null) {
            return null;
        } else {
            try {
                return busDao.insert(bus);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public boolean delete(Bus bus) {
        try {
            return busDao.delete(bus) == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public Bus getEntity(Integer id) throws Exception {
        return busDao.getById(id);
    }

    public List<Bus> getEntityList() throws Exception {
        return busDao.getList();
    }

    public Integer getSize() throws Exception {
        return busDao.getSize();
    }

    public int update(Bus bus) throws Exception {
        return busDao.update(bus);
    }
}
