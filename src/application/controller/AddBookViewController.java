
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class AddBookViewController implements Initializable {

    @FXML
    private TextField bookTitle;
    @FXML
    private TextField bookID;
    @FXML
    private TextField bookAuthor;
    @FXML
    private TextField bookPublisher;
    @FXML
    private TextField numofCopies;


    static ObservableList<Book> bookList =FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DatabaseAction dbAction=new DatabaseAction();
        try {
            bookList =dbAction.getAllBooks();
        } catch (SQLException ex) {
            Logger.getLogger(SignupViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void addbookbuttonAction(MouseEvent event) throws SQLException {

        if (!bookID.getText().equals("")) {

            String title=bookTitle.getText();
            String isbn=bookID.getText();
            String author=bookAuthor.getText();
            String publisher=bookPublisher.getText();
            int numOfCopies=Integer.parseInt(numofCopies.getText());


            Book book=new Book(title,isbn,author,publisher,numOfCopies);

            DatabaseAction dbAction=new DatabaseAction();
            dbAction.addBook(book);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Book added successfully");
            alert.showAndWait();
            bookTitle.clear();
            bookID.clear();
            bookAuthor.clear();
            bookPublisher.clear();
            numofCopies.clear();
        }

    }
    
}
