/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preparation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


/**
 *
 * @author nicolasvondru
 */
public class MainPageController implements Initializable {
    
    private Preparation main;
    
    @FXML
    private VBox viewContainer;
    
    private OverviewPageController overviewPageController;
    private PositionsPageController positionsPageController;
    


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
    
    public void setMain(Preparation main) {
        this.main = main;
    }

    @FXML
    public void openView_positions(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PositionsPage.fxml"));
        Parent root = loader.load();
        positionsPageController = loader.getController();
        positionsPageController.setMain(main);
        viewContainer.getChildren().clear();    
        viewContainer.getChildren().add(root);          
    }

    

    @FXML
    public void openView_overview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OverviewPage.fxml"));
        Parent root = loader.load();
        overviewPageController = loader.getController();
        overviewPageController.setMain(main);
        viewContainer.getChildren().clear();    
        viewContainer.getChildren().add(root); 
    }
    
}
