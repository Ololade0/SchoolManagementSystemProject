package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.repository.CourseRepository;

import Africa.semicolon.schoolProject.dto.request.CreateCourseRequest;
import Africa.semicolon.schoolProject.dto.request.DeleteCourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course addNewCourse(CreateCourseRequest createCourseRequest) {
        Course newCourse = new Course();
        newCourse.setCourseName(createCourseRequest.getCourseName());
        newCourse.setCourseCode(createCourseRequest.getCourseCode());
        return courseRepository.save(newCourse);
    }

    @Override
    public void delete(Course courseToDel) {
        courseRepository.delete(courseToDel);
    }

    @Override
    public Course deleteCourse(DeleteCourseRequest deleteCourseRequest) {
        for (Course course: courseRepository.findAll()) {
            if(Objects.equals(deleteCourseRequest.getCourseName(), course.getCourseName())) {
                courseRepository.delete(course);
                return course;
            }
        }
        return null;
    }
}
