package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookGUIController implements Initializable {
    @FXML private HBox windowBar;
    @FXML private Label closeButton;
    @FXML private Label minimizeButton;
    @FXML private Label headerLabel;
    @FXML private Label backButton;
    @FXML private Label bookNameLabel;
    @FXML private TextField bookNameTextField;
    @FXML private Label authorNameLabel;
    @FXML private TextField authorNameTextField;
    @FXML private Label dateOfPublishLabel;
    @FXML private ComboBox<Integer> dateOfPublishYear;
    @FXML private ComboBox<Integer> dateOfPublishMonth;
    @FXML private Label categoryLabel;
    @FXML private TextField categoryTextField;
    @FXML private Label submissionMessage;
    @FXML private Button submitButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i=2020; i > 1980; i--) {
            this.dateOfPublishYear.getItems().add(i);
        }
        for (int i=0; i<12;i++) {
            this.dateOfPublishMonth.getItems().add(i+1);
        }
    }

    @FXML
    public void submitEvent () {
        if (!bookNameTextField.getText().isEmpty() && !authorNameTextField.getText().isEmpty() && dateOfPublishYear.getValue() != null &&
                dateOfPublishMonth.getValue() != null && !categoryTextField.getText().isEmpty()) {
            // we have to make sure that all values are filled
            if (Main.myStore.addBook(bookNameTextField.getText(), authorNameTextField.getText(), dateOfPublishYear.getValue(), dateOfPublishMonth.getValue(), categoryTextField.getText())) {
                submissionMessage.setStyle("-fx-font-size: 20; -fx-text-fill: #ffffff;");
                submissionMessage.setText("The new Book is submitted");
            } else {
                submissionMessage.setStyle("-fx-font-size: 20; -fx-text-fill: #ff0000;");
                submissionMessage.setText("Make sure that the category is Available!");
            }
        } else {
            submissionMessage.setStyle("-fx-font-size: 20; -fx-text-fill: #ff0000;");
            submissionMessage.setText("You have to fill all the fields!");
        }
    }


    @FXML
    private void backEvent() throws Exception {
        this.submissionMessage.setText("");
        Main.root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
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
