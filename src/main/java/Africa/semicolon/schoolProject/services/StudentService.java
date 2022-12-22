package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.CreateCourseResponse;
import Africa.semicolon.schoolProject.dto.response.LoginResponse;
import Africa.semicolon.schoolProject.dto.response.RegisterAllCourseResponse;
import Africa.semicolon.schoolProject.dto.response.SelectCourseResponse;

import java.util.List;

public interface StudentService {

    Student admitstudent(AdmitStudentRequest admitStudentRequest);

     long totalNumbersOfStudent();

    void deleteAll();

    Student findStudentById(String id);

    List<Student> findAllStudent();

    String deleteById(String id);

    Student updateStudentProfile(UpdatedStudentProfileRequest updatedProfileRequest);


    List<Course> findAllCourses();

 //   RegisterCourseResponse activatedCourses(RegisterCourseRequest registerCourseRequest);


    long getTotalOfRegisteredCourses();

    void deleteAllCourses();


    SelectCourseResponse selectCourseById(SelectCourseRequest selectCourseRequest);

    SelectCourseResponse selectCourseByName(SelectCourseRequest selectCourseRequest);


    LoginResponse login(LoginRest loginRest);
}
