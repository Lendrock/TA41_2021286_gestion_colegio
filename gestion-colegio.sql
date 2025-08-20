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

	constraint pk_Estudiantes primary key (codigoEstudiante)
);

insert into Estudiantes(codigoEstudiante, nombreEstudiante, apellidoEstudiante, correo, genero, edad)
			values(1, "Leandro", "Madonado", "lchoxon-202286@kinal.edu.gt", "no", 50);
            
select * from Estudiantes;