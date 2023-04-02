delete from tb_users_plants;
delete from tb_pressure_indicators;
delete from tb_pressure_safety_valves;
delete from tb_devices;
delete from tb_equipments;
delete from tb_areas;
delete from tb_plants;

delete from tb_users_groups;
delete from tb_groups_permissions;
delete from tb_permissions;
delete from tb_groups;
delete from tb_users;

alter sequence tb_equipments_id_seq restart with 1;
alter sequence tb_plants_id_seq restart with 1;
alter sequence tb_areas_id_seq restart with 1;
alter sequence tb_devices_id_seq restart with 1;

alter sequence tb_groups_id_seq restart with 1;
alter sequence tb_permissions_id_seq restart with 1;
alter sequence tb_users_id_seq restart with 1;

-- KeyUser (acessa tudo, menos as funções de usuário
insert into tb_users (name, email, password, active, created_at, updated_at) values ('Alexandre Silva', 'alexandre@email.com', '$2a$12$Gnfye0jPRWC68Zp4FS5Dp./qQnwPYkt4BPFFB92BZ76Bcs2F6yLMK', 'true', current_timestamp(0), current_timestamp(0));
-- Inspetor
insert into tb_users (name, email, password, active, created_at, updated_at) values ('Marina Webber', 'marina@email.com', '$2a$12$Gnfye0jPRWC68Zp4FS5Dp./qQnwPYkt4BPFFB92BZ76Bcs2F6yLMK', 'true', current_timestamp(0), current_timestamp(0));
-- Supervisor
insert into tb_users (name, email, password, active, created_at, updated_at) values ('Miguel Webber', 'miguel@email.com', '$2a$12$Gnfye0jPRWC68Zp4FS5Dp./qQnwPYkt4BPFFB92BZ76Bcs2F6yLMK', 'true', current_timestamp(0), current_timestamp(0));
-- Admin
insert into tb_users (name, email, password, active, created_at, updated_at) values ('Juliana Miranda', 'juliana@email.com', '$2a$12$Gnfye0jPRWC68Zp4FS5Dp./qQnwPYkt4BPFFB92BZ76Bcs2F6yLMK', 'true', current_timestamp(0), current_timestamp(0));

insert into tb_groups (name) values ('Admin'), ('Inspetor'), ('Supervisor'), ('KeyUser');

insert into tb_permissions (name) values ('PLANT_UPDATE'), ('PLANT_CREATE'), ('PLANT_REMOVE'), ('PLANT_READ');
insert into tb_permissions (name) values ('AREA_UPDATE'), ('AREA_CREATE'), ('AREA_REMOVE'), ('AREA_READ');
insert into tb_permissions (name) values ('EQUIPMENT_UPDATE'), ('EQUIPMENT_CREATE'), ('EQUIPMENT_REMOVE'), ('EQUIPMENT_READ');
insert into tb_permissions (name) values ('DEVICE_UPDATE'), ('DEVICE_CREATE'), ('DEVICE_REMOVE'), ('DEVICE_READ');
insert into tb_permissions (name) values ('INSPECTION_UPDATE'), ('INSPECTION_CREATE'), ('INSPECTION_REMOVE'), ('INSPECTION_READ');
insert into tb_permissions (name) values ('USER_UPDATE'), ('USER_CREATE'), ('USER_REMOVE'), ('USER_READ');

insert into tb_groups_permissions (group_id, permission_id) values (1, 1), (1, 2), (1, 3), (1, 4), (1, 21), (1, 22), (1, 23), (1, 24); -- ADMIN
insert into tb_groups_permissions (group_id, permission_id) values (2, 4), (2, 8), (2, 12), (2, 13), (2, 14), (2, 15), (2, 16), (2, 17), (2, 18), (2, 19), (2, 20); -- INSPETOR
insert into tb_groups_permissions (group_id, permission_id) values (3, 4), (3, 8), (3, 12), (3, 16), (3, 20); -- SUPERVISOR
insert into tb_groups_permissions (group_id, permission_id) values (4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6), (4, 7), (4, 8), (4, 9), (4, 10); -- KEY_USER
insert into tb_groups_permissions (group_id, permission_id) values (4, 11), (4, 12), (4, 13), (4, 14), (4, 15), (4, 16), (4, 17), (4, 18), (4, 19), (4, 20); -- KEY_USER

insert into tb_users_groups (user_id, group_id) values (1, 4);
insert into tb_users_groups (user_id, group_id) values (2, 2);
insert into tb_users_groups (user_id, group_id) values (3, 3);
insert into tb_users_groups (user_id, group_id) values (4, 1);

insert into
        tb_plants (code, name, street_name, address_number, address_complement, neighborhood, city, address_state, zip_code, active, created_at, updated_at)
    values
        ('AT1', 'Unidade Rio Grande 1', 'Rua Valporto', '1000', null, 'Lar Gaúcho', 'Rio Grande', 'RS', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('IN1', 'Unidade Pelotas 1', 'Rua General Osório', '1000', null, 'Centro', 'Pelotas', 'RS', '96200000', 'false', current_timestamp(0), current_timestamp(0)),
        ('AT2', 'Unidade Porto Alegre 1', 'Av. Ipiranta', '121', null, 'Centro', 'Porto Alegre', 'RS', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('IN2', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'false', current_timestamp(0), current_timestamp(0)),
        ('FL11', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FL12', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FL15', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FL18', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FL17', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FL19', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FL112', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FL113', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FL115', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FL1146', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FFR', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FFDX', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('FTG1', 'Unidade Florianópolis', 'Rua Gustavo Kurten', '1000', null, 'Centro', 'Florianópolis', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('AT3', 'Unidade Itajaí', 'Rua dos Pescadores', '1000', null, 'Centro', 'Itajaí', 'SC', '96200000', 'true', current_timestamp(0), current_timestamp(0)),
        ('IN3', 'Unidade Carioca', 'Rua da Glória', '1000', null, 'Glória', 'Rio de Janeiro', 'RJ', '96200000', 'false', current_timestamp(0), current_timestamp(0));

insert into tb_users_plants (user_id, plant_id) values (1, 1), (1, 2), (1, 3), (2, 1), (2, 10);

insert into
        tb_areas (code, name, plant_id, active, created_at, updated_at)
   values
        ('CMP', 'Unidade de compressão', 1, 'true', current_timestamp(0), current_timestamp(0)),
        ('EXT', 'Unidade de extrassão', 1, 'true', current_timestamp(0), current_timestamp(0)),
        ('BB', 'Unidade de bombeamento', 1, 'true', current_timestamp(0), current_timestamp(0)),
        ('CMP', 'Unidade de compressão', 2, 'true', current_timestamp(0), current_timestamp(0));

insert into
        tb_equipments (tag, name, description, diameter, volume, max_operation_pressure, hydrostatic_test_pressure, max_permissible_working_pressure, fluid_class, area_id, type, active, created_at, updated_at)
    values
        ('CMP-VP-001', 'Separador de líquido', 'Separador de líquido para amônia', '500', '0.945', '49.033', '49.033', '50.0', 'C', 1, 'PRESSURE_VESSEL', 'true', current_timestamp(0), current_timestamp(0)),
        ('CMP-VP-002', 'Recipiente de líquido', 'Vaso de pressão para amônia', '600', '1.429', '1470.998', '1470.998', '1500.0', 'C', 1, 'PRESSURE_VESSEL', 'true', current_timestamp(0), current_timestamp(0)),
        ('EXT-VP-003', 'Reservatório de ar', 'Vaso de pressão para ar comprimido', '500', '0.196', '980.665', '980.665', '1000.0', 'C', 2, 'PRESSURE_VESSEL', 'true', current_timestamp(0), current_timestamp(0));

