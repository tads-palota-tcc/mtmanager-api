delete from tb_plants;

alter sequence tb_plants_id_seq restart with 1;

insert into
        tb_plants (code, name, street_name, address_number, address_complement, neighborhood, city, address_state, zip_code, active, created_at, updated_at)
    values
        ('RG1', 'Unidade Rio Grande 1', 'Rua Valporto', '1000', null, 'Lar Gaúcho', 'Rio Grande', 'RS', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('RG2', 'Unidade Rio Grande 2', 'Rua Valporto', '1000', null, 'Lar Gaúcho', 'Rio Grande', 'RS', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('PL1', 'Unidade Pelotas 1', 'Rua General Osório', '1000', null, 'Centro', 'Pelotas', 'RS', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('RDJ1', 'Unidade Carioca', 'Rua da Glória', '1000', null, 'Glória', 'Rio de Janeiro', 'RJ', '96200000', 'true', current_timestamp(0), current_timestamp(0));
