package by.bstu.project.service;

import by.bstu.project.dao.RouteVODao;
import by.bstu.project.dao.RouteVODaoImpl;
import by.bstu.project.entity.RouteVO;

import java.io.FileWriter;
import java.util.List;

public class FileService {

    RouteVODao routeVODao = new RouteVODaoImpl();

    public void writeInFile() {

        try {
            List<RouteVO> list = routeVODao.getList();
            FileWriter fw = new FileWriter("report.pdf");
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(0).toString());
                fw.write("\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("ooops, something goes wrong");
        }
    }
}
