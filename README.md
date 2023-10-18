# Simple API written in Kotlin
This project work as an example of API that can be written in Kotlin. It allows you to make an order, create new customer, get information about customers and orders and also offers a very simple user authentication example.

## Run Locally

Clone the project

```bash
  git clone https://github.com/Seba0855/sinwo2022.git
```

Go to the project directory

```bash
  cd sinwo2022
```

Run the project

```bash
  ./gradlew run
```

Use endpoints on `127.0.0.1:2137` or any other IP you specify in `Application.kt`

### Alternatively, you can also deploy the image to Docker

In order to deploy, run:
```bash
  ./gradlew runDocker
```


## API Reference

This example features SwaggerUI, so it can be used to review available API endpoints. It's available on `/swagger`

#### Get orders

```http
  POST /order/new
```

| Request Body - `list` | Type     | Description   |
| :-------- | :------- | :-------------------------------- |
| `item` | `String` | **Required**. Product name |
| `amount` | `Int` | **Required**. Amount of ordered products |
| `price` | `Double` | **Required**. Price per one item |

#### Get customer by ID

```http
  GET /customer
```
| Query parameter | Type     | Description    |
| :-------- | :------- | :-------------------------------- |
| `id` | `Int` | User ID |

