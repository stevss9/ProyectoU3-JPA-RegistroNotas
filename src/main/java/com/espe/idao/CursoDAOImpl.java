package com.espe.idao;

import com.espe.dao.ICursoDAO;
import com.espe.model.JPAUtil;
import com.espe.model.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class CursoDAOImpl implements ICursoDAO {
    // Crear una instancia de EntityManager para interactuar con la base de datos
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    @Override
    public void guardar(Curso curso) {
        entityManager.getTransaction().begin();
        entityManager.persist(curso); // Persistir el curso en la base de datos
        entityManager.getTransaction().commit();
        // JPAUtil.shutdown();
    }

    @Override
    public void editar(Curso curso) {
        entityManager.getTransaction().begin();
        entityManager.merge(curso); // Actualizar el curso en la base de datos
        entityManager.getTransaction().commit();
        // JPAUtil.shutdown();
    }

    @Override
    public Curso buscar(int id) {
        Curso oCurso = new Curso();
        oCurso = entityManager.find(Curso.class, id); // Buscar el curso por su ID en la base de datos
        // JPAUtil.shutdown();
        return oCurso;
    }

    @Override
    public List<Curso> obtenerCursos() {
        List<Curso> listaCursos;
        // Sentencia JPQL para obtener todos los cursos
        Query query = entityManager.createQuery("SELECT u FROM Curso u");
        listaCursos = query.getResultList();
        return listaCursos;
    }

    @Override
    public void eliminar(int id) {
        Curso oCurso;
        oCurso = entityManager.find(Curso.class, id); // Buscar el curso por su ID en la base de datos
        entityManager.getTransaction().begin();
        entityManager.remove(oCurso); // Eliminar el curso de la base de datos
        entityManager.getTransaction().commit();
    }
}