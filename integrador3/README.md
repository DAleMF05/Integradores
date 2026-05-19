# Integrador3

Proyecto universitario desarrollado con Spring Boot, Maven, JPA y MySQL.
La aplicación expone una API REST para la gestión de estudiantes, carreras e inscripciones.

## Requisitos

Antes de ejecutar el proyecto, asegurarse de tener instalado:

* Java 21
* Maven
* Docker y Docker Compose
* IntelliJ IDEA (recomendado)

---

# Puesta en marcha

## 1. Levantar la base de datos

El proyecto utiliza MySQL dentro de un contenedor Docker.

Existen dos formas de ejecutarlo.

### Opción A: Desde consola

Ubicarse en la raíz del proyecto y ejecutar:

```bash
docker-compose up -d
```

Esto crea:

* Un contenedor MySQL
* La base de datos `integrador3`
* El servicio en el puerto `3306`

### Opción B: Desde IntelliJ IDEA

* Abrir el archivo `docker-compose.yml`
* IntelliJ detectará automáticamente el servicio Docker
* Presionar el botón con la flecha verde (Run) que aparece junto al archivo o servicio

---

## 2. Configuración de la conexión

La aplicación ya se encuentra configurada para conectarse a:

* Host: `localhost`
* Puerto: `3306`
* Base de datos: `integrador3`
* Usuario: `root`
* Contraseña: vacía

Configuración ubicada en:

```properties
src/main/resources/application.properties
```

---

## 3. Ejecutar el proyecto

### Desde IntelliJ IDEA

* Abrir el proyecto como proyecto Maven
* Esperar la descarga de dependencias
* Ejecutar la clase:

```text
Integrador3Application
```

### Desde consola

```bash
mvn spring-boot:run
```

---

# Funcionalidades principales

La aplicación permite:

* Gestionar estudiantes
* Gestionar carreras
* Registrar inscripciones
* Consultar estudiantes por género
* Consultar estudiantes por libreta universitaria
* Consultar estudiantes por carrera y ciudad
* Obtener carreras ordenadas por cantidad de inscriptos
* Generar reportes de carreras

---

# Tecnologías utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Maven
* MySQL
* Docker
* Lombok

---

# Estructura general del proyecto

* `controller/` → Endpoints REST
* `service/` → Lógica de negocio
* `repository/` → Acceso a datos
* `entities/` → Entidades JPA
* `dto/` → Objetos de transferencia
* `utils/` → Carga de datos iniciales

---

# Notas

* La base de datos debe estar levantada antes de iniciar la aplicación
* Hibernate genera las tablas automáticamente
* Los datos CSV se cargan al iniciar el proyecto
* El puerto por defecto de la API es `8080`

---

# Autores

Proyecto realizado como trabajo práctico universitario por: **Bordonaro Matias, Di Fiore Fiorella, Machado Foss Daniel Alejandro**

# Endpoints REST

## Estudiantes

### Obtener todos los estudiantes

```http
GET http://localhost:8080/estudiantes
```

---

### Obtener estudiante por ID

```http
GET http://localhost:8080/estudiantes/1
```

---

### Obtener estudiante por libreta universitaria

```http
GET http://localhost:8080/estudiantes/lu/12345
```

---

### Obtener estudiantes por género

```http
GET http://localhost:8080/estudiantes/genero/M
```

---

### Obtener estudiantes por carrera y ciudad

```http
GET http://localhost:8080/estudiantes/carrera/Sistemas?ciudad=Tandil
```

---

### Crear estudiante

```http
POST http://localhost:8080/estudiantes
Content-Type: application/json
```

```json
{
  "nombre": "Juan",
  "apellido": "Perez",
  "edad": 22,
  "genero": "M",
  "dni": 40111222,
  "ciudad": "Tandil",
  "lu": "12345"
}
```

---

## Carreras

### Obtener todas las carreras

```http
GET http://localhost:8080/carreras
```

---

### Obtener carrera por ID

```http
GET http://localhost:8080/carreras/1
```

---

### Obtener carreras ordenadas por inscriptos

```http
GET http://localhost:8080/carreras/inscriptos
```

---

### Generar reporte de carreras

```http
GET http://localhost:8080/carreras/reporte
```

---

### Crear carrera

```http
POST http://localhost:8080/carreras
Content-Type: application/json
```

```json
{
  "nombre": "Ingenieria en Sistemas"
}
```

---

## Inscripciones

### Obtener todas las inscripciones

```http
GET http://localhost:8080/inscripciones
```

---

### Matricular estudiante

```http
POST http://localhost:8080/inscripciones/matricular
Content-Type: application/json
```

```json
{
  "estudianteId": 1,
  "carreraId": 1,
  "antiguedad": 2024,
  "graduado": false
}
```
