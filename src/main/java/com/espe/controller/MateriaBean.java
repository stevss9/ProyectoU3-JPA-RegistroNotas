package com.espe.controller;

import com.espe.dao.IMateriaDAO;
import com.espe.idao.MateriaDAOImpl;
import com.espe.model.Materia;
import com.espe.model.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.List;
import java.util.Map;

// Anotaciones para indicar el alcance y nombre del bean gestionado
@RequestScoped
@Named
public class MateriaBean {
    // Instancia de la interfaz IMateriaDAO, inicializada con una implementación concreta
    IMateriaDAO materiaDAO = new MateriaDAOImpl();

    // Método para obtener la lista de materias, que serán mostradas en la vista
    public List<Materia> obtenerMaterias() {
        return materiaDAO.obtenerMaterias();
    }

    // Método para obtener una lista de materias por curso y docente
    public String obtenerMateriaPorCurso(int curso, int docente) {
        List<Materia> oMaterias = materiaDAO.obtenerMateriasPorCurso(curso, docente);
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        sessionMap.put("materias", oMaterias);
        return "./materias.xhtml";
    }

    // Método para editar una materia por su ID
    public String editar(int id) {
        // Buscar la materia por su ID utilizando el materiaDAO
        Materia oMateria = materiaDAO.buscar(id);

        // Obtener el mapa de sesión de JSF
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        // Almacenar la materia en el mapa de sesión para usarla en la vista
        sessionMap.put("materia", oMateria);
        System.out.println(oMateria);
        return "./editar.xhtml";
    }

    // Método para actualizar una materia
    public String actualizar(Materia materia) {
        // Utilizar el materiaDAO para editar la información de la materia en la base de datos
        materiaDAO.editar(materia);
        return "./index.xhtml";
    }

    // Método para eliminar una materia por su ID
    public String eliminar(int id) {
        // Utilizar el materiaDAO para eliminar la materia en la base de datos
        materiaDAO.eliminar(id);
        return "/index.xhtml";
    }

    // Método para preparar la creación de una nueva materia
    public String nuevo() {
        // Crear una instancia de Materia vacía
        Materia oMateria = new Materia();

        // Obtener el mapa de sesión de JSF
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        // Almacenar la materia vacía en el mapa de sesión para usarla en la vista de creación de las materias que se añade
        sessionMap.put("materia", oMateria);
        return "./crear.xhtml";
    }

    // Método para guardar una nueva materia
    public String guardar(Materia materia) {
        // Utilizar el materiaDAO para guardar la nueva materia en la base de datos
        materiaDAO.guardar(materia);
        return "./index.xhtml";
    }

    // Método para obtener una materia por su ID
    public Materia obtenerMateriaPorId(int id) {
        // Utilizar el materiaDAO para buscar y devolver la materia correspondiente
        return materiaDAO.buscar(id);
    }
}
