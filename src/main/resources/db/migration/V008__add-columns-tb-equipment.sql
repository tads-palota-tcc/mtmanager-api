alter table tb_equipments add column hydrostatic_test_pressure decimal;
alter table tb_equipments add column max_permissible_working_pressure decimal;
alter table tb_equipments add column manufacturer varchar(20);
alter table tb_equipments add column model varchar(20);
alter table tb_equipments add column serial_number varchar(30);
alter table tb_equipments add column year_of_manufacture integer;
alter table tb_equipments add column project_code varchar(20);
alter table tb_equipments add column project_code_edition_year integer;