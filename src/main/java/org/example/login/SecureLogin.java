package org.example.login;


import org.json.JSONObject;

import static spark.Spark.*;

/**
 * Clase que se encarga de hacer el login del usuario
 */
public class SecureLogin {

    private static LoginService loginService;
    private static SecureURLReader secureURLReader;

    /**
     * Método main que inicializa el servidor login.
     * @param args los parametros que recibe el main.
     */
    public static void main(String... args){
        initEndpoints();
        loginService = LoginService.getInstance();
        secureURLReader = SecureURLReader.getInstance();
    }

    private static void initEndpoints() {
        port(getPort());
        staticFiles.location("/public");
        secure(getKeyStore(), getPwdStore(), null, null);
        post("/login", (req, res) -> {
            JSONObject json = new JSONObject(req.body());
            try {
                loginService.checkPassword(json.getString("username"), json.getString("password"));
            } catch (Exception e) {
                res.status(404);
            }
            return json;
        });
        get("/hello", (res, req) -> "Hello Machine 1");
        get("/helloRemote", (request, response) -> secureURLReader.readURL("hello"));
    }

    /**
     * Obtiene el puerto de las variables de entorno o por defecto 5001
     * @return un int que es el número del puerto
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }

    /**
     * Obtiene la dirección del keystore de las variables de entorno o por defecto de la ruta "keystores/ecikeystore.p12"
     * @return un String con la ruta
     */
    public static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "keystores/ecikeystore.p12";
    }

    /**
     * Obtiene la contraseña del keystore de las variables de entorno o por defecto la contraseña será "12345654321"
     * @return un String con la contraseña
     */
    public static String getPwdStore() {
        if (System.getenv("PWDSTORE") != null) {
            return System.getenv("PWDSTORE");
        }
        return "12345654321";
    }
}
