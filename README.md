# Zay Shop – JSP/Servlet E-Commerce Project

This project is developed as part of an examination at FPT Polytechnic. It is an e-commerce web application named "Zay Shop," built using Java EE technologies.

## Features

- **User Authentication**: Registration and login functionalities for users.
- **Product Management**: Display and manage products with categories.
- **Shopping Cart**: Add, update, and remove items from the cart.
- **Order Processing**: Place orders and view order history.
- **Admin Panel**: Administrative interface for managing products, categories, and orders.

## Technologies Used

- **Backend**: Java, JSP, Servlets
- **Frontend**: HTML, CSS, JavaScript, Less, SCSS
- **Build Tool**: Maven
- **IDE**: Eclipse

## Project Structure

```
jsp-servlet_zay-shop/
├── src/
│   └── main/
│       ├── java/
│       │   └── [Java source files]
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml
│           └── [JSP files, static resources]
├── .settings/
├── target/
├── test-output/
├── .classpath
├── .project
├── pom.xml
└── README.md
```

## Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/haitt2381/jsp-servlet_zay-shop.git
   ```

2. **Import the project into Eclipse**:
   - Open Eclipse.
   - Go to `File` > `Import` > `Existing Maven Projects`.
   - Select the cloned repository folder.

3. **Build the project**:
   - Right-click on the project in Eclipse.
   - Select `Run As` > `Maven Install`.

4. **Deploy to a server**:
   - Configure a Tomcat server in Eclipse.
   - Deploy the project to the server.

5. **Access the application**:
   - Open a web browser and navigate to `http://localhost:8080/jsp-servlet_zay-shop/`.
