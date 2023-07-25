package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Start;
import application.database.DatabaseAction;
import application.model.Admin;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginViewController implements Initializable {

    @FXML
    private PasswordField passField;
    @FXML
    private TextField emailField;
    @FXML
    private Label notification;


    static ObservableList<User> userList =FXCollections.observableArrayList();
    static ObservableList<Admin> adminList =FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseAction dbAction=new DatabaseAction();
        try {
            userList =dbAction.getAllUser();
        } catch (SQLException ex) {
            Logger.getLogger(SignupViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            adminList =dbAction.getAllAdmins();
        } catch (SQLException ex) {
            Logger.getLogger(SignupViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void loginButtonAction(MouseEvent event) {
        notification.setText("");
        String email=emailField.getText();
        String pass=passField.getText();
        
        for(User user: userList){
            if(user.getEmail().equals(email) && user.getPassword().equals(pass)){
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

        for(Admin admin: adminList){
            if(admin.getEmail().equals(email) && admin.getPassword().equals(pass)){
            try {

                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(Start.class.getResource("view/AdminView.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            else{
            notification.setText("Invalid Email or Password");
            break;
            }
        }
        
        
    }
}
