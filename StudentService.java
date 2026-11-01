import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    // ADD STUDENT
    public void addStudent(Student student) {

        if (searchStudent(student.getId()) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        students.add(student);
        System.out.println("Student added successfully!");
    }

    // SEARCH STUDENT
    public Student searchStudent(int id) {

        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    // UPDATE STUDENT
    public void updateStudent(int id, String name, int age) {

        Student student = searchStudent(id);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        student.setName(name);
        student.setAge(age);
        System.out.println("Student updated successfully!");
    }

    // DELETE STUDENT
    public void deleteStudent(int id) {

        Student student = searchStudent(id);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        students.remove(student);
        System.out.println("Student deleted successfully!");
    }

    // UPDATE SUBJECT MARKS
    public void updateMarks(int id, String subject, double marks) {

        Student student = searchStudent(id);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        if (!student.getSubjectMarks().containsKey(subject)) {
            System.out.println("Subject not found!");
            return;
        }

        student.addSubject(subject, marks);
        System.out.println("Marks updated successfully!");
    }

    // DISPLAY ALL STUDENTS
    public void displayAllStudents() {

        if (students.isEmpty()) {
            System.out.println("No student records available!");
            return;
        }

        for (Student student : students) {
            student.displayStudent();
        }
    }

    // OVERALL PERFORMANCE SUMMARY
    public void overallSummary() {

        if (students.isEmpty()) {
            System.out.println("No student records available!");
            return;
        }

        double totalAverage = 0;
        double highestAverage = -1;
        double lowestAverage = Double.MAX_VALUE;

        Student topper = null;
        int passed = 0;
        int failed = 0;

        for (Student student : students) {

            double average = GradeCalculator.calculateAverage(student);
            totalAverage += average;

            if (average > highestAverage) {
                highestAverage = average;
                topper = student;
            }

            if (average < lowestAverage) {
                lowestAverage = average;
            }

            if (GradeCalculator.calculateGrade(average).equals("F")) {
                failed++;
            } else {
                passed++;
            }
        }

        System.out.println("\n===== OVERALL PERFORMANCE SUMMARY =====");
        System.out.println("Total Students: " + students.size());
        System.out.printf("Class Average: %.2f%n", (totalAverage / students.size()));
        System.out.println("Topper: " + (topper != null ? topper.getName() : "N/A"));
        System.out.printf("Highest Average: %.2f%n", highestAverage);
        System.out.printf("Lowest Average: %.2f%n", (lowestAverage == Double.MAX_VALUE ? 0.0 : lowestAverage));
        System.out.println("Passed Students: " + passed);
        System.out.println("Failed Students: " + failed);
    }
}

