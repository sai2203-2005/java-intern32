import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static StudentService service = new StudentService();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Update Subject Marks");
            System.out.println("4. Search Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Display All Students");
            System.out.println("7. Student Performance Report");
            System.out.println("8. Overall Performance Summary");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                continue;
            }

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    updateStudent();
                    break;

                case 3:
                    updateMarks();
                    break;

                case 4:
                    searchStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    service.displayAllStudents();
                    break;

                case 7:
                    performanceReport();
                    break;

                case 8:
                    service.overallSummary();
                    break;

                case 9:
                    System.out.println("Thank you for using Student Management System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please select 1 to 9.");
            }
        }
    }

    // ADD STUDENT
    static void addStudent() {

        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Age: ");
        int age = Integer.parseInt(sc.nextLine().trim());

        Student student = new Student(id, name, age);

        System.out.print("Enter number of subjects: ");
        int count = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < count; i++) {

            System.out.print("Enter Subject Name: ");
            String subject = sc.nextLine().trim();

            System.out.print("Enter Marks (0-100): ");
            double marks = Double.parseDouble(sc.nextLine().trim());

            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks! Enter between 0 and 100.");
                i--;
                continue;
            }

            student.addSubject(subject, marks);
        }

        service.addStudent(student);
    }

    // UPDATE STUDENT
    static void updateStudent() {

        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Enter New Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter New Age: ");
        int age = Integer.parseInt(sc.nextLine().trim());

        service.updateStudent(id, name, age);
    }

    // UPDATE MARKS
    static void updateMarks() {

        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Enter Subject Name: ");
        String subject = sc.nextLine().trim();

        System.out.print("Enter New Marks: ");
        double marks = Double.parseDouble(sc.nextLine().trim());

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks! Must be between 0 and 100.");
            return;
        }

        service.updateMarks(id, subject, marks);
    }

    // SEARCH STUDENT
    static void searchStudent() {

        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        Student student = service.searchStudent(id);

        if (student != null) {
            student.displayStudent();
        } else {
            System.out.println("Student not found!");
        }
    }

    // DELETE STUDENT
    static void deleteStudent() {

        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        service.deleteStudent(id);
    }

    // INDIVIDUAL PERFORMANCE REPORT
    static void performanceReport() {

        System.out.print("Enter Student ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        Student student = service.searchStudent(id);

        if (student != null) {
            GradeCalculator.performanceReport(student);
        } else {
            System.out.println("Student not found!");
        }
    }
}

