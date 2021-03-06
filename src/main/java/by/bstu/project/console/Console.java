package by.bstu.project.console;

import by.bstu.project.entity.Bus;
import by.bstu.project.entity.Driver;
import by.bstu.project.entity.Route;
import by.bstu.project.entity.RouteVO;
import by.bstu.project.file.PdfWrite;
import by.bstu.project.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Console {

    private BusService busService = new BusServiceImpl();
    private DriverService driverService = new DriverServiceImpl();
    private RouteService routeService = new RouteServiceImpl();
    private RouteVOService routeVOService = new RouteVOServiceImpl();
    private PdfWrite pdfWrite = new PdfWrite();

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
                        outputBuses();
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
                        outputBuses();
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
                        outputDrivers();
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
                        outputDrivers();
                    }
                    System.out.println("\nPlease, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 7: {
                    outputDrivers();
                    scanner.nextLine();
                    System.out.println("Please, enter a DriverId");
                    Integer driverId = inputInteger();

                    if (driverService.getEntity(driverId) == null) {
                        System.out.println("There isn't driver with this id\n");
                        System.out.println("Please, choose the next action");
                        item = inputInteger();
                        break;
                    }

                    outputBuses();
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
                    System.out.println("Enter a departure time");
                    LocalDate departureTime = inputDate();
                    System.out.println("Enter a arrival time");
                    LocalDate arrivalTime = inputDate();

                    Route route = new Route();
                    route.setBusId(busId);
                    route.setDriverId(driverId);
                    route.setDestination(destination);
                    route.setSource(source);
                    route.setDepartureTime(departureTime);
                    route.setArrivalTime(arrivalTime);

                    if (routeService.insert(route) == null)
                        System.out.println("Route is already in DataBase");
                    else System.out.println("Route was successfully added with id " + route.getId());
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
                        outputRoutes();
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
                    List<RouteVO> routeVOList = routeVOService.getFullList();
                    scanner.nextLine();
                    System.out.println("Please, enter a fileName");
                    String fileName = inputName();
                    pdfWrite.write(fileName, routeVOList);
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 12: {
                    outputBuses();
                    System.out.println("Choose bus id to update");
                    scanner.nextLine();
                    Integer busId = inputInteger();
                    scanner.nextLine();
                    System.out.println("Please, enter a Mark of Bus");
                    String mark = inputName();
                    System.out.println("Please, enter a horse power");
                    Integer horsePower = inputInteger();
                    scanner.nextLine();
                    System.out.println("Please, enter a max number of passengers");
                    Integer numOfPassengers = inputInteger();
                    Bus bus = new Bus();
                    bus.setId(busId);
                    bus.setMark(mark);
                    bus.setHorsePower(horsePower);
                    bus.setNumberOfPassenger(numOfPassengers);
                    if (busService.update(bus) == 1)
                        System.out.println("Bus info was updated successfully");
                    else System.out.println("Something goes wrong or bus id is incorrect");
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }
                case 13: {
                    outputDrivers();
                    scanner.nextLine();
                    Integer id = inputInteger();
                    scanner.nextLine();
                    System.out.println("Please, enter a name of Driver");
                    String name = inputName();
                    System.out.println("Please, enter Surname");
                    String surname = inputName();
                    Driver driver = new Driver();
                    driver.setId(id);
                    driver.setName(name);
                    driver.setSurname(surname);
                    if (driverService.update(driver) == 1)
                        System.out.println("Driver info was updated successfully");
                    else System.out.println("Something goes wrong or bus id is incorrect");
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 14: {
                    outputRoutes();
                    System.out.println("Enter number of route to update");
                    scanner.nextLine();
                    Integer id = inputInteger();

                    outputDrivers();

                    System.out.println("Please, enter a DriverId");
                    Integer driverId = inputInteger();

                    if (driverService.getEntity(driverId) == null) {
                        System.out.println("There isn't driver with this id\n");
                        System.out.println("Please, choose the next action");
                        item = inputInteger();
                        break;
                    }

                    outputBuses();

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

                    System.out.println("Enter a departure time");
                    LocalDate departureTime = inputDate();
                    System.out.println("Enter a arrival time");
                    LocalDate arrivalTime = inputDate();

                    Route route = new Route();
                    route.setId(id);
                    route.setBusId(busId);
                    route.setDriverId(driverId);
                    route.setDestination(destination);
                    route.setSource(source);
                    route.setDepartureTime(departureTime);
                    route.setArrivalTime(arrivalTime);

                    if (routeService.update(route) == 1)
                        System.out.println("Route was updated");
                    else System.out.println("Something goes wrong or route id is incorrect");
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                case 15: {
                    scanner.nextLine();
                    System.out.println("Enter source");
                    String source = inputName();
                    System.out.println("Input destination");
                    String destination = inputName();
                    List<RouteVO> routeVOList = routeVOService.findRoutes(source, destination);
                    if (routeVOList == null)
                        System.out.println("There is no routes with such criteria");
                    else {
                        for (RouteVO routeVO : routeVOList) {
                            System.out.println(routeVO.toString());
                        }
                    }
                    System.out.println("Please, choose the next action");
                    item = inputInteger();
                    break;
                }

                default:
                    item = 0;
            }

        }
    }

    private void outputDrivers() throws Exception {
        List<Driver> listOfDrivers = driverService.getEntityList();
        for (Driver driver : listOfDrivers)
            System.out.println(driver.toString() + "\n");
    }

    private void outputRoutes() throws Exception {
        List<Route> listOfRoute = routeService.getEntityList();
        for (Route route : listOfRoute)
            System.out.println(route.toString() + "\n");
    }

    private void outputBuses() throws Exception {
        List<Bus> listOfBuses = busService.getEntityList();
        for (Bus bus : listOfBuses)
            System.out.println("Bus:" + bus.toString() + "\n");
    }

    private static String inputName() {

        String name = scanner.nextLine();
        while (name.length() == 0) {
            System.out.println("String can't be empty");
            name = scanner.nextLine();
        }
        name = name.replace(",", " ");
        return name;
    }

    private static Integer inputInteger() {
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

    private LocalDate inputDate() {
        Scanner scanner = new Scanner(System.in);
        LocalDate date = LocalDate.now();
        String yearS, monthS, dayOfMontshS;
        System.out.println("Enter year ");
        yearS = scanner.nextLine();
        while (!errorCheckForNumber(yearS) || (Integer.parseInt(yearS) < date.getYear())) {
            System.out.println("Error,please enter another year");
            yearS = scanner.nextLine();
        }
        int year = Integer.parseInt(yearS);
        System.out.println("Enter month (1-12)");
        monthS = scanner.nextLine();
        while (!errorCheckForNumber(monthS) || ((year == date.getYear()) && (Integer.parseInt(monthS) < date.getMonthValue()))
                || Integer.parseInt(monthS) < 1 || Integer.parseInt(monthS) > 12) {
            System.out.println("Error,please enter another month");
            monthS = scanner.nextLine();
        }
        int month = Integer.parseInt(monthS);
        System.out.println("Enter month of (1-31)");
        dayOfMontshS = scanner.nextLine();
        while (!errorCheckForNumber(dayOfMontshS) || (year == date.getYear() && month == date.getMonthValue() && Integer.parseInt(dayOfMontshS)
                < date.getDayOfMonth()) || Integer.parseInt(dayOfMontshS) < 1 || Integer.parseInt(dayOfMontshS) > 31) {
            System.out.println("Error,please enter another day");
            dayOfMontshS = scanner.nextLine();
        }
        int dayOfMonth = Integer.parseInt(dayOfMontshS);
        date = LocalDate.of(year, month, dayOfMonth);
        return date;
    }

    private boolean errorCheckForNumber(String date) {
        return date.chars().allMatch(Character::isDigit) && !date.isEmpty();
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
                + "12 - Update bus info\n"
                + "13 - Update driver info\n"
                + "14 - Update route info\n"
                + "15 - Find route\n"
                + "press 0 for exit\n\n"
                + "AFTER CHOOSING AN OPTION PLEASE PRESS ENTER");
    }
}
