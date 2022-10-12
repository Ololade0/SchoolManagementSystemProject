package Africa.semicolon.schoolProject.services;
import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.dto.request.CreateCourseRequest;

import Africa.semicolon.schoolProject.dto.request.SelectCourseRequest;
import Africa.semicolon.schoolProject.dto.request.UpdateCourseRequest;

import Africa.semicolon.schoolProject.exception.CourseExistException;
import Africa.semicolon.schoolProject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseServices{
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course registerCourse(CreateCourseRequest createCourseRequest) {
                Course newCourse = Course
                .builder()
                .courseName(createCourseRequest.getCourseName())
                .courseCode(createCourseRequest.getCourseCode())
                .courseStatus(createCourseRequest.getCourseStatus())
                .build();
        return courseRepository.save(newCourse);

    }
    @Override
    public long totalNumberOfCourses() {
        return courseRepository.count();
    }

    @Override
    public void deleteAll() {
        courseRepository.deleteAll();

    }

    @Override
    public Course findCourseById(String id) {
        return courseRepository.findCourseById(id);
    }

    @Override
    public List<Course> findAllCourses() {
      return courseRepository.findAll();

    }

    @Override
    public String deleteById(String id) {
        courseRepository.deleteById(id);
        return "Course successfully deleted";

    }

    @Override
    public Course updateCourseProfile(UpdateCourseRequest updateCourseRequest) {
       Course foundCourse =  courseRepository.findCourseById(updateCourseRequest.getCourseId());
        if(updateCourseRequest.getCourseName() != null){
            foundCourse.setCourseName(updateCourseRequest.getCourseName());
        }
        if(updateCourseRequest.getCourseCode() != null){
            foundCourse.setCourseCode(updateCourseRequest.getCourseCode());
        }
        if(updateCourseRequest.getCourseStatus() != null){
            foundCourse.setCourseStatus(updateCourseRequest.getCourseStatus());
        }
        return courseRepository.save(foundCourse);
    }

    @Override
    public Course selectCourse(SelectCourseRequest selectCourseRequest) {
        Course foundCourse = courseRepository.findCourseById(selectCourseRequest.getCourseId());
        if(foundCourse != null){
            courseRepository.save(foundCourse);
            return foundCourse;
        }
    throw  new CourseExistException("Course Cannot be found");
    }


}
