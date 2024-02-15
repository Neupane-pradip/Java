
package fi.tuni.prog3.comparison;
/**
 *
 * @author Pradip_Neuapne
 */

import java.util.Comparator;

public class Attainment implements Comparable<Attainment> {
    private String courseCode;
    private String studentNumber;
    private int grade;

    public static final Comparator<Attainment> CODE_STUDENT_CMP = new Comparator<Attainment>() {
        @Override
        public int compare(Attainment attainment1, Attainment attainment2) {
            int codeComparison = attainment1.courseCode.compareTo(attainment2.courseCode);
            if (codeComparison != 0) {
                return codeComparison;
            }
            return attainment1.studentNumber.compareTo(attainment2.studentNumber);
        }
    };

    public static final Comparator<Attainment> CODE_GRADE_CMP = new Comparator<Attainment>() {
        @Override
        public int compare(Attainment attainment1, Attainment attainment2) {
            int codeComparison = attainment1.courseCode.compareTo(attainment2.courseCode);
            if (codeComparison != 0) {
                return codeComparison;
            }
            // Compare grades in reverse order for descending order
            return Integer.compare(attainment2.grade, attainment1.grade);
        }
    };

    public Attainment(String courseCode, String studentNumber, int grade) {
        this.courseCode = courseCode;
        this.studentNumber = studentNumber;
        this.grade = grade;
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
        return courseCode + " " + studentNumber + " " + grade;
    }

    @Override
    public int compareTo(Attainment other) {
        int studentComparison = this.studentNumber.compareTo(other.studentNumber);
        if (studentComparison != 0) {
            return studentComparison;
        }
        return this.courseCode.compareTo(other.courseCode);
    }
}
