# 🐷 Piggy Store API

Este proyecto es una API RESTful desarrollada en **Spring Boot**, orientada a manejar productos y categorías para una tienda simple. Permite operaciones CRUD básicas y consultas avanzadas por nombre y precio.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- PostgreSQL
- Gradle

---

## 📂 Estructura principal

- `controller/`
  - `ProductController.java`  
    Endpoints para gestionar productos y sus consultas.
  - `CategoryController.java`  
    

- `service/`
  - `ProductService.java`  
    Lógica de negocio para productos.
  - `CategoryService.java`  
    Lógica de negocio para categorías.

- `repository/`
  - `ProductRepository.java`
  - `CategoryRepository.java`

- `dto/request/`
  - `ProductPostDto.java`
- `dto/response/`
  - `ProductDtoRes.java`
  - `CategoryDtoRes.java`
  - `ApiResponse.java`

- `domain/`
  - `Product.java`
  - `Category.java`

---

## 🔥 Endpoints principales

### 📦 Productos

| Método | Endpoint                     | Descripción                                     |
|--------|------------------------------|-------------------------------------------------|
| GET    | `/product`                   | Lista productos paginados. Soporta `page`, `size`, `sort`. |
| POST   | `/product/create`            | Crea un nuevo producto (requiere JSON).         |
| GET    | `/product/id/{id}`           | Busca un producto por ID.                       |
| GET    | `/product/name/{name}`       | Busca productos por nombre (LIKE).              |
| GET    | `/product/price/{min}/{max}` | Busca productos entre precios `min` y `max`.    |

#### Ejemplo para paginación:
GET /product?page=0&size=5&sort=name,asc


#### Ejemplo para búsqueda por precio:
GET /product/price/10/50

---

### 🏷️ Categorías

| Método | Endpoint                          | Descripción                                    |
|--------|-----------------------------------|------------------------------------------------|
| GET    | `/category`                       | Lista categorías paginadas.                    |
| GET    | `/category/id/{id}`               | Obtiene categoría por ID.                      |
| GET    | `/category/name/{name}`           | Busca categoría por nombre.                    |
| GET    | `/category/{category}/products`   | Devuelve productos con datos de categoría.     |

---

## ⚙ Ejemplo de cuerpo JSON para crear un producto

POST /product/create
Content-Type: application/json

{
"id": null,
"name": "Escoba",
"price": 5.0,
"description": "Nro 12, cerda gruesa multicolor",
"quantity": 30,
"categoryId": 3
}

---

## 📈 Respuesta estándar (`ApiResponse`)

Todos los endpoints que devuelven datos estructurados usan un JSON similar:

```json
{
  "success": true,
  "message": "Exito",
  "code": "SUCCESS",
  "result": [
    {
      "categoryId": 1,
      "categoryName": "Hogar",
      "categoryType": "Limpieza",
      "id": 10,
      "name": "Escoba",
      "price": 5.0,
      "description": "Nro 12, cerda gruesa multicolor",
      "quantity": 30
    }
  ],
  "errors": []
}
💡 Notas de desarrollo
El proyecto usa record en Java para DTOs, haciendo el código más limpio y seguro.

Paginación automática gracias a Pageable.

El ApiResponse generaliza el formato para consistencia.

Ejemplo de consulta JPQL con DTO:

@Query("SELECT new com.anuar.piggy_store.dto.response.ProductDtoRes(" +
    "c.id, c.name, c.type, p.id, p.name, p.price, p.description, p.quantity) " +
    "FROM Category c JOIN c.products p " +
    "WHERE LOWER(c.name) = LOWER(:category)")
List<ProductDtoRes> findByCategoryWithProducts(@Param("category") String category);

🚀 Cómo levantar el proyecto
Clona el repo:

git clone https://github.com/tu-usuario/piggy-store.git
cd piggy-store
Configura tu application.properties con tu base de datos.

Ejecuta:

./gradlew bootRun