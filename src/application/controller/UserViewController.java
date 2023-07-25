
package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Start;
import application.database.DatabaseAction;
import application.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class UserViewController implements Initializable {

    
    @FXML
    private TableView<Book> bookInfoTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> IDColumn;
    @FXML
    private TableColumn<Book, String> AuthorColumn;
    @FXML
    private TableColumn<Book, String> PublisherColumn;
    @FXML
    private TableColumn<Book, Integer> numCopies;
    
    
    @FXML
    private TextField searchField;
    @FXML
    private RadioButton rb1ID;
    @FXML
    private RadioButton rb2Author;
    
    static ObservableList<Book> bookList =FXCollections.observableArrayList();
    static ObservableList<Book> bookListtwo =FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bookInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseAction dbAction=new DatabaseAction();
        try {
            bookList = dbAction.getAllBooks();
        } catch (SQLException ex) {
            Logger.getLogger(BookViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("publisher"));
        numCopies.setCellValueFactory(new PropertyValueFactory<Book,Integer>("numcopies"));
        
        bookInfoTable.setItems(bookList);
    }    
    @FXML
    private void searchAction(MouseEvent event) throws SQLException {
        
        bookListtwo.clear();

        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library management","root","");
        Statement statement=conn.createStatement();
        if(rb2Author.isSelected()){
        String query= " SELECT * FROM bookinfo WHERE bookAuthor LIKE '%"+searchField.getText()+"%'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
           String title= rs.getString("bookTitle");
           String isbn=rs.getString("bookISBN");
           String author=rs.getString("bookAuthor");
           String publisher =rs.getString("bookPublisher");
           int numcopies =rs.getInt("NumCopies");
           
           Book bookinfo=new Book(title,isbn,author,publisher,numcopies);
            bookListtwo.add(bookinfo);
            
            titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("publisher"));
        numCopies.setCellValueFactory(new PropertyValueFactory<Book,Integer>("numcopies"));
            
        bookInfoTable.setItems(bookListtwo);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Book is not Available");
            alert.showAndWait();
                }
        }
        if(rb1ID.isSelected()){
        String query= " SELECT * FROM bookinfo WHERE bookTitle LIKE '%"+searchField.getText()+"%'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
           String title= rs.getString("bookTitle");
           String isbn=rs.getString("bookIsbn");
           String author=rs.getString("bookAuthor");
           String publisher =rs.getString("bookPublisher");
           int numcopies =rs.getInt("NumCopies");
           
           Book bookinfo=new Book(title,isbn,author,publisher,numcopies);
            bookListtwo.add(bookinfo);
            
            titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("publisher"));
        numCopies.setCellValueFactory(new PropertyValueFactory<Book,Integer>("numcopies"));
        
        bookInfoTable.setItems(bookListtwo);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Book is not Available");
            alert.showAndWait();
                }
        }
    }

    @FXML
    private void refreshAction(MouseEvent event) {
        bookInfoTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        DatabaseAction dbAction=new DatabaseAction();
        try {
            bookList = dbAction.getAllBooks();
        } catch (SQLException ex) {
            Logger.getLogger(BookViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("Isbn"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        PublisherColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("publisher"));
        numCopies.setCellValueFactory(new PropertyValueFactory<Book,Integer>("numcopies"));
            
        bookInfoTable.setItems(bookList);
        
    }

    @FXML
    private void issuebooksAction(MouseEvent event) {
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
    private void showissuedAction(MouseEvent event) {
    try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(Start.class.getResource("view/IssuedBooksView.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }

    @FXML
    private void logoutAction(MouseEvent event) {
        try {


                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(Start.class.getResource("view/MainView.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
    }

    @FXML
    private void returnBookAction(MouseEvent event) {
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
    
