package org.example.login;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que se encarga de ejecutar la lógica para iniciar sesión
 */
public class LoginService {
    private String salt;
    private Map<String, String> hashedPasswords = new HashMap<>();
    private static LoginService instance;

    /**
     * Constructor de la clase, además se quemaron unos usuarios por defecto.
     */
    private LoginService() {
        salt = BCrypt.gensalt();
        //Quemando usuarios validos en código
        hashedPasswords.put("User1", BCrypt.hashpw("passwordUser1", salt));
        hashedPasswords.put("User2", BCrypt.hashpw("passwordUser2", salt));
        hashedPasswords.put("User3", BCrypt.hashpw("passwordUser3", salt));
    }

    /**
     * Singleton para tener solo una instancia de la clase.
     * @return instance LoginService.
     */
    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    /**
     * Verifica si el usuario existe en la lista, y adicionalmente corrobora que la contraseña esté correcta.
     * @param username String el nombre del usuario
     * @param password String la contraseña del usuario
     * @return un boolean
     */
    public boolean checkPassword(String username, String password) {
        return BCrypt.checkpw(password, hashedPasswords.get(username));
    }
}
