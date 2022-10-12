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

    String deleteAllSchools();

    String deleteById(String id);


    School updateSchoolProfile(UpdateSchoolProfileRequest updateSchoolProfileRequest);

    AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest);



    long totalNumberOfStudents();

    String deleteAllStudents();

    Student findStudentById(GetStudentRequest getStudentRequest);



    DeleteStudentResponse deleteStudentById(DeleteStudentRequest deleteStudentRequest);


    UpdateStudentProfileResponse updateStudentProfile(UpdatedStudentProfileRequest updatedStudentProfileRequest);


    long totalNumberOfCourses();

    void deleteAllCourses();


    DeleteCourseResponse deleteCourseById(DeleteCourseRequest deleteCourseRequest);


    UpdateCourseResponse updateCourseProfile(UpdateCourseRequest updateCourseRequest);


    List<Student> findAllStudents(GetAllStudentRequest getAllStudentRequest);

    Course findCourseById(GetACourseRequest getACourseRequest);

    List<Course> findAllCourses(FindAllCourses findAllCourses);

    CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest);
}
