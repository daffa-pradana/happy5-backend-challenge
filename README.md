# Happy5 Backend Challenge

API Services for Messaging Feature submitted for Happy5 Junior Backend Engineer Assesment Test.

## Technologies

- Java 11
- SpringBoot 2.5.1
- MySQL server 5.7.34

## Setup

**Prerequisites:**

1. Database Setup

   Create database in your mysql server

2. application.properties Setup

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/<db-name>
   spring.datasource.username=<mysql-user>
   spring.datasource.password=<mysql-pass>
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
   spring.jpa.hibernate.ddl-auto=update
   ```

**Run Application:**

- With Maven

  required: maven should be installed in your local machine

  ```
  $ mvn spring-boot:run
  ```

- With IDE (IntelliJ, STS-4, Ecplise)

  run MessagingApplication.java

## Database Entity Schema

These schemas will automatically created once the app is running

#### **user**

| id         | first_name   | last_name    |
| ---------- | ------------ | ------------ |
| bigint(20) | varchar(255) | varchar(255) |

#### **message**

| message_id | group_id   | recipient_id | seen   | sender_id  | sent_at  | text         |
| ---------- | ---------- | ------------ | ------ | ---------- | -------- | ------------ |
| bigint(20) | bigint(20) | bigint(20)   | bit(1) | bigint(20) | datetime | varchar(255) |

#### **message_group**

| group_id   | members      |
| ---------- | ------------ |
| bigint(20) | varchar(255) |

## API Documentation

**User Features**

- List Users

  List existing user from database

  GET request

  ```
  http://localhost:8080/api/v1/user/list
  ```

- Add User

  Add a user to database

  POST request

  ```
  http://localhost:8080/api/v1/user/add
  ```

  Request Body (json)

  ```json
  {
    "firstName": "John",
    "lastName": "Doe"
  }
  ```

- Get User

  Get a specific user

  GET request

  ```
  http://localhost:8080/api/v1/user/get/{user-id}
  ```

- Delete User

  Delete a specific user

  DELETE request

  ```
  http://localhost:8080/api/v1/user/delete/{user-id}
  ```

**Messaging Features**

- Send Message

  Send a new message to another user on with new conversation

  POST request

  ```
  http://localhost:8080/api/v1/message/send/{recipient-id}
  ```

  Request Header

  ```
  sender-id | {sender-id}
  ```

  Request Body (text)

  ```
  a text message
  ```

- Reply Message

  Reply a message to a user in an existing conversation

  POST Request

  ```
  http://localhost:8080/api/v1/message/reply/{group-id}
  ```

  Request Header

  ```
  sender-id | {sender-id}
  ```

  Request Body (text)

  ```
  a text message
  ```

- List Conversations

  List conversations that involves current user

  GET Request

  ```
  http://localhost:8080/api/v1/message/conversation
  ```

  Request Header

  ```
  user-id | {user-id}
  ```

- List Messages

  List messages in a specific conversation that involves current user

  GET Request

  ```
  http://localhost:8080/api/v1/message/conversation/{group-id}
  ```

  Request Header

  ```
  user-id | {user-id}
  ```

## Submitted By

[Daffa Arrafi](https://github.com/daffaarravi)
