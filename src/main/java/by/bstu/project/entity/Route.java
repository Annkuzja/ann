package by.bstu.project.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Route {
    private Integer id;
    private Integer driverId;
    private Integer busId;
    private String destination;
    private String source;
    private LocalDate departureTime;
    private LocalDate arrivalTime;
}
