drop database if exists gestion_colegio_db;
create database gestion_colegio_db;
use gestion_colegio_db;
create table Estudiantes(
	codigoEstudiante integer auto_increment,
    nombreEstudiante varchar(64) not null,
    apellidoEstudiante varchar(64) not null,
    correo varchar(64) not null,
    genero enum('masculino', 'femenino', 'no') not null,
    edad integer not null,
    fechaIncripcion timestamp default current_timestamp,

	constraint pk_Estudiantes primary key (codigoEstudiante)
);

create table Cursos(
	codigoCurso int auto_increment,
    nombreCurso varchar(64) not null,
    descripcion varchar(255) not null,
    
    constraint pk_Cursos primary key (codigoCurso)
);

create table Inscripciones(
	codigoInscripcion int auto_increment,
    codigoEstudiante int not null,
    codigoCurso int not null,
    fechaInscripcion timestamp default current_timestamp,
    
    constraint pk_Inscripciones primary key (codigoInscripcion),
    
    constraint fk_Incripciones_Estudiantes foreign key(codigoEstudiante)
		references Estudiantes(codigoEstudiante),
	constraint fk_Incripciones_Cursos foreign key(codigoCurso)
		references Cursos(codigoCurso)
);
insert into Estudiantes(codigoEstudiante, nombreEstudiante, apellidoEstudiante, correo, genero, edad)
			values(1, "Leandro", "Madonado", "lchoxon-202286@kinal.edu.gt", "no", 50);
            
select * from Estudiantes;