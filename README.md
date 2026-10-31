# 🚀 Java 8 Streams API: Employee Records Analysis & Interview Guide

[![Java](https://img.shields.io/badge/Java-8%2B%20%2F%2023-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Build](https://img.shields.io/badge/Build-Passing-brightgreen.svg)]()

A comprehensive, production-grade guide and reference implementation for mastering **Java 8 Functional Programming and Streams API**. This repository demonstrates practical data processing, statistical aggregation, grouping, and filtering using real-world employee dataset records.

---

## 📌 Table of Contents

- [Overview](#-overview)
- [Domain Model](#-domain-model)
- [Core Stream Operations](#-core-stream-operations)
  - [1. Calculate Average Salary of All Employees](#1-calculate-average-salary-of-all-employees)
  - [2. Find the Highest-Paid Employee](#2-find-the-highest-paid-employee)
  - [3. Count Total Employees](#3-count-total-employees)
  - [4. Department-wise Employee Count](#4-department-wise-employee-count)
  - [5. Department-wise Average Salary](#5-department-wise-average-salary)
  - [6. Highest-Paid Employee in Each Department](#6-highest-paid-employee-in-each-department)
  - [7. Complete Department Statistics with DoubleSummaryStatistics](#7-complete-department-statistics-with-doublesummarystatistics)
- [Practice Interview Questions & Solutions](#-practice-interview-questions--solutions)
  - [Q1: Salary Greater Than 60,000](#q1-salary-greater-than-60000)
  - [Q2: Second-Highest Salary](#q2-second-highest-salary)
  - [Q3: Sort Employees by Salary (Descending)](#q3-sort-employees-by-salary-descending)
  - [Q4: Filter Employees in IT Department](#q4-filter-employees-in-it-department)
  - [Q5: Total Salary Paid by Each Department](#q5-total-salary-paid-by-each-department)
- [Senior Interview Pro-Tips](#-senior-interview-pro-tips)
  - [Pro-Tip 1: Eliminating Optional in groupingBy via collectingAndThen](#pro-tip-1-eliminating-optional-in-groupingby-via-collectingandthen)
  - [Pro-Tip 2: Transforming Group Values via Collectors.mapping](#pro-tip-2-transforming-group-values-via-collectorsmapping)
  - [Pro-Tip 3: Binary Segmentation via Collectors.partitioningBy](#pro-tip-3-binary-segmentation-via-collectorspartitioningby)
- [Stream Operations Cheat Sheet](#-stream-operations-cheat-sheet)
- [How to Run](#-how-to-run)
- [Execution Output](#-execution-output)

---

## 📖 Overview

The **Java Stream API** (introduced in Java 8) enables declarative, functional-style data processing on collections. Rather than writing imperative `for`/`while` loops and mutating shared state, Streams allow developers to compose pipelines of **intermediate operations** (e.g., `filter`, `map`, `sorted`) terminated by a **terminal operation** (e.g., `collect`, `count`, `reduce`, `average`).

This repository provides clear, idiomatic implementations of common data aggregation and transformation tasks on an employee dataset.

---

## 🏢 Domain Model

The core entity is the `Employee` model:

```java
class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + '\'' +
               ", department='" + department + '\'' +
               ", salary=" + salary + '}';
    }
}
```

### Dataset

```java
List<Employee> employees = Arrays.asList(
    new Employee(1, "Siva", "IT", 60000),
    new Employee(2, "Ravi", "HR", 45000),
    new Employee(3, "Kiran", "IT", 80000),
    new Employee(4, "Priya", "Finance", 70000),
    new Employee(5, "Anil", "HR", 50000),
    new Employee(6, "Divya", "Finance", 90000),
    new Employee(7, "Rahul", "IT", 75000),
    new Employee(8, "Sneha", "HR", 55000)
);
```

---

## ⚙️ Core Stream Operations

### 1. Calculate Average Salary of All Employees

```java
double averageSalary = employees.stream()
        .mapToDouble(Employee::getSalary)
        .average()
        .orElse(0.0);
```
- `mapToDouble(Employee::getSalary)`: Creates a primitive `DoubleStream`, preventing boxing overhead.
- `average()`: Computes the arithmetic mean, returning an `OptionalDouble`.
- `orElse(0.0)`: Safe fallback if the stream is empty.

---

### 2. Find the Highest-Paid Employee

```java
Employee highestPaid = employees.stream()
        .max(Comparator.comparingDouble(Employee::getSalary))
        .orElse(null);
```
- `Comparator.comparingDouble(...)`: Type-safe primitive extractor.
- `max(...)`: Terminal reduction returning `Optional<Employee>`.

---

### 3. Count Total Employees

```java
long totalEmployees = employees.stream().count();
```

---

### 4. Department-wise Employee Count

```java
Map<String, Long> departmentCount = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
        ));
```
- Categorizes employees by department key and applies downstream `Collectors.counting()`.

---

### 5. Department-wise Average Salary

```java
Map<String, Double> departmentAverage = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
        ));
```

---

### 6. Highest-Paid Employee in Each Department

```java
Map<String, Optional<Employee>> highestByDept = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
        ));
```

---

### 7. Complete Department Statistics with `DoubleSummaryStatistics`

```java
Map<String, DoubleSummaryStatistics> statistics = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.summarizingDouble(Employee::getSalary)
        ));
```

`DoubleSummaryStatistics` provides in a single pass:
- `getCount()`: Total records in department.
- `getSum()`: Total payroll cost.
- `getAverage()`: Department average salary.
- `getMin()`: Entry-level / minimum salary.
- `getMax()`: Top earner salary.

---

## 🎯 Practice Interview Questions & Solutions

### Q1: Salary Greater Than 60,000

```java
List<Employee> highEarners = employees.stream()
        .filter(e -> e.getSalary() > 60000)
        .collect(Collectors.toList());
```

---

### Q2: Second-Highest Salary

```java
Double secondHighestSalary = employees.stream()
        .map(Employee::getSalary)
        .distinct()                           // Critical: prevents ties from occupying both 1st and 2nd rank
        .sorted(Comparator.reverseOrder())    // Descending order
        .skip(1)                              // Bypass maximum
        .findFirst()                          // Retrieve 2nd highest
        .orElse(null);
```

> **Why `.distinct()` is essential**: If two employees share the top salary (e.g., \$90,000 and \$90,000), skipping 1 without `distinct()` incorrectly returns \$90,000. `distinct()` guarantees true rank selection.

---

### Q3: Sort Employees by Salary (Descending)

```java
List<Employee> sortedBySalaryDesc = employees.stream()
        .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
        .collect(Collectors.toList());
```

---

### Q4: Filter Employees in IT Department

```java
List<Employee> itEmployees = employees.stream()
        .filter(e -> "IT".equalsIgnoreCase(e.getDepartment()))
        .collect(Collectors.toList());
```

> **Defensive Coding Tip**: Use `"IT".equalsIgnoreCase(e.getDepartment())` rather than `e.getDepartment().equals("IT")` to avoid `NullPointerException` if department is null.

---

### Q5: Total Salary Paid by Each Department

```java
Map<String, Double> totalSalaryByDept = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.summingDouble(Employee::getSalary)
        ));
```

---

## 💡 Senior Interview Pro-Tips

### Pro-Tip 1: Eliminating `Optional` in `groupingBy` via `collectingAndThen`

When using `Collectors.maxBy()`, the result type is `Map<String, Optional<Employee>>`. To unwrap the `Optional` cleanly during collection, use `Collectors.collectingAndThen`:

```java
Map<String, Employee> topEarnerPerDept = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                        Optional::get
                )
        ));
```

---

### Pro-Tip 2: Transforming Group Values via `Collectors.mapping`

To extract just employee names grouped by department instead of full objects:

```java
Map<String, List<String>> namesByDept = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(Employee::getName, Collectors.toList())
        ));
// Result: {Finance=[Priya, Divya], HR=[Ravi, Anil, Sneha], IT=[Siva, Kiran, Rahul]}
```

---

### Pro-Tip 3: Binary Segmentation via `Collectors.partitioningBy`

When dividing data into exactly two groups based on a predicate, `partitioningBy()` is significantly faster and cleaner than multiple `filter()` calls:

```java
Map<Boolean, List<Employee>> partitioned = employees.stream()
        .collect(Collectors.partitioningBy(e -> e.getSalary() > 60000));

List<Employee> highEarners = partitioned.get(true);       // Salary > 60,000
List<Employee> standardEarners = partitioned.get(false);  // Salary <= 60,000
```

---

## 📊 Stream Operations Cheat Sheet

| Operation | Type | Return Type | Description |
| :--- | :--- | :--- | :--- |
| `filter(Predicate<T>)` | Intermediate | `Stream<T>` | Filters elements based on condition |
| `map(Function<T, R>)` | Intermediate | `Stream<R>` | Transforms elements into another type |
| `mapToDouble(ToDoubleFunction<T>)` | Intermediate | `DoubleStream` | Avoids boxing overhead for numeric operations |
| `distinct()` | Intermediate | `Stream<T>` | Removes duplicates using `equals()`/`hashCode()` |
| `sorted(Comparator<T>)` | Intermediate | `Stream<T>` | Sorts stream elements |
| `skip(n)` | Intermediate | `Stream<T>` | Discards first `n` elements |
| `limit(n)` | Intermediate | `Stream<T>` | Truncates stream to max length `n` |
| `count()` | Terminal | `long` | Returns total element count |
| `collect(Collector<T, A, R>)` | Terminal | `R` | Accumulates elements into collections/maps |
| `max(Comparator<T>)` | Terminal | `Optional<T>` | Returns highest element |
| `min(Comparator<T>)` | Terminal | `Optional<T>` | Returns lowest element |
| `average()` | Terminal (on primitive stream) | `OptionalDouble` | Computes arithmetic mean |

---

## 🚀 How to Run

### Prerequisites
* Java JDK 8 or higher (Java 17 / 21 / 23 recommended).

### Compilation & Execution

```powershell
# Compile
javac Practise.java

# Run
java Practise
```

---

## 🖥️ Execution Output

```text
========== 1. CORE STREAM OPERATIONS ==========

Average Salary: 65625.00
Highest Paid Employee: Employee{id=6, name='Divya', department='Finance', salary=90000.0}
Total Employees: 8

Department-wise Employee Count:
  Finance : 2
  HR : 3
  IT : 3

Department-wise Average Salary:
  Finance : 80000.00
  HR : 50000.00
  IT : 71666.67

Highest-paid Employee in Each Department:
  Finance : Divya - 90000.0
  HR : Sneha - 55000.0
  IT : Kiran - 80000.0

Complete Department Statistics:
  Department: Finance
    Count   : 2
    Average : 80000.00
    Max     : 90000.00
    Min     : 70000.00
    Sum     : 160000.00
  -------------------------
  Department: HR
    Count   : 3
    Average : 50000.00
    Max     : 55000.00
    Min     : 45000.00
    Sum     : 150000.00
  -------------------------
  Department: IT
    Count   : 3
    Average : 71666.67
    Max     : 80000.00
    Min     : 60000.00
    Sum     : 215000.00
  -------------------------

========== 2. PRACTICE QUESTIONS & ANSWERS ==========

Q1: Employees with salary > 60,000:
  Kiran (80000.0)
  Priya (70000.0)
  Divya (90000.0)
  Rahul (75000.0)

Q2: Second-highest salary:
  Second Highest Salary: 80000.0

Q3: Employees sorted by salary (descending):
  Divya    | Finance  | 90000.00
  Kiran    | IT       | 80000.00
  Rahul    | IT       | 75000.00
  Priya    | Finance  | 70000.00
  Siva     | IT       | 60000.00
  Sneha    | HR       | 55000.00
  Anil     | HR       | 50000.00
  Ravi     | HR       | 45000.00

Q4: Employees in IT department:
  Siva (ID: 1)
  Kiran (ID: 3)
  Rahul (ID: 7)

Q5: Total salary paid by each department:
  Finance  : 160000.00
  HR       : 150000.00
  IT       : 215000.00

========== 3. SENIOR DEVELOPER INTERVIEW PRO-TIPS ==========

Pro-Tip 1: Highest-paid employee per dept without Optional wrapper:
  Finance -> Divya ($90000.0)
  HR -> Sneha ($55000.0)
  IT -> Kiran ($80000.0)

Pro-Tip 2: Employee names list per department (groupingBy + mapping):
  Finance -> [Priya, Divya]
  HR -> [Ravi, Anil, Sneha]
  IT -> [Siva, Kiran, Rahul]

Pro-Tip 3: Partition employees by salary threshold (> 60k):
  High Earners (>60k) count: 4
  Standard Earners (<=60k) count: 4
```

---

## 📜 License

This project is licensed under the MIT License - feel free to use it for personal study, technical interview prep, and production reference.

