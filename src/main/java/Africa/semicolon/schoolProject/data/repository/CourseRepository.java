package Africa.semicolon.schoolProject.data.repository;

import Africa.semicolon.schoolProject.data.model.Course;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    Course findByCourseName(String name);




}
