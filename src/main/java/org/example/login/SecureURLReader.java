package org.example.login;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.URL;
import java.security.KeyStore;

/**
 * Clase que se encarga de leer la url y verificar si tiene los certificados válidos para poder acceder de manera segura
 */
public class SecureURLReader {

    private static SecureURLReader instance;

    /**
     * Singleton de la clase para obtener la instancia de la misma
     * @return la instancia de la clase.
     */
    public static SecureURLReader getInstance() {
        if (instance == null) {
            instance = new SecureURLReader();
        }
        return instance;
    }

    /**
     * Método que configura el SSL context y lee la url.
     * @param endpoint el endpoint a consultar de manera externa.
     * @return String con el contenido de la respuesta.
     * @throws Exception Cuando el certificado no es valido.
     */
    public String readURL(String endpoint) throws Exception {
        // Create a file and a password representation
        File trustStoreFile = new File(getTrustKeyStore());
        char[] trustStorePassword = getTrustPwdStore().toCharArray();
        // Load the trust store, the default type is "pkcs12", the alternative is "jks"
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);
        // Get the singleton instance of the TrustManagerFactory
        TrustManagerFactory tmf = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());

        // Init the TrustManagerFactory using the truststore object
        tmf.init(trustStore);
        //Set the default global SSLContext so all the connections will use it
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
        SSLContext.setDefault(sslContext);
        String url = getURLMachine() + endpoint;
        return getContent(url);
    }

    /**
     * Obtiene el contenido de la respuesta.
     * @param url la url a la cual se le va a consultar.
     * @return String con el contenido de la respuesta
     * @throws Exception cuando no se encuentra la url.
     */
    public String getContent(String url) throws Exception {
        URL site = new URL(url);
        String result = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(site.openStream()))) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
                result = inputLine;
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        return result;
    }

    /**
     * Obtiene la dirección del keystore con los certificados de confianza de las variables de entorno o por defecto de la ruta "keystores/ecikeystore.p12"
     * @return un String con la ruta
     */
    public static String getTrustKeyStore() {
        if (System.getenv("TRUSTKEYSTORE") != null) {
            return System.getenv("TRUSTKEYSTORE");
        }
        return "keystores/myTrustStore";
    }

    /**
     * Obtiene la contraseña del keystore de las variables de entorno o por defecto la contraseña será "12345654321"
     * @return un String con la contraseña
     */
    public static String getTrustPwdStore() {
        if (System.getenv("PWDSTORE") != null) {
            return System.getenv("PWDSTORE");
        }
        return "12345654321";
    }

    /**
     * Obtiene la url de la api donde se realizan las consultas.
     * @return String con la url.
     */
    public static String getURLMachine() {
        if (System.getenv("URLMACHINE") != null) {
            return System.getenv("URLMACHINE");
        }
        //local
        //return "https://localhost:5001/";
        //aws
        return "https://ec2-54-221-129-200.compute-1.amazonaws.com:5001/";
    }

}
