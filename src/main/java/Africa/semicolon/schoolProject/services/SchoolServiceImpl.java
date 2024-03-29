package Africa.semicolon.schoolProject.services;
import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.*;
import Africa.semicolon.schoolProject.exception.SchoolAlreadyExistException;
import Africa.semicolon.schoolProject.exception.SchoolDoesExistException;
import Africa.semicolon.schoolProject.repository.SchoolRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Data
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseServices courseServices;


    @Override
    public RegisterSchoolResponse registerSchool(RegisterSchoolRequest registerSchoolRequest) {
        if(schoolRepository.findSchoolBySchoolName(registerSchoolRequest.getSchoolName()).isPresent()){
            throw new SchoolAlreadyExistException("School with name : " + registerSchoolRequest.getSchoolName() + "already exist");
        }
        School newSchool = School.builder()
                .schoolName(registerSchoolRequest.getSchoolName())
                .bycrptedPassword(registerSchoolRequest.getPassword())
                .email(registerSchoolRequest.getEmail())
                .schoolLocation(registerSchoolRequest.getSchoolLocation())
                .build();
            School foundSchool = schoolRepository.save(newSchool);
            return RegisterSchoolResponse.builder()
                    .message("School successfully registered")
                    .id(foundSchool.getId())
                    .schoolName(foundSchool.getSchoolName())
                    .email(foundSchool.getEmail())
                    .schoolLocation(foundSchool.getSchoolLocation())
                    .build();
        }

    @Override
    public long TotalNumbersOfSchool() {
        return schoolRepository.count();
    }

    @Override
    public School findSchoolById(String id) {
        School sch = schoolRepository.findSchoolById(id);
        if (sch != null) {
            return schoolRepository.findById(id).orElseThrow(
                    () -> new SchoolDoesExistException("School Cannot be found")
            );
        }
        return null;
    }

    @Override
    public List<School> findAllSchools() {
            return schoolRepository.findAll();

        }

    @Override
    public String deleteAllSchools() {
        schoolRepository.deleteAll();
        return "Succesfully deleted";

    }

    @Override
    public String deleteById(String id) {
        schoolRepository.deleteById(id);
        return  "School successfully deleted";

    }

    @Override
    public School updateSchoolProfile(UpdateSchoolProfileRequest updateSchoolProfileRequest) {
        School foundSchool = schoolRepository.findSchoolById(updateSchoolProfileRequest.getSchoolId());
        if (updateSchoolProfileRequest.getSchoolName() != null) {
            foundSchool.setSchoolName(updateSchoolProfileRequest.getSchoolName());
        }
        if (updateSchoolProfileRequest.getSchoolLocation() != null) {
            foundSchool.setSchoolLocation(updateSchoolProfileRequest.getSchoolLocation());
        }
        schoolRepository.save(foundSchool);

        return foundSchool;
    }

    @Override
    public AdmitStudentResponse admitStudent(AdmitStudentRequest admitStudentRequest) {
        Student admittedStudent = studentService.admitstudent(admitStudentRequest);
        School foundSchool = schoolRepository.findSchoolById(admitStudentRequest.getSchoolId());
        if (foundSchool != null) {
            foundSchool.getStudents().add(admittedStudent);
            schoolRepository.save(foundSchool);
        }

        return AdmitStudentResponse
                .builder()
                .message("Student sucessfully admitted")
                .studentId(admittedStudent.getId())
                .build();


    }

    @Override
    public long totalNumberOfStudents() {
        return studentService.totalNumbersOfStudent();
    }

    @Override
    public String deleteAllStudents() {
            studentService.deleteAll();
            return "All student successfully deleted";
        }
 //   }

    @Override
    public Student findStudentById(GetStudentRequest getStudentRequest) {
        School foundSchool = schoolRepository.findSchoolById(getStudentRequest.getSchoolId());
        if(foundSchool != null){
            return studentService.findStudentById(getStudentRequest.getStudentId());
        }
        throw new SchoolDoesExistException("Schoool cannot be found");
    }



    @Override
    public DeleteStudentResponse deleteStudentById(DeleteStudentRequest deleteStudentRequest) {
      School foundSchool = schoolRepository.findSchoolById(deleteStudentRequest.getSchoolId());
      if(foundSchool!= null){
          studentService.deleteById(deleteStudentRequest.getId());

          }

                DeleteStudentResponse deleteStudentResponse = new DeleteStudentResponse();
                deleteStudentResponse.setMessage("Student successfully deleted");
                return deleteStudentResponse;
            }




    @Override
    public List<Student> findAllStudents(GetAllStudentRequest getAllStudentRequest) {
            return studentService.findAllStudent();
        }
    @Override
    public UpdateStudentProfileResponse updateStudentProfile(UpdatedStudentProfileRequest updatedStudentProfileRequest) {
        Student foundStudent = studentService.updateStudentProfile(updatedStudentProfileRequest);
        School foundSchool = schoolRepository.findSchoolById(updatedStudentProfileRequest.getSchoolId());
        if (foundSchool != null) {
            foundSchool.getStudents().add(foundStudent);
            schoolRepository.save(foundSchool);

        }
        return UpdateStudentProfileResponse
                .builder()
                .id(foundStudent.getId())
                .message("Todo successfully updated ")
                .build();


    }



    @Override
    public long totalNumberOfCourses() {
        return courseServices.totalNumberOfCourses();
    }

    @Override
    public void deleteAllCourses() {
        courseServices.deleteAll();


    }

    @Override
    public DeleteCourseResponse deleteCourseById(DeleteCourseRequest deleteCourseRequest) {
        School foundSchool = schoolRepository.findSchoolById(deleteCourseRequest.getSchoolId());

        if (foundSchool != null) {
           courseServices.deleteById(deleteCourseRequest.getCourseId());
        }

        return DeleteCourseResponse.builder()
                .message("Course successfully deleted")
                .build();

    }



    @Override
    public Course findCourseById(GetACourseRequest getACourseRequest) {
        School foundSchool = schoolRepository.findSchoolById(getACourseRequest.getSchoolId());
        if(foundSchool != null){
            return courseServices.findCourseById(getACourseRequest.getCourseId());
        }
        throw new SchoolDoesExistException("School cannot be found");
    }

    @Override
    public List<Course> findAllCourses(FindAllCourses findAllCourses) {
        return courseServices.findAllCourses();
    }

    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest) {
       Course registeredCourse = courseServices.registerCourse(createCourseRequest);
        School foundSchool = schoolRepository.findSchoolById(createCourseRequest.getSchoolId());
        if (foundSchool != null) {
            foundSchool.getCourses().add(registeredCourse);
            schoolRepository.save(foundSchool);
        }
        return CreateCourseResponse.builder()
                .message("Course successfully created")
                .courseId(registeredCourse.getId())
                .courseName(registeredCourse.getCourseName())
                .build();

    }

    @Override
    public LoginResponse login(LoginRest loginRest) {
        var foundSchool = schoolRepository.findSchoolByEmail(loginRest.getEmail());
        return foundSchool.map(this::buildSuccessfulLoginResponse).orElse(null);


    }

    private LoginResponse buildSuccessfulLoginResponse(School school) {
        return LoginResponse.builder()
                .code(200)
                .message("Login successful")
                .build();
    }


    @Override
    public UpdateCourseResponse updateCourseProfile(UpdateCourseRequest updateCourseRequest) {
        Course foundCourse = courseServices.updateCourseProfile(updateCourseRequest);
        School foundSchool = schoolRepository.findSchoolById(updateCourseRequest.getSchoolId());
        if (foundSchool != null) {
            foundSchool.getCourses().add(foundCourse);
            schoolRepository.save(foundSchool);
        }
        return UpdateCourseResponse
                .builder()
                .message("Course profile successfully updated ")
                .build();

    }



}
