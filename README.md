# Workshop Administrator

Sistema completo de administración de taller automotriz (backend + frontend)

---

## Tecnologías principales
- **Backend:** Java 17, Spring Boot, Spring Data JPA, PostgreSQL
- **Frontend:** React, Material UI, Axios

---

## Estructura del proyecto
- `/src/main/java/com/taller` — Código fuente backend (entidades, controladores, servicios)
- `/frontend` — Aplicación React (dashboard administrativo)

---

## Configuración y primer uso
### 1. Base de datos
- Crea una base PostgreSQL llamada `tallerdb`.
- Ajusta usuario/contraseña en `src/main/resources/application.properties` si es necesario.

### 2. Backend (Spring Boot)
```bash
./mvnw spring-boot:run
```
Esto levantará la API REST en `http://localhost:8080`.

> **Carga automática de datos de prueba:**
> Al iniciar, se insertan clientes, vehículos, servicios y órdenes de ejemplo. Puedes remover esto después eliminando la clase `DataLoader`.

### 3. Frontend (React)
```bash
cd frontend
npm install
npm start
```
Esto abre el dashboard en `http://localhost:3000`.

---

## Endpoints principales
- `GET /api/ordenes` — Lista todas las órdenes de trabajo
- `POST /api/ordenes` — Crea una orden
- `GET /api/vehiculos` — Lista vehículos (si implementado)
- `GET /api/clientes` — Lista clientes (si implementado)

---

## Funcionalidades actuales
- Dashboard con tarjetas resumen y tablas de órdenes recientes/pendientes
- CRUD de órdenes de trabajo
- Carga de datos de prueba para desarrollo

---

## Próximos pasos sugeridos
- Agregar gestión completa de vehículos, clientes y servicios en el frontend
- Reportes, filtros y búsqueda avanzada
- Seguridad y autenticación
- Documentar API con Swagger/OpenAPI

---

¡Listo para usar y personalizar según las necesidades de tu taller!
