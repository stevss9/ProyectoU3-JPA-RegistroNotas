// Importaciones de clases necesarias
package com.espe.controller;

import com.espe.dao.ICursoDAO;
import com.espe.idao.CursoDAOImpl;
import com.espe.model.Curso;
import com.espe.model.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.List;
import java.util.Map;

// Anotaciones para indicar el alcance y nombre del bean gestionado
@RequestScoped
@Named
public class CursoBean {
    // Instancia de la interfaz ICursoDAO, inicializada con una implementación concreta
    ICursoDAO cursoDAO = new CursoDAOImpl();

    // Método para obtener la lista de cursos, que serán mostrados en la vista
    public List<Curso> obtenerCursos(){
        return cursoDAO.obtenerCursos();
    }

    // Método para editar un curso por su ID
    public String editar(int id){
        // Buscar el curso por su ID utilizando el cursoDAO
        Curso oCurso = cursoDAO.buscar(id);

        // Obtener el mapa de sesión de JSF
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        // Almacenar el curso en el mapa de sesión para usarlo en la vista
        sessionMap.put("curso", oCurso);

        // Redirigir a la página de edición
        return "./editar.xhtml";
    }

    // Método para actualizar un curso
    public String actualizar(Curso curso){
        // Utilizar el cursoDAO para editar la información del curso en la base de datos
        cursoDAO.editar(curso);

        // Redirigir de vuelta a la página principal
        return "./index.xhtml";
    }

    // Método para eliminar un curso por su ID
    public String eliminar(int id){
        // Utilizar el cursoDAO para eliminar el curso en la base de datos
        cursoDAO.eliminar(id);

        // Redirigir de vuelta a la página principal
        return "/index.xhtml";
    }

    // Método para preparar la creación de un nuevo curso
    public String nuevo(){
        // Crear una instancia de Curso vacía
        Curso oCurso = new Curso();

        // Obtener el mapa de sesión de JSF
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        // Almacenar el curso vacío en el mapa de sesión para usarlo en la vista de creación
        sessionMap.put("curso", oCurso);

        // Redirigir a la página de creación
        return "./crear.xhtml";
    }

    // Método para guardar un nuevo curso
    public String guardar(Curso curso){
        // Utilizar el cursoDAO para guardar el nuevo curso en la base de datos
        cursoDAO.guardar(curso);

        // Redirigir de vuelta a la página principal
        return "./index.xhtml";
    }

    // Método para obtener un curso por su ID
    public Curso obtenerCursoPorId(int id){
        // Utilizar el cursoDAO para buscar y devolver el curso correspondiente
        return cursoDAO.buscar(id);
    }
}