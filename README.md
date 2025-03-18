# Animal Adoption Centre

## Overview
This is a JavaFX-based application that allows users to view adoption centres, see available animals for adoption, and filter animals by type (dog, cat, or all types). Each adoption centre is represented by a separate window displaying detailed information, including the name, location, capacity, and occupancy rate.

## Features
- **Adoption Centre Information:** View the name, location, capacity, and occupancy of each adoption centre.
- **Animal List:** View a list of animals available for adoption in each centre.
- **Filter Animals:** Filter the displayed animals by type (dog, cat, or all types).
  
## Technologies & Architecture
- **Programming Language:** Java
- **GUI Framework:** JavaFX
- **Database:** PostgreSQL
- **Persistence:** JDBC
- **Architecture:** Layered architecture with the following layers:
  - **Domain Layer:** Defines core entities (AdoptionCentre, Animal, Type) and their validation.
  - **Repository Layer:** Handles data persistence and retrieval from the PostgreSQL database.
  - **Service Layer:** Implements business logic and coordinates between the repository and UI layers.
  - **UI Layer:** Manages user interactions and displays data in JavaFX components.
- **Design Pattern:** Model-View-Controller (MVC) used for structuring the UI and logic.

## Installation & Setup
1. Install **PostgreSQL** and create a database.
2. Update the database connection details in the `AnimalRepository.java` class.
3. Import the project into an IDE such as IntelliJ IDEA or Eclipse.
4. Run the application from the main class (`HelloApplication.java`).

## Usage
1. Launch the application.
2. A separate window will open for each adoption centre, showing the adoption centre's details and a list of animals.
3. Use the combobox to filter animals by type (dog, cat, or all types).

