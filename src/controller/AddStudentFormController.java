package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Student;
import model.StudentTM;
import service.StudentService;

import java.time.LocalDate;

import static util.ValidationUtil.*;

public class AddStudentFormController {
    private final StudentService studentService = new StudentService();
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public TextField txtNIC;
    public TextField txtStudentName;
    public TextField txtContactNumber;
    public TextArea txtAddress;
    public TextField txtDateOfBirth;
    public TextField txtEmailAddress;
    public AnchorPane root;
    public ComboBox cmbCourseID;
    public ComboBox cmbBatchID;
    public TextField txtCourseId;

    public void initialize() {

        Platform.runLater(() -> {

            if (root.getUserData() != null) {
                StudentTM tm = (StudentTM) root.getUserData();
                Student student = studentService.findStudent(tm.getNic());

                txtNIC.setEditable(false);
                txtNIC.setText(student.getNic());
                txtStudentName.setText(student.getFullName());
                txtAddress.setText(student.getAddress());
                txtContactNumber.setText(student.getContactNumber());
                txtEmailAddress.setText(student.getEmailAddress());
                txtDateOfBirth.setText(student.getDateOfBirth().toString());

                btnSave.setText("Update Student");
            }
        });
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        // System.out.println(cmbCourseID.getSelectionModel().getSelectedItem().toString());
        try {
            if (!isValidated()) {
                return;
            }
            Student student = new Student(txtNIC.getText(),
                    txtStudentName.getText(),
                    txtContactNumber.getText(),
                    txtAddress.getText(),
                    LocalDate.parse(txtDateOfBirth.getText()),
                    txtEmailAddress.getText(),
                    (String) cmbCourseID.getValue(),
                    (String) cmbBatchID.getValue());

            if (btnSave.getText().equals("Save Student")) {
                studentService.saveStudent(student);
            } else {
                StudentTM tm = (StudentTM) root.getUserData();
                tm.setFullName(txtStudentName.getText());
                tm.setAddress(txtAddress.getText());
                tm.setContactNumber(txtContactNumber.getText());
                tm.setEmailAddress(txtEmailAddress.getText());
                tm.setCourse((String) cmbCourseID.getValue() + "-" + (String) cmbBatchID.getValue());
                studentService.updateStudent(student);
            }
            new Alert(Alert.AlertType.NONE, "Student has been saved successfully", ButtonType.OK).show();

        } catch (RuntimeException e) {
           // e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save the student", ButtonType.OK).show();
        }
    }

    private boolean isValidated() {
        String nic = txtNIC.getText();
        String fullName = txtStudentName.getText();
        String address = txtAddress.getText();
        String dob = txtDateOfBirth.getText();
        String contactnumber = txtContactNumber.getText();
        String email = txtEmailAddress.getText();

        if (!((nic.length() == 10 && (nic.endsWith("v") || nic.endsWith("V")) && isInteger(nic.substring(0, 9)))
                || (nic.length() == 12 && isInteger(nic)))) {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
            txtNIC.requestFocus();
            return false;
        } else if (!(isValid(fullName, true, false) && fullName.trim().length() >= 3)) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtStudentName.requestFocus();
            return false;
        } else if (!(isValid(address, true, true, ':', ',', '.', '-', '/', '\\'))) {
            new Alert(Alert.AlertType.ERROR, "Invalid address").show();
            txtAddress.requestFocus();
            return false;
        } else if (!isValidDate(dob)) {
            new Alert(Alert.AlertType.ERROR, "Invalid date").show();
            txtDateOfBirth.requestFocus();
        } else if (!(contactnumber.length() == 11 & isInteger(contactnumber.substring(0, 3)) && isInteger(contactnumber.substring(4, 11)))) {
            new Alert(Alert.AlertType.ERROR, "Invalid contact number").show();
            txtContactNumber.requestFocus();
            return false;
        } else if (!isValidEmail(email)) {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
            txtEmailAddress.requestFocus();
            return false;
        }
        return true;
    }

    public void btnCancel_OnAction(ActionEvent actionEvent) {
        Window window = btnCancel.getScene().getWindow();
        ((Stage) window).close();
    }

    public void txtContactNumber_OnKeyTyped(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().equals("-") && (txtContactNumber.getText().length() == 3)) {
            return;
        }

        if (!Character.isDigit(keyEvent.getCharacter().charAt(0))) {
            keyEvent.consume();     // This is not going to forward to the Java FX Runtime Env.
            return;
        }

        if ((txtContactNumber.getText().length() == 3) && (txtContactNumber.getCaretPosition() == txtContactNumber.getLength())) {
            txtContactNumber.appendText("-");
            txtContactNumber.positionCaret(txtContactNumber.getText().length() + 1);
        }
    }

    public void txtDateOfBirth_OnKeyTyped(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().equals("-") && (txtDateOfBirth.getText().length() == 4 || txtDateOfBirth.getText().length() == 7)) {
            return;
        }

        if (!Character.isDigit(keyEvent.getCharacter().charAt(0))) {
            keyEvent.consume();     // This is not going to forward to the Java FX Runtime Env.
            return;
        }

        if ((txtDateOfBirth.getText().length() == 4 || txtDateOfBirth.getText().length() == 7) && (txtDateOfBirth.getCaretPosition() == txtDateOfBirth.getLength())) {
            txtDateOfBirth.appendText("-");
            txtDateOfBirth.positionCaret(txtDateOfBirth.getText().length() + 1);
        }
    }
}
