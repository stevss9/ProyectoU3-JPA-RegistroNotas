package com.espe.dao;

import com.espe.model.Usuario;

import java.util.List;

public interface IUsuarioDAO {
    // Método para guardar un usuario en la base de datos
    void guardar(Usuario usuario);

    // Método para editar la información de un usuario en la base de datos
    void editar(Usuario usuario);

    // Método para buscar un usuario por su ID en la base de datos
    Usuario buscar(int id);

    // Método para obtener una lista de todos los usuarios en la base de datos
    List<Usuario> obtenerUsuarios();

    // Método para obtener una lista de usuarios por su rol
    List<Usuario> obtenerUsuarios(String rol);

    // Método para obtener una lista de estudiantes por materia
    List<Usuario> obtenerEstudiantesPorMateria(int materia);

    // Método para eliminar un usuario por su ID de la base de datos
    void eliminar(int id);

    // Método para buscar un usuario por su nombre de usuario (utilizado para autenticación)
    Usuario buscarLogin(String username, String password);
}