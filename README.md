# Denk-library

Denk library is a restfull crud system to manage a virtual book library. It was made by learning and practicing Spring framework.

At this moment you can do:

  - List/Create/Edit/Delete users.
  - List/Create/Edit/Delete books and author books.

### Docker Postgres and PgAdmin4

``
docker-compose up
``

### cURL commands

Ar this moment the project does't have graphical interface, so to try it you can use `curl`.

#### User
| Action | COMMAND |
| ------ | ------ |
| LIST | `curl -X GET localhost:8080/user/list -H 'Content-type:application/json'` |
| GET | `curl -X GET localhost:8080/user/get/2 -H 'Content-type:application/json'` |
| ADD |`curl -X PUT localhost:8080/user/add -H 'Content-type:application/json' -d '{"firstName":"Samdwish","lastName":"Martelo","userRole":"LIBRARIAN","password":"xyxy2"}'` |
| EDIT | `curl -X PUT localhost:8080/user/edit/3 -H 'Content-type:application/json' -d '{"firstName":"Samdba","lastName":"Pepsi","userRole":"LIBRARIAN","password":"xyxy2"}'` |
| DELETE | `curl -X DELETE localhost:8080/user/delete/2 -H 'Content-type:application/json'` |

#### Book
| Action | COMMAND |
| ------ | ------ |
| LIST | `curl -X GET localhost:8080/book/list -H 'Content-type:application/json'` |
| LIST ORDER ASC | `curl -X GET localhost:8080/book/list/order/asc -H 'Content-type:application/json'` |
| LIST ORDER DESC | `curl -X GET localhost:8080/book/list/order/desc -H 'Content-type:application/json'` |
| GET | `curl -X GET localhost:8080/book/get/6 -H 'Content-type:application/json'` |
| ADD | `curl -X PUT localhost:8080/book/add -H 'Content-type:application/json' -d '{"title":"Samdwish Book", "bookAuthor": {"firstName":"Joanna", "lastName":"Fun"}}'` |
| EDIT | `curl -X PUT localhost:8080/book/edit/6 -H 'Content-type:application/json' -d '{"title":"Samdwish Book Editable", "bookAuthor": {"firstName":"Joanna", "lastName":"Fun"}}'` |
| DELETE | `curl -X DELETE localhost:8080/book/delete/6 -H 'Content-type:application/json'` |

#### Login/Logout
| Action | COMMAND |
| ------ | ------ |
| LOGIN | `curl -X GET --user admin:password http://localhost:8080/user/list -H 'Content-type:application/json'` |
| LOGOUT | `curl -X GET http://localhost:8080/login/logout -H 'Content-type:application/json'` |
