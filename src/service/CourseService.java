package service;

import model.Course;
import service.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private static final List<Course> courseDB = new ArrayList<>();

    static {
        // add dummy data to course table
        Course c1 = new Course("DEP-2", "Direct Entry Program", 2 ,20, LocalDate.of(2021, 3, 3), "Note1");
        Course c2 = new Course("DEP-3", "Direct Entry Program", 3 ,15, LocalDate.of(2022, 9, 3), "Note2");
        Course c3 = new Course("GNCT-10", "General NCT", 10 ,100, LocalDate.of(2020, 9, 3), "Note 3");
        courseDB.add(c1);
        courseDB.add(c2);
        courseDB.add(c3);
    }

    public CourseService(){

    }

    public void saveCourse(Course course){
        courseDB.add(course);
    }

    public void updateCourse(Course course) throws NotFoundException {
        Course c = findCourse(course.getCourseID());
        courseDB.set(courseDB.indexOf(c), course);
    }

    public void deleteCourse(String courseId) throws NotFoundException {
        Course course = findCourse(courseId);
        courseDB.remove(course);

    }

    public List<Course> findAllCourses(){
        return courseDB;
    }

    public Course findCourse(String courseId) throws NotFoundException {
        for (Course course : courseDB) {
            if (course.getCourseID().equals(courseId)){
                return course;
            }
        }
        throw new NotFoundException();
    }

    public List<Course> findCourses(String query){
        List<Course> result = new ArrayList<>();
        for (Course course : courseDB) {

            if (course.getCourseName().contains(query) ||
                course.getCourseID().contains(query)){
                result.add(course);
            }

        }
        return result;
    }
}
