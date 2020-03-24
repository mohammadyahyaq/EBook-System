package sample;

/*
________________________________________________
Name: Mohammad Yahya Ahmad Alghafli (1741679)
Subject: Software Technology Topics (CPCS-405)
________________________________________________
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static Store myStore = new Store();
    public static Stage primaryStage;
    public static Parent root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        this.root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        primaryStage.setTitle("e-book system");
        primaryStage.setScene(new Scene(root, 794, 546));
        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED); // this line of code remove the default border of javafx
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
