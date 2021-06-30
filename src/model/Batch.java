package model;

import java.time.LocalDate;

public class Batch {
    private String id;
    private int studentCount;
    private LocalDate commencingDate;

    public Batch() {
    }

    public Batch(String id, int studentCount, LocalDate commencingDate) {
        this.setId(id);
        this.setStudentCount(studentCount);
        this.setCommencingDate(commencingDate);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public LocalDate getCommencingDate() {
        return commencingDate;
    }

    public void setCommencingDate(LocalDate commencingDate) {
        this.commencingDate = commencingDate;
    }

    @Override
    public String toString() {
        return id;
    }
}
