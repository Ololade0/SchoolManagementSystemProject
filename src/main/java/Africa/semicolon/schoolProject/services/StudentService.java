package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.dto.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    void deleteStudent(Student student);

    Student findStudentById(String studentId);

    Student saveNewStudent(Student student);

    List<Student> getAllStudents();
}
