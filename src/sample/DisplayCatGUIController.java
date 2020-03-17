package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayCatGUIController implements Initializable {

    @FXML ListView<String> catList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.catList.setItems(Main.myStore.getCatList());
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
