Mace Backend

To Run:
mvn spring-boot:run




--------------------------------------------------------
---------------------------------------------------------

Sample Request:

GET localhost:8080/user/

Headers:
Accept | application/json
Authorization | Basic dXNlcjpwYXNzd29yZA==

---------------------------------------------------------

Success Response:

[
  {
    "username": "user1",
    "password": null
  },
  {
    "username": "user2",
    "password": null
  }
]

---------------------------------------------------------

Error Response:

{
  "timestamp": 1475499145248,
  "status": 401,
  "error": "Unauthorized",
  "message": "Username not found.",
  "path": "/user/"
}

--------------------------------------------------------
--------------------------------------------------------
               R E G I S T R A T I O N
--------------------------------------------------------
--------------------------------------------------------

Sample Request:

{
	"username": "user",
	"password" : "password"
}

