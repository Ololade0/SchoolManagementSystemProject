package Africa.semicolon.schoolProject.services;


import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.dto.request.CreateCourseRequest;
import Africa.semicolon.schoolProject.dto.request.SelectCourseRequest;
import Africa.semicolon.schoolProject.dto.request.UpdateCourseRequest;

import java.util.List;

public interface CourseServices {
  //  Course registerAllCourse(RegisterAllCourseRequest registerAllCourseRequest);

    long totalNumberOfCourses();

    void deleteAll();

    Course findCourseById(String id);

    List<Course> findAllCourses();

    String deleteById(String id);

    Course updateCourseProfile(UpdateCourseRequest updateCourseRequest);


    Course registerCourse(CreateCourseRequest createCourseRequest);

    Course selectCoursesById(SelectCourseRequest selectCourseRequest);

    Course selectCoursesByName(String courseName);

    ;
}
