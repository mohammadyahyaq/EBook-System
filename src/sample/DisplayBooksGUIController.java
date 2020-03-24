package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DisplayBooksGUIController implements Initializable {

    @FXML private TableView<Book> bookList;
    @FXML private ChoiceBox<String> filterCB;
    @FXML private TextField searchTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setMinWidth(45);
        idColumn.setResizable(false);
        idColumn.setReorderable(false);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> nameColumn = new TableColumn<>("Book Name");
        nameColumn.setMinWidth(250);
        nameColumn.setResizable(false);
        nameColumn.setReorderable(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author Name");
        authorColumn.setMinWidth(150);
        authorColumn.setResizable(false);
        authorColumn.setReorderable(false);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> dateColumn = new TableColumn<>("Dof");
        dateColumn.setMinWidth(80);
        dateColumn.setResizable(false);
        dateColumn.setReorderable(false);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfString"));

        TableColumn<Book, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(80);
        quantityColumn.setResizable(false);
        quantityColumn.setReorderable(false);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Book, String> rentedColumn = new TableColumn<>("Rented Copies");
        rentedColumn.setMinWidth(90);
        rentedColumn.setResizable(false);
        rentedColumn.setReorderable(false);
        rentedColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfRentedBooks"));

        bookList.getColumns().addAll(idColumn, nameColumn, authorColumn, dateColumn, quantityColumn, rentedColumn);
        bookList.setItems(Main.myStore.getObservableList());

        filterCB.getItems().addAll("All", "Available", "Not Available");
        filterCB.setValue("All");


        searchTextField.textProperty().addListener((v, oldValue, newValue) -> {
            if (filterCB.getValue().equals("All")){
                ObservableList<Book> newList =FXCollections.observableList(Main.myStore.getObservableList().stream().filter(book -> book.getName().toLowerCase().startsWith(newValue.toLowerCase())).collect(Collectors.toList()));
                bookList.setItems(newList);
            } else if (filterCB.getValue().equals("Available")) {
                ObservableList<Book> newList =FXCollections.observableList(Main.myStore.getAvailableBooks().stream().filter(book -> book.getName().toLowerCase().startsWith(newValue.toLowerCase())).collect(Collectors.toList()));
                bookList.setItems(newList);
            } else {
                ObservableList<Book> newList =FXCollections.observableList(Main.myStore.getRentedBooks().stream().filter(book -> book.getName().toLowerCase().startsWith(newValue.toLowerCase())).collect(Collectors.toList()));
                bookList.setItems(newList);
            }
        });
    }

    @FXML
    public void submitEvent () {
        if (filterCB.getValue().equals("All")) {
            bookList.setItems(Main.myStore.getObservableList()); // this will display only all the books
        } else if (filterCB.getValue().equals("Available")) {
            bookList.setItems(Main.myStore.getAvailableBooks()); // this will display only the available books
        } else {
            bookList.setItems(Main.myStore.getRentedBooks()); // this will display only the unavailable books
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
