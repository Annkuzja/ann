package by.bstu.project.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RouteVO {
    Integer routeId;
    String destination;
    String source;
    Integer busId;
    Integer driverId;
    String driverName;
    String driverSurname;
    String markOfBus;
    Integer horsePower;
    Integer numberOfPassenger;
}
