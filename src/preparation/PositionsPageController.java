/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preparation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nicolasvondru
 */
public class PositionsPageController implements Initializable {

    
    private Preparation main;
    
    @FXML
    private TextField tfNewPosition;
    @FXML
    private TableColumn<Position, ?> colPositions;
    @FXML
    private TableView<?> tblPositions;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colPositions.setCellValueFactory(new PropertyValueFactory("name"));
                
    }    

    @FXML
    private void handleBtn_delete(ActionEvent event) {
        main.getdBQuery().deletePosition((Position) tblPositions.getSelectionModel().getSelectedItem());
        List positions = main.getdBQuery().getAllPositions();
        tblPositions.getItems().clear();
        tblPositions.getItems().addAll(positions);
    }

    @FXML
    private void handleBtn_save(ActionEvent event) {
        Position position = new Position(Integer.MAX_VALUE, tfNewPosition.getText());
        main.getdBQuery().insertPosition(position);
        List positions = main.getdBQuery().getAllPositions();
        tblPositions.getItems().clear();
        tblPositions.getItems().addAll(positions);
    }

    void setMain(Preparation main) {
        this.main = main;
        List positions = main.getdBQuery().getAllPositions();
        tblPositions.getItems().clear();
        tblPositions.getItems().addAll(positions);
    }
    
}
