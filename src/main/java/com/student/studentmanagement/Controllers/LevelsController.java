package com.student.studentmanagement.Controllers;

import com.student.studentmanagement.Application.Repositories.LevelsRepo;
import com.student.studentmanagement.Domain.Entities.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class LevelsController implements Initializable {
    @FXML
    private Button adminBtn;
    @FXML
    private Button teacherBtn;
    @FXML
    private Button courseBtn;
    @FXML
    private Button subjectBtn;
    @FXML
    private Button newLevelBtn;
    @FXML
    private TableView<Level> levelTable;
    @FXML
    private TableColumn<Level, String> levelName;
    @FXML
    private TableColumn<Level, Void> levelUpdate;
    @FXML
    private TableColumn<Level, Void> levelDelete;

    private ObservableList<Level> levelsList = FXCollections.observableArrayList();
    private LevelsRepo levelsRepo = new LevelsRepo();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newLevelBtn.setOnAction(event -> openModalDialog());
        levelName.setCellValueFactory(cellData -> {
            Level level = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(level.getLevelName());
        });

        levelTable.setEditable(true);
        levelName.setCellFactory(TextFieldTableCell.forTableColumn());
        levelName.setOnEditCommit(event -> {
            Level level = event.getRowValue();
            level.setLevelName(event.getNewValue());
            updateLevel(level);
        });

        addUpdateButtonColumn();

        addDeleteButtonColumn();

        initializeLevelTable();
    }
    private void openModalDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/student/studentmanagement/createLevelModal.fxml"));
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setScene(new Scene(loader.load()));

            createLevelController controller = loader.getController();
            modalStage.setTitle("create level");
            modalStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initializeLevelTable() {
        try {
            List<Level> levels = levelsRepo.getAll();

            levelsList.clear();
            levelsList.addAll(levels);

            levelTable.setItems(levelsList);
        } catch (Exception e) {
            System.err.println("Error initializing level table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void addUpdateButtonColumn() {
        Callback<TableColumn<Level, Void>, TableCell<Level, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Level, Void> call(final TableColumn<Level, Void> param) {
                return new TableCell<Level, Void>() {
                    private final Button btn = new Button("Update");

                    {
                        btn.setOnAction(event -> {
                            Level level = getTableView().getItems().get(getIndex());
                            updateLevel(level);
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

        levelUpdate.setCellFactory(cellFactory);
    }

    private void addDeleteButtonColumn() {
        Callback<TableColumn<Level, Void>, TableCell<Level, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Level, Void> call(final TableColumn<Level, Void> param) {
                return new TableCell<Level, Void>() {
                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction(event -> {
                            Level level = getTableView().getItems().get(getIndex());
                            deleteLevel(level);
                            levelsList.remove(level);
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

        levelDelete.setCellFactory(cellFactory);
    }

    public void updateLevel(Level level) {
        levelsRepo.updateLevel(level);
    }

    public void deleteLevel(Level level) {
        levelsRepo.deleteLevel(level);
    }
}