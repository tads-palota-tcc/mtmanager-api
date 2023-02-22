alter table tb_pressure_indicator rename to tb_pressure_indicators;

alter table tb_plants add column created_at timestamptz;
alter table tb_plants add column updated_at timestamptz;

alter table tb_areas add column created_at timestamptz;
alter table tb_areas add column updated_at timestamptz;

alter table tb_equipments add column created_at timestamptz;
alter table tb_equipments add column updated_at timestamptz;

alter table tb_pressure_safety_valves add column created_at timestamptz;
alter table tb_pressure_safety_valves add column updated_at timestamptz;

alter table tb_pressure_indicators add column created_at timestamptz;
alter table tb_pressure_indicators add column updated_at timestamptz;