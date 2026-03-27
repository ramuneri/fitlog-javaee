TODO:
- MyBatis for all
- Edit component via JPA
- CDI/use case beans??
- when class becomes CDI bean?
- MyBatis declarative transactions?

- Edit exercise with JPA as well


# FitLog – Java EE Gym Workout Tracker

FitLog is a university project built with Java EE that allows users to create and manage gym workout plans, exercises, and muscle groups.

The application demonstrates both ORM (JPA) and DataMapper (MyBatis) approaches for working with a relational database.

---

## Features

- Create workout plans
- Add exercises to workout plans
- Assign muscle groups to exercises
- View related data (e.g., workout plans with exercises, exercises with muscle groups)
- Data persistence across server restarts
- MyBatis-based exercise management (in addition to JPA)

---

## Technologies Used

- Java EE (Jakarta EE)
- JSF (JavaServer Faces)
- CDI (Contexts and Dependency Injection)
- JPA (Hibernate) – ORM
- MyBatis – DataMapper
- Maven
- WildFly 26
- H2 Database (file-based)

---

## Architecture Overview

- **UI (JSF)** – forms and pages for user interaction
- **CDI Use Case Beans** – handle business logic and data flow
- **JPA DAO Layer** – database access using ORM
- **MyBatis Mapper Layer** – database access using SQL
- **H2 Database** – local file-based persistence

---

## Database

- Configured via WildFly datasource (`FitLogDS`)
- Uses H2 file-based database
- Data persists after full server restart
- Tables are generated automatically via JPA (`hibernate.hbm2ddl.auto=update`)

---

## Purpose

This project was developed as part of a university assignment to demonstrate:

- ORM vs DataMapper approaches
- Entity relationships (one-to-many, many-to-many)
- Data binding and UI interaction
- CDI and layered architecture


## DB

![img_1.png](img_1.png)

