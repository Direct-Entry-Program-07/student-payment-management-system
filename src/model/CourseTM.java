package model;

import java.time.LocalDate;
import java.util.Arrays;

public class CourseTM {
    private String courseID;
    private String courseName;
    private int BatchID;
    private int noOfStudentsForTheBatch;
    private LocalDate batchCommencingDate;
    private String note;

    public CourseTM() {
    }

    public CourseTM(String courseID, String courseName, int batchID, int noOfStudentsForTheBatch, LocalDate batchCommencingDate, String note) {
        this.courseID = courseID;
        this.courseName = courseName;
        BatchID = batchID;
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
        return BatchID;
    }

    public void setBatchID(int batchID) {
        BatchID = batchID;
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
        return "CourseTM{" +
                "courseID='" + courseID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", BatchID=" + BatchID +
                ", noOfStudentsForTheBatch=" + noOfStudentsForTheBatch +
                ", batchCommencingDate=" + batchCommencingDate +
                ", note='" + note + '\'' +
                '}';
    }
}
