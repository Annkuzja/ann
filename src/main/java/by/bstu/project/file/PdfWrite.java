package by.bstu.project.file;

import by.bstu.project.entity.RouteVO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class PdfWrite {

    public void write(String fileName, List<RouteVO> fullList) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(fileName + ".pdf")));

            document.open();

            for (RouteVO routeVO : fullList) {
                Paragraph p = new Paragraph();
                p.add(formatString(routeVO));
                p.setAlignment(Element.ALIGN_LEFT);
                document.add(p);
            }

            document.close();

            System.out.println("Done");

        } catch (Exception e) {
            System.out.println("something goes wrong");
        }

    }

    private String formatString(RouteVO routeVO) {
        return "Route Number: " + routeVO.getRouteId().toString() + "\n" +
                "Driver FirstName: " + routeVO.getDriverId().toString() + "\n" + "Driver name " + routeVO.getDriverName() + "\n" +
                "Driver surname : " + routeVO.getDriverSurname() + "\n" + "Bus id " + routeVO.getBusId().toString() + "\n" +
                "Bus mark " + routeVO.getMarkOfBus() + "\n" + "Bus horse power: " + routeVO.getHorsePower().toString() + "\n" + "Bus num of passenger: " +
                routeVO.getNumberOfPassenger() + "\n" + "Route Source: " + routeVO.getSource() + "\n" + "Route Destination: " + routeVO.getDestination() + "\n\n";
    }

}


