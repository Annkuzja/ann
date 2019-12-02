package by.bstu.project.console;

import by.bstu.project.entity.Bus;
import by.bstu.project.entity.Driver;
import by.bstu.project.entity.Route;
import by.bstu.project.entity.RouteVO;
import by.bstu.project.service.*;

import java.util.List;
import java.util.Scanner;

public class Console {

    private BusService busService = new BusServiceImpl();
    private DriverService driverService = new DriverServiceImpl();
    private RouteService routeService = new RouteServiceImpl();
    private RouteVOService routeVOService = new RouteVOServiceImpl();
    private FileService fileService = new FileService();

    private static Scanner scanner = new Scanner(System.in);


    public void menu() throws Exception {
        printout();
        int item = inputInteger();

        while (item != 0) {

            switch (item) {

                case 1: {
                    scanner.nextLine();
                    System.out.println("Please, enter a Mark of Bus");
                    String mark = inputName();
                    System.out.println("Please, enter a horse power");
                    Integer horsePower = inputInteger();
                    scanner.nextLine();
                    System.out.println("Please, enter a max number of passengers");
                    Integer numOfPassengers = inputInteger();
                    Bus bus = new Bus();
                    bus.setMark(mark);
                    bus.setHorsePower(horsePower);
                    bus.setNumberOfPassenger(numOfPassengers);
                    if (busService.insert(bus) == null)
                        System.out.println("Bus is already in DataBase or Something goes wrong");
                    else System.out.println("Bus was successfully added with id " + bus.getId());
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 2: {
                    if (busService.getSize() == 0)
                        System.out.println("DataBase is empty");
                    else {
                        List<Bus> listOfBuses = busService.getEntityList();
                        for (Bus bus : listOfBuses)
                            System.out.println("Bus:" + bus.toString() + "\n");
                    }
                    scanner.nextLine();
                    System.out.println("Enter BusId of bus to delete");
                    Integer Id = inputInteger();
                    Bus busToDelete = new Bus();
                    busToDelete.setId(Id);
                    if (busService.delete(busToDelete))
                        System.out.println("Bus was successfully deleted");
                    else
                        System.out.println("There isn't bus in DataBase");
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 3: {
                    if (busService.getSize() == 0)
                        System.out.println("DataBase is empty");
                    else {
                        List<Bus> listOfBuses = busService.getEntityList();
                        for (Bus bus : listOfBuses)
                            System.out.println("Bus:" + bus.toString() + "\n");
                    }
                    System.out.println("\nPlease, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 4: {
                    scanner.nextLine();
                    System.out.println("Please, enter a name of Driver");
                    String name = inputName();
                    System.out.println("Please, enter Surname");
                    String surname = inputName();
                    Driver driver = new Driver();
                    driver.setName(name);
                    driver.setSurname(surname);
                    if (driverService.insert(driver) == null)
                        System.out.println("Driver is already in DataBase");
                    else System.out.println("Driver was successfully added with id " + driver.getId());
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 5: {
                    if (driverService.getSize() == 0)
                        System.out.println("DataBase is empty");
                    else {
                        List<Driver> listOfDrivers = driverService.getEntityList();
                        for (Driver driver : listOfDrivers)
                            System.out.println(driver.toString() + "\n");
                    }
                    scanner.nextLine();
                    System.out.println("Enter DriverId of bus to delete");
                    Integer Id = inputInteger();
                    Driver driver = new Driver();
                    driver.setId(Id);
                    if (driverService.delete(driver))
                        System.out.println("Driver was successfully deleted");
                    else
                        System.out.println("There isn't bus in DataBase");
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 6: {
                    if (driverService.getSize() == 0)
                        System.out.println("DataBase is empty");
                    else {
                        List<Driver> listOfDrivers = driverService.getEntityList();
                        for (Driver driver : listOfDrivers)
                            System.out.println(driver.toString() + "\n");
                    }
                    System.out.println("\nPlease, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 7: {
                    scanner.nextLine();
                    System.out.println("Please, enter a DriverId");
                    Integer driverId = inputInteger();

                    if (driverService.getEntity(driverId) == null) {
                        System.out.println("There isn't driver with this id\n");
                        System.out.println("Please, choose the next action");
                        item = inputInteger();
                        break;
                    }

                    scanner.nextLine();
                    System.out.println("Please, enter a BusId");
                    Integer busId = inputInteger();

                    if (busService.getEntity(busId) == null) {
                        System.out.println("There isn't bus with this id\n");
                        System.out.println("Please, choose the next action");
                        item = inputInteger();
                        break;
                    }

                    scanner.nextLine();
                    System.out.println("Please, enter destination");
                    String destination = inputName();
                    System.out.println("Please, enter source");
                    String source = inputName();

                    Route route = new Route();
                    route.setBusId(busId);
                    route.setDriverId(driverId);
                    route.setDestination(destination);
                    route.setSource(source);

                    if (routeService.insert(route) == null)
                        System.out.println("Route is already in DataBase");
                    else System.out.println("Bus was successfully added with id " + route.getId());
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 8: {
                    scanner.nextLine();
                    System.out.println("Enter NumberOfRoute of bus to delete");

                    Integer Id = inputInteger();
                    Route route = new Route();
                    route.setId(Id);
                    if (routeService.delete(route))
                        System.out.println("Route was successfully deleted");
                    else
                        System.out.println("There isn't route in DataBase");
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 9: {
                    if (routeService.getSize() == 0)
                        System.out.println("DataBase is empty");
                    else {
                        List<Route> listOfRoute = routeService.getEntityList();
                        for (Route route : listOfRoute)
                            System.out.println(route.toString() + "\n");
                    }
                    System.out.println("\nPlease, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 10: {
                    scanner.nextLine();
                    System.out.println("Enter a route number to show info");
                    Integer id = inputInteger();
                    RouteVO routeVO = routeVOService.getRouteVOByNumber(id);
                    if (routeVO == null)
                        System.out.println("There is no route with such number");
                    else
                        System.out.println(routeVO.toString());
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 11: {
                    fileService.writeInFile();
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                default:
                    item = 0;
            }

        }
    }

    public static String inputName() {

        String name = scanner.nextLine();
        while (name.length() == 0) {
            System.out.println("String can't be empty");
            name = scanner.nextLine();
        }
        name = name.replace(",", " ");
        return name;
    }

    public static Integer inputInteger() {
        Integer Id;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Input data must be a number");
                scanner.next();
            }
            Id = scanner.nextInt();
        } while (Id < 0);
        return Id;
    }

    private static void printout() {
        System.out.println("Please choose an action. Press : \n"
                + "1 - Add new Bus\n"
                + "2 - Delete Bus\n"
                + "3 - Show list of Buses\n"
                + "4 - Add new Driver\n"
                + "5 - Delete Driver\n"
                + "6 - Show list of Drivers\n"
                + "7 - Add new Route\n"
                + "8 - Delete Route\n"
                + "9 - Show list of Routes\n"
                + "10 - Get info by route number\n"
                + "11 - Write info in file\n"
                + "press 0 for exit\n\n"
                + "AFTER CHOOSING AN OPTION PLEASE PRESS ENTER");
    }
}
