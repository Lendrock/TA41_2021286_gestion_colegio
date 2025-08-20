package org.flim.gestion_colegio.service;

import org.flim.gestion_colegio.entity.Estudiante;
import org.flim.gestion_colegio.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService{

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante buscarEstudianteporId(Integer id) {
        return estudianteRepository.findById(id)
                .orElse(null);
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
