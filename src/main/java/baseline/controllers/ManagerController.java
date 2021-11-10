package baseline.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ManagerController {

    @FXML
    private Button addButton;

    @FXML
    private Label nameErrorLabel;

    @FXML
    private TextField nameText;

    @FXML
    private Label priceErrorLabel;

    @FXML
    private TextField priceText;

    @FXML
    private Button removeItemButton;

    @FXML
    private Label serialErrorLabel;

    @FXML
    private TextField serialText;

    @FXML
    private TextField sortNameText;

    @FXML
    private TextField sortSerialText;

    @FXML
    void addPressed(ActionEvent event) {

    }

    @FXML
    void removeSelectedItem(ActionEvent event) {

    }

}
