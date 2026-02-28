# University Enrollment System

A Java OOP project simulating a university enrollment management system. Features student registration, lecturer management, research assistant tracking, and enrollment management using object-oriented design principles.

---

## Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Class Diagram](#class-diagram)
- [Features](#features)
- [How to Run](#how-to-run)
- [Console Menu](#console-menu)
- [GUI Features](#gui-features)
- [Technologies Used](#technologies-used)
- [Testing](#testing)

---

## Overview

This system allows a university administrator to manage students, lecturers, and research assistants through both a console-based menu and a Swing GUI. It was built as part of a Level 5 Java OOP coursework at the University of Westminster, demonstrating core principles such as inheritance, interfaces, polymorphism, and the MVC pattern.

---

## Project Structure

```
UniversityEnrollmentSystem/
├── src/
│   ├── main/java/universityenrollmentsystem/
│   │   ├── Person.java                        # Base class for all people
│   │   ├── Student.java                       # Extends Person – stores course and modules
│   │   ├── Lecturer.java                      # Extends Person – stores office and specialisation
│   │   ├── ResearchAssistant.java             # Extends Person – stores project, hours, supervisor
│   │   ├── EnrollmentManager.java             # Interface defining core system operations
│   │   ├── WestminsterEnrollmentManager.java  # Implements EnrollmentManager – full logic
│   │   ├── UniversityTableModel.java          # MVC table model for the Swing GUI
│   │   ├── UniversityTableGUI.java            # Swing JFrame GUI window
│   │   └── UniversityEnrollmentSystem.java    # Main entry point
│   └── test/java/universityenrollmentsystem/
│       └── ExtendedModelBehaviourTest.java    # JUnit 5 unit tests
├── pom.xml
└── README.md
```

---

## Class Diagram

```
Person  (base class)
├── Student           (courseTitle, modulesEnrolled)
├── Lecturer          (officeNumber, specialisation)
└── ResearchAssistant (projectTitle, hoursPerWeek, supervisorName)

EnrollmentManager  (interface)
└── WestminsterEnrollmentManager  (implements EnrollmentManager)
```

---

## Features

### Console Menu

| Option | Description |
|--------|-------------|
| `0` | Save and exit |
| `1` | Add a new person (Student, Lecturer, or Research Assistant) |
| `2` | Print list of all people |
| `3` | Open GUI window |
| `4` | List people by role (Students, Lecturers, or Research Assistants) |
| `5` | Print list sorted by role, then surname, then name |

### GUI Window

- Displays all enrolled people in a sortable table with columns: **Name, Surname, Date of Birth, Role, Summary**
- **Statistics button** – shows a popup with total people, student count, lecturer count, research assistant count, and total modules enrolled across all students
- **Show CS Students button** – filters the table to display only students whose course title contains "computer" (case-insensitive); shows an information dialog if none are found

---

## How to Run

### Prerequisites

- Java 17 or higher
- Maven 3.x (or open directly in IntelliJ IDEA / NetBeans)

### Run via IntelliJ IDEA

1. Clone the repository:
   ```bash
   git clone https://github.com/CrishalLiyanageCS/UniversityEnrollmentSystem.git
   ```
2. Open the project in IntelliJ IDEA
3. Let Maven sync dependencies automatically
4. Run `UniversityEnrollmentSystem.java`

### Run via Maven

```bash
mvn compile exec:java
```

---

## Console Menu

When the program starts, you will see:

```
-- UNIVERSITY ENROLLMENT SYSTEM CONSOLE MENU--
To save and exit, press 0
To Add a new person, press 1
To Print the list of all people press 2
To Open GUI, press 3
To List people by role, press 4
To Print list sorted by role and name, press 5
```

When adding a person, you will be prompted for:
- First name, last name, ID, date of birth (`dd/MM/yyyy`)
- **Student**: course title, number of modules enrolled
- **Lecturer**: office number, specialisation/department
- **Research Assistant**: project title, hours per week, supervisor name

---

## GUI Features

The GUI table displays the following columns for each person:

| Column | Description |
|--------|-------------|
| Name | First name |
| Surname | Last name |
| Date of Birth | Formatted as `dd/MM/yyyy` |
| Role | Student, Lecturer, or Other |
| Summary | e.g. `Student – BSc Computer Science, modules: 6` / `Lecturer – office: 101, Computer Science` / `Other – ID: RA001` |

Click any column header to sort the table.

---

## Technologies Used

- **Java 17**
- **Java Swing** – GUI (JFrame, JTable, JOptionPane)
- **Maven** – build and dependency management
- **JUnit 5** – unit testing

---

## Testing

Unit tests are located in `src/test/java/universityenrollmentsystem/ExtendedModelBehaviourTest.java`.

| Test Method | What it verifies |
|-------------|-----------------|
| `testPersonDateFormatting` | `getStringDate()` returns the correct `dd/MM/yyyy` format |
| `testStudentToStringContainsCourseAndModules` | `toString()` includes course title and module count |
| `testLecturerSpecialisation` | `getSpecialisation()` returns the correct value after being set |

### Run tests via IntelliJ
Right-click `ExtendedModelBehaviourTest.java` → **Run**

### Run tests via Maven
```bash
mvn test
```
