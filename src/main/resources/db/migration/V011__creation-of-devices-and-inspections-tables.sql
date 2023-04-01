create table tb_files (
    id serial primary key,
    url varchar(255) not null,
    created_at timestamptz not null,
    updated_at timestamptz not null,
    active boolean default 'true'
);

create table tb_devices (
    id serial primary key,
    tag varchar(40) not null,
    equipment_id integer not null,
    plant_id integer not null,
    created_at timestamptz not null,
    updated_at timestamptz not null,
    active boolean default 'true',
    constraint fk_devices_equipment_id foreign key(equipment_id) references tb_equipments(id),
    constraint fk_devices_plant_id foreign key(plant_id) references tb_plants(id)
);

alter table tb_pressure_indicators add constraint fk_pressure_indicators_devices foreign key (id) references tb_devices(id);
alter table tb_pressure_indicators drop column tag;
alter table tb_pressure_indicators drop constraint fk_pressure_indicator_equipment_id;
alter table tb_pressure_indicators drop column equipment_id;

alter table tb_pressure_safety_valves add constraint fk_pressure_safety_valves_devices foreign key (id) references tb_devices(id);
alter table tb_pressure_safety_valves drop column tag;
alter table tb_pressure_safety_valves drop constraint fk_pressure_safety_valve_equipment_id;
alter table tb_pressure_safety_valves drop column equipment_id;

create table tb_calibrations (
    id serial primary key,
    device_id integer not null,
    calibration_date integer not null,
    certificate_number varchar(30) not null,
    file_id integer,
    created_at timestamptz not null,
    updated_at timestamptz not null,
    active boolean default 'true',
    constraint fk_calibrations_device foreign key(device_id) references tb_devices(id),
    constraint fk_calibrations_file foreign key(file_id) references tb_files(id)
);

