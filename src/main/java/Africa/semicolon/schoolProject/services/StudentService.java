package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;
import Africa.semicolon.schoolProject.dto.request.SelectCourseRequest;
import Africa.semicolon.schoolProject.dto.response.AdmitStudentResponse;
import Africa.semicolon.schoolProject.dto.response.SelectCourseReponse;

import java.util.List;

public interface StudentService {
    void deleteStudent(Student student);

    Student findStudentById(String studentId);

    Student saveNewStudent(Student student);

    List<Student> getAllStudents();



    long size();



    Student getStudentByEmail(String email);

    Student findStudentByEmail(String email);

    void delete(Student studToDel);

    void deleteAll();

    SelectCourseReponse selectCourse(SelectCourseRequest selectCourseRequest);

    Student admitStudent(AdmitStudentRequest admitStudentRequest2);
}
