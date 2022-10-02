package Africa.semicolon.schoolProject.services;

import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.data.repository.CourseRepository;
import Africa.semicolon.schoolProject.data.repository.SchoolRepository;
import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.*;
import Africa.semicolon.schoolProject.exception.SchoolExistDoesException;
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
            schoolFound.getStudents().add(newStudent);
            studentServices.saveNewStudent(newStudent);
            schoolRepository.save(schoolFound);
        }
        if (schoolFound == null) {
            throw new SchoolExistDoesException(admitStudentRequest.getSchoolName() + "does not exist");

        }

        AdmitStudentResponse admitStudentResponse = new AdmitStudentResponse();
        admitStudentResponse.setMessage(" Student successfully admitted");
        return admitStudentResponse;


    }

    @Override
    public String deleteStudent(DeleteStudentRequest deleteStudentRequest) {
        School foundSchool = schoolRepository.findSchoolById(deleteStudentRequest.getId());
        if(foundSchool != null){
            for(var studToDel : foundSchool.getStudents()){
                if(Objects.equals(studToDel.getId(), deleteStudentRequest.getStudentId()))
                    studentServices.delete(studToDel);
                foundSchool.getStudents().remove(studToDel);
                    schoolRepository.save(foundSchool);
                    return "deleted successfully";
                }

            }
        return null;
        }






    @Override
    public List<Student> getAllStudents() {
        return schoolRepository.findAll().get(0).getStudents();
    }



    @Override
    public void deleteAll() {
        schoolRepository.deleteAll();
        studentServices.deleteAll();
        courseServices.deleteAll();
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
        course.setCourseStatus(createCourseRequest.getCourseStatus());
        School schoolFound = schoolRepository.findSchoolBySchoolNameIgnoreCase("Semicolon");

        if (schoolFound != null) {
          course =   courseServices.saveNewCourse(course);
            schoolFound.getCourses().add(course);
            schoolRepository.save(schoolFound);
        }
        if (schoolFound == null) {
            throw new SchoolExistDoesException(createCourseRequest.getSchoolName() + "does not exist");
        }
        CreateCourseResponse createCourseResponse = new CreateCourseResponse();
        createCourseResponse.setCourseId(course.getId());
        createCourseResponse.setMessage(course.getCourseName() + "Course created successfully");
        return createCourseResponse;
    }

    @Override
    public String deleteCourse(DeleteCourseRequest deleteCourseRequest) {
        var foundSchool = schoolRepository.findSchoolById(deleteCourseRequest.getId());
        if (foundSchool != null) {
            for (var courseToDel : foundSchool.getCourses()) {
                if (Objects.equals(courseToDel.getId(), deleteCourseRequest.getCourseId())) {
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
    public School findSchoolByName(String schoolName) {
        return schoolRepository.findSchoolBySchoolName(schoolName);
//        School foundSchool = schoolRepository.findSchoolBySchoolNameIgnoreCase(schoolName);
//        if (foundSchool == null) {
//            throw new SchoolExistDoesException("School not found!");
//        }

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
        newSchool = schoolRepository.save(newSchool);

        RegisterSchoolResponse registerSchoolResponse = new RegisterSchoolResponse();
        registerSchoolResponse.setSchoolId(newSchool.getId());
        registerSchoolResponse.setMessage(newSchool.getSchoolName() + " Registration successful");
        return registerSchoolResponse;

    }

    @Override
    public long totalUsers() {

        return schoolRepository.count();
    }

    @Override

    public UpdateCourseResponse updateCourse(UpdateCourseRequest updateCourseRequest) {
        var school = schoolRepository.findSchoolById(updateCourseRequest.getSchoolId());
        if (school != null) {
       Course course1 = courseServices.findCourseById(updateCourseRequest.getCourseId());
            if (updateCourseRequest.getCourseName() != null) {
                course1.setCourseName(updateCourseRequest.getCourseName());
            }
            if (updateCourseRequest.getCourseCode() != null) {
                course1.setCourseCode(updateCourseRequest.getCourseCode());
            }

            courseServices.saveNewCourse(course1);
            courseServices.reSaveNewCourse(course1);
            school.getCourses().add(course1);
            schoolRepository.save(school);
            UpdateCourseResponse updateCourseResponse = new UpdateCourseResponse();
            updateCourseResponse.setMessage("course successfully updated");

            return updateCourseResponse;
        }


        return null;
    }



    @Override
    public Course getCourseByName(String courseName) {

        return courseServices.getCourseByName(courseName);
    }

    @Override
    public Student getAStudent(String id) {
        return studentServices.findStudentById(id);
    }

    @Override
    public Course getACourse(String id) {
        return courseServices.findCourseById(id);
    }

    @Override
    public Student findStudentByEmail(String email) {
        return studentServices.findStudentByEmail(email);
    }

    @Override
    public Course findCourseById(String id) {
        return courseServices.findCourseById(id);
    }

    @Override
    public School findSchoolBySchoolName(String schoolName) {
        return schoolRepository.findSchoolBySchoolName(schoolName);
    }

    @Override
    public School findSchoolById(String schoolId) {
        return schoolRepository.findSchoolById(schoolId);
    }


}
