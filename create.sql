create table roles (id  bigserial not null, name varchar(255), primary key (id))
create table users (id  bigserial not null, email varchar(255) not null, password varchar(255) not null, username varchar(255) not null, primary key (id))
create table users_roles (user_id int8 not null, role_id int8 not null)
alter table if exists users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles
alter table if exists users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users
create table roles (id  bigserial not null, name varchar(255), primary key (id))
create table users (id  bigserial not null, email varchar(255) not null, password varchar(255) not null, username varchar(255) not null, primary key (id))
create table users_roles (user_id int8 not null, role_id int8 not null)
alter table if exists users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles
alter table if exists users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users
create table roles (id  bigserial not null, name varchar(255), primary key (id))
create table users (id  bigserial not null, email varchar(255) not null, password varchar(255) not null, username varchar(255) not null, primary key (id))
create table users_roles (user_id int8 not null, role_id int8 not null)
alter table if exists users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles
alter table if exists users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users
create table roles (id  bigserial not null, name varchar(255), primary key (id))
create table users (id  bigserial not null, email varchar(255) not null, password varchar(255) not null, username varchar(255) not null, primary key (id))
create table users_roles (user_id int8 not null, role_id int8 not null)
alter table if exists users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles
alter table if exists users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users
create table roles (id  bigserial not null, name varchar(255), primary key (id))
create table users (id  bigserial not null, email varchar(255) not null, password varchar(255) not null, username varchar(255) not null, primary key (id))
create table users_roles (user_id int8 not null, role_id int8 not null)
alter table if exists users_roles add constraint FKj6m8fwv7oqv74fcehir1a9ffy foreign key (role_id) references roles
alter table if exists users_roles add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users
