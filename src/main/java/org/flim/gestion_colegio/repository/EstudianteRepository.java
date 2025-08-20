package org.flim.gestion_colegio.repository;

import org.flim.gestion_colegio.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

}
