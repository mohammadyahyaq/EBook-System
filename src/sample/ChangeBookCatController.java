package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeBookCatController implements Initializable {

    @FXML
    private Label bookIDNameLabel;
    @FXML
    private TextField bookIDNameTextField;
    @FXML
    private Label catLabel;
    @FXML
    private TextField catTextField;
    private ToggleGroup IDNameGroup = new ToggleGroup();
    @FXML
    private RadioButton idRadio;
    @FXML
    private RadioButton nameRadio;
    @FXML
    private Label submissionMessage;
    @FXML
    private Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.idRadio.setToggleGroup(IDNameGroup);
        this.nameRadio.setToggleGroup(IDNameGroup);
        this.idRadio.setSelected(true); // to make the search by ID is the default one !
        this.idRadio.selectedProperty().addListener((v, oldValue, newValue) -> {
            // here we added a listener to the radio button to do actions when the user changed its value
            if (newValue == true) {
                // if id selected
                this.bookIDNameLabel.setText("Book ID:");
                this.bookIDNameTextField.setPromptText("ID");
            } else {
                // if it is not selected (that means Name is selected)
                this.bookIDNameLabel.setText("Book Name:");
                this.bookIDNameTextField.setPromptText("Name");
            }
        });
    }

    public void submitEvent() {
        if (idRadio.isSelected()) {
            // then the search by ID
            if (!bookIDNameTextField.getText().isEmpty() && !catTextField.getText().isEmpty()) {
                try {
                    int ID = Integer.parseInt(bookIDNameTextField.getText());
                    if (Main.myStore.changeBookCategory(ID, catTextField.getText())) {
                        submissionMessage.setStyle("-fx-font-size: 20; -fx-text-fill: #ffffff;");
                        submissionMessage.setText("The process is done");
                    } else {
                        submissionMessage.setStyle("-fx-font-size: 20; -fx-text-fill: #ff0000;");
                        submissionMessage.setText("The Book ID or the category are not correct!");
                    }
                } catch (NumberFormatException e) {
                    submissionMessage.setStyle("-fx-font-size: 20; -fx-text-fill: #ff0000;");
                    submissionMessage.setText("The ID must be a number");
                }
            } else {
                submissionMessage.setStyle("-fx-font-size: 20; -fx-text-fill: #ff0000;");
                submissionMessage.setText("You have to fill the form");
            }
        } else {
            // then the search by Name
            if (!bookIDNameTextField.getText().isEmpty() && !catTextField.getText().isEmpty()) {
                if (Main.myStore.changeBookCategory(bookIDNameTextField.getText(), catTextField.getText())) {
                    submissionMessage.setStyle("-fx-font-size: 20; -fx-text-fill: #ffffff;");
                    submissionMessage.setText("The process is done");
                } else {
                    submissionMessage.setStyle("-fx-font-size: 20; -fx-text-fill: #ff0000;");
                    submissionMessage.setText("The Book Name or the category are not correct!");
                }
            } else {
                submissionMessage.setStyle("-fx-font-size: 20; -fx-text-fill: #ff0000;");
                submissionMessage.setText("You have to fill the form");
            }
        }
    }

    @FXML
    private void backEvent() throws Exception {
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

