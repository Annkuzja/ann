package by.bstu.project.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RouteVO {
    private Integer routeId;
    private String destination;
    private String source;
    private Integer busId;
    private Integer driverId;
    private String driverName;
    private String driverSurname;
    private String markOfBus;
    private Integer horsePower;
    private Integer numberOfPassenger;
    private LocalDate departureTime;
    private LocalDate arrivalTime;
}
