package fi.tuni.prog3.junitattainment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AttainmentTest {

    @Test
    public void testConstructorThrowsIllegalArgumentException() {
        System.out.println("testConstructorThrowsIllegalArgumentException");
        assertThrows(IllegalArgumentException.class, () -> {
            Attainment attainment = new Attainment("COMP.CS.140", "12345", -1);
        });
    }

    @Test
    public void testGetCourseCode() {
        System.out.println("testGetCourseCode");
        Attainment instance = new Attainment("COMP.CS.140", "12345", 4);
        String expResult = "COMP.CS.140";
        String result = instance.getCourseCode();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetStudentNumber() {
        System.out.println("testGetStudentNumber");
        Attainment instance = new Attainment("COMP.CS.140", "12345", 4);
        String expResult = "12345";
        String result = instance.getStudentNumber();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetGrade() {
        System.out.println("testGetGrade");
        Attainment instance = new Attainment("COMP.CS.140", "12345", 4);
        int expResult = 4;
        int result = instance.getGrade();
        assertEquals(expResult, result);
    }

    @Test
    public void testToString() {
        System.out.println("testToString");
        Attainment instance = new Attainment("COMP.CS.140", "12345", 4);
        String expResult = "COMP.CS.140 12345 4";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testCompareTo() {
        System.out.println("testCompareTo");
        Attainment other = new Attainment("COMP.CS.140", "12345", 4);
        Attainment instance = new Attainment("COMP.CS.300", "12345", 5);
        int expResult = 2;
        int result = instance.compareTo(other);
        assertEquals(expResult, result);
    }
}
