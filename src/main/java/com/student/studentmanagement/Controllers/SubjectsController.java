package com.student.studentmanagement.Controllers;

import com.student.studentmanagement.Application.Dtos.SubjectWithLevelsAndTeacherDTO;
import com.student.studentmanagement.Application.Repositories.SubjectRepo;
import com.student.studentmanagement.Domain.Entities.Level;
import com.student.studentmanagement.Domain.Entities.Subject;
import com.student.studentmanagement.Domain.Entities.Subject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SubjectsController implements Initializable {
    @FXML
    private Button newSubjectBtn;
    @FXML
    private Button levelBtn;
    @FXML
    private TableView<SubjectWithLevelsAndTeacherDTO> subjectTable;
    @FXML
    private TableColumn<SubjectWithLevelsAndTeacherDTO , String> subjectName;
    @FXML
    private TableColumn<SubjectWithLevelsAndTeacherDTO , String> levelName;
    @FXML
    private TableColumn<SubjectWithLevelsAndTeacherDTO , String> teacherName;
    @FXML
    private TableColumn<SubjectWithLevelsAndTeacherDTO, Void> subjectUpdate;
    @FXML
    private TableColumn<SubjectWithLevelsAndTeacherDTO, Void> subjectDelete;
    private List<SubjectWithLevelsAndTeacherDTO> SubjectsList;
    private SubjectRepo subjectRepo = new SubjectRepo();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        levelBtn.setOnAction(new EventHandler<ActionEvent >() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Level(event); // Call the Login method
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        newSubjectBtn.setOnAction(new EventHandler<ActionEvent >() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    newSubject(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
            SubjectsList = subjectRepo.getAllSubjectsWithLevelsAndTeachers();
        ObservableList<SubjectWithLevelsAndTeacherDTO> data = FXCollections.observableArrayList(SubjectsList);
        subjectTable.setItems(data);
        subjectName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSubjectName()));

        levelName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getLevelNames()));

        teacherName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTeacherName()));
        addUpdateButtonColumn();

        addDeleteButtonColumn();
    }


    private void addUpdateButtonColumn() {
        Callback<TableColumn<SubjectWithLevelsAndTeacherDTO, Void>, TableCell<SubjectWithLevelsAndTeacherDTO, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<SubjectWithLevelsAndTeacherDTO, Void> call(final TableColumn<SubjectWithLevelsAndTeacherDTO, Void> param) {
                return new TableCell<SubjectWithLevelsAndTeacherDTO, Void>() {
                    private final Button btn = new Button("Update");

                    {
                        btn.setOnAction(event -> {
                            SubjectWithLevelsAndTeacherDTO Subject = getTableView().getItems().get(getIndex());
                            subjectUpdate(Subject);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        subjectUpdate.setCellFactory(cellFactory);
    }

    private void addDeleteButtonColumn() {
        Callback<TableColumn<SubjectWithLevelsAndTeacherDTO, Void>, TableCell<SubjectWithLevelsAndTeacherDTO, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<SubjectWithLevelsAndTeacherDTO, Void> call(final TableColumn<SubjectWithLevelsAndTeacherDTO, Void> param) {
                return new TableCell<SubjectWithLevelsAndTeacherDTO, Void>() {
                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction(event -> {
                            SubjectWithLevelsAndTeacherDTO subject = getTableView().getItems().get(getIndex());
                            subjectDelete(subject);
                            SubjectsList.remove(subject);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        subjectDelete.setCellFactory(cellFactory);
    }
    public void subjectUpdate(SubjectWithLevelsAndTeacherDTO subject) {
        //levelsRepo.updateLevel(level);
    }

    public void subjectDelete(SubjectWithLevelsAndTeacherDTO subject) {
        //levelsRepo.deleteLevel(level);
    }
    @FXML
    void Level(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/student/studentmanagement/Levels.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void newSubject(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/student/studentmanagement/createSubject.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
