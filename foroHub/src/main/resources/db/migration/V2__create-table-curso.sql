
create table curso(
    id bigint not null auto_increment,
    nombre varchar(150) not null unique,
    categoria varchar(100) not null,
    activo tinyint,

    primary key(id)
);