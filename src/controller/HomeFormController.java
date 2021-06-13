package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import util.AppBarIcon;
import util.MaterialUI;

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

    public void initialize(){
        MaterialUI.paintTextFields(txtQuery);
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
