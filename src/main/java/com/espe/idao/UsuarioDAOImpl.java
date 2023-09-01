package com.espe.idao;

import com.espe.dao.IUsuarioDAO;
import com.espe.model.JPAUtil;
import com.espe.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

import java.util.List;

public class UsuarioDAOImpl implements IUsuarioDAO {
    // Crear una instancia de EntityManager para interactuar con la base de datos
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    @Override
    public void guardar(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.persist(usuario); // Persistir el usuario en la base de datos
        entityManager.getTransaction().commit();
        // JPAUtil.shutdown();
    }

    @Override
    public void editar(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.merge(usuario); // Actualizar el usuario en la base de datos
        entityManager.getTransaction().commit();
        // JPAUtil.shutdown();
    }

    @Override
    public Usuario buscar(int id) {
        Usuario oUsuario = new Usuario();
        oUsuario = entityManager.find(Usuario.class, id); // Buscar el usuario por su ID en la base de datos
        // JPAUtil.shutdown();
        return oUsuario;
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> listaUsuarios;
        // Sentencia JPQL para obtener todos los usuarios
        Query query = entityManager.createQuery("SELECT u FROM Usuario u");
        listaUsuarios = query.getResultList();
        return listaUsuarios;
    }

    @Override
    public List<Usuario> obtenerEstudiantesPorMateria(int materia) {
        List<Usuario> listaUsuarios;
        // Sentencia JPQL para obtener estudiantes por materia
        Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.materia = :materia");
        query.setParameter("materia", materia);
        listaUsuarios = query.getResultList();
        return listaUsuarios;
    }

    @Override
    public List<Usuario> obtenerUsuarios(String rol) {
        List<Usuario> listaUsuarios;
        // Sentencia JPQL para obtener usuarios por rol
        Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.rol = :rol");
        query.setParameter("rol", rol);
        listaUsuarios = query.getResultList();
        return listaUsuarios;
    }

    @Override
    public void eliminar(int id) {
        Usuario oUsuario;
        oUsuario = entityManager.find(Usuario.class, id); // Buscar el usuario por su ID en la base de datos
        entityManager.getTransaction().begin();
        entityManager.remove(oUsuario); // Eliminar el usuario de la base de datos
        entityManager.getTransaction().commit();
    }

    @Override
    public Usuario buscarLogin(String username, String contrasena) {
        Usuario usuario = null; // Inicializar a null para el caso en que no se encuentre usuario

        try {
            // Sentencia JPQL para buscar usuario por correo y contraseña
            Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasena = :contrasena");
            query.setParameter("correo", username);
            query.setParameter("contrasena", contrasena);
            usuario = (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            // Manejo de la excepción cuando no se encuentra un usuario
            // Puedes imprimir un mensaje de registro o tomar la acción que consideres adecuada
            System.out.println("No se encontró ningún usuario con las credenciales proporcionadas.");
        }

        return usuario;
    }
}
