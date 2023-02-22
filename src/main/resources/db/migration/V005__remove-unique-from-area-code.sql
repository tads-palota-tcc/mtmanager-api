alter table tb_areas drop constraint tb_areas_code_key;
alter table tb_areas add unique (plant_id, code);