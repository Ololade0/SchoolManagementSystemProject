package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.dto.request.CreateCourseRequest;
import Africa.semicolon.schoolProject.dto.request.DeleteCourseRequest;


public interface CourseService {

    Course addNewCourse(CreateCourseRequest createCourseRequest);

    void delete(Course courseToDel);

    Course deleteCourse(DeleteCourseRequest deleteCourseRequest);
}
