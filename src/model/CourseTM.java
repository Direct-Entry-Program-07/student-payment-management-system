package model;

import java.time.LocalDate;

public class CourseTM {
    private String courseID;
    private String courseName;
    private Batch selectedBatch;
    private int noOfStudentsForTheBatch;
    private LocalDate batchCommencingDate;
    private String note;

    public CourseTM() {
    }

    public CourseTM(String courseID, String courseName, Batch selectedBatch, int noOfStudentsForTheBatch, LocalDate batchCommencingDate, String note) {
        this.setCourseID(courseID);
        this.setCourseName(courseName);
        this.setSelectedBatch(selectedBatch);
        this.setNoOfStudentsForTheBatch(noOfStudentsForTheBatch);
        this.setBatchCommencingDate(batchCommencingDate);
        this.setNote(note);
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

    public Batch getSelectedBatch() {
        return selectedBatch;
    }

    public void setSelectedBatch(Batch selectedBatch) {
        this.selectedBatch = selectedBatch;
    }

    public int getNoOfStudentsForTheBatch() {
        if (selectedBatch == null){
            return 0;
        }
        return selectedBatch.getStudentCount();
    }

    public void setNoOfStudentsForTheBatch(int noOfStudentsForTheBatch) {
        this.selectedBatch.setStudentCount(noOfStudentsForTheBatch);
    }

    public LocalDate getBatchCommencingDate() {
        if (selectedBatch == null){
            return LocalDate.parse("1111-11-11");
        }

        return selectedBatch.getCommencingDate();
    }

    public void setBatchCommencingDate(LocalDate batchCommencingDate) {
        this.selectedBatch.setCommencingDate(batchCommencingDate);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
