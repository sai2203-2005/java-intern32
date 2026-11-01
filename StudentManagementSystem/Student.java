import java.util.LinkedHashMap;
import java.util.Map;

public class Student {

    private int id;
    private String name;
    private int age;

    private Map<String, Double> subjectMarks;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.subjectMarks = new LinkedHashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Map<String, Double> getSubjectMarks() {
        return subjectMarks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addSubject(String subject, double marks) {
        subjectMarks.put(subject, marks);
    }

    public void displayStudent() {

        System.out.println("\nStudent ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        System.out.println("Subject Marks:");

        if (subjectMarks.isEmpty()) {
            System.out.println("  No subjects enrolled yet.");
            return;
        }

        for (Map.Entry<String, Double> entry : subjectMarks.entrySet()) {
            System.out.println("  " + entry.getKey() + " : " + entry.getValue());
        }
    }
}
