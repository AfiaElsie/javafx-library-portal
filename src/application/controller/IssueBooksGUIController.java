
package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Start;
import application.database.DatabaseAction;
import application.model.IssueBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class IssueBooksGUIController implements Initializable {

    @FXML
    private TableColumn<IssueBook, String> titleColumn;
    @FXML
    private TableColumn<IssueBook, String> ISBNColumn;
    @FXML
    private TableColumn<IssueBook, String> authorColumn;
    @FXML
    private TableColumn<IssueBook, String> publisherColumn;
    @FXML
    private TableView<IssueBook> issuedBookstable;


    ObservableList<IssueBook> issuedBookList=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        issuedBookstable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseAction dbAction=new DatabaseAction();
        try {
            issuedBookList= dbAction.getAllIssuedBooks();
        } catch (SQLException ex) {
            Logger.getLogger(BookViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("Isbn"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        
        
        issuedBookstable.setItems(issuedBookList);
    }    

    @FXML
    private void backAction(MouseEvent event) {
        try {


                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(Start.class.getResource("view/UserView.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }


    @FXML
    private void issueBookAction(ActionEvent event) {
        try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(Start.class.getResource("view/IssueBookView.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }

    @FXML
    private void returnbookAction(MouseEvent event) {
        try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(Start.class.getResource("view/ReturnBookView.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }
    
}
