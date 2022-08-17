package Africa.semicolon.schoolProject.data.dto.repository;


import Africa.semicolon.schoolProject.data.dto.model.Course;
import Africa.semicolon.schoolProject.data.dto.model.School;
import Africa.semicolon.schoolProject.data.dto.model.Student;
import Africa.semicolon.schoolProject.dto.request.CreateCourseRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SchoolRepository extends MongoRepository<School, String> {

    School findByStudents(List<Student> students);


    School findStudentById(String id);

    School findSchoolBySchoolNameIgnoreCase(String name);
    School findSchoolBySchoolName(String name);


    void findCourseByName(String courseName);

}
