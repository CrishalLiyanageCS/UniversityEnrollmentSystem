package universityenrollmentsystem;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ExtendedModelBehaviourTest {
    // Test 1: Verifies that getStringDate() formats a known LocalDate as "dd/MM/yyyy"
    @Test
    public void testPersonDateFormatting() {
        Student person = new Student("Alice", "Brown");
        person.setDob(LocalDate.of(2000, 3, 15));

        assertEquals("15/03/2000", person.getStringDate());
    }

    // Test 2: Verifies that Student toString() contains the course title and modules count
    @Test
    public void testStudentToStringContainsCourseAndModules() {
        Student student = new Student("John", "Smith");
        student.setCourseTitle("BSc Computer Science");
        student.setModulesEnrolled(6);

        String result = student.toString();

        assertTrue(result.contains("BSc Computer Science"));
        assertTrue(result.contains("6"));
    }

    // Test 3: Verifies that Lecturer correctly stores and returns the specialisation
    @Test
    public void testLecturerSpecialisation() {
        Lecturer lecturer = new Lecturer("Jane", "Doe");
        lecturer.setSpecialisation("Artificial Intelligence");

        assertEquals("Artificial Intelligence", lecturer.getSpecialisation());
    }

}