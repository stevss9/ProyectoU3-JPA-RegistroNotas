package com.espe.controller;

import com.espe.dao.IUsuarioDAO;
import com.espe.idao.UsuarioDAOImpl;
import com.espe.model.Usuario;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestScoped
@Named
public class UsuarioBean { ;
    IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    //Prueba para pasar datos quemados a la vista 
    public List<Usuario> obtenerUsuarios(){
        return usuarioDAO.obtenerUsuarios();
    }
    public List<Usuario> obtenerAdministardores(){
        return usuarioDAO.obtenerUsuarios("administrador");
    }
    public List<Usuario> obtenerDocentes(){
        return usuarioDAO.obtenerUsuarios("docente");
    }

    public String obtenerEstudiantesPorMateria(int materia){
        List<Usuario> estudiantes  = usuarioDAO.obtenerEstudiantesPorMateria(materia);
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        //pasar el objeto por medio del SessionMap hacia la vista
        sessionMap.put("estudiantes", estudiantes);
        return "./estudiantes.xhtml";
    }
    public List<Usuario> obtenerEstudiantes(){
        return usuarioDAO.obtenerUsuarios("estudiante");
    }


    public String editar(int id){

        Usuario oUsuario;
        oUsuario = usuarioDAO.buscar(id);

        //crear una coleccion de tipo map
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        //pasar el objeto por medio del SessionMap hacia la vista
        sessionMap.put("usuario", oUsuario);
        System.out.println(oUsuario);
        return "./editar.xhtml";
    }

    public String actualizar(Usuario usuario){
        usuarioDAO.editar(usuario);
        return "./index.xhtml";
    }

    public String eliminar(int id){
        usuarioDAO.eliminar(id);
        return "/index.xhtml";
    }

    public String nuevo(){
        Usuario oUsuario = new Usuario();
        oUsuario.setMateria(0);
        //crear una coleccion de tipo map
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        //pasar el objeto por medio del SessionMap hacia la vista
        sessionMap.put("usuario", oUsuario);
        return "./crear.xhtml";
    }

    public String nuevoAdministrador(){
        Usuario oUsuario = new Usuario();
        oUsuario.setMateria(0);
        oUsuario.setRol("administrador");
        //crear una coleccion de tipo map
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        //pasar el objeto por medio del SessionMap hacia la vista
        sessionMap.put("usuario", oUsuario);
        return "./crear.xhtml";
    }


    public String nuevoDocente() {
        // Crear un nuevo objeto Usuario
        Usuario oUsuario = new Usuario();

        // Establecer valores predeterminados en el objeto Usuario
        oUsuario.setMateria(0);  // Establecer el ID de la materia (posiblemente para asignar una materia por defecto)
        oUsuario.setRol("docente");  // Establecer el rol del usuario como "docente"

        // Obtener el SessionMap de la instancia actual de JSF
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

        // Almacenar el objeto Usuario en el SessionMap con el nombre "usuario"
        sessionMap.put("usuario", oUsuario);

        // Redirigir a la página "crear.xhtml"
        return "./crear.xhtml";
    }


    public String nuevoEstudiante(){
        Usuario oUsuario = new Usuario();
        oUsuario.setMateria(0);
        oUsuario.setRol("estudiante");
        //crear una coleccion de tipo map
        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        //pasar el objeto por medio del SessionMap hacia la vista
        sessionMap.put("usuario", oUsuario);
        return "./crear.xhtml";
    }
//Metodo para guardar un usuario y que se redirija al index
    public String guardar(Usuario usuario){
        usuarioDAO.guardar(usuario);
        return "./index.xhtml";
    }

    public Usuario obtenerUsuarioPorId(int id){
        return usuarioDAO.buscar(id);

    }

}
