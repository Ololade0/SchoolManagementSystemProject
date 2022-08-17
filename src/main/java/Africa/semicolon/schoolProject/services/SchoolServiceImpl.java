package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.dto.model.Course;
import Africa.semicolon.schoolProject.data.dto.model.School;
import Africa.semicolon.schoolProject.data.dto.model.Student;
import Africa.semicolon.schoolProject.data.dto.repository.SchoolRepository;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.AdmitStudentResponse;
import Africa.semicolon.schoolProject.dto.response.AllStudentResponse;
import Africa.semicolon.schoolProject.dto.response.CreateCourseResponse;
import Africa.semicolon.schoolProject.exception.SchoolExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private StudentService studentServices;
    @Autowired
    private CourseServices courseServices;


    @Override
    public AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest) {
        Student newStudent = new Student();
        newStudent.setStudentFirstName(admitStudentRequest.getStudentFirstName());
        newStudent.setStudentLastName(admitStudentRequest.getStudentLastName());
        newStudent.setGender(admitStudentRequest.getGender());
        newStudent.setStudentAge(admitStudentRequest.getStudentAge());
        newStudent.setEmail(admitStudentRequest.getEmailAddress());

        var schoolFound = schoolRepository.findSchoolBySchoolNameIgnoreCase(admitStudentRequest.getSchoolName());
        if (schoolFound != null) {
            studentServices.saveNewStudent(newStudent);
            schoolFound.getStudents().add(newStudent);
            schoolRepository.save(schoolFound);
        }
        if (schoolFound == null) {
            throw new SchoolExistException(admitStudentRequest.getSchoolName() + "does not exist");

        }

        AdmitStudentResponse admitStudentResponse = new AdmitStudentResponse();
        admitStudentResponse.setMessage("Student successfully admitted");
        return admitStudentResponse;


    }

    @Override
    public List<Student> getAllStudents() {

        return schoolRepository.findAll().get(0).getStudents();
    }

    @Override
    public void deleteStudent(DeleteStudentRequest deleteStudentRequest) {
        var schoolFound = schoolRepository.findSchoolBySchoolNameIgnoreCase(deleteStudentRequest.getSchoolName());
        if (schoolFound != null) {
            for (var studentToDel : schoolFound.getStudents()) {
                if (Objects.equals(studentToDel.getId(), deleteStudentRequest.getStudentId())) {
                    studentServices.deleteStudent(studentToDel);
                    schoolFound.getStudents().remove(studentToDel);
                    break;
                }
            }
        }

    }

    @Override
    public void deleteAll() {
        schoolRepository.deleteAll();
    }

    @Override
    public List<AllStudentResponse> findStudentBelongingTo(String firstName) {
        Student student = studentServices.findStudentById(firstName);
        School school = new School();
        List<Student> allStudent = school.getStudents();
        allStudent.add(student);
        List<AllStudentResponse> response = new ArrayList<>();
        for (var students : allStudent) {
            AllStudentResponse allStudentResponse = new AllStudentResponse();
            allStudentResponse.setFirstName(students.getStudentFirstName());
            allStudentResponse.setLastName(students.getStudentLastName());
            response.add(allStudentResponse);

        }
        return response;
    }

    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest) {
        Course course = new Course();
        course.setCourseName(createCourseRequest.getCourseName());
        course.setCourseId(createCourseRequest.getCourseId());
        course.setCourseStatus(createCourseRequest.getCourseStatus());
        School schoolFound = schoolRepository.findSchoolBySchoolNameIgnoreCase("Semicolon");
        if (schoolFound != null) {
            courseServices.saveNewCourse(course);
            schoolFound.getCourses().add(course);
            schoolRepository.save(schoolFound);
        }
        if (schoolFound == null) {
            throw new SchoolExistException(createCourseRequest.getSchoolName() + "does not exist");
        }
        CreateCourseResponse createCourseResponse = new CreateCourseResponse();
        createCourseResponse.setMessage("Course created successfully");
        return createCourseResponse;
    }

    @Override
    public void deleteCourse(DeleteCourseRequest deleteCourseRequest) {
        School foundSchool = schoolRepository.findSchoolBySchoolName(deleteCourseRequest.getSchoolName());
        if (foundSchool != null) {
            for (var courseToDel : foundSchool.getCourses()) {
                if (Objects.equals(courseToDel.getCourseId(), deleteCourseRequest.getCourseId()))
                    courseServices.delete(courseToDel);
                foundSchool.getCourses().remove(courseToDel);
                break;
            }
        }
    }

    @Override
    public List<Course> getAllCourses() {
       return schoolRepository.findAll().get(0).getCourses();
    }

    @Override
    public Course findACourse( String courseName) {
        School school = new School();
        for(Course course : school.getCourses()){
            if(course.getCourseName().equals(courseName)){
                return course;
            }
        }
        return null;
    }




}












