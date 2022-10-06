package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.AdmitStudentRequest;
import Africa.semicolon.schoolProject.dto.request.RegisterCourseRequest;
import Africa.semicolon.schoolProject.dto.request.SelectCourseRequest;
import Africa.semicolon.schoolProject.dto.request.UpdatedStudentProfileRequest;
import Africa.semicolon.schoolProject.dto.response.RegisterSchoolResponse;
import Africa.semicolon.schoolProject.dto.response.SelectCourseResponse;

import java.util.List;

public interface StudentService {

    Student admitstudent(AdmitStudentRequest admitStudentRequest);

     long totalNumbersOfStudent();

    void deleteAll();

    Student findStudentById(String id);

    List<Student> findAllStudent();

    void deleteById(String id);

    Student updateStudentProfile(UpdatedStudentProfileRequest updatedProfileRequest);


    List<Course> findAllCourses();

    RegisterSchoolResponse activatedCourses(RegisterCourseRequest registerCourseRequest);


    long getTotalOfActivatedCourses();

    void deleteAllCourses();


    SelectCourseResponse selectCourseById(SelectCourseRequest selectCourseRequest);
}
