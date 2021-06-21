package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Course;
import model.CourseTM;
import service.CourseService;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.time.LocalDate;

public class AddCourseFormController {
    private final CourseService courseService = new CourseService();

    public JFXButton btnSave;
    public JFXButton btnCancel;
    public AnchorPane root;
    public TextField txtCourseId;
    public TextField txtCourseName;
    public TextField txtBatchId;
    public JFXButton btnCreateANewBatch;
    public TextField txtNoOfStudents;
    public JFXDatePicker pckrCommencingDate;
    public TextArea txtNote;

    public void initialize() {
        txtBatchId.setEditable(false);
        Platform.runLater(() -> {

            if (root.getUserData() != null) {
                CourseTM tm = (CourseTM) root.getUserData();
                Course course = null;
                try {
                    course = courseService.findCourse(tm.getCourseID());
                } catch (NotFoundException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Something terribly wrong, Please contact DEP)!", ButtonType.OK).show();
                }


                txtCourseId.setEditable(false);
                txtCourseId.setText(course.getCourseID()+"-"+course.getBatchID());
                txtCourseName.setText(course.getCourseName());
                txtBatchId.setEditable(false);
                btnCreateANewBatch.setDisable(true);
                txtBatchId.setText(String.valueOf(course.getBatchID()));
                txtNoOfStudents.setText(String.valueOf(course.getNoOfStudentsForTheBatch()));
                pckrCommencingDate.setValue(LocalDate.parse(pckrCommencingDate.getValue().toString()));
                txtNote.setText(course.getNote());

                btnSave.setText("Update Course");
            }
        });
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {

        try {
            Course course = new Course(
                    txtCourseId.getText()+"-"+txtBatchId.getText(),
                    txtCourseName.getText(),
                    Integer.parseInt(txtBatchId.getText()),
                    Integer.parseInt(txtNoOfStudents.getText()),
                    LocalDate.parse(pckrCommencingDate.getValue().toString()),
                    txtNote.getText()
            );

            if (btnSave.getText().equals("Save Course")) {
                courseService.saveCourse(course);
            } else {

                CourseTM tm = (CourseTM) root.getUserData();
                tm.setCourseName(txtCourseName.getText());
                tm.setBatchID(Integer.parseInt(txtBatchId.getText()));
                tm.setNoOfStudentsForTheBatch(Integer.parseInt(txtNoOfStudents.getText()));
                tm.setBatchCommencingDate(LocalDate.parse(pckrCommencingDate.getValue().toString()));
                tm.setNote(txtNote.getText());
                courseService.updateCourse(course);
            }
            new Alert(Alert.AlertType.NONE, "Course has been saved successfully", ButtonType.OK).show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void btnCancel_OnAction(ActionEvent actionEvent) {
        Window window = btnCancel.getScene().getWindow();
        ((Stage) window).close();
    }

    public void btnCreateANewBatch_OnAction(ActionEvent actionEvent) {
        txtBatchId.setText(String.valueOf(Integer.parseInt(txtBatchId.getText()) + 1));
        btnCreateANewBatch.setDisable(true);
    }
}
