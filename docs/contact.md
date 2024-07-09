# Contact API Spec

## Create Contact

Endpoint : POST /api/contacts

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "firstName" : "Arbi Dwi",
  "lastName" : "Wijaya",
  "email" : "arbi@gmail.com",
  "phone" : "08123456789"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "random-string",
    "firstName" : "Arbi Dwi",
    "lastName" : "Wijaya",
    "email" : "arbi@gmail.com",
    "phone" : "08123456789"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Email format Invalid, Phone format Invalid."
}
```

## Update Contact

Endpoint : PUT /api/contacts/{idContact}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body :

```json
{
  "firstName" : "Arbi Dwi",
  "lastName" : "Wijaya",
  "email" : "arbi@gmail.com",
  "phone" : "08123456789"
}
```

Response Body (Success) :

```json
{
  "data" : {
    "id" : "random-string",
    "firstName" : "Arbi Dwi",
    "lastName" : "Wijaya",
    "email" : "arbi@gmail.com",
    "phone" : "08123456789"
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Email format Invalid, Phone format Invalid."
}
```

## Get Contact

Endpoint : GET /api/contacts/{idContact}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : {
    "id" : "random-string",
    "firstName" : "Arbi Dwi",
    "lastName" : "Wijaya",
    "email" : "arbi@gmail.com",
    "phone" : "08123456789"
  }
}
```

Response Body (Failed, 404) :

```json
{
  "errors" : "Contact not Found!"
}
```

## Search Contact

Endpoint : GET /api/contacts

Query Param : 

- name : String, contact first name or last name, using like query, optional
- phone : String, contact phone, using like query, optional
- email : String, contact email, using like query, optional
- page : Integer, start from 0, default 0
- size : Integer, default 10

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : [
    {
      "id" : "random-string",
      "firstName" : "Arbi Dwi",
      "lastName" : "Wijaya",
      "email" : "arbi@gmail.com",
      "phone" : "08123456789"
    }
  ],
  "paging" : {
    "currentPage" : 0,
    "totalPage" : 10,
    "size" : 10
  }
}
```

Response Body (Failed) :

```json
{
  "errors" : "Unauthorized"
}
```

## Remove Contact

Endpoint : DELETE /api/contacts/{idContact}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :

```json
{
  "data" : "Ok"
}
```

Response Body (Failed) :

```json
{
  "errors" : "Contact not Found!"
}
```