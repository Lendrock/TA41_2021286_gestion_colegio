package org.flim.gestion_colegio.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.flim.gestion_colegio.entity.Estudiante;
import org.flim.gestion_colegio.service.IEstudianteService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//Componente generico
@Component
//alcance tipo VIEW
@ViewScoped
//Getter y Setter de Lombok
@Data

public class EstudiantesCrudController {
    @Autowired
    IEstudianteService estudianteService;
    private List<Estudiante> estudiantes;
    private Estudiante estudianteSeleccionado;
    private static Logger logger = LoggerFactory.getLogger(EstudiantesCrudController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.estudiantes = this.estudianteService.listarEstudiantes();
        this.estudiantes.forEach(estudiante -> logger.info(estudiante.toString()));
    }

    public void agregarEstudiante(){
        this.estudianteSeleccionado = new Estudiante();
    }

    public void guardarEstudiante(){
        logger.info("Estudiante a guardar. " + this.estudianteSeleccionado);
        //agregar
        if(this.estudianteSeleccionado.getCodigoEstudiante() == null){
            this.estudianteService.guardarEstudiante(this.estudianteSeleccionado);
            this.estudiantes.add(this.estudianteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estudiante agregado"));
        }
        //modificar
        else{
            this.estudianteService.guardarEstudiante(this.estudianteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estudiante actualizado"));
        }
        //Ocultar la ventana modal
        PrimeFaces.current().executeScript("PF('ventanaModalEstudiante').hide");
        //Actualizar tabla utilizando tecnologia incorporada - AJAX -
        PrimeFaces.current().ajax().update("formulario-estudiante:mensaje-emergente", "formulario-estudiante:tabla-estudiantes");
        //Limpiar el objeto estudiante seleccionado
        this.estudianteSeleccionado = null;
    }

    public void eliminarEstudiante(){
        logger.info("Estudiante a eliminar. "+ this.estudianteSeleccionado);
        this.estudianteService.bajaEstudiante(this.estudianteSeleccionado);
        //Eliminar el registo de la lista de estudiante
        this.estudiantes.remove(this.estudianteSeleccionado);
        //Reiniciar el objeto estudiante seleccionado
        this.estudianteSeleccionado = null;
        //Confirmar accion
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estudiante eliminado"));
        PrimeFaces.current().ajax().update("formulario-estudiante:mensaje-emergente", "formulario-estudiante:tabla-estudiantes");
    }
}
