package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;
import Africa.semicolon.schoolProject.dto.request.DeleteStudentRequest;

import java.util.List;

public interface StudentService {
    Student deleteStudent(DeleteStudentRequest student);

    Student saveNewStudent(AdmitStudentRequest admitStudentRequest);

    List<Student> getAllStudents();

    long size();
}
