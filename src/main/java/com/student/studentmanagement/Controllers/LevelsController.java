package com.student.studentmanagement.Controllers;

import com.student.studentmanagement.Application.Repositories.LevelsRepo;
import com.student.studentmanagement.Domain.Entities.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
        // Set up the table columns
        levelName.setCellValueFactory(cellData -> {
            Level level = cellData.getValue();
            return new javafx.beans.property.SimpleStringProperty(level.getLevelName());
        });

        // Make levelName column editable
        levelTable.setEditable(true);
        levelName.setCellFactory(TextFieldTableCell.forTableColumn());
        levelName.setOnEditCommit(event -> {
            Level level = event.getRowValue();
            level.setLevelName(event.getNewValue());
            updateLevel(level.getLevelId(), level.getLevelName());
        });

        // Add update button column
        addUpdateButtonColumn();

        // Add delete button column
        addDeleteButtonColumn();

        // Initialize the table with data
        initializeLevelTable();
    }

    private void initializeLevelTable() {
        try {
            // Get data from repository
            List<Level> levels = levelsRepo.getAll();

            // Populate the observable list
            levelsList.clear();
            levelsList.addAll(levels);

            // Set the items to the table
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
                            updateLevel(level.getLevelId(), level.getLevelName());
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
                            deleteLevel(level.getLevelId(), level.getLevelName());
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

    // Method to update level
    public void updateLevel(UUID id, String name) {
        System.out.println("Updating level with ID: " + id + ", new name: " + name);
        // Here you would call your repository method to update the level in the database
        // levelsRepo.update(id, name);
    }

    // Method to delete level
    public void deleteLevel(UUID id, String name) {
        System.out.println("Deleting level with ID: " + id + ", name: " + name);
        // Here you would call your repository method to delete the level from the database
        // levelsRepo.delete(id);
    }
}