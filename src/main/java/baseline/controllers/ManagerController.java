package baseline.controllers;

import baseline.functions.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController implements Initializable {
    FileChooser fileChooser = new FileChooser();
    public ObservableList<Item> list = FXCollections.observableArrayList();

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
        //make a function that deals with this
        addItem();
    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        //make a function that deals with this
        removeItem();
    }

    public void removeItem() {
    }
    public void addItem(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        //initialize the table in another function
    }
    public void initializeTable(){

    }
    public void saveFile(){

    }
    public void saveAsTSV(){

    }
    public void saveAsHTML(){

    }
    public void saveAsJSON(){

    }
    public void loadFile(){

    }


}
