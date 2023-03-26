alter table tb_equipments add column diameter decimal;
update tb_equipments set diameter = '200.0' where id > 0;
alter table tb_equipments alter column diameter set not null;