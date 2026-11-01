# 🎓 Student Record Management System (Core Java)

[![Java](https://img.shields.io/badge/Java-8%2B%20%2F%2023-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Build](https://img.shields.io/badge/Build-Passing-brightgreen.svg)]()
[![Paradigm](https://img.shields.io/badge/OOP-Encapsulation%20%26%20Collections-blueviolet.svg)]()

A menu-driven, object-oriented console application developed in **Core Java** to manage student academic records, subjects, marks, letter grades, and institutional performance metrics.

---

## 📌 Table of Contents

- [Overview](#-overview)
- [Project Architecture](#-project-architecture)
- [Grading Scale](#-grading-scale)
- [Project Structure](#-project-structure)
- [Class Responsibilities](#-class-responsibilities)
- [Core Features](#-core-features)
- [Java Concepts Implemented](#-java-concepts-implemented)
- [How to Compile and Run](#-how-to-compile-and-run)
- [Sample Execution Output](#-sample-execution-output)
- [Future Enhancements Roadmap](#-future-enhancements-roadmap)
- [License](#-license)

---

## 📖 Overview

The **Student Record Management System** is designed for educational institutions to streamline student record handling. It leverages Java Collections (`ArrayList`, `LinkedHashMap`), the Streams API, and foundational Object-Oriented Programming (OOP) concepts to execute in-memory CRUD operations and academic performance analytics without external database dependencies.

---

## 🏗️ Project Architecture

```
                       ┌─────────────────────────┐
                       │        Main.java        │
                       │  (Console Menu & I/O)   │
                       └────────────┬────────────┘
                                    │ delegates
                                    ▼
                       ┌─────────────────────────┐
                       │   StudentService.java   │
                       │   (CRUD & Collection)   │
                       └────────────┬────────────┘
                                    │
               ┌────────────────────┴────────────────────┐
               ▼                                         ▼
  ┌─────────────────────────┐               ┌─────────────────────────┐
  │      Student.java       │               │   GradeCalculator.java  │
  │  (Data Model & Vitals)  │               │   (Analytics & Streams) │
  └─────────────────────────┘               └─────────────────────────┘
```

---

## 📊 Grading Scale

The application automatically evaluates student percentages against standard academic tiers:

| Percentage Range | Letter Grade | Academic Status |
| :--- | :---: | :--- |
| **90.00% – 100.00%** | **A+** | Passed (Excellence) |
| **80.00% – 89.99%**  | **A**  | Passed (Distinction) |
| **70.00% – 79.99%**  | **B**  | Passed (First Class) |
| **60.00% – 69.99%**  | **C**  | Passed (Second Class) |
| **50.00% – 59.99%**  | **D**  | Passed (Pass Class) |
| **Below 50.00%**     | **F**  | Needs Improvement (Failed) |

---

## 📁 Project Structure

```
StudentManagementSystem/
│
├── Student.java           # Model: Encapsulates student ID, name, age, and subject-marks map
├── StudentService.java    # Service: Executes in-memory CRUD operations and cohort aggregation
├── GradeCalculator.java   # Utility: Computes total, average, letter grade, min/max via Streams
└── Main.java              # Controller: Menu-driven loop and sanitized user input handling
```

---

## 🔍 Class Responsibilities

### 1. `Student.java`
* Represents an individual student entity.
* Employs encapsulation with `private` fields and public accessors/mutators.
* Utilizes `LinkedHashMap<String, Double>` to maintain the insertion order of registered subjects and scores.

### 2. `GradeCalculator.java`
* Stateless utility class performing statistical calculations on a `Student` instance.
* Uses the **Java 8 Streams API** (`mapToDouble`, `max()`, `min()`) to dynamically determine the highest and lowest subject scores.
* Formats individual academic report cards.

### 3. `StudentService.java`
* Implements repository-level business logic:
  * **Create:** Prevents duplicate Student IDs.
  * **Read:** Fast linear scan search and formatted directory listing.
  * **Update:** Updates demographic info or updates subject scores with boundary validation.
  * **Delete:** Removes student records by ID.
  * **Cohort Summary:** Calculates overall class average, identifies the class topper, highest/lowest class average, and pass/fail distribution.

### 4. `Main.java`
* Console controller presenting a 9-option interactive menu.
* Implements robust input sanitization (`Integer.parseInt(sc.nextLine().trim())`) to eliminate `Scanner.nextInt()` newline skipping pitfalls.

---

## ⚡ Core Features

| Feature | Description |
| :--- | :--- |
| **Add Student** | Registers ID, name, age, and dynamic number of subjects with scores ($0-100$). |
| **Update Student** | Modifies existing student profile information. |
| **Update Subject Marks** | Adjusts marks for an enrolled subject with range validation. |
| **Search Student** | Look up student by unique ID. |
| **Delete Student** | Removes record from system. |
| **Display All** | Tabulates all student profiles and enrolled courses. |
| **Student Performance Report** | Detailed report card with total, average, grade, highest, and lowest score. |
| **Overall Summary** | Cohort analytics: class average, topper, pass/fail counts. |

---

## 🧠 Java Concepts Implemented

| Concept | Application |
| :--- | :--- |
| **Object-Oriented Programming** | High cohesion and separation of concerns between Model, Service, and Controller. |
| **Encapsulation** | Private instance variables guarded by getters, setters, and boundary checks. |
| **Collections (`ArrayList`)** | Dynamic in-memory repository for student objects. |
| **Collections (`LinkedHashMap`)** | Associative mapping of subject names to grades preserving insertion order. |
| **Java 8 Streams API** | Functional computation of minimum, maximum, and average values. |
| **Defensive Programming** | Boundary checks ($0 \le \text{marks} \le 100$) and input exception handling. |

---

## 🚀 How to Compile and Run

### Prerequisites
* **Java Development Kit (JDK) 8 or higher** (JDK 17, 21, or 23 recommended).

### Commands

1. **Compile all files:**
   ```powershell
   javac Student.java GradeCalculator.java StudentService.java Main.java
   ```

2. **Run the application:**
   ```powershell
   java Main
   ```

---

## 🖥️ Sample Execution Output

```text
===== STUDENT MANAGEMENT SYSTEM =====
1. Add Student
2. Update Student
3. Update Subject Marks
4. Search Student
5. Delete Student
6. Display All Students
7. Student Performance Report
8. Overall Performance Summary
9. Exit
Enter your choice: 1

Enter Student ID: 101
Enter Name: Rahul
Enter Age: 21
Enter number of subjects: 3
Enter Subject Name: Java
Enter Marks (0-100): 95
Enter Subject Name: DBMS
Enter Marks (0-100): 85
Enter Subject Name: Python
Enter Marks (0-100): 90
Student added successfully!

===== STUDENT MANAGEMENT SYSTEM =====
Enter your choice: 7
Enter Student ID: 101

===== ACADEMIC PERFORMANCE =====
Student: Rahul
Total Marks: 270.0
Average: 90.00
Grade: A+
Highest Marks: 95.0
Lowest Marks: 85.0
Subjects: 3
Status: Passed

===== STUDENT MANAGEMENT SYSTEM =====
Enter your choice: 8

===== OVERALL PERFORMANCE SUMMARY =====
Total Students: 1
Class Average: 90.00
Topper: Rahul
Highest Average: 90.00
Lowest Average: 90.00
Passed Students: 1
Failed Students: 0

===== STUDENT MANAGEMENT SYSTEM =====
Enter your choice: 9
Thank you for using Student Management System!
```

---

## 🗺️ Future Enhancements Roadmap

* [ ] **Version 2.0 (Database Persistence):** Replace in-memory `ArrayList` with **JDBC + MySQL/PostgreSQL** for permanent records.
* [ ] **Version 2.5 (File Export):** Export performance report cards to formatted CSV and PDF.
* [ ] **Version 3.0 (Full-Stack Enterprise):** 
  * Spring Boot RESTful API backend.
  * Role-Based Access Control (Admin, Faculty, Student).
  * React.js / Vite responsive dashboard with Chart.js analytics.

---

## 📜 License

This project is licensed under the MIT License - feel free to use it for coursework, internship assignments, and portfolio building.
