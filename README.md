# High-Performance Gaming Platform

## Project Overview
This is a **High-Performance Gaming Platform** built with a **Java MVC** architecture. It provides core e-commerce capabilities such as:
- User registration and login
- Product (game) display
- Shopping cart
- Order management
- Payment integration
- Logistics management
- Coupons and promotional events
- Product recommendation
- Data analytics and reporting

## Features
1. **Front-end**: React.
2. **Back-end**: SpringBoot framework handles business logic and request/response flow.
3. **Databases**:
   - **Redis**: In-memory data store for frequently accessed or cached data.
   - **MySQL**: Persistent storage for user information, game details, and other transactional data.
4. **Persistence Layer**: MyBatis for mapping and executing SQL statements against the MySQL database.

## Deployment Steps

### 1. Prerequisites
Make sure you have installed the following on your Ubuntu virtual machine (created via VMware Pro):
- **Ubuntu** operating system
- **Java 8**
- **Apache Tomcat** (version 9.x recommended)
- **MySQL** 
- **Redis**

### 2. Prepare and Package the Application
1. **Build the WAR file**  
   - In your local development environment (e.g., IntelliJ IDEA), use Maven to package the project.  
   - This will generate a `shop.war` file in the `target` directory.

2. **Set Up the MySQL Database**  
   - Log into MySQL as the root user (e.g., `mysql -u root -p`).
   - Run `source ./schema.sql` to create the `shop` database and its tables.
   - Run `source ./dbdata.sql` to populate the tables with initial data.

3. **Deploy the WAR to Tomcat**  
   - Transfer the `shop.war` file to the `webapps` folder of your Tomcat installation (e.g., `/usr/local/tomcat/apache-tomcat-9.0.93/webapps`).

4. **Configure Tomcat**  
   - Navigate to your Tomcat `conf` directory and open `server.xml`.  
   - Inside the `<Host>` section, add:
     ```xml
     <Context docBase="shop" path="/" reloadable="false" />
     ```

5. **Start Tomcat**  
   - Move to Tomcat’s `bin` directory and start Tomcat:
     ```bash
     cd /usr/local/tomcat/apache-tomcat-9.0.93/bin
     sh startup.sh
     ```
   - To stop Tomcat, you can run:
     ```bash
     sh shutdown.sh
     ```

6. **Enable All Game Records**  
   - Log into MySQL as root and enable all game entries:
     ```sql
     USE shop;
     UPDATE game SET stat = '1';
     ```

### 3. Access the Application
Once Tomcat is running, open your browser and go to:
```
http://127.0.0.1:8080/shop/
```
You should now see the **High-Performance Gaming Platform** homepage where you can register, log in, browse games, manage your cart, and place orders.

---

## Additional Notes
- **Redis** should be installed and running to enable caching for frequently accessed data.  
- Ensure the MySQL credentials in your `application` or `Spring` configuration files match your actual MySQL setup.
- If you change the Tomcat port from the default `8080`, update the URL accordingly.

Enjoy exploring and extending the High-Performance Gaming Platform!
