# Taller 4 AREP
En este taller se utilizo Docker para crear imagenes y usarla en contenedores, además se implemento un pequeño servicio REST con ayuda de Spark.
## Iniciando

### Prerrequisitos

* Git 
* Java
* Maven
* Docker

### Instalando el proyecto

Lo primero será traer del repositorio remoto el proyecto a la máquina local, para esto ejecutamos el siguiente comando por medio de consola.

```
git clone https://github.com/wilmer-rodriguez-r/AREP_Taller05.git
```

Esto creará un directorio nuevo donde accederemos y ejecutaremos el siguiente comando.

```
mvn package
```
Lo anterior fue para traer dependencias y demás que puedan ser necesarios para el proyecto, después de esto ejecutamos el siguiente comando para poder correr el servidor.
```
java -cp "target/classes;target/dependency/*" org.example.SparkWebServer
```
Eso hará que ya estén en ejecución el servidor. Para corroborar esto puedes entrar al siguiente enlace donde está predeterminado el [servidor web](http://localhost:4567/index) o en caso contrario ingresa la siguiente url en tu navegador http://localhost:4567/index.

### Corriendo con Docker

En el siguiente repositorio https://hub.docker.com/repository/docker/wilmerrodriguez/arep_taller05/general, se econtrará la imagen para poder ejecutar el proyecto en Docker.

Para poder ejecutar dicha imagen, ingrese el siguiente comando:

```
docker run -d -p 36000:6000 --name sparkweb wilmerrodriguez/arep_taller05
```

En este caso el servidor web estaria en el siguiente enlace [servidor web](http://localhost:36000/index) o en el siguiente link http://localhost:36000/index.

### Como construir la imagen localmente.

Para poder crear una imagen de docker localmente del proyecto, tendrías que ejecutar el siguiente comando en la raiz del proyecto.

```
docker build --tag imagen .
```

Esto ejecutara algo parecido a lo siguiente.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller05/assets/77862048/e691ce7d-a6f3-4fb6-9a87-eeb269f5821f)

Si quisieras cambiar la configuración de la imagen, en el archivo Dockerfile puedes verla.

### En ejecución.

Cuando inicies el proyecto, podras ver que lo siguiente en el navegador.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller05/assets/77862048/67ea6a62-e470-41aa-b651-0ee06272dec2)

Se puede apreciar 4 funcionalidades, la primera simplemente calcula el seno de un número dado.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller05/assets/77862048/5862e95f-1342-444b-a724-e2cd8ecdc268)

La segunda calcula el coseno del número dado.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller05/assets/77862048/4cfd94b5-c961-4725-b46b-6f5e0bc8cdd2)

La tercera dice si la cadena proporcionada es palíndromo o no.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller05/assets/77862048/b3466903-a8c9-45a0-92cc-1ea73d82930a)

Y la última, cálcula la magnitud de un vector supondiendo que parte del origen.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller05/assets/77862048/18f44e1f-46e4-4105-b84c-a08a70152bf7)

## Diseño

Este proyecto solo tiene un servidor que provee tanto la vista, es decir los html y el js, y los endpoints o servicios rest.
## Construido con

* [Maven](https://maven.apache.org/) - Administrador de dependencias

## Version

1.0-SNAPSHOT

## Autores

Wilmer Arley Rodríguez Ropero - [wilmer-rodriguez-r](https://github.com/wilmer-rodriguez-r)

## Licencia

GNU General Public License family

## Agradecimientos

* Luis Daniel Benavides Navarro

## Referencias

[1] 	B. N. L. Daniel, "Introducción a esquemas de nombres, redes, clientes y servicios con Java," p. 18, 20 Agosto 2020.
