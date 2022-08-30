package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.*;

import java.util.List;
import java.util.Optional;


public interface SchoolService {

    AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest);


    String deleteStudent(DeleteStudentRequest deleteStudentRequest);

    List<Student> getAllStudents();


    void deleteAll();

    List<AllStudentResponse> findStudentBelongingTo(String firstName);

    CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest);

    String deleteCourse(DeleteCourseRequest deleteCourseRequest);

    List<Course> getAllCourses();



//    Course findCourseByName(String courseName);

    Optional<Course> getACourse(GetACourseRequest getACourseRequest);

    School findSchoolByName(String schoolName);

    long size();

    School save(School newSchool);

    List<School>findAll();

    Student getStudentById(String id);

    RegisterSchoolResponse registerSchool(RegisterSchoolRequest registerSchoolRequest);

    long totalUsers();

    UpdateCourseResponse updateCourse(UpdateCourseRequest updateCourseRequest);

    Student getStudentByEmail(String email);

    Course getCourseByName(String courseName);
}
