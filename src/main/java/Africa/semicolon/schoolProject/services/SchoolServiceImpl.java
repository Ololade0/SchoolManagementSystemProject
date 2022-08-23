package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.data.repository.CourseRepository;
import Africa.semicolon.schoolProject.data.repository.SchoolRepository;
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
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private StudentService studentServices;
    @Autowired
    private CourseServices courseServices;
    @Autowired
    private CourseRepository courseRepository;


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
        var schoolFound = schoolRepository.findSchoolById(deleteStudentRequest.getId());
        if (schoolFound != null) {
            for (var studentToDel : schoolFound.getStudents()) {
                if (Objects.equals(studentToDel.getId(), deleteStudentRequest.getStudentId())) {
                    studentServices.deleteStudent(studentToDel);
                    schoolFound.getStudents().remove(studentToDel);
                    schoolRepository.save(schoolFound);
                    return;
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
    public String deleteCourse(DeleteCourseRequest deleteCourseRequest) {
        var foundSchool = schoolRepository.findSchoolById(deleteCourseRequest.getId());
        if (foundSchool != null) {
            for (var courseToDel : foundSchool.getCourses()) {
                if (Objects.equals(courseToDel.getCourseId(), deleteCourseRequest.getCourseId())) {
                    courseServices.delete(courseToDel);
                    foundSchool.getCourses().remove(courseToDel);
                    schoolRepository.save(foundSchool);
                    return "deleted successfully";
                }

            }
        }
        return "error";
    }

    @Override
    public List<Course> getAllCourses() {
        return schoolRepository.findAll().get(0).getCourses();
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    @Override
    public Optional<Course> getACourses(GetACourseRequest getACourseRequest) {
        return courseRepository.findById(getACourseRequest.getCourseId());
    }

    @Override
    public School findSchoolByName(String schoolName) {
        School foundSchool = schoolRepository.findSchoolBySchoolNameIgnoreCase(schoolName);
        if (foundSchool == null) {
            throw new SchoolExistException("School not found!");
        }
        return foundSchool;
    }

    @Override
    public long size() {
        return schoolRepository.count();
    }

    @Override
    public School save(School newSchool) {
        return schoolRepository.save(newSchool);
    }

    @Override
    public List<School> findAll() {
        return schoolRepository.findAll();
    }


}













