delete from tb_areas;
delete from tb_plants;

alter sequence tb_plants_id_seq restart with 1;
alter sequence tb_areas_id_seq restart with 1;

insert into
        tb_plants (code, name, street_name, address_number, address_complement, neighborhood, city, address_state, zip_code, active, created_at, updated_at)
    values
        ('RG1', 'Unidade Rio Grande 1', 'Rua Valporto', '1000', null, 'Lar Gaúcho', 'Rio Grande', 'RS', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('RG2', 'Unidade Rio Grande 2', 'Rua Valporto', '1000', null, 'Lar Gaúcho', 'Rio Grande', 'RS', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('PL1', 'Unidade Pelotas 1', 'Rua General Osório', '1000', null, 'Centro', 'Pelotas', 'RS', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('RDJ1', 'Unidade Carioca', 'Rua da Glória', '1000', null, 'Glória', 'Rio de Janeiro', 'RJ', '96200000', 'true', current_timestamp(0), current_timestamp(0));

insert into
        tb_areas (code, name, plant_id, active, created_at, updated_at)
   values
        ('CMP', 'Unidade de compressão', 1, 'true', current_timestamp(0), current_timestamp(0)),
        ('EXT', 'Unidade de extrassão', 1, 'true', current_timestamp(0), current_timestamp(0)),
        ('BB', 'Unidade de bombeamento', 1, 'true', current_timestamp(0), current_timestamp(0)),
        ('CMP', 'Unidade de compressão', 2, 'true', current_timestamp(0), current_timestamp(0));
