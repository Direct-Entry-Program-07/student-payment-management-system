package controller;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.MaterialUI;

public class ManagePaymentsFormController {
    public TextField txtQuery;
    public TableView tblPayments;

    public void initialize(){
        MaterialUI.paintTextFields(txtQuery);
    }
}
