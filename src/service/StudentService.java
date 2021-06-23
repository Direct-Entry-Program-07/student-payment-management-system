package service;

import model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private static final List<Student> studentDB = new ArrayList<>();

    static {
        //Add dummy data to student table
        Student s1 = new Student("453627453627", "Nimal Rajapaksha", "072-3456784", "Galle", LocalDate.of(2000, 10, 12), "nimal@abc.com", "DEP", "7");
        Student s2 = new Student("385936274V", "Kamal Ranatunga", "093-4526473", "Kandy", LocalDate.of(1987, 03, 19), "kamal@pqr.com", "GDCE", "13");
        studentDB.add(s1);
        studentDB.add(s2);
    }

    public StudentService() {

    }

    public void saveStudent(Student student){
        studentDB.add(student);
    }

    public void updateStudent(Student student){
        Student s = findStudent(student.getNic());
        int index = studentDB.indexOf(s);
        studentDB.set(index, student);
    }

    public void deleteStudent(String nic){
        Student student = findStudent(nic);
        studentDB.remove(student);

    }

    public List<Student> findAllStudents(){
        return studentDB;
    }

    public Student findStudent(String nic){
        for (Student student : studentDB) {
            if (student.getNic().equals(nic)){
                return student;
            }
        }
        return null;
    }

    public List<Student> findStudents(String query){
        List<Student> result = new ArrayList<>();

        for (Student student: studentDB) {

            if (student.getFullName().contains(query) ||
                    student.getNic().contains(query) ||
                    student.getAddress().contains(query) ||
                    student.getContactNumber().contains(query) ||
                    student.getEmailAddress().contains(query) ||
                    student.getCourseId().contains(query)){
                result.add(student);
            }
        }
        return result;
    }
}
