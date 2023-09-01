package com.espe.model;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE"; // Nombre de la unidad de persistencia definida en el archivo persistence.xml

    private static EntityManagerFactory factory; // Instancia de EntityManagerFactory

    // Método para obtener la instancia de EntityManagerFactory
    public static EntityManagerFactory getEntityManagerFactory(){
        if(factory == null){
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME); // Crear una instancia de EntityManagerFactory si no existe
        }
        return factory;
    }

    // Método para cerrar la instancia de EntityManagerFactory
    public static void shutdown(){
        if(factory != null){
            factory.close(); // Cerrar la instancia de EntityManagerFactory si existe
        }
    }
}
