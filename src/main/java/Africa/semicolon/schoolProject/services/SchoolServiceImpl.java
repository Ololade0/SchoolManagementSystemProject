package Africa.semicolon.schoolProject.services;
import Africa.semicolon.schoolProject.data.model.Course;
import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.data.model.Student;
import Africa.semicolon.schoolProject.dto.request.*;
import Africa.semicolon.schoolProject.dto.response.*;
import Africa.semicolon.schoolProject.exception.SchoolDoesExistException;
import Africa.semicolon.schoolProject.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        School newSchool = School.builder()
                .schoolName(registerSchoolRequest.getSchoolName())
                .schoolLocation(registerSchoolRequest.getSchoolLocation())
                .build();
         schoolRepository.save(newSchool);
         return RegisterSchoolResponse.builder()
                 .message("School successfully registered")
                  .id(newSchool.getId())
                 .build();


    }

    @Override
    public long TotalNumbersOfSchool() {
        return schoolRepository.count();
    }

    @Override
    public School findSchoolById(String id) {
        return schoolRepository.findById(id).orElseThrow(
                () -> new SchoolDoesExistException("School Cannot be found")
        );
    }

    @Override
    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public void deleteAllSchools() {
        schoolRepository.deleteAll();

    }

    @Override
    public void deleteById(String id) {
        schoolRepository.deleteById(id);

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
    public void deleteAllStudents() {
        studentService.deleteAll();

    }


    @Override
    public Student findStudentById(GetStudentRequest getStudentRequest) {
        return studentService.findStudentById(getStudentRequest.getId());

    }

    @Override
    public Student findStudentsById(String studentId) {
        return studentService.findStudentById(studentId);
    }

    @Override
    public String deleteStudentById(DeleteStudentRequest deleteAllStudentRequest) {
        School foundSchool = schoolRepository.findSchoolById(deleteAllStudentRequest.getSchoolId());
        if (foundSchool != null) {
            for (var studToDel : foundSchool.getStudents()) {
                if (studToDel.getId().equalsIgnoreCase(deleteAllStudentRequest.getId())) {
                    studentService.deleteById(studToDel.getId());
                    foundSchool.getStudents().remove(studToDel);
                    schoolRepository.save(foundSchool);
                    return "successfully deleted";
                }
            }
        }
        return "error";
    }

    @Override
    public List<Student> findAllStudents() {
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
                .message("Student profile successfully updated ")
                .build();


    }

    @Override
    public RegisterCourseResponse createCourse(RegisterCourseRequest createCourseRequest) {
        Course registeredCourse = courseServices.registerCourse(createCourseRequest);
        School foundSchool = schoolRepository.findSchoolById(createCourseRequest.getSchoolId());
        if (foundSchool != null) {
            foundSchool.getCourses().add(registeredCourse);
            schoolRepository.save(foundSchool);
        }
        return RegisterCourseResponse
                .builder()
                .message("Course succesfully registered")
                .courseId(registeredCourse.getId())
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
    public String deleteCourseById(DeleteCourseRequest deleteCourseRequest) {
        School foundSchool = schoolRepository.findSchoolById(deleteCourseRequest.getSchoolId());
        if (foundSchool != null) {
            for (var courseToDel : foundSchool.getCourses()) {
                if (courseToDel.getId().equalsIgnoreCase(deleteCourseRequest.getCourseId())) {
                    courseServices.deleteById(courseToDel.getId());
                    foundSchool.getCourses().remove(courseToDel);
                    schoolRepository.save(foundSchool);
                    return "Course successfully deleted";
                }
            }
        }
        return "error";
    }

    @Override
    public Course findCourseById(String courseId) {
        return courseServices.findCourseById(courseId);
    }

    @Override
    public List<Course> findAllCourses() {
        return courseServices.findAllCourses();
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
