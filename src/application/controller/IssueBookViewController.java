package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.Start;
import application.database.DatabaseAction;
import application.model.Book;
import application.model.IssueBook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class IssueBookViewController implements Initializable {

    @FXML
    private TextField ISBN;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void issueAction(MouseEvent event) throws SQLException {
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library management","root","");
        Statement statement=conn.createStatement();
        
        String query= " SELECT * FROM bookinfo WHERE bookISBN LIKE '%"+ISBN.getText()+"%'";
        ResultSet rs=statement.executeQuery(query);
        
        if(rs.next()){
           String title= rs.getString("bookTitle");
           String isbn=rs.getString("bookISBN");
           String author=rs.getString("bookAuthor");
           String publisher =rs.getString("bookPublisher");
           int numcopies =rs.getInt("NumCopies");
           
           IssueBook bookobj=new IssueBook(title,isbn,author,publisher);
           Book bookinfo = new Book(title,isbn,author,publisher,numcopies);
        

        DatabaseAction dbAction=new DatabaseAction();
        dbAction.addIssuedBook(bookobj);
        DatabaseAction dbAction2=new DatabaseAction();
        dbAction2.reserveBook(bookinfo);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Book issued successfully :-)");
            
            alert.showAndWait();
        
       }
    }

    @FXML
    private void showissuedBooksAction(MouseEvent event) {
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
    private void BackAction(MouseEvent event) {
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

    
}