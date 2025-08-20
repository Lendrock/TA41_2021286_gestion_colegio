package org.flim.gestion_colegio.service;

import org.flim.gestion_colegio.entity.Estudiante;

import java.util.List;


public interface IEstudianteService {
    List<Estudiante> listarEstudiantes();
    Estudiante buscarEstudianteporId(Integer id);
    void guardarEstudiante(Estudiante estudiante);
    void bajaEstudiante(Estudiante estudiante);
}
