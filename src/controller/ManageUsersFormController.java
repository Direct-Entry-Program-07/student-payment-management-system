package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import service.UserServiceRedisImpl;
import util.AppBarIcon;
import util.MaterialUI;

import java.io.IOException;
import java.util.Set;

public class ManageUsersFormController {

    public TextField txtQuery;
    public TableView<UserTM> tblUsers;
    public JFXButton btnAddUser;

    private final UserServiceRedisImpl userService = new UserServiceRedisImpl();

    public void initialize(){

        MaterialUI.paintTextFields(txtQuery);

        tblUsers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("fullname"));
        TableColumn<UserTM, ComboBox> userTypeCombo = (TableColumn<UserTM, ComboBox>) tblUsers.getColumns().get(1);
        userTypeCombo.setCellValueFactory(param -> {
            ComboBox<String> cmbBatch = new ComboBox();
            cmbBatch.setPrefWidth(tblUsers.getColumns().get(1).getWidth());
            cmbBatch.setPrefHeight(40);

            // batchId.setItems(batchIdOptions);

            tblUsers.getColumns().get(1).widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    cmbBatch.setPrefWidth((double) newValue);
                }
            });

            return new ReadOnlyObjectWrapper<>(cmbBatch);
        });

        tblUsers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("username"));
        tblUsers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblUsers.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        tblUsers.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblUsers.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("joinedDate"));

        LoadAllUsers("");

    }

    private void LoadAllUsers(String query) {
        tblUsers.getItems().clear();

        for (User user : userService.findUsers(query)) {

            tblUsers.getItems().add(new UserTM(
                    user.getFullname(),
                    user.getUserType(),
                    user.getUsername(),
                    user.getAddress(),
                    user.getContactNumber(),
                    user.getEmailAddress(),
                    user.getJoinedDate()
            ));
        }
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
