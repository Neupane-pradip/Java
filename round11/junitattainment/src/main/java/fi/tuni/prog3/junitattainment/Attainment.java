package fi.tuni.prog3.junitattainment;

public class Attainment implements Comparable<Attainment> {
    private final String courseCode;
    private final String studentNumber;
    private final int grade;

    public Attainment(String courseCode, String studentNumber, int grade) {
        validateParameters(courseCode, studentNumber, grade);
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
    }

    private void validateParameters(String courseCode, String studentNumber, int grade) {
        if (courseCode == null || studentNumber == null || grade < 0 || grade > 5) {
            throw new IllegalArgumentException("Invalid course code, student number, or grade!");
        }
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", courseCode, studentNumber, grade);
    }

    @Override
    public int compareTo(Attainment other) {
        int compareByStudentNumber = this.studentNumber.compareTo(other.studentNumber);
        if (compareByStudentNumber == 0) {
            return this.courseCode.compareTo(other.courseCode);
        }
        return compareByStudentNumber;
    }
}
