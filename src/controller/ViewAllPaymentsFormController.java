package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.MaterialUI;

public class ViewAllPaymentsFormController {

    public TextField txtQuery;
    public TableView tblAllPayments;
    public JFXButton btnPrintReceipt;

    public void initialize(){

        MaterialUI.paintTextFields(txtQuery);

        tblAllPayments.getItems().add(new Object());
        tblAllPayments.getItems().add(new Object());
        tblAllPayments.getItems().add(new Object());
        tblAllPayments.getItems().add(new Object());
    }

    public void btnPrintReceipt_OnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.ERROR, "Can't find the Printer", ButtonType.OK).show();
    }
}
