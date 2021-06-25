package model;

import java.time.LocalDate;
import java.util.Arrays;

public class Course {
    private String courseID;
    private String courseName;
    private int batchID;
    private int noOfStudentsForTheBatch;
    private LocalDate batchCommencingDate;
    private String note;

    public Course() {
    }

    public Course(String courseID, String courseName, int batchID, int noOfStudentsForTheBatch, LocalDate batchCommencingDate, String note) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.batchID = batchID;
        this.noOfStudentsForTheBatch = noOfStudentsForTheBatch;
        this.batchCommencingDate = batchCommencingDate;
        this.note = note;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public int getBatchID() {
        return batchID;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }

    public int getNoOfStudentsForTheBatch() {
        return noOfStudentsForTheBatch;
    }

    public void setNoOfStudentsForTheBatch(int noOfStudentsForTheBatch) {
        this.noOfStudentsForTheBatch = noOfStudentsForTheBatch;
    }

    public LocalDate getBatchCommencingDate() {
        return batchCommencingDate;
    }

    public void setBatchCommencingDate(LocalDate batchCommencingDate) {
        this.batchCommencingDate = batchCommencingDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", batchID=" + batchID +
                ", noOfStudentsForTheBatch=" + noOfStudentsForTheBatch +
                ", batchCommencingDate=" + batchCommencingDate +
                ", note='" + note + '\'' +
                '}';
    }
}
