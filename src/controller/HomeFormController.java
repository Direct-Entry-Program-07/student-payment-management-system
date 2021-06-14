package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import util.AppBarIcon;
import util.MaterialUI;

import java.util.Date;

public class HomeFormController {

    public TableView tblDashboard;
    public JFXButton btnManageStudents;
    public JFXButton btnManageCourses;
    public JFXButton btnManagePayments;
    public JFXButton btnManageUsers;
    public JFXButton btnViewPayments;
    public Label lblDate;
    public JFXDatePicker pckrDate;
    public TextField txtQuery;
    public Label lblTime;

    public void initialize(){

        MaterialUI.paintTextFields(txtQuery);

        GetTime();
        GetDate();

        tblDashboard.getItems().add(new Object());
        tblDashboard.getItems().add(new Object());
        tblDashboard.getItems().add(new Object());
        tblDashboard.getItems().add(new Object());
    }

    private void GetDate() {
        String date = String.format("%1$tY-%1$tm-%1$td", new Date());
        lblDate.setText(date);
    }

    private void GetTime() {
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                while (true){
                    String time = String.format("%tT", new Date());
                    try{
                        Thread.sleep(900);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    Platform.runLater(()->{
                        lblTime.setText(time);
                    });
                }
            }
        };
        new Thread(task).start();
    }

    public void btnManageStudents_OnAction(ActionEvent actionEvent) {
        navigate("Manage Students", "/view/ManageStudentsForm.fxml");
    }

    public void btnManageStudents_OnKeyReleased(KeyEvent keyEvent) {
    }

    public void btnManageCourses_OnAction(ActionEvent actionEvent) {
        navigate("Manage Course Details", "/view/ManageCoursesForm.fxml");
    }

    public void btnManageCourses_OnKeyReleased(KeyEvent keyEvent) {
    }

    public void btnManagePayments_OnAction(ActionEvent actionEvent) {
        navigate("Manage Payment Details", "/view/ManagePaymentsForm.fxml");
    }

    public void btnManagePayments_OnKyeReleased(KeyEvent keyEvent) {
    }

    public void btnManageUsers_OnAction(ActionEvent actionEvent) {
        navigate("Manage Users", "/view/ManageUsersForm.fxml");
    }

    public void btnManageUsers_OnKeyReleased(KeyEvent keyEvent) {
    }

    public void btnViewPayments_OnAction(ActionEvent actionEvent) {
        navigate("View All Payments", "/view/ViewAllPaymentsForm.fxml");
    }

    public void btnViewPayments_OnKeyReleased(KeyEvent keyEvent) {
    }

    private void navigate(String title, String url){
        MainFormController ctrl = (MainFormController) tblDashboard.getScene().getUserData();
        ctrl.navigate(title, url, AppBarIcon.NAV_ICON_BACK, ()->{
            ctrl.navigate("Student Management System", "/view/HomeForm.fxml", AppBarIcon.NAV_ICON_HOME);
        });
    }
}
