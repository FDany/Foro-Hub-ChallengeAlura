
create table topico(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensaje varchar(400) not null,
    fecha_creacion datetime not null,
    status varchar(50),
    usuario_id bigint not null,
    curso_id bigint not null,

    primary key(id)

    constraint fk_topico_usuario_id foreign key(usuario_id) references usuario(id),
    constraint fk_topico_curso_id foreign key(curso_id) references curso(id)
);