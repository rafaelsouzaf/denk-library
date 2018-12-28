# Denk-library

Denk library is a restful crud system to manage a virtual book library. It was made because I want to learn and practicing Spring framework.

At this moment you can do:

  - List/Create/Get/Edit/Delete users.
  - List/Create/Get/Edit/Delete books and author books.
  - Add/Get/Change-Status borrow books.
  - Access restricted interfaces with the correct Role using Basic Auth (see examples).
  - Manipulate the login/logout. (Not yet)
  
Next step:
  
  - Unit tests.
  - Visual interface?!

### Docker (Postgres and PgAdmin4)

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

_** To connect the PgAdmin container with the Postgres container, in the PgAdmin configuration we need to use `host: postgres_container` (the host is the container's name). The `localhost` or `127.0.0.1` does not work because in this case is connections between containers.

#### Running SpringBoot app

DEV:

`mvn spring-boot:run`

QA:

`mvn spring-boot:run -Dspring.profiles.active=qa`

PROD:

`mvn spring-boot:run -Dspring.profiles.active=prod`


### cURL commands

Ar this moment the project does't have graphical interface, so to try it you can use `curl` or Postman [https://www.getpostman.com/].

#### Book
| Action | COMMAND |
| ------ | ------ |
| LIST | `curl -X GET localhost:8080/book/list -H 'Content-type:application/json'` |
| LIST ORDER ASC | `curl -X GET localhost:8080/book/list/order/asc -H 'Content-type:application/json'` |
| LIST ORDER DESC | `curl -X GET localhost:8080/book/list/order/desc -H 'Content-type:application/json'` |
| GET | `curl -X GET localhost:8080/book/get/6 -H 'Content-type:application/json'` |
| GET BY TITLE| `curl -X GET localhost:8080/book/get/title/book%20title -H 'Content-type:application/json'` |
| GET BY EXACT TITLE| `curl -X GET localhost:8080/book/get/title/exact/book%20title%201 -H 'Content-type:application/json'` |
| ADD | `curl -X PUT localhost:8080/book/add -H 'Content-type:application/json' -d '{"title":"Samdwish Book", "bookAuthor": {"firstName":"Joanna", "lastName":"Fun"}}'` |
| EDIT | `curl -X POST localhost:8080/book/edit/6 -H 'Content-type:application/json' -d '{"title":"Samdwish Book Editable", "bookAuthor": {"firstName":"Joanna", "lastName":"Fun"}}'` |
| DELETE | `curl -X DELETE localhost:8080/book/delete/6 -H 'Content-type:application/json'` |

#### Borrow Books
| Action | COMMAND |
| ------ | ------ |
| GET | `curl -X GET localhost:8080/borrow/get/17 -H 'Content-type:application/json'` |
| ADD | `curl -X PUT localhost:8080/borrow/add -H 'Content-type:application/json' -d '{"bookId": 14, "userId": 2, "status": "AVAILABLE"}'` |
| CHANGE STATUS | `curl -X POST localhost:8080/borrow/change-status/17/RENTED -H 'Content-type:application/json'` |

#### User Administration (RESTRICTED, Access only with Basic Auth)

To use the API below it's necessary to add the user and password in the HEAD. Without that the answer will be `Access denied`. See examples:

| Action | COMMAND |
| ------ | ------ |
| LIST | `curl -X GET --user admin:password localhost:8080/user/list -H 'Content-type:application/json'` |
| GET | `curl -X GET --user admin:password localhost:8080/user/get/2 -H 'Content-type:application/json'` |
| ADD |`curl -X PUT --user admin:password localhost:8080/user/add -H 'Content-type:application/json' -d '{"email":"sam@dwish.com", "firstName":"Samdwish","lastName":"Martelo","userRole":"LIBRARIAN","password":"xyxy2"}'` |
| EDIT | `curl -X POST --user admin:password localhost:8080/user/edit/3 -H 'Content-type:application/json' -d '{"email":"sambda@dwish.com", "firstName":"Samdba","lastName":"Pepsi","userRole":"LIBRARIAN","password":"xyxy2"}'` |
| DELETE | `curl -X DELETE --user admin:password localhost:8080/user/delete/2 -H 'Content-type:application/json'` |


#### Login/Logout (Pending... not ready yet)
| Action | COMMAND |
| ------ | ------ |
| LOGIN |  |
| LOGOUT | `curl -X GET http://localhost:8080/login/logout -H 'Content-type:application/json'` |
