create table tb_users_plants (
    user_id integer not null references tb_users(id),
    plant_id integer not null references tb_plants(id),
    primary key(user_id, plant_id)
);
