package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.AppBarIcon;
import util.MaterialUI;

import java.io.IOException;

public class ManageUsersFormController {

    public TextField txtQuery;
    public TableView tblUsers;
    public JFXButton btnAddUser;

    public void initialize(){

        MaterialUI.paintTextFields(txtQuery);

        tblUsers.getItems().add(new Object());
        tblUsers.getItems().add(new Object());
        tblUsers.getItems().add(new Object());
        tblUsers.getItems().add(new Object());
    }

    public void btnAddUser_OnAction(ActionEvent actionEvent) {
        try {
            Stage secondaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/SecondaryMainForm.fxml"));
            Scene secondaryScene = new Scene(loader.load());
            SecondaryMainFormController ctrl = loader.getController();

            secondaryStage.setScene(secondaryScene);
            secondaryScene.setFill(Color.TRANSPARENT);
            secondaryStage.initStyle(StageStyle.TRANSPARENT);
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(txtQuery.getScene().getWindow());
            secondaryStage.setTitle("Add new User");

            ctrl.navigate("Add new User", "/view/AddUserForm.fxml", AppBarIcon.NAV_ICON_NONE);

            secondaryStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
