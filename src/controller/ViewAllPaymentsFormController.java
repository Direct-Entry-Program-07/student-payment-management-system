package controller;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.MaterialUI;

public class ViewAllPaymentsFormController {

    public TextField txtQuery;
    public TableView tblAllPayments;

    public void initialize(){
        MaterialUI.paintTextFields(txtQuery);
    }
}
