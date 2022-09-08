package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;

import java.util.List;

public interface StudentService {
    void deleteStudent(Student student);

    Student findStudentById(String studentId);

    Student saveNewStudent(Student student);

    List<Student> getAllStudents();



    long size();



    Student getStudentByEmail(String email);

    Student findStudentByEmail(String email);
}
