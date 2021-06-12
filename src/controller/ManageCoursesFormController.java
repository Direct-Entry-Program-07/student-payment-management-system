package controller;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.MaterialUI;

public class ManageCoursesFormController {

    public TextField txtQuery;
    public TableView tblCourses;

    public void initialize(){
        MaterialUI.paintTextFields(txtQuery);
    }
}
