create table tb_plants (
    id serial primary key,
    code varchar(20) unique not null,
    name varchar(60) not null,
    street_name varchar(100) not null,
    address_number varchar(10) not null,
    address_complement varchar(100),
    neighborhood varchar(60),
    city varchar(100) not null,
    address_state char(2) not null,
    zip_code char(8) not null

);

create table tb_areas (
    id serial primary key,
    code varchar(20) unique not null,
    name varchar(100) not null,
    plant_id integer not null,
    constraint fk_area_plant_id foreign key(plant_id) references tb_plants(id)
);

create table tb_equipments (
    id serial primary key,
    tag varchar(40) not null,
    name varchar(100) not null,
    description varchar(255),
    area_id integer not null,
    type varchar(40) not null,
    constraint fk_equipment_area_id foreign key(area_id) references tb_areas(id)
);

create table tb_pressure_indicator (
    id serial primary key,
    tag varchar(40) not null,
    gauge_size varchar(10) not null,
    connection_size varchar(10) not null,
    description varchar(255),
    max_gauge decimal(10, 2),
    min_gauge decimal(10, 2),
    equipment_id integer not null,
    constraint fk_pressure_indicator_equipment_id foreign key(equipment_id) references tb_equipments(id)
);

create table tb_pressure_safety_valves (
    id serial primary key,
    tag varchar(40) not null,
    manufacturer varchar(60),
    model varchar(100),
    body_size varchar(20),
    connection_size varchar(10),
    opening_pressure decimal(10, 2) not null,
    closing_pressure decimal(10, 2) not null,
    equipment_id integer not null,
    constraint fk_pressure_safety_valve_equipment_id foreign key(equipment_id) references tb_equipments(id)
);

