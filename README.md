# Event Management System

## Overview
This is a Spring Boot application for managing events. It provides endpoints for uploading events from CSV files and finding events based on location and date.

### 1. Source Code:
You can find the source code on GitHub at [Event Management System](https://github.com/PragyanKPradhan/Event-Management-System.git).

### 2. API Documentation:
#### Endpoints:
1. **Upload Events:**
   - **URL:** `/events/upload`
   - **Method:** POST
   - **Request Payload:** Multipart file containing event data
   - **Response:** Success message or error message
   - **Description:** This endpoint allows users to upload events from a CSV file. The request body should contain a multipart file with event data. The controller delegates the file handling and event creation to the EventService and returns a success message or error message based on the result.

2. **Find Events:**
   - **URL:** `/events/find`
   - **Method:** GET
   - **Request Parameters:**
     - `longitude`: Longitude of the user's location
     - `latitude`: Latitude of the user's location
     - `date`: Date for which events are to be retrieved
   - **Response:** EventResponse object containing a list of EventDto objects
   - **Description:** This endpoint allows users to find events based on their location (longitude and latitude) and a specific date. The controller retrieves the user's location and the date from the request parameters and delegates the event retrieval to the EventService. It returns an EventResponse object containing a list of EventDto objects representing the events found.

#### Request/Response Formats:
- **EventDto:**
  ```json
  {
    "event_name": "string",
    "city_name": "string",
    "weather": "string",
    "date": "yyyy-MM-dd",
    "distance_km": "number"
  }
  ```

- **EventResponse:**
  ```json
  {
    "events": [
      {
        "event_name": "string",
        "city_name": "string",
        "weather": "string",
        "date": "yyyy-MM-dd",
        "distance_km": "number"
      }
    ],
    "page": "number",
    "pageSize": "number",
    "totalEvents": "number",
    "totalPages": "number"
  }
  ```

### 3. Justification:
#### Tech Stack:
- **Spring Boot:** Used for rapid development of robust, production-grade applications.
- **Spring Data JPA:** Simplifies data access through JPA/Hibernate.
- **Spring Webflux:** Provides reactive programming support for handling asynchronous and non-blocking requests.
- **MySQL Database:** Chosen for its widespread adoption, relational data model, and compatibility with Spring Data JPA.
- **Lombok:** Reduces boilerplate code for POJOs.

#### Design Decisions:
- **Reactive Architecture:** Utilized Spring Webflux for reactive endpoints to handle concurrent requests efficiently.
- **Separation of Concerns:** Divided functionalities into services and repositories for better code organization and maintainability.
- **External API Integration:** Used WebClient for making HTTP requests to external weather and distance calculation APIs.
- **Error Handling:** Implemented error handling for various scenarios such as file upload failures and API request failures.

#### Challenges Addressed:
- **Asynchronous Processing:** Managed asynchronous processing of external API requests to ensure optimal performance.
- **Data Parsing:** Handled data parsing from CSV files and formatted dates and times appropriately.
- **Integration Testing:** Ensured proper integration testing of controllers and services to validate API functionality.

### 4. Instructions to Run Code:
1. Clone the repository from GitHub.
2. Ensure you have Java 17 and Maven installed on your system.
3. Set up MySQL database and update the `application.yml` file with the database URL, username, and password.
4. Build the project using Maven: `mvn clean install`.
5. Run the Spring Boot application: `mvn spring-boot:run`.
6. Access the API endpoints using a tool like Postman or through a web browser.

### 5. Demonstration:
These are the screenshots and video demonstrating the execution of the provided test case through the API using Postman tool:

[Watch Demonstration Video](https://drive.google.com/file/d/1ygmeJ0Bu6Zd6doltZC69Mic4w1nZ86Rc/view?usp=sharing)
- **Upload Events:**
  ![Upload Events](https://github.com/PragyanKPradhan/Event-Management-System/assets/144383179/783c7440-3114-448b-b7b7-e0f71a14ac41)
- **Find Events:**
  ![Find Events](https://github.com/PragyanKPradhan/Event-Management-System/assets/144383179/0eccb60b-8915-48a4-9149-6c6622c9cdaf)

  
