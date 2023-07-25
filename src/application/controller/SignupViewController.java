
package application.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.database.DatabaseAction;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;



public class SignupViewController implements Initializable {
   

    @FXML
    private TextField ConfirmpassField;
    @FXML
    private TextField NameField;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField userIDField;
    @FXML
    private TextField EmailField;
    @FXML
    private Label notification;


    static ObservableList<User> userList =FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DatabaseAction dbAction=new DatabaseAction();
        try {
            userList =dbAction.getAllUser();
        } catch (SQLException ex) {
            Logger.getLogger(SignupViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    

    @FXML
    private void signUpAction(ActionEvent event) throws IOException, SQLException {
        
        if(userIDField.getText().equals("")) return;
        
        else {
        int id=Integer.parseInt(userIDField.getText());
        String name=NameField.getText();
        String email=EmailField.getText();
        String password=PasswordField.getText();
        String confirmpass=ConfirmpassField.getText();
        
        if(!email.contains("@gmail.com")){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Email");
            
            alert.showAndWait();
            
        }
       
        
          if((!password.equals(confirmpass)) || (PasswordField.getText().length()<7)){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Password doesn't match or Password < 8 or Can be Invalid Email");
            alert.showAndWait();
          }else{
              User user=new User(id,name,email,password,confirmpass);
                //database action
                DatabaseAction dbAction=new DatabaseAction();
                dbAction.addUser(user);
                NameField.clear();
                EmailField.clear();
                userIDField.clear();
                ConfirmpassField.clear();
                PasswordField.clear();
          }

        }
    
}

    @FXML
    private void emailfieldAction(KeyEvent event) {
    notification.setText("");
        String email=EmailField.getText();
        
        for(User user: userList){
            if(user.getEmail().equals(email)){
                notification.setText("Not Available");
                break;
            }
        }
    }
  
}
