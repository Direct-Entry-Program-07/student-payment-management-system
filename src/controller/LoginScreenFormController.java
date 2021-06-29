package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.AppBarIcon;

import java.io.IOException;

public class LoginScreenFormController {
    public JFXButton btnLogin;
    public JFXButton btnCancel;
    public TextField txtPassword;
    public TextField txtUserName;
    public AnchorPane root;

    public void btnLogin_OnAction(ActionEvent actionEvent) throws IOException {


        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        MainFormController ctrl = fxmlLoader.getController();
        ctrl.navigate("Student Management System","/view/HomeForm.fxml", AppBarIcon.NAV_ICON_HOME);
        mainScene.setUserData(ctrl);
        mainScene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Student Payment Management System");
        primaryStage.show();
        primaryStage.centerOnScreen();

        Platform.runLater(()->{
            ((Stage)(btnLogin.getScene().getWindow())).close();
        });

    }

    public void btnCancel_OnAction(ActionEvent actionEvent) {

    }
}
