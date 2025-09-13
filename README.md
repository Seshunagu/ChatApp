Project Name: ChatApp
Overview:

ChatApp is a full-stack real-time messaging application built with Spring Boot (backend) and Vanilla JavaScript + HTML/CSS (frontend). It allows users to register, login, search other users, and exchange messages in real-time via WebSockets (STOMP over SockJS).

Key Features:

User Authentication & Authorization:

Registration and login with JWT-based authentication.

Passwords are securely stored using BCrypt hashing.

Only authenticated users can access chat functionalities.

Real-Time Chat:

Users can send and receive messages instantly using WebSocket + STOMP.

Unread messages are indicated with a red dot on the contact list.

Chat history is persisted in a MySQL database.

User Management:

Users can search for other registered users by email.

Self-chat is restricted.

Logout clears the session and disconnects from WebSocket.

Database:

MySQL is used to store user and message data.

Tables:

users (id, username, email, password, created_at)

message (id, sender_id, receiver_id, content, timestamp)

Proper constraints and foreign keys ensure referential integrity.

Frontend:

Responsive and visually appealing interface using CSS Flexbox + Grid.

Auth view with toggle between login/register forms.

Chat view with contact list sidebar, chat messages, and input box.

Modal for notifications and error messages.

Backend:

Spring Boot 3.2.5, Java 17

Spring Security for authentication and authorization.

Spring Data JPA for database operations.

HikariCP for efficient database connection pooling.

Deployment:

Deployed on Render with database hosted on Railway.

Environment variables used for DB credentials and JWT secret.

Technology Stack:

Backend: Java, Spring Boot, Spring Security, Spring Data JPA, WebSockets (STOMP + SockJS)

Frontend: HTML, CSS, JavaScript

Database: MySQL (Railway)

Deployment: Render

How It Works:

User registers → credentials stored in MySQL.

User logs in → receives JWT token → token used for authorization in API calls.

User searches for contacts → selects contact → opens chat.

Messages are sent via WebSocket → saved in message table → displayed in real-time.
