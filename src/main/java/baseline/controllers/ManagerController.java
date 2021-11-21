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
import javafx.stage.Window;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ManagerController extends InventoryManagementApplication implements Initializable {
    FileChooser fileChooser = new FileChooser();
    public ObservableList<Item> list = FXCollections.observableArrayList();
    Item item = new Item("","","");


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
    private TableColumn<Item, String> colName;

    @FXML
    private TableColumn<Item, String> colPrice;

    @FXML
    private TableColumn<Item, String> colSerial;

    @FXML
    private TableView listTable;

    @FXML
    private Button searchClear;



    @FXML
    void addPressed(ActionEvent event) {
        //make a function that deals with this
        addItem();
    }

    public void addItem() {
        int i = 0;
        if (!item.nameRegex(nameText.getText())) {
            nameErrorLabel.setText("Name must be within 2-256 characters");
            i++;
        }
        else {
            nameErrorLabel.setText("");
        }
        if ( uniqueSerial(nameText.getText()) && item.serialRegex(serialText.getText())){
            serialErrorLabel.setText("");
        }
        else {
            i++;
            serialErrorLabel.setText("Serial must be unique and in proper format A-XXX-XXX-XXX");
        }
        if (!item.priceRegex(priceText.getText())){
            priceErrorLabel.setText("Price must be greater than 0 and only up to two decimals.");
            i++;
        }
        else {
            priceErrorLabel.setText("");

        }
        if (i == 0){
            BigDecimal number = BigDecimal.valueOf(0);
            Item newItem = new Item("","","");
            newItem.setName(nameText.getText());
            newItem.setSerial(serialText.getText());
            newItem.setPrice(priceText.getText());
            list.add(newItem);

            nameText.clear();
            serialText.clear();
            priceText.clear();

            nameErrorLabel.setText("");
            serialErrorLabel.setText("");
            priceText.setText("");
        }


    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        //make a function that deals with this
        removeItem();
    }
    public void removeItem() {
        listTable.getItems().removeAll(listTable.getSelectionModel().getSelectedItem());
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
        list.remove(0,list.size());
    }

    @FXML
    void htmlPressed(ActionEvent event) {
        //make it save as an HTML
        saveFile("*.html");
    }

    @FXML
    void jsonPressed(ActionEvent event) {
        //Make it save as an JSON
        saveFile("*.JSON");
    }

    @FXML
    void tsvPressed(ActionEvent event) {
        //make it save as a txt
        saveFile("*.txt");
    }
    public void saveFile(String fileType){
        Window stage = nameErrorLabel.getScene().getWindow();
        //open up filechooser and set up for a txt file to be saved
        fileChooser.setTitle("Save Dialog");
        fileChooser.setInitialFileName("Lister");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file", fileType));
        if (fileType.equals("*.txt")){
            try {
                File file = fileChooser.showSaveDialog(stage);
                fileChooser.setInitialDirectory(file.getParentFile());
                saveAsTSV(file);
            }
            catch (Exception ignored){}
        }
        else if (fileType.equals("*.JSON")){
            try {
                File file = fileChooser.showSaveDialog(stage);
                fileChooser.setInitialDirectory(file.getParentFile());
                saveAsJSON(file);
            }
            catch (Exception ignored){}
        }
        else{
            try {
                File file = fileChooser.showSaveDialog(stage);
                fileChooser.setInitialDirectory(file.getParentFile());
                saveAsHTML(file);
            }
            catch (Exception ignored){}
        }
        }

        //depending on the what file it is, open up a file to save as
        // and save it in the format that file needs
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
    void searchClearPressed(ActionEvent event) {
        //load in original list and clear the text fields
        clearSearchBars();
    }
    public void clearSearchBars(){
        sortNameText.clear();
        sortSerialText.clear();
        listTable.setItems(list);
    }

    @FXML
    void sortNameTyped(ActionEvent event) {
        //Sort the lists according to the text of the names
        sortNames(sortNameText.getText());
    }
    public void sortNames(String text){
        ObservableList<Item> names = FXCollections.observableArrayList();
        String lowertext = text.toLowerCase(Locale.ROOT);

        for (Item item : list) {
            String lowername = item.getName().toLowerCase(Locale.ROOT);
            if (lowername.contains(lowertext)) {
                names.add(item);
            }
        }
        listTable.setItems(names);
    }
    @FXML
    void sortSerialTyped(ActionEvent event) {
        //sort the lists according to the text of the serial
        sortSerial(sortSerialText.getText());
    }
    public void sortSerial(String text){
        //compare the list to the text given, if any Serials contain that string, show up
        ObservableList<Item> serial = FXCollections.observableArrayList();
        String lowertext = text.toLowerCase(Locale.ROOT);

        for (Item item : list) {
            String lowerserial = item.getSerial().toLowerCase(Locale.ROOT);
            if (lowerserial.contains(lowertext)) {
                serial.add(item);
            }
        }
        listTable.setItems(serial);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        //initialize the table in another function
        initializeTable();
    }
    public void initializeTable(){

        //Set up the three columns, letting two of them being editable
        // and the complete one being kinda
        listTable.setItems(list);
        listTable.setEditable(true);
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit((EventHandler<TableColumn.CellEditEvent<Item, String>>) event -> {
                    if (item.nameRegex(String.valueOf(event.getNewValue())) == true) {
                        ((Item) event.getTableView().getItems().get(event.getTablePosition().getRow())).setName(String.valueOf((event.getNewValue())));
                        nameErrorLabel.setText("");
                    } else {
                        nameErrorLabel.setText("Name must be within 2-256 characters");
                        listTable.refresh();
                    }
                }
        );

        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colSerial.setCellFactory(TextFieldTableCell.forTableColumn());
        colSerial.setOnEditCommit((EventHandler<TableColumn.CellEditEvent<Item, String>>) event -> {
                    if (Boolean.FALSE.equals(item.serialRegex(String.valueOf(event.getNewValue())))|| !uniqueSerial(String.valueOf(event.getNewValue()))) {
                        serialErrorLabel.setText("Serial must in the format A-XXX-XXX-XXX where X is either letter or number");
                        listTable.refresh();
                    } else {
                        ((Item) event.getTableView().getItems().get(event.getTablePosition().getRow())).setSerial(String.valueOf((event.getNewValue())));
                        serialErrorLabel.setText("");
                    }
                }
        );
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPrice.setCellFactory(TextFieldTableCell.forTableColumn());
        colPrice.setOnEditCommit((EventHandler<TableColumn.CellEditEvent<Item,String>>) event -> {
                    if (Boolean.FALSE.equals(item.priceRegex(String.valueOf(event.getNewValue())))) {
                        priceErrorLabel.setText("Price must be greater than 0 And  numbers only (decimal allowed)");
                        listTable.refresh();
                    } else {
                        ((Item) event.getTableView().getItems().get(event.getTablePosition().getRow())).setPrice(String.valueOf((event.getNewValue())));
                        priceErrorLabel.setText("");
                    }
                }
        );


    }
    public void saveAsTSV(File file){
        try {
            //write down the entire list into a file
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            for (int i = 0; i < list.size();i++) {
                fileWriter.write(list.get(i).getName() + "\t");
                fileWriter.write( list.get(i).getSerial() + "\t");
                fileWriter.write(list.get(i).getPrice()+"\n");

            }
            fileWriter.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void saveAsHTML(File file){
        try {
            //write down the entire list into a file
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write("<html>\n\t<head>\n\t\t<title>Invex system</title>\n\t</head>\n\t<body>\n\t\t<table>\n\t\t");
            fileWriter.write("<thread>\n\t\t<tr>\n\t\t");
            fileWriter.write("<td>Name</td>\n\t\t");
            fileWriter.write("<td>Serial Number</td>\n\t\t");
            fileWriter.write("<td>Price (in $)</td>\n\t\t</tr>\n\t\t</thread>\n\t\t<tbody>\n\t\t");
            for (int i = 0; i < list.size();i++) {
                fileWriter.write("<tr>\n\t\t");
                fileWriter.write("<td>"+list.get(i).getName()+"</td>\n\t\t");
                fileWriter.write("<td>"+list.get(i).getSerial()+"</td>\n\t\t");
                fileWriter.write("<td>"+list.get(i).getPrice()+"</td>\n\t\t");
                fileWriter.write("</tr>\n\t\t");
            }
            fileWriter.write("</tbody>\n\t\t</table>\n\t");
            fileWriter.write("</body>\n</html>");
            fileWriter.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public void saveAsJSON(File file){

    }
    public void loadAsTSV(){
        Window stage = nameErrorLabel.getScene().getWindow();
        // load up the filechooser and look for only text files
        fileChooser.setTitle("Load Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("choose a text file (TSV), html, or JSON", "*.txt","*.html","*.JSON"));
        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(file.getParentFile());
        try (Scanner input = new Scanner(Paths.get(String.valueOf(file))).useDelimiter("\n"))
        {
            clearAllItems();
            //make sure the list is empty then scan in all the strings, parsing them correctly
            //also use a while to make sure it continues after the delimiter
            while (input.hasNext()) {
                Item items = new Item("", "","");
                String str = input.next();
                String[] ArrayofString = str.split("\t", 3);
                items.setName(ArrayofString[0]);
                items.setSerial(ArrayofString[1]);
                items.setPrice(ArrayofString[2]);
                list.add(items);
            }
            nameErrorLabel.setText("");
        }
        catch (ArrayIndexOutOfBoundsException | IOException arrayIndexOutOfBoundsException) {
            nameErrorLabel.setText("Either the file was corrupted or not in inveX format.");
        }


    }
    public void loadAsHTML(){
        Window stage = nameErrorLabel.getScene().getWindow();
        // load up the filechooser and look for only text files
        fileChooser.setTitle("Load Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("choose a text file (TSV), html, or JSON", "*.txt","*.html","*.JSON"));
        File file = fileChooser.showOpenDialog(stage);
        fileChooser.setInitialDirectory(file.getParentFile());
        try (Scanner input = new Scanner(Paths.get(String.valueOf(file))).useDelimiter("</html>"))
        {
            clearAllItems();
            //make sure the list is empty then scan in all the strings, parsing them correctly
            //also use a while to make sure it continues after the delimiter
            while (input.hasNext()) {
                Item items = new Item("", "","");
                String str = input.next();
                String[] ArrayofString = str.split("\t", 3);
                items.setName(ArrayofString[0]);
                items.setSerial(ArrayofString[1]);
                items.setPrice(ArrayofString[2]);
                list.add(items);
            }
            nameErrorLabel.setText("");
        }
        catch (ArrayIndexOutOfBoundsException | IOException arrayIndexOutOfBoundsException) {
            nameErrorLabel.setText("Either the file was corrupted or not in inveX format.");
        }


    }
    public void loadAsJSON(){

    }
    public boolean uniqueSerial(String serial){
        for (Item item : list) {
            if (item.getSerial().equals(serial)){
                return false;
            }
        }
        return  true;
    }

}
