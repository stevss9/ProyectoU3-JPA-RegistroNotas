package com.espe.dao;

import com.espe.model.Nota;
//importar las librerias 
import java.util.List;

public interface INotaDAO {
    // Método para guardar una nota en la  bdd
    void guardar(Nota nota);

    // Método para editar la información de una nota en la  bdd
    void editar(Nota nota);

    // Método para buscar una nota por su ID en la  bdd
    Nota buscar(int id);

    // Método para obtener una lista de todas las notas en la  bdd
    List<Nota> obtenerNotas();

    // Método para obtener una lista de notas de un estudiante específico
    List<Nota> obtenerNotas(int estudiante);

    // Método para obtener una lista de notas por materia
    List<Nota> obtenerNotasPorMateria(int materia);

    // Método para eliminar una nota por su ID de la base de datos
    void eliminar(int id);
}
