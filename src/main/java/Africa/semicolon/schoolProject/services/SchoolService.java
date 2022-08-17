package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.dto.model.Course;
import Africa.semicolon.schoolProject.data.dto.model.School;
import Africa.semicolon.schoolProject.data.dto.model.Student;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.AdmitStudentResponse;
import Africa.semicolon.schoolProject.dto.response.AllStudentResponse;
import Africa.semicolon.schoolProject.dto.response.CreateCourseResponse;

import java.util.List;


public interface SchoolService {

    AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest);


    void deleteStudent(DeleteStudentRequest deleteStudentRequest);  // changed your deleteRequest class  type to String type below

    List<Student> getAllStudents();


    void deleteAll();

    List<AllStudentResponse> findStudentBelongingTo(String firstName);

    CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest);

    void deleteCourse(DeleteCourseRequest deleteCourseRequest);

    List<Course> getAllCourses();

    Course findACourse( String courseName);
}
