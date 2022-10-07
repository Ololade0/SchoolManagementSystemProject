package Africa.semicolon.schoolProject.services;


import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.*;

import java.util.List;

public interface SchoolService {

    RegisterSchoolResponse registerSchool(RegisterSchoolRequest registerSchoolRequest);

    long TotalNumbersOfSchool();


    School findSchoolById(String id);

    List<School> findAllSchools();

    void deleteAllSchools();

    void deleteById(String id);


    School updateSchoolProfile(UpdateSchoolProfileRequest updateSchoolProfileRequest);

    AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest);



    long totalNumberOfStudents();

    void deleteAllStudents();

    Student findStudentById(GetStudentRequest getStudentRequest);

    Student findStudentsById(String studentI);

    String deleteStudentById(DeleteStudentRequest deleteAllStudentRequest);

    List<Student> findAllStudents();

    UpdateStudentProfileResponse updateStudentProfile(UpdatedStudentProfileRequest updatedStudentProfileRequest);

    RegisterCourseResponse createCourse(RegisterCourseRequest createCourseRequest);

    long totalNumberOfCourses();

    void deleteAllCourses();


    String deleteCourseById(DeleteCourseRequest deleteCourseRequest);

    Course findCourseById(String courseId);

    List<Course> findAllCourses();

    UpdateCourseResponse updateCourseProfile(UpdateCourseRequest updateCourseRequest);
}
