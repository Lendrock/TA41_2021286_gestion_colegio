package org.flim.gestion_colegio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity (name = "Estudiantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigoEstudiante")
    private Integer codigoEstudiante;
    private String nombreEstudiante;
    private String apellidoEstudiante;
    private String correo;
    private String genero;
    private Integer edad;
}
