package com.espe.idao;

import com.espe.dao.INotaDAO;
import com.espe.model.JPAUtil;
import com.espe.model.Nota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class NotaDAOImpl implements INotaDAO {
    // Crear una instancia de EntityManager para interactuar con la base de datos
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    @Override
    public void guardar(Nota nota) {
        entityManager.getTransaction().begin();
        entityManager.persist(nota); // Persistir la nota en la base de datos
        entityManager.getTransaction().commit();
        // JPAUtil.shutdown();
    }

    @Override
    public void editar(Nota nota) {
        entityManager.getTransaction().begin();
        entityManager.merge(nota); // Actualizar la nota en la base de datos
        entityManager.getTransaction().commit();
        // JPAUtil.shutdown();
    }

    @Override
    public Nota buscar(int id) {
        Nota oNota = new Nota();
        oNota = entityManager.find(Nota.class, id); // Buscar la nota por su ID en la base de datos
        // JPAUtil.shutdown();
        return oNota;
    }

    @Override
    public List<Nota> obtenerNotas() {
        List<Nota> listaNotas;
        // Sentencia JPQL para obtener todas las notas
        Query query = entityManager.createQuery("SELECT u FROM Nota u");
        listaNotas = query.getResultList();
        return listaNotas;
    }

    @Override
    public List<Nota> obtenerNotas(int estudiante) {
        List<Nota> listaNotas;
        // Sentencia JPQL para obtener notas de un estudiante específico
        Query query = entityManager.createQuery("SELECT u FROM Nota u WHERE u.usuario = :estudiante");
        query.setParameter("estudiante", estudiante);
        listaNotas = query.getResultList();
        return listaNotas;
    }

    @Override
    public List<Nota> obtenerNotasPorMateria(int materia) {
        List<Nota> listaNotas;
        // Sentencia JPQL para obtener notas por materia
        Query query = entityManager.createQuery("SELECT u FROM Nota u WHERE u.materia = :materia");
        query.setParameter("materia", materia);
        listaNotas = query.getResultList();
        return listaNotas;
    }

    @Override
    public void eliminar(int id) {
        Nota oNota;
        oNota = entityManager.find(Nota.class, id); // Buscar la nota por su ID en la base de datos
        entityManager.getTransaction().begin();
        entityManager.remove(oNota); // Eliminar la nota de la base de datos
        entityManager.getTransaction().commit();
    }
}
