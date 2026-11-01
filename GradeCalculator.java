import java.util.Map;

public class GradeCalculator {

    public static double calculateTotal(Student student) {

        double total = 0;

        for (double marks : student.getSubjectMarks().values()) {
            total += marks;
        }

        return total;
    }

    public static double calculateAverage(Student student) {

        int count = student.getSubjectMarks().size();

        if (count == 0) {
            return 0;
        }

        return calculateTotal(student) / count;
    }

    public static String calculateGrade(double average) {

        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static double highestMarks(Student student) {

        return student.getSubjectMarks()
                .values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0);
    }

    public static double lowestMarks(Student student) {

        return student.getSubjectMarks()
                .values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0);
    }

    public static void performanceReport(Student student) {

        double total = calculateTotal(student);
        double average = calculateAverage(student);
        String grade = calculateGrade(average);

        System.out.println("\n===== ACADEMIC PERFORMANCE =====");
        System.out.println("Student: " + student.getName());
        System.out.println("Total Marks: " + total);
        System.out.printf("Average: %.2f%n", average);
        System.out.println("Grade: " + grade);
        System.out.println("Highest Marks: " + highestMarks(student));
        System.out.println("Lowest Marks: " + lowestMarks(student));
        System.out.println("Subjects: " + student.getSubjectMarks().size());
        System.out.println("Status: " + (grade.equals("F") ? "Needs Improvement" : "Passed"));
    }
}

