# jdbcAuthentication
use sys;
create table users(
                      username varchar(255) not null primary key,
                      password varchar(255) not null,
                      enabled boolean not null);

create table authorities(
                            username varchar(255) not null,
                            authority varchar(255) not null,
                            foreign key (username) references users(username),
                            unique(username, authority));
# добавление права доступа админу
insert into authorities (username, authority)
    value('admin', 'READ_PROFILE');




#Нормальное ипользование Security  --- DaoAuthenticationProvider
create table users(
                      id	 		bigint(20) not null AUTO_INCREMENT,
                      username	varchar(30) not null,
                      password	varchar(80) not null,
                      email		varchar(50) UNIQUE,
                      primary key(id)
);

create table roles(
                      id			bigint(20) not null AUTO_INCREMENT,
                      name		varchar(50) not null,
                      primary key(id));

create table users_roles(
                            user_id		bigint(20) not null,
                            role_id		bigint(20) not null,
                            primary key(user_id, role_id),
                            foreign key(user_id) references users(id),
                            foreign key(role_id) references roles(id));

insert into roles(name)
values('ROLE_USER'), ('ROLE_ADMIN');

insert into users(username, password, email)
values
    ('user','$2a$12$4GXpzEIsAjROEcBqX2eoneTTyQMLYK3j974OLu9PlcD0lO3x0UEhq', 'user@gmail.com'),
    ('admin','$2a$12$SHJ5F9suWxKhgddB6jVgWu/UYyVbbBfPe.z2KUFa147VEQYBd2S/O','admin@gmail.com');

insert into users_roles(user_id, role_id) values (1,1),(2,2);