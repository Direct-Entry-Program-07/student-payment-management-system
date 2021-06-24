package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Course;
import model.CourseTM;
import service.CourseService;
import service.exception.NotFoundException;
import util.AppBarIcon;
import util.MaterialUI;

import java.io.IOException;
import java.util.Optional;

public class ManageCoursesFormController {

    private final CourseService courseService = new CourseService();
    public TextField txtQuery;
    public TableView<CourseTM> tblCourses;
    public JFXButton btnAddCourse;
    ObservableList<String> cmbBatchIdOptions = FXCollections.observableArrayList();

    public void initialize() {



        MaterialUI.paintTextFields(txtQuery);

        tblCourses.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courseID"));
        tblCourses.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("courseName"));
        TableColumn<CourseTM, ComboBox> batchIdCombo = (TableColumn<CourseTM, ComboBox>) tblCourses.getColumns().get(2);
        batchIdCombo.setCellValueFactory(param -> {
            ComboBox batchId = new ComboBox();
            batchId.setPrefWidth(tblCourses.getColumns().get(2).getWidth());
            batchId.setPrefHeight(40);

           tblCourses.getColumns().get(2).widthProperty().addListener(new ChangeListener<Number>() {
               @Override
               public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                   batchId.setPrefWidth((double) newValue);
               }
           });

            return new ReadOnlyObjectWrapper<>(batchId);
        });
        tblCourses.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("noOfStudentsForTheBatch"));
        tblCourses.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>(""));
        tblCourses.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("batchCommencingDate"));
        TableColumn<CourseTM, HBox> operateCol = (TableColumn<CourseTM, HBox>) tblCourses.getColumns().get(6);

        operateCol.setCellValueFactory(param -> {
            ImageView imgEdit = new ImageView("/view/assets/icons/Edit.png");
            ImageView imgTrash = new ImageView("/view/assets/icons/Trash.png");

            imgEdit.getStyleClass().add("action-icons");
            imgTrash.getStyleClass().add("action-icons");

            imgEdit.setOnMouseClicked(event -> updateCourse(param.getValue()));
            imgTrash.setOnMouseClicked(event -> deleteCourse(param.getValue()));

            return new ReadOnlyObjectWrapper<>(new HBox(10, imgEdit, imgTrash));
        });

        txtQuery.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                LoadAllCourses(newValue);
            }
        });

        LoadAllCourses("");
    }

    private void deleteCourse(CourseTM tm) {
        try{
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this student?", ButtonType.YES, ButtonType.NO).showAndWait();
            if (buttonType.get() == ButtonType.YES){
                courseService.deleteCourse(tm.getCourseID());
                tblCourses.getItems().remove(tm);
            }
        }catch (RuntimeException | NotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item", ButtonType.OK).show();
        }
    }

    private void updateCourse(CourseTM tm) {
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
            secondaryStage.setTitle("Update Course");
            secondaryStage.centerOnScreen();
            ctrl.navigate("Update Course", "/view/AddCourseForm.fxml", AppBarIcon.NAV_ICON_NONE, null, tm);

            secondaryStage.showAndWait();
            tblCourses.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadAllCourses(String query) {

        tblCourses.getItems().clear();

        for (Course course : courseService.findCourses(query)) {
            tblCourses.getItems().add(new CourseTM(
                    course.getCourseID(),
                    course.getCourseName(),
                    course.getBatchID(),
                    course.getNoOfStudentsForTheBatch(),
                    course.getBatchCommencingDate(),
                    course.getNote()));
        }
    }

    public void btnAddCourse_OnAction(ActionEvent actionEvent) {
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
            secondaryStage.setTitle("Add new Course");

            ctrl.navigate("Add new Course", "/view/AddCourseForm.fxml", AppBarIcon.NAV_ICON_NONE);

            secondaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
