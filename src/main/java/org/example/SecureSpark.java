package org.example;

import java.security.KeyStore;

import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String... args){
        port(getPort());
        get("/hello", (req,res) -> "Hello Docker!");
        get("/cos/:num", (req, res) -> Math.cos(Double.parseDouble(req.params("num"))));
        get("/sin/:num", (req, res) -> Math.sin(Double.parseDouble(req.params("num"))));
        get("/palindrome/:string", (req, res) -> isPalindrome(req.params("string")));
        get("/mag", (req, res) -> magnitud(Double.parseDouble(req.queryMap().get("x").value()),
                Double.parseDouble(req.queryMap().get("y").value())));
        get("/index", (req, res) -> getHtml());
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static boolean isPalindrome(String string) {
        String reversed = new StringBuilder(string).reverse().toString();
        return string.equals(reversed);
    }

    private static double magnitud(double param1, double param2) {
        double sum = Math.pow(param1, 2) + Math.pow(param2, 2);
        return Math.sqrt(sum);
    }

    public static String getHtml() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <title>Bono</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <h1>Bono</h1>\n" +
                "        <h2>Seno</h2>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Num:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"1\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"seno()\">\n" +
                "        </form> \n" +
                "        <div id=\"getSeno\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function seno() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getSeno\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/sin/\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "        <h2>Coseno</h2>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"name\">Num:</label><br>\n" +
                "            <input type=\"text\" id=\"name\" name=\"name\" value=\"0\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"coseno()\">\n" +
                "        </form> \n" +
                "        <div id=\"getCoseno\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function coseno() {\n" +
                "                let nameVar = document.getElementById(\"name\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getCoseno\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/cos/\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "        <h2>Palindromo</h2>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"cadena\">Cadena:</label><br>\n" +
                "            <input type=\"text\" id=\"cadena\" name=\"cadena\" value=\"anna\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"palindrome()\">\n" +
                "        </form> \n" +
                "        <div id=\"getPalindrome\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function palindrome() {\n" +
                "                let nameVar = document.getElementById(\"cadena\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getPalindrome\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/palindrome/\"+nameVar);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "        <h2>Magnitud</h2>\n" +
                "        <form action=\"/hello\">\n" +
                "            <label for=\"x\">X:</label><br>\n" +
                "            <input type=\"text\" id=\"x\" name=\"x\" value=\"2\"><br><br>\n" +
                "            <label for=\"y\">y:</label><br>\n" +
                "            <input type=\"text\" id=\"y\" name=\"y\" value=\"2\"><br><br>\n" +
                "            <input type=\"button\" value=\"Submit\" onclick=\"magnitud()\">\n" +
                "        </form> \n" +
                "        <div id=\"getMag\"></div>\n" +
                "\n" +
                "        <script>\n" +
                "            function magnitud() {\n" +
                "                let xNum = document.getElementById(\"x\").value;\n" +
                "                let yNum = document.getElementById(\"y\").value;\n" +
                "                const xhttp = new XMLHttpRequest();\n" +
                "                xhttp.onload = function() {\n" +
                "                    document.getElementById(\"getMag\").innerHTML =\n" +
                "                    this.responseText;\n" +
                "                }\n" +
                "                xhttp.open(\"GET\", \"/mag?x=\"+xNum+\"&y=\"+yNum);\n" +
                "                xhttp.send();\n" +
                "            }\n" +
                "        </script>\n" +
                "\n" +
                "    </body>\n" +
                "</html>";
    }
}
