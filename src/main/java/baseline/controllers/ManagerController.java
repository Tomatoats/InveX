package baseline.controllers;

import baseline.InventoryManagementApplication;
import baseline.functions.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController extends InventoryManagementApplication implements Initializable {
    FileChooser fileChooser = new FileChooser();
    public ObservableList<Item> list = FXCollections.observableArrayList();
    Item item = new Item("","",0);

    @FXML
    private MenuItem aboutButton;

    @FXML
    private Button addButton;

    @FXML
    private MenuItem deleteAllButton;

    @FXML
    private MenuItem loadHTMLButton;

    @FXML
    private MenuItem loadJSONButton;

    @FXML
    private MenuItem loadTSVButton;

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
    private MenuItem saveHTML;

    @FXML
    private MenuItem saveJSON;

    @FXML
    private MenuItem saveTSV;

    @FXML
    private Label serialErrorLabel;

    @FXML
    private TextField serialText;

    @FXML
    private TextField sortNameText;

    @FXML
    private TextField sortSerialText;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colSerial;

    @FXML
    private TableView listTable;



    @FXML
    void addPressed(ActionEvent event) {
        //make a function that deals with this
        addItem();
    }

    public void addItem(){

    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        //make a function that deals with this
        removeItem();
    }
    public void removeItem() {
    }
    @FXML
    void aboutPressed(ActionEvent event) {
        //make a function that deals with this
        openUpAbout();
    }
    public void openUpAbout(){

    }

    @FXML
    void clearPressed(ActionEvent event) {
        //make a function that deals with this
        clearAllItems();
    }
    public void clearAllItems(){

    }

    @FXML
    void htmlPressed(ActionEvent event) {
        //make it save as an HTML
        saveFile("HTML");
    }

    @FXML
    void jsonPressed(ActionEvent event) {
        //Make it save as an JSON
        saveFile("JSON");
    }

    @FXML
    void tsvPressed(ActionEvent event) {
        //make it save as a txt
        saveFile("txt");
    }
    public void saveFile(String fileType){
        //depending on the what file it is, open up a file to save as
        // and save it in the format that file needs
    }

    @FXML
    void loadHTMLPressed(ActionEvent event) {
        //load the list  as an HTML
        loadAsHTML();
    }

    @FXML
    void loadJSONPressed(ActionEvent event) {
        //load the list as a JSON
        loadAsJSON();
    }

    @FXML
    void loadTSVPressed(ActionEvent event) {
        //load the list as a TSV
        loadAsTSV();
    }


    @FXML
    void sortNameTyped(ActionEvent event) {
        //Sort the lists according to the text of the names
        sortNames(sortNameText.getText());
    }
    public void sortNames(String text){
        //Compare the list to the text given, if any names contain that string, show up
    }
    @FXML
    void sortSerialTyped(ActionEvent event) {
        //sort the lists according to the text of the serial
        sortSerial(sortSerialText.getText());
    }
    public void sortSerial(String text){
        //compare the list to the text given, if any Serials contain that string, show up
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        //initialize the table in another function
        initializeTable();
    }
    public void initializeTable(){

        //Set up the three columns, letting two of them being editable
        // and the complete one being kinda there
        listTable.setEditable(true);
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit((EventHandler<TableColumn.CellEditEvent>) event -> {
                    if (item.nameRegex(String.valueOf(event.getNewValue())) == true) {
                        ((Item) event.getTableView().getItems().get(event.getTablePosition().getRow())).setName(String.valueOf(event.getNewValue()));
                        nameErrorLabel.setText("");
                    } else {
                        nameErrorLabel.setText("Name must be within 2-256 characters");
                    }
                }
        );

        colSerial.setCellValueFactory(new PropertyValueFactory<>("Serial Number"));
        colSerial.setCellFactory(TextFieldTableCell.forTableColumn());
        colSerial.setOnEditCommit((EventHandler<TableColumn.CellEditEvent>) event -> {
                    if (Boolean.FALSE.equals(item.serialRegex(String.valueOf(event.getNewValue())))) {
                        serialErrorLabel.setText("Serial must in the format A-XXX-XXX-XXX where X is either letter or number");
                    } else {
                        ((Item) event.getTableView().getItems().get(event.getTablePosition().getRow())).setSerial(String.valueOf((event.getNewValue())));
                        serialErrorLabel.setText("");
                    }
                }
        );
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrice.setOnEditCommit((EventHandler<TableColumn.CellEditEvent>) event -> {
                    if (Boolean.FALSE.equals(item.priceRegex(String.valueOf(event.getNewValue())))) {
                        priceErrorLabel.setText("Price must be greater than 0 And  numbers only (decimal allowed)");
                    } else {
                        ((Item) event.getTableView().getItems().get(event.getTablePosition().getRow())).setPrice(String.valueOf((event.getNewValue())));
                        priceErrorLabel.setText("");
                    }
                }
        );


        listTable.setItems(list);

    }
    public void saveAsTSV(){

    }
    public void saveAsHTML(){

    }
    public void saveAsJSON(){

    }
    public void loadAsTSV(){

    }
    public void loadAsHTML(){

    }
    public void loadAsJSON(){

    }

}
