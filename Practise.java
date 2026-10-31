import java.util.*;
import java.util.stream.*;

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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary + '}';
    }
}

public class Practise {

    public static void main(String[] args) {

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

        System.out.println("========== 1. CORE STREAM OPERATIONS ==========\n");

        // 1. Calculate Average Salary of all employees
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
        System.out.printf("Average Salary: %.2f%n", averageSalary);

        // 2. Find the highest-paid employee
        Employee highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
        System.out.println("Highest Paid Employee: " + highestPaid);

        // 3. Count total employees
        long totalEmployees = employees.stream().count();
        System.out.println("Total Employees: " + totalEmployees);

        // 4. Department-wise employee count
        System.out.println("\nDepartment-wise Employee Count:");
        Map<String, Long> departmentCount = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
        departmentCount.forEach((dept, count) ->
                System.out.println("  " + dept + " : " + count));

        // 5. Department-wise average salary
        System.out.println("\nDepartment-wise Average Salary:");
        Map<String, Double> departmentAverage = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        departmentAverage.forEach((dept, avg) ->
                System.out.printf("  %s : %.2f%n", dept, avg));

        // 6. Highest-paid employee in each department (Standard groupingBy with maxBy)
        System.out.println("\nHighest-paid Employee in Each Department:");
        Map<String, Optional<Employee>> highestByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
                ));
        highestByDepartment.forEach((dept, emp) ->
                System.out.println("  " + dept + " : " + emp.map(Employee::getName).orElse("None")
                        + " - " + emp.map(e -> String.valueOf(e.getSalary())).orElse("0.0")));

        // 7. Complete department statistics (summarizingDouble)
        System.out.println("\nComplete Department Statistics:");
        Map<String, DoubleSummaryStatistics> statistics = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summarizingDouble(Employee::getSalary)
                ));

        statistics.forEach((dept, stats) -> {
            System.out.println("  Department: " + dept);
            System.out.println("    Count   : " + stats.getCount());
            System.out.printf("    Average : %.2f%n", stats.getAverage());
            System.out.printf("    Max     : %.2f%n", stats.getMax());
            System.out.printf("    Min     : %.2f%n", stats.getMin());
            System.out.printf("    Sum     : %.2f%n", stats.getSum());
            System.out.println("  -------------------------");
        });

        System.out.println("\n========== 2. PRACTICE QUESTIONS & ANSWERS ==========\n");

        // Practice Q1: Find employees whose salary is greater than 60,000
        System.out.println("Q1: Employees with salary > 60,000:");
        List<Employee> highEarners = employees.stream()
                .filter(e -> e.getSalary() > 60000)
                .collect(Collectors.toList());
        highEarners.forEach(e -> System.out.println("  " + e.getName() + " (" + e.getSalary() + ")"));

        // Practice Q2: Find the second-highest salary
        System.out.println("\nQ2: Second-highest salary:");
        Double secondHighestSalary = employees.stream()
                .map(Employee::getSalary)
                .distinct() // Prevent duplicate values from skewing the rank
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println("  Second Highest Salary: " + secondHighestSalary);

        // Practice Q3: Sort employees by salary in descending order
        System.out.println("\nQ3: Employees sorted by salary (descending):");
        List<Employee> sortedBySalaryDesc = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
        sortedBySalaryDesc.forEach(e ->
                System.out.printf("  %-8s | %-8s | %.2f%n", e.getName(), e.getDepartment(), e.getSalary()));

        // Practice Q4: Find employees working in the IT department
        System.out.println("\nQ4: Employees in IT department:");
        List<Employee> itEmployees = employees.stream()
                .filter(e -> "IT".equalsIgnoreCase(e.getDepartment()))
                .collect(Collectors.toList());
        itEmployees.forEach(e -> System.out.println("  " + e.getName() + " (ID: " + e.getId() + ")"));

        // Practice Q5: Find the total salary paid by each department
        System.out.println("\nQ5: Total salary paid by each department:");
        Map<String, Double> totalSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ));
        totalSalaryByDept.forEach((dept, total) ->
                System.out.printf("  %-8s : %.2f%n", dept, total));

        System.out.println("\n========== 3. SENIOR DEVELOPER INTERVIEW PRO-TIPS ==========\n");

        // Interview Tip 1: Unwrapping Optional inside groupingBy with collectingAndThen
        System.out.println("Pro-Tip 1: Highest-paid employee per dept without Optional wrapper:");
        Map<String, Employee> cleanHighestByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                                Optional::get
                        )
                ));
        cleanHighestByDept.forEach((dept, emp) ->
                System.out.println("  " + dept + " -> " + emp.getName() + " ($" + emp.getSalary() + ")"));

        // Interview Tip 2: Extracting only names per department (groupingBy + mapping)
        System.out.println("\nPro-Tip 2: Employee names list per department (groupingBy + mapping):");
        Map<String, List<String>> namesByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toList())
                ));
        namesByDept.forEach((dept, names) ->
                System.out.println("  " + dept + " -> " + names));

        // Interview Tip 3: Partitioning employees (salary > 60k vs <= 60k)
        System.out.println("\nPro-Tip 3: Partition employees by salary threshold (> 60k):");
        Map<Boolean, List<Employee>> partitionedBySalary = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 60000));
        System.out.println("  High Earners (>60k) count: " + partitionedBySalary.get(true).size());
        System.out.println("  Standard Earners (<=60k) count: " + partitionedBySalary.get(false).size());
    }
}

