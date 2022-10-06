package Africa.semicolon.schoolProject.services;


import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.dto.request.RegisterCourseRequest;
import Africa.semicolon.schoolProject.dto.request.SelectCourseRequest;
import Africa.semicolon.schoolProject.dto.request.UpdateCourseRequest;

import java.util.List;

public interface CourseServices {
    Course registerCourse(RegisterCourseRequest createCourseRequest);

    long totalNumberOfCourses();

    void deleteAll();

    Course findCourseById(String id);

    List<Course> findAllCourses();

    void deleteById(String id);

    Course updateCourseProfile(UpdateCourseRequest updateCourseRequest);

    Course selectCourse(SelectCourseRequest selectCourseRequest);

    ;
}
