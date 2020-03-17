package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MainGUIController {

    @FXML
    private HBox windowBar;
    @FXML
    private Label closeButton;
    @FXML
    private Label minimizeButton;
    @FXML
    private Label headerLabel;
    @FXML
    private Button addBookButton;
    @FXML
    private Button rentABookCat;
    @FXML
    private Button changeBookCat;
    @FXML
    private Button displayBooksButton;
    @FXML
    private Button displayAvailableCatButton;


    @FXML
    public void addBookEvent() throws Exception {
        Main.root = FXMLLoader.load(getClass().getResource("AddBookGUI.fxml"));
        Main.primaryStage.setScene(new Scene(Main.root, 794, 546));
    }

    @FXML
    public void rentABookEvent() throws Exception {
        Main.root = FXMLLoader.load(getClass().getResource("RentABookGUI.fxml"));
        Main.primaryStage.setScene(new Scene(Main.root, 794, 546));
    }

    @FXML
    public void changeBookCatEvent() throws Exception {
        Main.root = FXMLLoader.load(getClass().getResource("ChangeBookCatGUI.fxml"));
        Main.primaryStage.setScene(new Scene(Main.root, 794, 546));
    }

    @FXML
    public void displayBooksEvent() throws Exception {
        Main.root = FXMLLoader.load(getClass().getResource("DisplayBooksGUI.fxml"));
        Main.primaryStage.setScene(new Scene(Main.root, 794, 546));
    }

    @FXML
    public void displayAvailableCatEvent() throws Exception {
        Main.root = FXMLLoader.load(getClass().getResource("DisplayCatGUI.fxml"));
        Main.primaryStage.setScene(new Scene(Main.root, 794, 546));
    }

    @FXML
    public void closeEvent() {
        Main.primaryStage.close();
    }

    @FXML
    public void minimizeScreenEvent() {
        Main.primaryStage.setIconified(true); // this will minimize the stage
    }

    @FXML
    public void moveWindowEvent() {
    }
}
