# Getting Started

### Prerequisites
The postman collection is available in the resource folder of the project.

### Get the access token
Login to get a JWT token:

```
curl --location 'http://localhost:9090/api/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "admin",
    "password": "password"
}'
```
### Use the access token
Use the token to access protected endpoints:

* Get all users:
```
curl --location 'http://localhost:9090/api/users' \
--header 'Authorization: Bearer YOUR_JWT_TOKEN_HERE' \
```

* Get all users filtered by name:
```
curl --location 'http://localhost:9090/api/users/search/oo' \
--header 'Authorization: Bearer YOUR_JWT_TOKEN_HERE' \
```

* Call an external API:
```
curl --location 'http://localhost:9090/api/users/pokemon' \
--header 'Authorization: Bearer YOUR_JWT_TOKEN_HERE' \
```

* Encode a string:
```
curl --location --request POST 'http://localhost:9090/api/users/encoding/the-string' \
--header 'Authorization: YOUR_JWT_TOKEN_HERE' \
```