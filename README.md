# Taller 7 AREP
En este taller es nos pide crear una arquitectura segura, donde se garantice autenticación, autorización e integridad de los servicios.
## Iniciando
Antes de ejecutar el proyecto es necesario poseer las siguiente herramientas.
### Prerrequisitos

* Git 
* Java
* Maven

### Instalando el proyecto

Lo primero será traer del repositorio remoto el proyecto a la máquina local, para esto ejecutamos el siguiente comando por medio de consola.

```
git clone https://github.com/wilmer-rodriguez-r/AREP_Taller07.git
```

Esto creará un directorio nuevo donde accederemos y ejecutaremos el siguiente comando.

```
mvn package
```
Lo anterior fue para traer dependencias y demás que puedan ser necesarios para el proyecto.

Como en este proyecto se quiere comunicar dos maquinas distintas y de manera segura, ejecutaremos dos maquinas virtuales de java con los siguientes comandos. 
```
java -cp "target/classes:target/dependency/*" org.example.login.SecureLogin
```

```
java -cp "target/classes:target/dependency/*" org.example.helloService.SecureHello
```

Con esto ya estara corriendo ambos servicios de manera local.

### Corriendo Localmente

Despues de ejecutar los anteriores comandos, podremos acceder al siguiente enlace https://localhost:5000, el cual nos mostrara lo siguiente.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller07/assets/77862048/0a4bae20-df81-453b-990f-eaead6ff96c4)

Como podemos ver nos pide un usuario y una contraseña, si intentamos ingresar con usuario que no existe nos mostrara lo siguiente.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller07/assets/77862048/5bd1f399-9f85-4c95-8176-15916a98782e)

En el código del proyecto se quemaron unos usuarios por defecto.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller07/assets/77862048/badee310-6234-4e93-9168-fed47cb9bd70)

Si ingresamos con alguno de estos usuarios al login, nos aparecera lo siguiente.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller07/assets/77862048/ac2607e2-9af2-4cb4-a8b2-7af0c09db9ec)


![image](https://github.com/wilmer-rodriguez-r/AREP_Taller07/assets/77862048/29db4c84-f3e5-49f4-851b-5384c3505c7a)

En el primer servicio, practicamente es que nos salude la maquina uno, es decir la que actua de fachada en este caso que seria el login. El segundo servicio es que no salude la maquina remota a la cual nos conectamos de manera segura.

Saludando a la maquina del login.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller07/assets/77862048/24fe7c6c-374b-476b-bcf6-dc3565056527)

Saludando a la maquina remota.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller07/assets/77862048/0d1ffad7-c2a3-4bce-83da-13bf70afaa59)

Con esto vemos que nuestras maquinas se comunican y lo hacen de una manera segura.

### Corriendo AWS

Para correr en AWS hemos creado dos instacias para poder ejecutar cada servicio por aparte.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller07/assets/77862048/8a06f60e-9af1-4544-aa12-5bc375dfdbc5)

A continuación se muestra un video con el funcionamiento de ambas maquinas.


https://github.com/wilmer-rodriguez-r/AREP_Taller07/assets/77862048/61149d13-3d6f-4e98-ae6d-f2fffcbf7d7e


## Arquitectura

La arquitectura que se realizo fue la siguiente.

![image](https://github.com/wilmer-rodriguez-r/AREP_Taller07/assets/77862048/5bb6e865-17e9-401f-bf84-427fb026a1c5)

Como podemos observar la primera maquina es la que posee el servicio de login, mientras que la otra posee uno que queramos, en este caso es el "helloService". Cada comunicación tanto entre las maquinos como con el cliente se hace con http + ssl, esto lo logramos con ayuda de los certificados para que cada servicio expuesto este seguro.

## Extensibilidad

A la hora de agregar nuevos servicios simplemente deberiamos actualizar nuestro certificados de confianza para que estos puedan acceder a las distintas direcciones y no nos genere inconvenientes.

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
