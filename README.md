# Java and Database connectivity
This is a monorepo project that demonstrates different ways to work with databases using java. Technoloies include:
- Java JDBC
- Spring JdbcTemplate
- Spring Data JDBC
- SPring Data JPA

### How to start local database
Setting up a local database might be cumbersome so we simplified the process by using Docker and MySQL container.
All you have to do is to have Docker installed on your computer and run the following command while you are in the root forlder of the repository:
`docker-compose up -d`
This will start MySQL database and you can connect to it using your favourite tool (DBeaver, HeidiSQL, etc.)

### How to start each sub-project
All sub-projects are Maven Spring Boot console applications without any web features. Because of this you have to have java 17 and maven 3 installed on your computer.
In order to start the projects:
1. Navigate to respective folder: `cd ./java-jdbc`
2. Download dependencies and compile the project by execute: `mvn clean install`
3. Run the project from the console or your favourite IDE