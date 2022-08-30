package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.data.repository.CourseRepository;
import Africa.semicolon.schoolProject.data.repository.SchoolRepository;
import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.*;
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
            schoolFound.getStudents().add(newStudent);
            studentServices.saveNewStudent(newStudent);
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
    public String deleteStudent(DeleteStudentRequest deleteStudentRequest) {
        var schoolFound = schoolRepository.findSchoolById(deleteStudentRequest.getId());
        if (schoolFound != null) {
            for (var studentToDel : schoolFound.getStudents()) {
                if (Objects.equals(studentToDel.getId(), deleteStudentRequest.getStudentId())) {
                    studentServices.deleteStudent(studentToDel);
                    schoolFound.getStudents().remove(studentToDel);
                    schoolRepository.save(schoolFound);
                    return "student sucessfully deleted";
                }
            }
        }
        return " error";
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
    public Optional<Course> getACourse(GetACourseRequest getACourseRequest) {
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

    @Override
    public Student getStudentById(String id) {
        return studentServices.findStudentById(id);
    }

    @Override
    public RegisterSchoolResponse registerSchool(RegisterSchoolRequest registerSchoolRequest) {
        School newSchool = new School();
        newSchool.setSchoolName(registerSchoolRequest.getSchoolName());
        newSchool.setSchoolLocation(registerSchoolRequest.getSchoolLocation());
        schoolRepository.save(newSchool);
        RegisterSchoolResponse registerSchoolResponse = new RegisterSchoolResponse();
        registerSchoolResponse.setMessage("Registration Successful");
        return registerSchoolResponse;

    }

    @Override
    public long totalUsers() {

        return schoolRepository.count();
    }

    @Override
    public UpdateCourseResponse updateCourse(UpdateCourseRequest updateCourseRequest) {
        School school = schoolRepository.findSchoolBySchoolNameIgnoreCase(updateCourseRequest.getSchoolName());
    Course course = courseRepository.findByCourseName(updateCourseRequest.getCourseName());
     school.getCourses().remove(course);
   if(updateCourseRequest.getCourseName()!= null){
       course.setCourseName(updateCourseRequest.getCourseName());
    }
   if(updateCourseRequest.getCourseCode()!= null){
       course.setCourseCode(updateCourseRequest.getCourseCode());
        }
   if(updateCourseRequest.getCourseId()!= null){
            course.setCourseId(updateCourseRequest.getCourseId());
        }

   courseServices.saveNewCourse(course);
   school.getCourses().add(course);
   schoolRepository.save(school);
   UpdateCourseResponse updateCourseResponse = new UpdateCourseResponse();
   updateCourseResponse.setMessage("course succesfully updated");
   return updateCourseResponse;





    }

    @Override
    public Student getStudentByEmail(String email) {

        return studentServices.getStudentByEmail(email);
    }

    @Override
    public Course getCourseByName(String courseName) {

        return courseServices.getCourseByName(courseName);
    }

}
