package by.bstu.project.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Bus {
    private Integer id;
    private String mark;
    private Integer horsePower;
    private Integer numberOfPassenger;
}
