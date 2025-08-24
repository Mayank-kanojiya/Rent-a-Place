# RentAPlace
Rent A Place is a scalable, full-stack web application for managing rental properties, bookings, messaging, and users. Architected with Spring Boot microservices and a modular Angular frontend, it provides a seamless user experience for both property owners and renters.

🚀 Features
Dynamic Property Search

Filter by location, date, type, and features

Detailed property pages with galleries

Owner Dashboard

Add, update, and remove properties

Approve or decline booking requests instantly

Manage messages and email communications with users

User Dashboard

Search and book properties

Track status of bookings

Send and receive messages to owners

Secure Authentication

Separate registration/login for User and Owner roles

JWT-based authentication and Spring Security for endpoint protection

Real-time Messaging

Dedicated Chat Microservice for handling messaging between users and owners

Responsive UI & Modern Experience

Angular components for every feature

Desktop and mobile friendly

Microservice Ready

Main Rental microservice (Spring Boot)

Chat microservice for communication

🏗️ Project Structure
Backend (Spring Boot Java)
Main Rental Microservice (rentaplace_service)
controller/: REST APIs
(Authenticate, OwnerController, PropertyController, EmailController, UserController)

Dto/: Data Transfer Objects
(BookingDto, PropertyDto, AuthenticationRequest/Response, etc.)

exceptions/: Custom exceptions

model/: JPA entities for User, Property, Booking, Email

repo/: JPA Repositories

Security/: SecurityConfig.java for roles/JWT/Spring Security

service/: Business logic/service layer

Util/: JwtUtil.java for JWT token handling

Chat Microservice
config/: CORS & app configs

controller/: Chatcontroller.java

dto/: Chat DTOs

model/: Chat entity

repo/: ChatRepo.java

service/: ChatService.java

Frontend (Angular)
Highly Modular Folder Structure

addproperty/, emails/, header/, login/, messages/, model/, ownerdashboard/, ownersignup/, property/, Service/, signup/, userbookings/, userdashboard/

Routing and main app structure: app-routing.module.ts, app.module.ts

⚡ Getting Started
Prerequisites
Node.js & Angular CLI

Java JDK 17+ / 21+

MySQL

Maven

Git

Setup Guide
1. Clone the Repository

bash
git clone https://github.com/Mayank-kanojiya/Rent-a-Place.git
cd Rent-a-Place
2. Backend Service Setup

bash
cd rentaplace_service
# Edit src/main/resources/application.properties for your MySQL setup
mvn spring-boot:run
3. (Optional) Start Chat Microservice

bash
cd ChatMicroService
mvn spring-boot:run
4. Frontend Angular Setup

bash
cd client  # Or src/app if you use Angular CLI workspace
npm install
ng serve
Go to http://localhost:4200 in your browser.

🔗 Usage
Register as a User (for booking) or Owner (for managing properties)

Owners: Add/manage properties, view & reply to booking requests, chat with users

Users: Search properties, book, message owners, track bookings

Real-time messaging between users and owners (if Chat/MS is running)

📸 Screenshots
User Experience & Features:

Search & Book Properties:

User Dashboard:

User Registration:

Login Form:

Book a Property:

My Bookings:

Owner Experience:

Owner Registration:

Owner Properties Dashboard:

Add Property:

Booking Requests List:

Message Table (Owner Replies):

Email Notifications:

Showcase:

Property Detail & Gallery:

📝 Contributing
Pull requests are welcome.
Open an issue for bug reports, improvements, or questions.

🙋‍♂️ Author
Mayank Kanojiya
GitHub Profile
Email: ggum251@gmail.com

📄 License
MIT License

Tip: You can further enhance this README by adding a Tech Stack badges section, a live demo link (if you deploy), or install instructions for contributors. Let me know if you want this or help with anything else!
Execute npm i @ng-bootstrap/ng-bootstrap for bootstrap
and npm i ng2-search-filter for search
