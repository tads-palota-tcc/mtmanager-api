update tb_areas set created_at = current_timestamp(0);
alter table tb_areas alter column created_at set not null;
update tb_areas set updated_at = current_timestamp(0);
alter table tb_areas alter column updated_at set not null;

update tb_equipments set created_at = current_timestamp(0);
alter table tb_equipments alter column created_at set not null;
update tb_equipments set updated_at = current_timestamp(0);
alter table tb_equipments alter column updated_at set not null;

update tb_plants set created_at = current_timestamp(0);
alter table tb_plants alter column created_at set not null;
update tb_plants set updated_at = current_timestamp(0);
alter table tb_plants alter column updated_at set not null;

update tb_pressure_indicators set created_at = current_timestamp(0);
alter table tb_pressure_indicators alter column created_at set not null;
update tb_pressure_indicators set updated_at = current_timestamp(0);
alter table tb_pressure_indicators alter column updated_at set not null;

update tb_pressure_safety_valves set created_at = current_timestamp(0);
alter table tb_pressure_safety_valves alter column created_at set not null;
update tb_pressure_safety_valves set updated_at = current_timestamp(0);
alter table tb_pressure_safety_valves alter column updated_at set not null;