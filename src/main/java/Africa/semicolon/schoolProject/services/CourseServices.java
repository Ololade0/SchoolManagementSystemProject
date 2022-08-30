package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;



public interface CourseServices {

    Course saveNewCourse(Course newCourse);

    void delete(Course courseToDel);

    Course getCourseByName(String courseName);
}
