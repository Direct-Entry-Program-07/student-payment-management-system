import model.Batch;
import model.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseServiceTest {

    public Set<Course> findDuplicates(List<Course> listContainingDuplicates){
        final Set<Course> duplicates = new HashSet<>();
        final Set<Course> notDuplicates = new HashSet<>();
        final Set<Course> set1 = new HashSet<>();

        for (Course string : listContainingDuplicates)
        {
            if (!set1.add(string))
            {
                duplicates.add(string);
            }else {
                notDuplicates.add(string);
            }
        }
        return notDuplicates;
    }
}
