# Workshop Administrator Backend

Backend para la administración de un taller de laminado y pintura, construido con Spring Boot y PostgreSQL.

## Estructura del proyecto
- **Cliente**: CRUD básico como ejemplo inicial.
- **Tecnologías**: Java 17, Spring Boot, Spring Data JPA, PostgreSQL.

## Configuración
1. Crea una base de datos en PostgreSQL llamada `tallerdb`.
2. Modifica el usuario y contraseña en `src/main/resources/application.properties` si es necesario.
3. Ejecuta el proyecto con:
   ```bash
   ./mvnw spring-boot:run
   ```

## Endpoints de ejemplo
- `GET /api/clientes` — Lista todos los clientes
- `POST /api/clientes` — Crea un cliente
- `GET /api/clientes/{id}` — Obtiene un cliente por ID
- `PUT /api/clientes/{id}` — Actualiza un cliente
- `DELETE /api/clientes/{id}` — Elimina un cliente

## Próximos pasos
- Agregar entidades: Vehículo, Orden de trabajo, Servicio
- Implementar autenticación y autorización
- Documentar API con Swagger

---

¡Listo para conectar con tu frontend en React!
