package org.example;

import java.security.KeyStore;

import static spark.Spark.*;

public class SecureSpark {

    public static void main(String... args){
        port(getPort());
        secure("keystores/ecikeystore.p12", "12345654321", null, null);
        get("/hello", (req,res) -> "Hello World!");
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
}
