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
git clone https://github.com/wilmer-rodriguez-r/AREP_Taller04.git
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
