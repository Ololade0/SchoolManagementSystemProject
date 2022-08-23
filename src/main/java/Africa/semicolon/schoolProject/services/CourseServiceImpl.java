package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseServices{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveNewCourse(Course newCourse) {
        return courseRepository.save(newCourse);
    }

    @Override
    public void delete(Course courseToDel) {
        courseRepository.delete(courseToDel);
    }
}
