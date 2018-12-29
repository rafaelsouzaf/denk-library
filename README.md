# Denk-library

Denk-Library is a RESTful CRUD web service to manage a book library. This is not a production 
project and it's not complete. It's not my goal here to make a _real_ software. This software was made 
just to learn and to practice Spring framework technologies.

At this moment you can do:

  - Manage users.
  - Manage books and author's.
  - Borrow books.
  - Access restricted interfaces using Basic Auth.
  
Next step:
  
  - Unit tests.

### Docker (Postgres and PgAdmin4)

This project are using the Postgres database and the PgAdmin4 client. Both are running in 
docker containers that is possible to start using the follow command:

``
docker-compose up
``

#### PgAdmin4
- Link: http://localhost:5050/browser/
- PgAdmin4 User: pgadmin4@pgadmin.org
- PgAdmin4 Password: admin

#### Postgres DB 
- Host: postgres_container (Docker's hostname)
- Database: postgres
- User: postgres
- Pass: admin

_** To connect the PgAdmin container with the Postgres container, in the PgAdmin configuration we need to 
use `host: postgres_container` (the host is the container's name). The `localhost` or `127.0.0.1` does 
not work because in this case is connections between containers._

#### Running SpringBoot app

DEV:

`mvn spring-boot:run`

QA:

`mvn spring-boot:run -Dspring.profiles.active=qa`

PROD:

`mvn spring-boot:run -Dspring.profiles.active=prod`

### Graphical Interface

It's not the objective of this project create a visual interface to manage the data. I have 
been working with web interfaces for many years and it's not a challenge for me. My main goal 
with this project is to practice Spring Boot framework.

### cURL commands

To manage the web service we can use `curl` or Postman [https://www.getpostman.com/].

### Access control

For now I am using _Basic Auth_ to control the access of the web services, with `@Secured` and 
`@PreAuthorize` annotations. The list of roles are:

| ROLE          | USERNAME              | PASSWORD
| ------        | ------                | ------
| Admin         | admin@gmail.com       | admin
| Librarian     | librarian@gmail.com   | librarian
| Visitor       | visitor@gmail.com     | visitor
| Anonymous     | -                     | -

It's possible to add or edit the roles using the cURL commands. The list of interfaces with their respective 
access control are in the table below.

#### Book
| ACTION                | ROLES             | cURL EXAMPLE
| ------                | ------            | ------
| LIST                  | -                 | `curl -X GET      localhost:8080/book/list`
| LIST-ORDER-ASC        | -                 | `curl -X GET      localhost:8080/book/list/order/asc`
| LIST-ORDER-DESC       | -                 | `curl -X GET      localhost:8080/book/list/order/desc`
| GET                   | -                 | `curl -X GET      localhost:8080/book/get/6`
| GET-BY-TITLE          | -                 | `curl -X GET      localhost:8080/book/get/title/book%20title`
| GET-BY-EXACT-TITLE    | -                 | `curl -X GET      localhost:8080/book/get/title/exact/book%20title%201`
| ADD                   | Admin,Librarian   | `curl -X PUT      --user librarian@gmail.com:librarian    localhost:8080/book/add         -H 'Content-type:application/json'      -d '{"title":"Samdwish Book","bookAuthor":[{"firstName":"Joanna", "lastName":"Fun"}]}'`
| EDIT                  | Admin,Librarian   | `curl -X POST     --user librarian@gmail.com:librarian    localhost:8080/book/edit/6      -H 'Content-type:application/json'      -d '{"title":"Samdwish Book Edited"}'`
| DELETE                | Admin,Librarian   | `curl -X DELETE   --user librarian@gmail.com:librarian    localhost:8080/book/delete/6`

#### Borrow Books

| ACTION                | ROLES                       | cURL EXAMPLE
| ------                | ------                      | ------
| GET                   | Admin,Librarian,Visitor     | `curl -X GET      --user visitor@gmail.com:visitor    localhost:8080/borrow/get/1`
| ADD                   | Admin,Librarian,Visitor     | `curl -X PUT      --user visitor@gmail.com:visitor    localhost:8080/borrow/add       -H 'Content-type:application/json'      -d '{"bookId":5,"userId":5,"status":"AVAILABLE"}'`
| CHANGE-STATUS         | Admin,Librarian,Visitor     | `curl -X POST     --user visitor@gmail.com:visitor    localhost:8080/borrow/change-status/1/RENTED`

#### User Administration

| ACTION                | ROLES             | cURL EXAMPLE
| ------                | ------            | ------
| LIST                  | Admin,Librarian   | `curl -X GET      --user admin@gmail.com:admin    localhost:8080/user/list`
| GET                   | Admin,Librarian   | `curl -X GET      --user admin@gmail.com:admin    localhost:8080/user/get/2`
| ADD                   | Admin             | `curl -X PUT      --user admin@gmail.com:admin    localhost:8080/user/add         -H 'Content-type:application/json'      -d '{"email":"sam@dwish.com","firstName":"Samdwish","lastName":"Martelo","userRole":"ROLE_LIBRARIAN","password":"xyxy2","enabled":true}'`
| EDIT                  | Admin             | `curl -X POST     --user admin@gmail.com:admin    localhost:8080/user/edit/3      -H 'Content-type:application/json'      -d '{"email":"sambda@dwish.com","firstName":"Samdba","lastName":"Pepsi","userRole":"ROLE_LIBRARIAN","password":"xyxy2","enabled":true}'`
| DELETE                | Admin             | `curl -X DELETE   --user admin@gmail.com:admin    localhost:8080/user/delete/2`
