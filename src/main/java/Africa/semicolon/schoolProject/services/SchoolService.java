package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.AdmitStudentResponse;
import Africa.semicolon.schoolProject.dto.response.AllStudentResponse;
import Africa.semicolon.schoolProject.dto.response.CreateCourseResponse;

import java.util.List;
import java.util.Optional;


public interface SchoolService {

    AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest);


    void deleteStudent(DeleteStudentRequest deleteStudentRequest);  // changed your deleteRequest class  type to String type below

    List<Student> getAllStudents();


    void deleteAll();

    List<AllStudentResponse> findStudentBelongingTo(String firstName);

    CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest);

    String deleteCourse(DeleteCourseRequest deleteCourseRequest);

    List<Course> getAllCourses();



    Course getCourseByName(String courseName);

    Optional<Course> getACourses(GetACourseRequest getACourseRequest);

    School findSchoolByName(String schoolName);

    long size();

    School save(School newSchool);

    List<School>findAll();
}
