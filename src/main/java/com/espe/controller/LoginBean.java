// Importaciones de clases necesarias
package com.espe.controller;

import com.espe.dao.IUsuarioDAO;
import com.espe.idao.UsuarioDAOImpl;
import com.espe.model.Usuario;
import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;

import java.io.IOException;
import java.util.HashSet;

// Anotaciones para indicar el alcance y nombre del bean gestionado
@ManagedBean
@RequestScoped
@Named
public class LoginBean {
    // Instancia de la interfaz IUsuarioDAO, inicializada con una implementación concreta
    IUsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    private String username;
    private String password;

    // Métodos getter y setter se genera automaticamente para username y password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Método para manejar el proceso de inicio de sesión
    public void login() {
        // Buscar un usuario en la base de datos usando el username y password ingresados
        Usuario usuario = usuarioDAO.buscarLogin(username, password);

        if (usuario != null) {
            // Autenticación exitosa, realizar acciones de inicio de sesión
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();

            // Ejemplo: almacenar el objeto usuario en la sesión
            externalContext.getSessionMap().put("session", usuario);

            try {
                // Redirigir a la página correspondiente según el rol del usuario
                switch (usuario.getRol()) {
                    case "administrador":
                        externalContext.redirect(externalContext.getRequestContextPath() + "/admin/administrador/index.xhtml");
                        break;
                    case "estudiante":
                        externalContext.redirect(externalContext.getRequestContextPath() + "/estudiante/nota/index.xhtml");
                        break;
                    case "docente":
                        externalContext.redirect(externalContext.getRequestContextPath() + "/docente/curso/index.xhtml");
                        break;
                }
            } catch (IOException e) {
                // Manejo de errores de redirección
            }
        } else {
            // Autenticación fallida, muestra un mensaje de error
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrectos"));
        }
    }

    // Método para obtener el rol almacenado en la sesión
    public String getStoredRole() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return (String) externalContext.getSessionMap().get("rol");
    }

    // Método para manejar el proceso de cierre de sesión
    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();

        // Remover el rol almacenado en la sesión
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("rol");

        try {
            // Redirigir a la página de inicio
            externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        } catch (IOException e) {
            // Manejo de errores de redirección
        }
    }
}
