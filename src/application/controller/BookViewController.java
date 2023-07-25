
package application.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.database.DatabaseAction;
import application.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class BookViewController implements Initializable {

    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> publisherColumn;
    @FXML
    private TableColumn<Book, Integer> numcopies;
    @FXML
    private TableView<Book> bookInfoTable;
    @FXML
    private TextField titleEditField;
    @FXML
    private TextField AuthorEditField;
    @FXML
    private TextField PublisherEditField;

    static ObservableList<Book> bookList =FXCollections.observableArrayList();
    
    @FXML
    private TextField numOfCopies;
    @FXML
    private TextField ISBNEditField;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseAction dbAction=new DatabaseAction();
        try {
            bookList = dbAction.getAllBooks();
        } catch (SQLException ex) {
            Logger.getLogger(BookViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        numcopies.setCellValueFactory(new PropertyValueFactory<>("numcopies"));
            
        bookInfoTable.setItems(bookList);
    } 

    
    @FXML
    private void updateButtonAction() throws SQLException {
        if(titleEditField.getText().equals("") || ISBNEditField.getText().equals("") || AuthorEditField.getText().equals("") || 
           PublisherEditField.getText().equals("") ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please give all info");
            alert.showAndWait();
            return;
        }
        
        String newTitle = titleEditField.getText();
        String bookisbn=ISBNEditField.getText();
        String newAuthor = AuthorEditField.getText();
        String newPublisher = PublisherEditField.getText();
        int numofcopies=Integer.parseInt(numOfCopies.getText());
        
        Book bookinfoobj=new Book(newTitle, bookisbn, newAuthor, newPublisher,numofcopies);
        
        
        
        //database action
        DatabaseAction dbAction=new DatabaseAction();
        dbAction.updateBook(bookinfoobj);
        
            
        
        
        titleEditField.clear();
        ISBNEditField.clear();
        AuthorEditField.clear();
        PublisherEditField.clear();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Book info Updated Successfully");
            alert.showAndWait();
    }
    
    
    @FXML
    private void deleteButtonAction() throws SQLException {
        ObservableList<Book> selectedBooks;
        selectedBooks=bookInfoTable.getSelectionModel().getSelectedItems();
        AddBookViewController.bookList.removeAll(selectedBooks);
        DatabaseAction dbAction=new DatabaseAction();
        dbAction.deleteBooks(selectedBooks);
        }

}
