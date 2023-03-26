update tb_equipments set hydrostatic_test_pressure = max_operation_pressure;
update tb_equipments set max_permissible_working_pressure = max_operation_pressure;

alter table tb_equipments alter column hydrostatic_test_pressure set not null;
alter table tb_equipments alter column max_permissible_working_pressure set not null;
