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

public class ManagePaymentsFormController {
    public TextField txtQuery;
    public TableView tblPayments;
    public JFXButton btnAddPayment;

    public void initialize(){

        MaterialUI.paintTextFields(txtQuery);

        tblPayments.getItems().add(new Object());
        tblPayments.getItems().add(new Object());
        tblPayments.getItems().add(new Object());
        tblPayments.getItems().add(new Object());
    }

    public void btnAddPayment_OnAction(ActionEvent actionEvent) {
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
            secondaryStage.setTitle("Add new Payment");

            ctrl.navigate("Add new Payment", "/view/AddPaymentForm.fxml", AppBarIcon.NAV_ICON_NONE);

            secondaryStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
