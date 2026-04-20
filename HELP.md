# 🧾 Order Management API

API REST desarrollada con Spring Boot para la gestión de productos y compras, implementando autenticación segura con JWT, Refresh Tokens y arquitectura limpia.
___

## 🚀 Tecnologías

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- MySQL
- JPA / Hibernate
- Docker & Docker Compose
- Arquitectura Hexagonal

___

## 🧠 Características principales

- ✅ Autenticación con JWT
- ✅ Refresh Token con rotación
- ✅ Control de roles (`ROLE_ADMIN`, `ROLE_USER`)
- ✅ Manejo global de excepciones
___

## 🏗️ Estructura del proyecto

```bash
├── application
│   ├── dto
│   └── usecase
├── domain
│   ├── model
│   └── repository
├── infrastructure
│   ├── config
│   ├── persistence
│   ├── security
│   └── util
└── web
    ├── controller
    └── exception
```
___

## 🔐 Autenticación

La API utiliza:

- Access Token (expira en 1 hora)
- Refresh Token (persistido en base de datos)

```bash
Login → Access Token + Refresh Token
Access Token → usado en requests
Refresh Token → genera nuevos tokens
Rotación → invalida el anterior
```
___
## 🧪 Endpoints
### 🔑 Auth

| Método | Endpoint       | Descripción   |
| ------ | -------------- | ------------- |
| POST   | /auth/register | Registro      |
| POST   | /auth/login    | Login         |
| POST   | /auth/refresh  | Renovar token |
| POST   | /auth/logout   | Logout        |

### 📦 Productos

| Método | Endpoint       | Rol   |
| ------ | -------------- | ----- |
| GET    | /products      | USER  |
| GET    | /products/{id} | USER  |
| POST   | /products      | ADMIN |
| PUT    | /products/{id} | ADMIN |
| DELETE | /products/{id} | ADMIN |

## 🐳 Ejecución con Docker
1. Construir el proyecto
``mvn clean package -DskipTests``
2. Levantar contenedores
``docker-compose up --build``
3. Acceder a la API
``http://localhost:8080``

## 🔐 Seguridad
- 🔒 Passwords encriptadas con BCrypt
- 🛡️ Validación de JWT con filtro personalizado
- 🚫 Manejo de errores centralizado
- 🔄 Revocación de refresh tokens


