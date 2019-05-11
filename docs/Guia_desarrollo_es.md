# Guia de desarrollo

## Desarrollo

**Java** (Openjdk 1.8.0_191)

Aplicación programada en java8

**Base de datos** (10.1.38-MariaDB )

Para poder realizar tests en local es necesario crear el usuario "user" con contraseña "pass".
Estas credenciales las utilizará la tarea de gradle createDatabase para crear la estructura e insertar unos datos mínimos para realizar los tests.

**Gradle** (4.4.1)

Posiblemente funcione con versiones posteriores. 
En CI se crea un wrapper con la versión 4.4.1.

**Intellij** (ideaIC-2018.2.5 Community Edition)

Para programar es necesario también añadir el plugin: IntelliJ Lombok plugin

Para poder trabajar con lombok es necesario habilitar el preprocesado de anotaciones, el cual se encuentra en:

*File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors > Enable annotation processing*

**Sonarqube** (7.7 Community Edition)

Configuración por defecto

**Sistema operativo**

El proyecto se ha probado en el sistema operativo linux Mint. Es posible que haya que realizar cambios para que todo funcione en Windows, como por ejemplo en el pipeline el cual actualmente usa sh.

## Contribuciones

Para realizar cualquier contribución al proyecto, se debe crear una issue si no existe ya.

Dependiendo de los permisos del usuario en el proyecto:

- Si no se tienen permisos de escritura, se trabajará mediante [ForkFlow](https://es.atlassian.com/git/tutorials/comparing-workflows/forking-workflow)
- Si se tienen permisos de escritura, se trabajará mediante [GitFlow](https://es.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)

Mejores prácticas: 

- Intentar no mergear PRs sin la aprobación del resto de revisores
- No mergear un build fallido
- No realizar cambios directamente sobre las ramas master o develop

## Versionado

Versionado basado en [SemVer](https://semver.org/lang/es/).

Las versiones constarán de 3 números: Major.Minor.Patch con la siguiente semántica:

- Mayor: Cambios no compatibles con versiones anteriores
- Minor: Nuevas funcionalidades compatibles con versiones anteriores
- Patch: Correcciones a fallos encontrados

En las ramas que no sean master, se mantendrá el sufijo -SNAPSHOT tras el número de versión.

Ej: Ranking-1.0.3-SNAPSHOT

## Guia de estilo

Configuración por defecto de Sonarqube
