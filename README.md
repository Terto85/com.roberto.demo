# com.roberto.demo
El proyecto puede abrirse con cualquier IDE, importándolo con Maven, y con acceso a los repositorios públicos de maven central. 

Utilizar jdk-23 para compilarlo, comprobar también el jdk utilizado por maven. Preferiblemente openjdk-23. 

La aplicación tiene los test unitarios y de integración. 

Una vez compilado, crear una operacion de ejecución de Spring para la clase:
com.roberto.demo.application.DemoApplication

Si se prefiere, se puede ejecutar la siguiente orden en el target tras haber compilado el proyecto:
PATH_TO_JDK-23\bin\java" -jar demo-0.0.1-SNAPSHOT.jar com.roberto.demo.application.DemoApplication

Si se dispone de entorno docker, también puede ejecutarse mediante el script batch 
demo/dockerRun.bat, que levantará directamente el contenedor en ejecución

Esto arrancará la aplicación. 

Con la aplicación arrancada, para probarla, podemos encontrar 2 ficheros de test de la aplicación e2e 
demo/apitest/httpclient contiene las pruebas en formato httpclient de IntelliJ 
demo/apitest/postman contiene las pruebas en formato postman, puede importarse directamente el json en el postman como una nueva colección, y se tendrá el juego de pruebas con test de los resultados. 
