# Nano Todo List API


This is a **Todo List Backend API** built with:  

- **Java Spring Boot** (RESTful API)  
- **Gradle** (build tool)  
- **H2 Database** (embedded, in-memory)  

### Key Features:  
- **CRUD Operations** (Create, Read, Update, Delete todos)  
-  **Validation** (e.g., title cannot be blank)  
-  **Automatic Timestamps** (`createdAt`, `updatedAt`)  
-  **Error Handling** (e.g., 404 if todo not found)  

### No Frontend – Pure API!  
Use tools like **Postman**, `curl`, or connect a frontend later.  

**Example Request:**  
```bash
curl -X POST -H "Content-Type: application/json" -d '{"title":"Learn Gradle","description":"Install Gradle 8.1.3"}' http://localhost:8080/api/todos
```

**Purpose**: Learning backend development with Spring Boot & Gradle. 
