package com.example.demo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


import java.io.File;
import java.io.IOException;

import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Row;


public class HelloController {
    @FXML
    private AnchorPane ConnectHeader;

    @FXML
    private AnchorPane separate;

    @FXML
    private Label SecondHeader;

    @FXML
    private TextField DataBaseName;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private Button connect;

    @FXML
    private TextField IDField;

    @FXML
    private TextField NameHotelField;

    @FXML
    private TextField HotelRoomNumberField;

    @FXML
    private TextField LvLHotelRoomField;

    @FXML
    private TextField StatusHotelRoomField;

    @FXML
    private TextField FullNameField;

    @FXML
    private TextField NumberField;

    @FXML
    private Button addRow;

    @FXML
    private GridPane root;

    @FXML
    private Button update;

    @FXML
    private Button baseDelete;

    @FXML
    protected void Update() throws IOException{
        File file = new File(DataBaseName.getText()+".mdb");
        Database db = new DatabaseBuilder(file)
                .setReadOnly(false)
                .open();
        Table HotelManager = db.getTable("Hotel Manager");
        root.getChildren().clear();
        root.setGridLinesVisible(true);
        int row_g = 0;
        for (Row row : HotelManager) {
            int col_g = 0;
            for (Object value : row.values().toArray()) {
                Label chil = new Label();
                chil.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
                chil.setText(value.toString());
                root.setGridLinesVisible(true);
                root.add(chil, col_g, row_g);
                col_g += 1;
            }

            Button editBtn = new Button();
            editBtn.setText("Изменить");
            editBtn.setOnAction(e -> {
                editRow(row, HotelManager);
                editBtn.setText("Изменено");
                editBtn.setDisable(true);
            });
            root.add(editBtn, col_g + 1, row_g);

            Button deleteBtn = new Button();
            deleteBtn.setText("Удалить");
            deleteBtn.setOnAction(e -> {
                try {
                    HotelManager.deleteRow(row);
                    deleteBtn.setText("Удалено");
                    deleteBtn.setDisable(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            root.add(deleteBtn, col_g + 2, row_g);

            row_g += 1;
        }
    }

    @FXML
    protected void addRow(ActionEvent actionEvent) throws IOException{
        File file = new File(DataBaseName.getText()+".mdb");
        Database db = new DatabaseBuilder(file)
                .setReadOnly(false)
                .open();
        Table HotelManager = db.getTable("Hotel Manager");
        if ((IDField.getText() != null) &&
                (NameHotelField.getText() != null) &&
                (HotelRoomNumberField.getText() != null) &&
                (LvLHotelRoomField.getText() != null) &&
                (StatusHotelRoomField.getText() != null) &&
                (FullNameField.getText() != null) &&
                (NumberField.getText() != null))
        {
            HotelManager.addRow(
                    IDField.getText(),
                    NameHotelField.getText(),
                    HotelRoomNumberField.getText(),
                    LvLHotelRoomField.getText(),
                    StatusHotelRoomField.getText(),
                    FullNameField.getText(),
                    NumberField.getText());
        }
        IDField.setText("");
        NameHotelField.setText("");
        HotelRoomNumberField.setText("");
        LvLHotelRoomField.setText("");
        StatusHotelRoomField.setText("");
        FullNameField.setText("");
        NumberField.setText("");
        CreateBase();
    }


    @FXML
    protected void CreateBase() throws IOException {
        root.getChildren().clear();
        root.setGridLinesVisible(true);
        if (new File(DataBaseName.getText()+".mdb").isFile()) {
            File file = new File(DataBaseName.getText()+".mdb");
            Database db = new DatabaseBuilder(file)
                    .open();
        }
        else {
            File file = new File(DataBaseName.getText()+".mdb");
            Database db = new DatabaseBuilder(file)
                    .setFileFormat(Database.FileFormat.V2000)
                    .create();
            Table HotelManager = new TableBuilder("Hotel Manager")
                    .addColumn(new ColumnBuilder("ID", DataType.TEXT))
                    .addColumn(new ColumnBuilder("Название отеля", DataType.TEXT))
                    .addColumn(new ColumnBuilder("№", DataType.TEXT))
                    .addColumn(new ColumnBuilder("КлассНомера", DataType.TEXT))
                    .addColumn(new ColumnBuilder("Статус номера", DataType.TEXT))
                    .addColumn(new ColumnBuilder("ФИО Клиента", DataType.TEXT))
                    .addColumn(new ColumnBuilder("Номер телефона Клиента", DataType.TEXT))
                    .toTable(db);
        }
        File file = new File(DataBaseName.getText()+".mdb");
        Database db = new DatabaseBuilder(file)
                .setReadOnly(false)
                .open();
        Table HotelManager = db.getTable("Hotel Manager");

        ConnectHeader.setVisible(false);
        DataBaseName.setVisible(false);
        connect.setVisible(false);
        separate.setVisible(false);

        scrollpane.setVisible(true);
        SecondHeader.setVisible(true);
        IDField.setVisible(true);
        NameHotelField.setVisible(true);
        HotelRoomNumberField.setVisible(true);
        LvLHotelRoomField.setVisible(true);
        StatusHotelRoomField.setVisible(true);
        FullNameField.setVisible(true);
        NumberField.setVisible(true);
        addRow.setVisible(true);
        update.setVisible(true);
        baseDelete.setVisible(true);
        baseDelete.setText("Удалить базу");

        int row_g = 0;
        for (Row row : HotelManager) {
            int col_g = 0;
            for (Object value : row.values().toArray()) {
                Label chil = new Label();
                chil.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
                chil.setText(value.toString());
                root.setGridLinesVisible(true);
                root.add(chil, col_g, row_g);
                col_g += 1;
            }

            Button editBtn = new Button();
            editBtn.setText("Изменить");
            editBtn.setOnAction(e -> {
                editRow(row, HotelManager);
                editBtn.setText("Изменено");
                editBtn.setDisable(true);
            });
            root.add(editBtn, col_g + 1, row_g);

            Button deleteBtn = new Button();
            deleteBtn.setText("Удалить");
            deleteBtn.setOnAction(e -> {
                try {
                    HotelManager.deleteRow(row);
                    deleteBtn.setText("Удалено");
                    deleteBtn.setDisable(true);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            root.add(deleteBtn, col_g + 3, row_g);

            row_g += 1;
        }
    }

    private void editRow(Row row, Table HotelManager) {
        // Assuming you have text fields for editing
        // You need to create text fields for each column or reuse the existing ones
        TextField idTextField = new TextField(row.getString("ID"));
        TextField nameTextField = new TextField(row.getString("Название отеля"));
        TextField HotelRoomNumberField = new TextField(row.getString("№"));
        // ... Repeat this for other columns

        // Create a dialog for editing
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Edit");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("ID:"), 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(new Label("Название отеля:"), 0, 1);
        gridPane.add(nameTextField, 1, 1);
        gridPane.add(new Label("№:"), 0, 2);
        gridPane.add(HotelRoomNumberField, 1, 2);
        // ... Repeat this for other columns
        dialog.getDialogPane().setContent(gridPane);

        // Handle the OK button press
        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                // Update the row with the new values
                row.put("ID", idTextField.getText());
                row.put("Название отеля", nameTextField.getText());
                row.put("№", HotelRoomNumberField.getText());


                // ... Repeat this for other columns

                try {
                    // Update the row in the database
                    HotelManager.updateRow(row);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    public void deleteDatabase(ActionEvent actionEvent) {
        File file = new File(DataBaseName.getText()+".mdb");
        if(file.delete()){
            System.out.println(DataBaseName.getText()+".mdb" + " file was deleted");
        }else System.out.println(DataBaseName.getText()+".mdb" + " file is`t found");
        Platform.exit();
    }


}