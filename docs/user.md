# User API Spec

## Register User

Endpoint : POST /api/users

Request Body :

```json
{
  "username" : "wijaya",
  "password" : "secret",
  "name" : "Arbi Dwi Wijaya"
}
```

Response Body (Success) :

```json
{
  "data" : "Ok"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Username must not blank!"
}
```

## Login User

Endpoint : POST /api/auth/login

Request Body :

```json
{
  "username" : "wijaya",
  "password" : "secret"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt" : 1122334455
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Username or Password Wrong!"
}
```

## Get User

Endpoint : GET /api/users/current

Request Header : 

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "username" : "wijaya",
    "name" : "Arbi Dwi Wijaya"
  }
}
```

Response Body (Failed, 401) :

```json
{
  "errors" : "Unauthorized"
}
```

## Update User

Endpoint : PATCH /api/users/current

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "name" : "Nishikino Maki",
  "password" : "passwordbaru"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "username" : "wijaya",
    "name" : "Nishikino Maki"
  }
}
```

Response Body (Failed) :

```json
{
  "data" : "Failed",
  "errors" : "Username must not blank!"
}
```

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header :

- X-API-TOKEN : Token (Mandatory)
  Response Body (Success) :

```json
{
  "data" : "Ok"
}
```
