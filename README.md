# API Biblioweb

## Introducción

API desarrollada con el objetivo de cumplir la función de proveer información acerca de libros, editoriales, autores y entre otros relacionados al usuario solicitante; incluyendo personalización como gestión de favoritos, edición de perfil y paginación.

## Autenticación (`/api/v1/auth`)

### Http Methods

**Register User** `POST /register`

<details>
<summary>HTTP Messages</summary>

**Request**

```json
{
  "username": "example",
  "email": "example@example.example",
  "password": "example"
}
```
**Response**

```json
{
  "tokenDeAcceso": "{JsonWebToken}",
  "tipoDeToken": "Bearer"
}
```
</details>

**Authenticate User** `POST /login`

<details>
<summary>HTTP Messages</summary>
  
**Request**

```json
{
  "username": "example",
  "password": "example"
}
```
ó

```json
{
  "username": "example@example.example",
  "password": "example"
}
```
**Response**

```json
{
  "tokenDeAcceso": "{JsonWebToken}",
  "tipoDeToken": "Bearer"
}

```
</details>

## Autores (`/api/v1/autores`)

### Http Methods

**Crear Autor** `POST /api/v1/autores`

<details>
<summary>HTTP Messages</summary>
  
**Request**

```json
{
  "nombres": "exampleName",
  "apellidos": "exampleLastname",
  "fech_nacimiento": "dd/mm/yyyy",
  "fotografia": "{cdnImg}",
  "extra_info": "exampleExtra"
}
```
**Response**

```json
{
  "codigoaut": "AU1",
  "nombres": "exampleName",
  "apellidos": "exampleLastname",
  "fech_nacimiento": "dd/mm/yyyy",
  "fotografia": "{cdnImg}",
  "extra_info": "exampleExtra"
}
```
</details>

**Listar Autores Paginados** `GET /api/v1/autores`

- **Request Params**
  - Default: `?pageNo=0&pageSize=10&sortBy=id&sortDir=asc`
  
    Dónde:
    - *pageNo: número de página.*
    - *pageSize: tamaño de página.*
    - *sortBy: ordenar por el valor especificado.*
    - *sortDir: ordenar valores en dirección ascendente o descendente.*

<details>
<summary>HTTP Messages</summary>
  
**Response**

```json
{
  "content": [
    {
      "codigoaut": "AU1",
      "nombres": "exampleName",
      "apellidos": "exampleLastname",
      "fech_nacimiento": "dd/mm/yyyy",
      "fotografia": "cdnImg",
      "extra_info": "exampleExtra"
    },
    {
      "codigoaut": "AU2",
      "nombres": "exampleName1",
      "apellidos": "exampleLastname1",
      "fech_nacimiento": "dd/mm/yyyy",
      "fotografia": "cdnImg1",
      "extra_info": "exampleExtra1"
    },
    {
      "codigoaut": "AU3",
      "nombres": "exampleName2",
      "apellidos": "exampleLastname2",
      "fech_nacimiento": "dd/mm/yyyy",
      "fotografia": "cdnImg2",
      "extra_info": "exampleExtra2"
    }
  ],
  "pageNo": 0,
  "pageSize": 10,
  "totalElements": 3,
  "totalPages": 1,
  "last": true
}
```
</details>

**Listar Autores** `GET /all`

<details>
<summary>HTTP Messages</summary>
  
**Response**

```json
[
  {
    "codigoaut": "AU1",
    "nombres": "exampleName",
    "apellidos": "exampleLastname",
    "fech_nacimiento": "dd/mm/yyyy",
    "fotografia": "cdnImg",
    "extra_info": "exampleExtra"
  },
  {
    "codigoaut": "AU2",
    "nombres": "exampleName1",
    "apellidos": "exampleLastname1",
    "fech_nacimiento": "dd/mm/yyyy",
    "fotografia": "cdnImg1",
    "extra_info": "exampleExtra1"
  },
  {
    "codigoaut": "AU3",
    "nombres": "exampleName2",
    "apellidos": "exampleLastname2",
    "fech_nacimiento": "dd/mm/yyyy",
    "fotografia": "cdnImg2",
    "extra_info": "exampleExtra2"
  }
]
```
</details>

**Buscar Autor por Id** `GET /{codigoaut}`

- **Path Variables**
  
    - *codigoaut: id (codigoaut) del registro de un autor.*

<details>
<summary>HTTP Messages</summary>

**Request**

`/api/v1/autores/AU1`

**Response**

```json
{
  "codigoaut": "AU1",
  "nombres": "exampleName",
  "apellidos": "exampleLastname",
  "fech_nacimiento": "dd/mm/yyyy",
  "fotografia": "cdnImg",
  "extra_info": "exampleExtra"
}
```
</details>

**Buscar Autor por Coincidencia** `GET /cs/{coincidencia}`

- **Path Variables**
  
    - *coincidencia: id (codigoaut), nombres, apellidos o fech_nacimiento del un autor.*

<details>
<summary>HTTP Messages</summary>

**Request**

`/api/v1/autores/cs/AU1`

**Response**

```json
{
  "content": [
    {
      "codigoaut": "AU1",
      "nombres": "exampleName",
      "apellidos": "exampleLastname",
      "fech_nacimiento": "dd/mm/yyyy",
      "fotografia": "cdnImg",
      "extra_info": "exampleExtra"
    }
  ],
  "pageNo": 0,
  "pageSize": 10,
  "totalElements": 1,
  "totalPages": 1,
  "last": true
}
```
</details>

**Actualizar Autor** `PUT /{codigoaut}`

- **Path Variables**
  
    - *codigoaut: id (codigoaut) del registro de un autor.*

<details>
<summary>HTTP Messages</summary>

**Request**

`/api/v1/autores/AU1`

```json
{
  "nombres": "exampleUpdName",
  "apellidos": "exampleUpdLastname",
  "fech_nacimiento": "dd/mm/yyyy",
  "fotografia": "{cdnImgUpd}",
  "extra_info": "exampleUpdExtra"
}
```

**Response**

```json
{
  "codigoaut": "AU1",
  "nombres": "exampleUpdName",
  "apellidos": "exampleUpdLastname",
  "fech_nacimiento": "dd/mm/yyyy",
  "fotografia": "{cdnImgUpd}",
  "extra_info": "exampleUpdExtra"
}
```
</details>

**Eliminar Autor** `DELETE /{codigoaut}`

- **Path Variables**
  
    - *codigoaut: id (codigoaut) del registro de un autor.*

<details>
<summary>HTTP Messages</summary>

**Request**

`/api/v1/autores/AU1`

**Response**

`Autor eliminado con éxito`

</details>

## Editoriales `(/api/v1/editoriales)`

## Géneros `(/api/v1/generos)`

## Libros `(/api/v1/libros)`

## Personas_perfil `(/api/v1/ppl)`

## Roles `(/api/v1/roles)`

## Usuarios `(/api/v1/usuarios)`
