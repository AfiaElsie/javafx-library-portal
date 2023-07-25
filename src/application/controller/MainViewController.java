
package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import application.Start;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;


public class MainViewController implements Initializable {

    @FXML
    private VBox mainContent;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Parent pane=FXMLLoader.load(Start.class.getResource("view/LoginView.fxml"));
            mainContent.getChildren().setAll(pane);
            
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        Parent pane=FXMLLoader.load(Start.class.getResource("view/LoginView.fxml"));
        mainContent.getChildren().setAll(pane);
    }

    @FXML
    private void signupAction(ActionEvent event) throws IOException {
        Parent pane=FXMLLoader.load(Start.class.getResource("view/SignupView.fxml"));
        mainContent.getChildren().setAll(pane);
    }
    
}
