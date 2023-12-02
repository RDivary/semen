# Report Penjualan Generation Application in CSV Format using Spring Boot

This application is a simple Spring Boot application with the primary purpose of generating reports penjualan in CSV format upon startup and automatically shutting down the service once the report generation process is completed.

## Prerequisites

```
- Maven
- Java 21
- PostgreSql
```
## Configuration

Ensure to fill in the required configurations in the `application.properties` file before running the application. Configurations that need to be adjusted include database or data source configuration necessary for the report penjualan generation process.

Example `application.properties` configuration:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:5432/your_database_name
spring.datasource.username=your_database_username
spring.datasource.password=your_database_password

spring.jpa.hibernate.ddl-auto=none

max-get-data-per-batch=1000

file-path-csv="/csv//%s.csv"