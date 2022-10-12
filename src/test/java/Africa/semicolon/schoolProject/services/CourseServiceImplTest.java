package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.dto.request.CreateCourseRequest;
import Africa.semicolon.schoolProject.dto.request.UpdateCourseRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CourseServiceImplTest {
    @Autowired
    CourseServices courseServices ;
    Course savedCourse;

    @BeforeEach
    void setUp() {
        CreateCourseRequest createCourseRequest = CreateCourseRequest
                .builder()
                .courseName("Python")
                .courseCode("101")
                .courseStatus("activated")
                .build();
       savedCourse =  courseServices.registerCourse(createCourseRequest);
    }

    @AfterEach
    void tearDown() {
        courseServices.deleteAll();
    }
    @Test
    public void CourseCanBeCreated(){
        Course newCourse = Course.builder()
                .courseName("Python")
                .courseCode("101")
                .courseStatus("activated")
                .build();
        assertEquals("Python", newCourse.getCourseName());
    }
    @Test
    public void CourseCanBeCreate(){
        assertEquals(1, courseServices.totalNumberOfCourses());
    }

    @Test
    public void CourseCanBeFindById(){
        Course foundCourse = courseServices.findCourseById(savedCourse.getId());
        assertThat(foundCourse.getId()).isNotNull();
        assertThat(foundCourse.getId()).isEqualTo(savedCourse.getId());
    }
    @Test
    public void findAllCourses(){
        courseServices.findAllCourses();
        assertEquals("Python", courseServices.findAllCourses().get(0).getCourseName());
    }

    @Test
    public void CourseCanBeDeleteById(){
        courseServices.deleteById(savedCourse.getId());
        assertEquals(0, courseServices.totalNumberOfCourses());

    }
    @Test
    public void deleteAllCourses(){
        courseServices.deleteAll();
        assertEquals(0, courseServices.totalNumberOfCourses());
    }
    @Test
    public void updateCourseProfile(){
        UpdateCourseRequest updateCourseRequest = UpdateCourseRequest
                .builder()
                .courseName("Java")
                .courseCode("102")
                .courseStatus("disactivated")
                .build();
        updateCourseRequest.setCourseId(savedCourse.getId());
        courseServices.updateCourseProfile(updateCourseRequest);
        assertEquals("Java", courseServices.findAllCourses().get(0).getCourseName());
        assertEquals("102", courseServices.findAllCourses().get(0).getCourseCode());
        assertEquals("disactivated", courseServices.findAllCourses().get(0).getCourseStatus());


    }


    }

