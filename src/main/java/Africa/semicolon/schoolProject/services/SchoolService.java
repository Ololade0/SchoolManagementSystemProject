package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.*;

import java.util.List;


public interface SchoolService {

    AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest);

    CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest);


    DeleteStudentResponse expelStudent(DeleteStudentRequest deleteStudentRequest);  // changed your deleteRequest class  type to String type below

    List<Student> getAllStudentsRegisteredIn(String schoolName);

    FindStudentResponse findStudentRegisteredIn(FindStudentRequest findStudentRequest);


    String deleteCourse(DeleteCourseRequest deleteCourseRequest);

    List<Course> getAllCoursesOfferedIn(String schoolName);

    FindCourseResponse findCourseOfferedIn(FindCourseRequest findCourseRequest);

    long size();

    RegisterSchoolResponse registerSchool(RegisterSchoolRequest registerSchoolRequest);
}
