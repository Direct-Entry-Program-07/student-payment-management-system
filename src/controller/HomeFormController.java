package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import util.AppBarIcon;

public class HomeFormController {

    public TableView tblDashboard;
    public JFXButton btnManageStudents;
    public JFXButton btnManageCourses;
    public JFXButton btnManagePayments;
    public JFXButton btnManageUsers;
    public JFXButton btnViewPayments;

    public void initialize(){
        
    }

    public void btnManageStudents_OnAction(ActionEvent actionEvent) {
        navigate("Manage Students", "/view/ManageStudentsForm.fxml");
    }

    public void btnManageStudents_OnKeyReleased(KeyEvent keyEvent) {
    }

    public void btnManageCourses_OnAction(ActionEvent actionEvent) {
    }

    public void btnManageCourses_OnKeyReleased(KeyEvent keyEvent) {
    }

    public void btnManagePayments_OnAction(ActionEvent actionEvent) {
    }

    public void btnManagePayments_OnKyeReleased(KeyEvent keyEvent) {
    }

    public void btnManageUsers_OnAction(ActionEvent actionEvent) {
    }

    public void btnManageUsers_OnKeyReleased(KeyEvent keyEvent) {
    }

    public void btnViewPayments_OnAction(ActionEvent actionEvent) {
    }

    public void btnViewPayments_OnKeyReleased(KeyEvent keyEvent) {
    }

    private void navigate(String title, String url){
        MainFormController ctrl = (MainFormController) tblDashboard.getScene().getUserData();
        ctrl.navigate(title, url, AppBarIcon.NAV_ICON_BACK);
    }
}
