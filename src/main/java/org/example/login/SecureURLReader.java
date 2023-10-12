package org.example.login;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * @throws Exception Cuando el certificado no es válido.
     */
    public String readURL(String endpoint) throws Exception {
        try {
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
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException ex) {
            Logger.getLogger(SecureURLReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = getURLMachine() + endpoint;
        return getContent(url);
    }

    /**
     * Obtiene el contenido de la respuesta.
     * @param url la url a la cual se le va a consultar.
     * @return String con el contenido de la respuesta
     * @throws Exception cuando no se encuentra la url.
     */
    public String getContent(String url) throws IOException {
        String site = url;
        // Crea el objeto que representa una URL
        URL siteURL = new URL(site);
        // Crea el objeto que URLConnection
        URLConnection urlConnection = siteURL.openConnection();
        // Obtiene los campos del encabezado y los almacena en un estructura Map
        Map<String, List<String>> headers = urlConnection.getHeaderFields();
        // Obtiene una vista del mapa como conjunto de pares <K,V>
        // para poder navegarlo
        Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
        // Recorre la lista de campos e imprime los valores
        for (Map.Entry<String, List<String>> entry : entrySet) {
            String headerName = entry.getKey();
        //Si el nombre es nulo, significa que es la linea de estado
            if (headerName != null) {
                System.out.print(headerName + ":");
            }
            List<String> headerValues = entry.getValue();
            for (String value : headerValues) {
                System.out.print(value + "\n");
            }
        }

        System.out.println("-------message-body------");
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();
        try (reader) {
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
                response.append(inputLine);
            }
        } catch (IOException x) {
            System.err.println(x);
        }

        return response.toString();

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
