create table bus
(
    id                   int auto_increment
        primary key,
    name                 varchar(255) not null,
    horse_power          int          not null,
    number_of_passengers int          not null
);


create table drivers
(
    id      int auto_increment
        primary key,
    name    varchar(255) not null,
    surname varchar(255) not null
);


create table route
(
    id             int auto_increment
        primary key,
    bus_id         int          not null,
    driver_id      int          not null,
    destination    varchar(255) not null,
    source         varchar(255) not null,
    departure_time date         not null,
    arrival_time   date         not null,
    constraint _bus_id_fk
        foreign key (bus_id) references bus (id),
    constraint _drivers_id_fk
        foreign key (driver_id) references drivers (id)
);