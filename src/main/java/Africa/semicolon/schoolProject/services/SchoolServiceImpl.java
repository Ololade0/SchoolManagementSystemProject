package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.data.repository.SchoolRepository;
import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.*;
import Africa.semicolon.schoolProject.exception.SchoolExistsException;
import Africa.semicolon.schoolProject.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;


    @Override
    public RegisterSchoolResponse registerSchool(RegisterSchoolRequest registerSchoolRequest) {
        School newSchool = new School();
        Mapper.map(registerSchoolRequest, newSchool);
        schoolRepository.save(newSchool);

        RegisterSchoolResponse registerSchoolResponse = new RegisterSchoolResponse();
        registerSchoolResponse.setMessage("Registration Successful");
        return registerSchoolResponse;

    }

    @Override
    public AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest) {
        admitStudentRequest.setStudentNumber((int)studentService.size());
        Student newStudent = studentService.saveNewStudent(admitStudentRequest);

        School schoolFound = findSchool(admitStudentRequest.getSchoolName());
        schoolFound.getStudents().add(newStudent);
        schoolRepository.save(schoolFound);

        AdmitStudentResponse admitStudentResponse = new AdmitStudentResponse();
        admitStudentResponse.setMessage("Student successfully admitted.");
        return admitStudentResponse;
    }

    private School findSchool(String schoolName) {
        var schoolFound = schoolRepository.findSchoolBySchoolNameIgnoreCase(schoolName);
        if (schoolFound == null) {
            throw new SchoolExistsException(schoolName + "does not exist");
        }
        return schoolFound;
    }

    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest) {
        Course course = courseService.addNewCourse(createCourseRequest);

        School schoolFound = findSchool(createCourseRequest.getSchoolName());
        schoolFound.getCourses().add(course);
        schoolRepository.save(schoolFound);

        CreateCourseResponse createCourseResponse = new CreateCourseResponse();
        createCourseResponse.setMessage("Course created successfully");
        return createCourseResponse;
    }

    @Override
    public FindStudentResponse findStudentRegisteredIn(FindStudentRequest findStudentRequest) {
        School school = findSchool(findStudentRequest.getSchoolName());
        List<Student> allStudentsInSchool = school.getStudents();
        FindStudentResponse findStudentResponse = new FindStudentResponse();
        for (var student : allStudentsInSchool) {
            if (findStudentRequest.getStudentNumber() == student.getStudentNumber()) {
                Mapper.map(findStudentResponse, student);
            }
        }
        return findStudentResponse;
    }

    @Override
    public List<Student> getAllStudentsRegisteredIn(String schoolName) {
        School foundSchool = findSchool(schoolName);
        return foundSchool.getStudents();
    }

    @Override
    public List<Course> getAllCoursesOfferedIn(String schoolName) {
        School foundSchool = findSchool(schoolName);
        return foundSchool.getCourses();
    }

    @Override
    public FindCourseResponse findCourseOfferedIn(FindCourseRequest findCourseRequest) {
        School school = findSchool(findCourseRequest.getSchoolName());
        List<Course> allCoursesInSchool = school.getCourses();
        FindCourseResponse findCourseResponse = new FindCourseResponse();
        for (var course : allCoursesInSchool) {
            if (Objects.equals(findCourseRequest.getCourseName(), course.getCourseName())) {
                Mapper.map(findCourseResponse, course);
            }
        }
        return findCourseResponse;
    }

    @Override
    public DeleteStudentResponse expelStudent(DeleteStudentRequest deleteStudentRequest) {
        Student studentToDel = studentService.deleteStudent(deleteStudentRequest);
        var schoolFound = findSchool(deleteStudentRequest.getSchoolName());
        schoolFound.getStudents().remove(studentToDel);
        schoolRepository.save(schoolFound);

        DeleteStudentResponse response = new DeleteStudentResponse();
        response.setMessage("Course created successfully");

        return response;
    }

    @Override
    public DeleteCourseResponse deleteCourse(DeleteCourseRequest deleteCourseRequest) {
        Course courseToDel = courseService.deleteCourse(deleteCourseRequest);
        var schoolFound = findSchool(deleteCourseRequest.getSchoolName());
        schoolFound.getCourses().remove(courseToDel);
        schoolRepository.save(schoolFound);

        DeleteCourseResponse response = new DeleteCourseResponse();
        response.setMessage("Course created successfully");

        return response;
    }
}













