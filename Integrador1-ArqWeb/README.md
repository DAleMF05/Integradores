# Integrador1-ArqWeb

Proyecto universitario desarrollado en Java utilizando **Maven**, **JDBC** y **MySQL**, ejecutado desde IntelliJ IDEA.

## Requisitos

Antes de ejecutar el proyecto, asegurarse de tener instalado:

* Java 21
* Maven
* Docker y Docker Compose
* IntelliJ IDEA (opcional pero recomendado)

---

## Puesta en marcha

### 1. Levantar la base de datos

El proyecto utiliza MySQL dentro de un contenedor Docker.

Tenés dos formas de hacerlo:

#### Opción A: Desde consola

Desde la raíz del proyecto, ejecutar:

```bash
docker-compose up -d
```

Esto va a:

* Crear un contenedor MySQL
* Crear la base de datos `integrador1`
* Exponer el puerto `3306`

#### Opción B: Desde IntelliJ IDEA

* Abrir el archivo `docker-compose.yml`
* IntelliJ detecta automáticamente el servicio
* Hacer click en el botón de **flecha verde (Run)** que aparece al lado del archivo o del servicio

Esto ejecuta el `docker-compose` directamente desde el IDE.

---

### 2. Configuración de la conexión

El proyecto ya está preparado para conectarse a:

* Host: `localhost`
* Puerto: `3306`
* Base de datos: `integrador1`
* Usuario: `root`
* Password: *(vacío)*

Si cambiás algo en el `docker-compose.yml`, tenés que reflejarlo en `HelperMySQL` y en `MySQLDAOFactory`.

---

### 3. Ejecutar el proyecto

Tenés dos opciones:

#### Desde IntelliJ

* Abrir el proyecto como proyecto Maven
* Esperar a que descargue dependencias
* Ejecutar la clase `Main`

#### Desde consola

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="org.example.Main"
```

---

## ¿Qué hace el programa?

Al ejecutarse, el sistema:

1. Elimina las tablas existentes (si hay)
2. Crea las tablas necesarias
3. Carga datos desde archivos CSV
4. Ejecuta consultas de prueba usando DAOs:

* Buscar cliente por ID
* Obtener el producto con mayor recaudación
* Listar clientes ordenados por facturación

---

## Tecnologías utilizadas

* Java 21
* Maven
* JDBC
* MySQL
* Docker
* Lombok
* Apache Commons CSV

---

## Estructura general

* `dao/` → Acceso a datos
* `dto/` → Objetos de transferencia
* `entities/` → Entidades del modelo
* `factory/` → Implementación de Abstract Factory
* `utils/` → Helpers (conexión, carga de datos)
* `Main.java` → Punto de entrada

---

## Notas

* Es necesario que el contenedor de MySQL esté corriendo antes de ejecutar el proyecto
* Si el puerto 3306 está en uso, cambiarlo en el `docker-compose.yml`
* Los datos se cargan automáticamente desde CSV al iniciar

---

## Ejecución de prueba

Al correr el programa, deberías ver en consola:

* Un cliente buscado por ID
* El producto con mayor recaudación
* Una lista de clientes ordenados por facturación

---

## Autores

Proyecto realizado como trabajo práctico universitario por:
Matias Bordonaro, Fiorella Di Fiore, Alejandro Machado.
