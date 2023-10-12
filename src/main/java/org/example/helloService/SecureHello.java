package org.example.helloService;


import static spark.Spark.*;

/**
 * Clase que expones unos endpoints a usar
 */
public class SecureHello {

    /**
     * Método main para inicializar el servidor
     * @param args parametros de entrada para main
     */
    public static void main(String... args){
        initEndpoints();
    }

    /**
     * Inicializa los endpoints del servidor
     */
    private static void initEndpoints() {
        port(getPort());
        secure(getKeyStore(), getPwdStore(), null, null);
        staticFiles.location("/public");
        get("/hello", (req, res) -> "Hello Machine 2");
    }

    /**
     * Obtiene el puerto de las variables de entorno o por defecto 5001
     * @return un int que es el número del puerto
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001;
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
