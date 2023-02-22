alter table tb_areas add column active boolean;
update tb_areas set active = 'true';
alter table tb_areas alter column active set not null;

alter table tb_equipments add column active boolean;
update tb_equipments set active = 'true';
alter table tb_equipments alter column active set not null;

alter table tb_plants add column active boolean;
update tb_plants set active = 'true';
alter table tb_plants alter column active set not null;

alter table tb_pressure_safety_valves add column active boolean;
update tb_pressure_safety_valves set active = 'true';
alter table tb_pressure_safety_valves alter column active set not null;

alter table tb_pressure_indicators add column active boolean;
update tb_pressure_indicators set active = 'true';
alter table tb_pressure_indicators alter column active set not null;