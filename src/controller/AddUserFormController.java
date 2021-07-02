package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.*;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import service.UserService;

import java.time.LocalDate;

public class AddUserFormController {
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public ComboBox cmbUserType;
    public TextField txtUsername;
    public TextField txtFullname;
    public TextField txtPassword;
    public AnchorPane root;
    public TextArea txtAddress;
    public TextField txtContactNumber;
    public TextField txtEmail;

    public UserService userService = new UserService();
    public ObservableList<String> usertypes = FXCollections.observableArrayList();

    public void initialize() {
        Platform.runLater(() -> {

            cmbUserType.setValue(usertypes);

            if (root.getUserData() != null) {
                UserTM tm = (UserTM) root.getUserData();
                User user = userService.findUser(tm.getNic());

                // cmbUserType.setItems();
                txtUsername.setText(user.getUsername());
                txtFullname.setText(user.getFullname());
                txtAddress.setText(user.getAddress());
                txtContactNumber.setText(user.getContactNumber());
                txtEmail.setText(user.getEmailAddress());

                btnSave.setText("Update User");
            }
        });
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        /*try {
            if (!isValidated()) {
                return;
            }

            String pwdHash = DigestUtils.sha256Hex(txtPassword.getText());
            User user = new User(cmbUserType.selectionModelProperty().getValue().toString(),
                    txtFullname.getText(),
                    txtUsername.getText(),
                    txtFullname.getText(),
                    pwdHash,
                    txtAddress.getText(),
                   txtContactNumber.getText(),
                    txtEmail.getText());

            if (btnSave.getText().equals("Save User")) {
                userService.saveUser(user);
            } else {
                UserTM tm = (UserTM) root.getUserData();
                tm.setUserType((String) cmbUserType.getValue());
                tm.setNic(txt.getText());
                tm.setContactNumber(txtContactNumber.getText());
                tm.setEmailAddress(txtEmailAddress.getText());
                tm.setCourse(cmbCourseID.getValue() + "-" + cmbBatchID.getValue());
                studentService.updateStudent(student);
            }
            new Alert(Alert.AlertType.NONE, "Student has been saved successfully", ButtonType.OK).show();

        } catch (RuntimeException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save the student", ButtonType.OK).show();
        }*/
    }

    private boolean isValidated() {
        return false;
    }

    public void btnCancel_OnAction(ActionEvent actionEvent) {
        Window window = btnCancel.getScene().getWindow();
        ((Stage) window).close();
    }
}
