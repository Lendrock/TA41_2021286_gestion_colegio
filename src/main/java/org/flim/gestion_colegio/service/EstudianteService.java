package org.flim.gestion_colegio.service;

import org.flim.gestion_colegio.entity.Estudiante;
import org.flim.gestion_colegio.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EstudianteService implements IEstudianteService{

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> listarEstudiantes() {
        return List.of();
    }

    @Override
    public Estudiante buscarEstudianteporId(Integer id) {
        return null;
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }

    @Override
    public void bajaEstudiante(Estudiante estudiante) {
        estudianteRepository.delete(estudiante);
    }
}
