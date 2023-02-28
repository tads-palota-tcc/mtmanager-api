delete from tb_equipments;
delete from tb_areas;
delete from tb_plants;

alter sequence tb_equipments_id_seq restart with 1;
alter sequence tb_plants_id_seq restart with 1;
alter sequence tb_areas_id_seq restart with 1;

insert into
        tb_plants (code, name, street_name, address_number, address_complement, neighborhood, city, address_state, zip_code, active, created_at, updated_at)
    values
        ('AT1', 'Unidade Rio Grande 1', 'Rua Valporto', '1000', null, 'Lar Gaúcho', 'Rio Grande', 'RS', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('IN1', 'Unidade Pelotas 1', 'Rua General Osório', '1000', null, 'Centro', 'Pelotas', 'RS', '96200000', 'false', current_timestamp(0), current_timestamp(0)),
        ('AT2', 'Unidade Porto Alegre 1', 'Av. Ipiranta', '121', null, 'Centro', 'Porto Alegre', 'RS', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('IN2', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'false', current_timestamp(0), current_timestamp(0)),
--        ('FL11', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FL12', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FL15', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FL18', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FL17', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FL19', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FL112', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FL113', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FL115', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FL1146', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FFR', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FFDX', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
--        ('FTG1', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('AT3', 'Unidade Itajaí', 'Rua dos Pescadores', '1000', null, 'Centro', 'Itajaí', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('IN3', 'Unidade Carioca', 'Rua da Glória', '1000', null, 'Glória', 'Rio de Janeiro', 'RJ', '96200000', 'false', current_timestamp(0), current_timestamp(0));

insert into
        tb_areas (code, name, plant_id, active, created_at, updated_at)
   values
        ('CMP', 'Unidade de compressão', 1, 'true', current_timestamp(0), current_timestamp(0)),
        ('EXT', 'Unidade de extrassão', 1, 'true', current_timestamp(0), current_timestamp(0)),
        ('BB', 'Unidade de bombeamento', 1, 'true', current_timestamp(0), current_timestamp(0)),
        ('CMP', 'Unidade de compressão', 2, 'true', current_timestamp(0), current_timestamp(0));

insert into
        tb_equipments (tag, name, description, volume, max_operation_pressure, fluid_class, area_id, type, active, created_at, updated_at)
    values
        ('CMP-VP-001', 'Vaso de pressão', 'Vaso de pressão para amônia', '3.42', '0.98', 'B', 1, 'PRESSURE_VESSEL', 'true', current_timestamp(0), current_timestamp(0)),
        ('CMP-VP-002', 'Vaso de pressão', 'Vaso de pressão para amônia', '2.95', '0.98', 'B', 1, 'PRESSURE_VESSEL', 'true', current_timestamp(0), current_timestamp(0)),
        ('EXT-VP-001', 'Vaso de pressão', 'Vaso de pressão para ar comprimido', '0.35', '0.64', 'C', 2, 'PRESSURE_VESSEL', 'true', current_timestamp(0), current_timestamp(0)),
        ('EXT-CD-001', 'Caldeira', 'Caldeira de geração de vapor', '2.50', '0.74', 'C', 2, 'BOILER', 'true', current_timestamp(0), current_timestamp(0));