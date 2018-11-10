# denk-library

LIST USERS

`curl -X GET localhost:8080/user/list -H 'Content-type:application/json'`

GET SPECIFIC USER

`curl -X GET localhost:8080/user/get/2 -H 'Content-type:application/json'`

ADD NEW USER

`curl -X PUT localhost:8080/user/add -H 'Content-type:application/json' -d '{"firstName":"Samdwish","lastName":"Martelo","userRole":"LIBRARIAN","password":"xyxy2"}'`

EDIT EXISTENT USER

`curl -X PUT localhost:8080/user/edit/3 -H 'Content-type:application/json' -d '{"firstName":"Samdba","lastName":"Pepsi","userRole":"LIBRARIAN","password":"xyxy2"}'`

DELETE SPECIFIC USER

`curl -X DELETE localhost:8080/user/delete/2 -H 'Content-type:application/json'`
