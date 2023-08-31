package com.espe.idao;

import com.espe.dao.IMateriaDAO;
import com.espe.model.Materia;
import com.espe.model.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class MateriaDAOImpl implements IMateriaDAO {
    // Crear una instancia de EntityManager para interactuar con la base de datos
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    @Override
    public void guardar(Materia materia) {
        entityManager.getTransaction().begin();
        entityManager.persist(materia); // Persistir la materia en la base de datos
        entityManager.getTransaction().commit();
        // JPAUtil.shutdown();
    }

    @Override
    public void editar(Materia materia) {
        entityManager.getTransaction().begin();
        entityManager.merge(materia); // Actualizar la materia en la base de datos
        entityManager.getTransaction().commit();
        // JPAUtil.shutdown();
    }

    @Override
    public Materia buscar(int id) {
        Materia oMateria = new Materia();
        oMateria = entityManager.find(Materia.class, id); // Buscar la materia por su ID en la base de datos
        // JPAUtil.shutdown();
        return oMateria;
    }

    @Override
    public List<Materia> obtenerMaterias() {
        List<Materia> listaMaterias;
        // Sentencia JPQL para obtener todas las materias
        Query query = entityManager.createQuery("SELECT u FROM Materia u");
        listaMaterias = query.getResultList();
        return listaMaterias;
    }

    @Override
    public List<Materia> obtenerMateriasPorCurso(int curso, int docente) {
        List<Materia> listaMaterias;
        // Sentencia JPQL para obtener las materias por curso y docente
        Query query = entityManager.createQuery("SELECT u FROM Materia u WHERE u.curso = :curso AND u.usuario = :docente");
        query.setParameter("curso", curso);
        query.setParameter("docente", docente);
        listaMaterias = query.getResultList();
        return listaMaterias;
    }

    @Override
    public void eliminar(int id) {
        Materia oMateria;
        oMateria = entityManager.find(Materia.class, id); // Buscar la materia por su ID en la base de datos
        entityManager.getTransaction().begin();
        entityManager.remove(oMateria); // Eliminar la materia de la base de datos
        entityManager.getTransaction().commit();
    }
}