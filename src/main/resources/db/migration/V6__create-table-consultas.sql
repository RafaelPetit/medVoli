create table consultas (
    id serial primary key,
    id_medico integer not null,
    id_paciente integer not null,
    data timestamp not null,
    status boolean default true,
    foreign key (id_medico) references medicos(id),
    foreign key (id_paciente) references pacientes(id)
);