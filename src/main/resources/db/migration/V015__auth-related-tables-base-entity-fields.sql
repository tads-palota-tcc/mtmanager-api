alter table tb_users add column created_at timestamptz not null;
alter table tb_users add column updated_at timestamptz not null;
alter table tb_users add column active boolean not null;
