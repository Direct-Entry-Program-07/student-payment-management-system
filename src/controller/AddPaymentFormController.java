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
import model.Payment;
import model.PaymentTM;
import model.Student;
import model.StudentTM;
import service.CourseService;
import service.PaymentService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddPaymentFormController {
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public TextField txtStudentNIC;
    public ComboBox cmbCourseName;
    public TextField txtTotalFee;
    public TextField txtRemaining;
    public ComboBox cmbPaymentReason;
    public TextField txtPaymentAmount;
    public ComboBox cmbPaymentMethod;
    public TextField txtRefNumber;
    public TextField txtBalance;
    public TextArea txtNote;
    public AnchorPane root;

    public PaymentService paymentService = new PaymentService();
    ObservableList<String> courseNames = FXCollections.observableArrayList();
    ObservableList<String> paymentReasons = FXCollections.observableArrayList();
    ObservableList<String> paymentMethod = FXCollections.observableArrayList();


    public void initialize(){
        Platform.runLater(() -> {

            if (root.getUserData() != null) {
                PaymentTM tm = (PaymentTM) root.getUserData();
                Payment payment = paymentService.findPayment(tm.getReceiptNumber());


                txtStudentNIC.setText(payment.getNic());
                cmbCourseName.setItems(courseNames);
                txtTotalFee.setText(String.valueOf(payment.getTotalFee()));
                txtRemaining.setText(String.valueOf(payment.getRemaining()));
                cmbPaymentReason.setItems(paymentReasons);
                txtPaymentAmount.setText(String.valueOf(payment.getPaymentAmount()));
                cmbPaymentMethod.setItems(paymentMethod);
                txtRefNumber.setText(txtRefNumber.getText());
                txtBalance.setText(String.valueOf(txtBalance.getText()));
                txtNote.setText(txtNote.getText());

                btnSave.setText("Update Payment");
            }
        });
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        try {
            if (!isValidated()) {
                return;
            }
            Payment payment = new Payment(
                    txtRefNumber.getText(),
                    txtStudentNIC.getText(),
                    "Student Name",
                    (String) cmbCourseName.getValue(),
                    new BigDecimal(txtTotalFee.getText().toString()),
                    new BigDecimal(txtRemaining.getText().toString()),
                    (String)cmbPaymentReason.getValue(),
                    new BigDecimal(txtPaymentAmount.getText().toString()),
                    (String)cmbPaymentMethod.getValue(),
                    new BigDecimal(txtBalance.getText().toString()),
                    txtNote.getText()
            );

            if (btnSave.getText().equals("Save Payment")) {
                paymentService.savePayment(payment);
            } else {
                StudentTM tm = (StudentTM) root.getUserData();
                tm.setFullName(txtStudentName.getText());
                tm.setAddress(txtAddress.getText());
                tm.setContactNumber(txtContactNumber.getText());
                tm.setEmailAddress(txtEmailAddress.getText());
                tm.setCourse(cmbCourseID.getValue() + "-" + cmbBatchID.getValue());
                paymentService.updatePayment(payment);
            }
            new Alert(Alert.AlertType.NONE, "Payment has been saved successfully", ButtonType.OK).show();

        } catch (RuntimeException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save the payment", ButtonType.OK).show();
        }
    }

    private boolean isValidated() {
        String nic = txtStudentNIC.getText();

    }

    public void btnCancel_OnAction(ActionEvent actionEvent) {
        Window window = btnCancel.getScene().getWindow();
        ((Stage) window).close();
    }
}
