# Proyecto Integrador 2 - JPA + Maven + MySQL

## Descripción

Este proyecto es una aplicación Java desarrollada con **Maven** que utiliza **JPA (Hibernate)** para la persistencia de datos en una base de datos **MySQL**.

Incluye carga inicial de datos desde archivos CSV y consultas sobre estudiantes, carreras e inscripciones.

---

## Requisitos previos

Antes de ejecutar el proyecto, asegurarse de tener instalado:

* Java 21
* Maven
* Docker y Docker Compose
* IntelliJ IDEA (recomendado)

---

##  Cómo ejecutar el proyecto

### 1. Levantar la base de datos con Docker

El proyecto incluye un archivo `docker-compose.yml` que levanta una base de datos MySQL.

Tenés dos opciones:

#### ✔ Opción A: Desde IntelliJ (recomendada)

1. Abrir el archivo `docker-compose.yml`
2. Hacer clic en el botón (flecha verde) que aparece en el margen izquierdo
3. IntelliJ ejecutará automáticamente el contenedor

#### ✔ Opción B: Desde consola

Ubicado en la raíz del proyecto, ejecutar:

```bash
docker-compose up -d
```

Esto creará un contenedor MySQL en el puerto `3306` con:

* Base de datos: `integrador2`
* Usuario: `root`
* Contraseña: *(vacía)*

---

### 2. Configuración de la base de datos

La configuración se encuentra en `persistence.xml`:

* URL: `jdbc:mysql://localhost:3306/integrador2`
* Usuario: `root`
* Contraseña: *(vacía)*

Además:

* `hibernate.hbm2ddl.auto = create-drop`
   La base se crea automáticamente al iniciar y se elimina al finalizar.

---

### 3. Importar el proyecto en IntelliJ

1. Abrir IntelliJ
2. Seleccionar **"Open"**
3. Elegir la carpeta del proyecto
4. IntelliJ detectará automáticamente que es un proyecto Maven
5. Esperar a que descargue las dependencias

---

### 4. Ejecutar la aplicación

1. Ir a la clase `Main`
2. Ejecutar el método `main`

---

## Funcionalidades principales

* Carga de datos desde CSV:

    * `estudiantes.csv`
    * `carreras.csv`
    * `estudianteCarrera.csv`

* Operaciones disponibles:

    * Alta de estudiantes, carreras e inscripciones
    * Consultas ordenadas por:

        * DNI
        * Género
        * Cantidad de inscriptos
    * Búsquedas filtradas (por ciudad, carrera, etc.)
    * Generación de reportes

---

## Estructura del proyecto

* `modelo/` → Entidades JPA
* `repository/` → Acceso a datos
* `dto/` → Objetos de transferencia
* `factory/JPAUtil` → Manejo del EntityManager
* `Main.java` → Punto de entrada

---

## Notas importantes

* El proyecto utiliza **Hibernate como implementación de JPA**
* La base de datos se reinicia en cada ejecución debido a `create-drop`
* Asegurarse de que el contenedor Docker esté corriendo antes de ejecutar el programa
* Los archivos CSV deben estar en la ruta:

  ```
  src/main/resources/
  ```

---

## Ejemplo de ejecución

Al correr el `main`, el sistema:

1. Carga datos desde CSV
2. Inserta nuevas entidades
3. Realiza consultas y muestra resultados por consola

---

## Tecnologías utilizadas

* Java 21
* Maven
* JPA (Jakarta Persistence)
* Hibernate
* MySQL
* Docker

---

## Autores

Proyecto realizado como trabajo práctico universitario por: **Bordonaro Matias, Di Fiore Fiorella, Machado Foss Daniel Alejandro**
