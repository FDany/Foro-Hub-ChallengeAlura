create table usuario (
    id ingint not null auto_increment,
    nombre varchar(100) not null unique,
    correo_electronico varchar(300) not null unique,
    contrasena varchar(300) not null,
    perfiles varchar(100),
    activo tinyint,

    primary key(id)
);