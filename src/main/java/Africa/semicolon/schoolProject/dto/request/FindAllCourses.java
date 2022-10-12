package Africa.semicolon.schoolProject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllCourses {
    private String schoolId;
    private String courseId;

}
