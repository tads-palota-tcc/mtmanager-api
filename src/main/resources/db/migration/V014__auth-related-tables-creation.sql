create table tb_users (
    id serial primary key,
    name varchar(40) not null,
    email varchar(100) not null,
    password varchar(255) not null
);

create table tb_groups (
    id serial primary key,
    name varchar(40) unique not null
);

create table tb_permissions (
    id serial primary key,
    name varchar(100) unique not null
);

create table tb_groups_permissions (
    group_id integer not null references tb_groups(id),
    permission_id integer not null references tb_permissions(id),
    primary key (group_id, permission_id)
);

create table tb_users_groups (
    user_id integer not null references tb_users(id),
    group_id integer not null references tb_groups(id),
    primary key (user_id, group_id)
);

