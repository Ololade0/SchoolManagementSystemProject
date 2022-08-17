package Africa.semicolon.schoolProject.data.dto.repository;

import Africa.semicolon.schoolProject.data.dto.model.Course;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
}
