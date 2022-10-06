package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;
import Africa.semicolon.schoolProject.dto.request.UpdatedStudentProfileRequest;

import java.util.List;

public interface StudentService {

    Student admitstudent(AdmitStudentRequest admitStudentRequest);

     long TotalNUmbersOfStudent();

    void deleteAll();

    Student findStudentById(String id);

    List<Student> findAllStudent();

    void deleteById(String id);

    Student updateStudentProfile(UpdatedStudentProfileRequest updatedProfileRequest);
}
