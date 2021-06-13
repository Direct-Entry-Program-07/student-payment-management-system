package controller;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.MaterialUI;

public class ManageUsersFormController {

    public TextField txtQuery;
    public TableView tblUsers;

    public void initialize(){
        MaterialUI.paintTextFields(txtQuery);
    }
}
