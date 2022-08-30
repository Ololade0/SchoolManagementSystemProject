package Africa.semicolon.schoolProject.data.repository;

import Africa.semicolon.schoolProject.data.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    Student findStudentById(String studentId);
    Student findStudentByEmail(String email);

}

