# Management Bounded Context

Este bounded context maneja las operaciones de inventario y gestión de recursos del sistema CafeLab.

## Estructura del Bounded Context

### Domain Layer
- **Aggregates**: `InventoryEntry` - Entidad principal para gestionar entradas de inventario
- **Value Objects**: `FinalProduct` - Representa el producto final generado
- **Commands**: Comandos para crear, actualizar y eliminar entradas de inventario
- **Services**: Interfaces y servicios de dominio para comandos y consultas

### Application Layer
- **Internal**: Implementaciones de los servicios de aplicación
- **ACL**: Facade para comunicación entre bounded contexts

### Infrastructure Layer
- **Persistence**: Repositorios JPA para persistencia de datos

### Interfaces Layer
- **REST**: Controladores REST para exponer la API
- **Resources**: DTOs para la comunicación con el frontend
- **Transform**: Ensambladores para convertir entre recursos y comandos

## Endpoints REST

### Inventory Entries
- `POST /api/v1/inventory-entries` - Crear nueva entrada de inventario
- `GET /api/v1/inventory-entries` - Obtener todas las entradas
- `GET /api/v1/inventory-entries/{id}` - Obtener entrada por ID
- `GET /api/v1/inventory-entries/user/{userId}` - Obtener entradas por usuario
- `GET /api/v1/inventory-entries/coffee-lot/{coffeeLotId}` - Obtener entradas por lote de café
- `PUT /api/v1/inventory-entries/{id}` - Actualizar entrada de inventario
- `DELETE /api/v1/inventory-entries/{id}` - Eliminar entrada de inventario

## Modelo de Datos

### InventoryEntry
- `id`: Identificador único
- `userId`: ID del usuario propietario
- `coffeeLotId`: ID del lote de café utilizado
- `quantityUsed`: Cantidad utilizada
- `dateUsed`: Fecha de uso
- `finalProduct`: Producto final generado
- `createdAt`: Fecha de creación
- `updatedAt`: Fecha de última actualización

## Validaciones

- Todos los campos son obligatorios
- `quantityUsed` debe ser mayor a 0
- `finalProduct` no puede exceder 100 caracteres
- `userId` y `coffeeLotId` deben ser IDs válidos existentes

## Dependencias

- Spring Boot
- Spring Data JPA
- Spring Web
- Lombok
- Swagger/OpenAPI 