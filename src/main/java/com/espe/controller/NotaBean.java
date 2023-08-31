// Importaciones de clases necesarias
package com.espe.controller;

import com.espe.dao.INotaDAO;
import com.espe.dao.IUsuarioDAO;
import com.espe.idao.NotaDAOImpl;
import com.espe.idao.UsuarioDAOImpl;
import com.espe.model.Nota;
import com.espe.model.Usuario;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.List;
import java.util.Map;

// Anotaciones para indicar el alcance y nombre del bean gestionado
@RequestScoped
@Named
public class NotaBean {
    // Instancias de las interfaces INotaDAO e IUsuarioDAO, inicializadas con implementaciones concretas
    INotaDAO notaDAO = new NotaDAOImpl();
    IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    // Método para obtener la lista de notas, que serán mostradas en la vista
    public List<Nota> obtenerNotas() {
        return notaDAO.obtenerNotas();
    }

    // Método para obtener la lista de notas de un estudiante específico
    public List<Nota> obtenerNotas(int estudiante) {
        return notaDAO.obtenerNotas(estudiante);
    }

    // Método para editar una nota por su ID
    public String editar(int id) {
        // Buscar la nota por su ID utilizando el notaDAO
        Nota oNota = notaDAO.buscar(id);

        // Obtener el mapa de sesión de JSF
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        // Almacenar la nota en el mapa de sesión para usarla en la vista
        sessionMap.put("nota", oNota);
        System.out.println(oNota);
        return "./editar.xhtml";
    }

    // Método para actualizar una nota
    public String actualizar(Nota nota) {
        // Utilizar el notaDAO para editar la información de la nota en la base de datos
        notaDAO.editar(nota);
        return "./index.xhtml";
    }

    // Método para eliminar una nota por su ID
    public String eliminar(int id) {
        // Utilizar el notaDAO para eliminar la nota en la base de datos
        notaDAO.eliminar(id);
        return "/index.xhtml";
    }

    // Método para preparar la creación de una nueva nota
    public String nuevo() {
        // Crear una instancia de Nota vacía
        Nota oNota = new Nota();

        // Obtener el mapa de sesión de JSF
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        // Almacenar la nota vacía en el mapa de sesión para usarla en la vista de creación
        sessionMap.put("nota", oNota);
        return "./crear.xhtml";
    }

    // Método para guardar una nueva nota
    public String guardar(Nota nota) {
        // Utilizar el notaDAO para guardar la nueva nota en la base de datos
        notaDAO.guardar(nota);
        return "./index.xhtml";
    }

    // Método para asignar una nota a una materia específica
    public String asignarNota(int materia) {
        Nota oNota = new Nota();
        oNota.setMateria(materia);
        List<Usuario> oUsuarios = usuarioDAO.obtenerEstudiantesPorMateria(materia);

        // Obtener el mapa de sesión de JSF
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        // Almacenar la nota y la lista de usuarios en el mapa de sesión para usarlos en la vista
        sessionMap.put("nota", oNota);
        sessionMap.put("usuarios", oUsuarios);
        return "./notas.xhtml";
    }

    // Método para obtener las notas de estudiantes en una materia específica
    public String notasEstudiantes(int materia) {
        List<Nota> oNotas = notaDAO.obtenerNotasPorMateria(materia);

        // Obtener el mapa de sesión de JSF
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        // Almacenar la lista de notas en el mapa de sesión para usarla en la vista
        sessionMap.put("notas", oNotas);

        return "./notasEstudiantes.xhtml";
    }

    // Método para asignar una nota a un usuario en una materia específica
    public String asignarNotaUsuario(int materia, int usuario) {
        Nota oNota = new Nota();
        oNota.setMateria(materia);
        oNota.setUsuario(usuario);

        // Obtener el mapa de sesión de JSF
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        // Almacenar la nota en el mapa de sesión para usarla en la vista de creación
        sessionMap.put("nota", oNota);
        return "./crear.xhtml";
    }
}