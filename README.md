# RentAPlace

Rent A Place is a full-stack, multi-module web application for managing rental properties and bookings, offering tailored dashboards for owners and users, along with seamless communication features. The backend is implemented with Spring Boot microservices and the frontend with Angular, making the system scalable and maintainable.

🚀 Features
Property Search & Booking

Search properties by date range, location, and property features.

Detailed property pages with image galleries.

User booking requests and history tracking.

User & Owner Dashboards

Owners can add, update, delete properties.

Approve or decline booking requests.

Manage messages and email communications.

Users can browse properties, book, and send messages.

Authentication & Authorization

Secure JWT-based login and token management.

Separate flows for user and owner registration and login.

Messaging Service

Real-time chat capability via independent chat microservice.

Responsive UI

Modular Angular frontend with a clean and intuitive interface.

📁 Project Structure
Backend
Main service (rentaplace_service)

Controllers: Authentication, Owner, Property, User, Email

DTOs: AuthenticationRequest/Response, Booking, Message, Property, User

Models: User, Property, Booking, Email

Repositories: UserRepo, PropertyRepo, BookingRepo, EmailRepo

Services: UserService, PropertyService, BookingService, EmailService

Security: JWT and Spring Security configurations

Exceptions and utilities

Chat Microservice (ChatMicroService)

Handles messaging functionality

Contains ChatController, ChatService, ChatRepo, and related DTOs/models

Frontend (Angular)
Modular architecture with feature folders:

login, signup, ownerSignup, ownerDashboard, userDashboard, messages, property, addProperty, emails, userBookings, etc.

Central service and routing modules for management

🛠️ Installation & Running
Prerequisites:
Java 17/21+, Maven, MySQL, Node.js, Angular CLI

Steps:
Clone Repository

bash
git clone https://github.com/Mayank-kanojia/Rent-A-Place.git
cd Rent-A-Place
Run Backend
Navigate to backend service folder (rentaplace_service), configure DB credentials in application.properties:

bash
mvn spring-boot:run
Run Chat Microservice (Optional)

bash
cd ChatMicroService
mvn spring-boot:run
Run Frontend

bash
cd client # or your Angular frontend dir
npm install
ng serve
Visit http://localhost:4200

📸 Screenshots: Snippets folder
Feature	Screenshot
Search Properties	
User Dashboard	
User Registration	
Login Page	
Book Property	
User Bookings	
Owner Registration	
Owner Dashboard	
Add Property	
Booking Requests	
Messaging	
Owner Email Management	
Property Gallery	
🤝 Contributing
Feel free to suggest features, report bugs, or improve documentation. Pull requests are warmly welcomed!

👤 Author
Mayank Kanojia
GitHub Profile
Email: ggum251@gmail.com

📄 License
This project is licensed under the MIT License.

Execute npm i @ng-bootstrap/ng-bootstrap for bootstrap
and npm i ng2-search-filter for search
