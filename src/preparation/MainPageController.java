/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preparation;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author nicolasvondru
 */
public class MainPageController implements Initializable {
    
    private Label label;
    @FXML
    private TableView<Person> tblPersons;
    @FXML
    private TableColumn<?, ?> colPrename;
    @FXML
    private TableColumn<?, ?> colLastname;
    @FXML
    private TableColumn<?, ?> colBirthdate;
    @FXML
    private TableColumn<Person, String> colStatus;
    @FXML
    private TextField tfPrename;
    @FXML
    private TextField tfLastname;
    @FXML
    private DatePicker dpBirthdate;
    @FXML
    private CheckBox cbStatus;
    
    private Preparation main;
    private List persons;
    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colPrename.setCellValueFactory(new PropertyValueFactory("prename"));       
        colLastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        colBirthdate.setCellValueFactory(new PropertyValueFactory("birthdate"));
        colStatus.setCellValueFactory(
                (TableColumn.CellDataFeatures<Person, String> tbl) -> 
            {
                if (tbl.getValue().getOnline() == 1) {
                    return new ReadOnlyStringWrapper("Online");
                }else{
                    return new ReadOnlyStringWrapper("Offline");
                }                
            }
        );
        persons = main.getdBQuery().getAllPersons();
        tblPersons.getItems().addAll(persons);

    }    

    @FXML
    private void handleBtn_delete(ActionEvent event) {
        Person person = (Person)tblPersons.getSelectionModel().getSelectedItem();
        main.getdBQuery().deletePerson(person);
        persons = main.getdBQuery().getAllPersons();
        tblPersons.getItems().clear();
        tblPersons.getItems().addAll(persons);
    }

    @FXML
    private void handleBtn_save(ActionEvent event) {
        String prename = tfPrename.getText();
        String lastname = tfLastname.getText();
        Date birthdate = Date.valueOf(dpBirthdate.getValue());
        short status;

        if(cbStatus.isSelected()){
            status = 1;
        }else{
            status = 0;
        }
        Person person = new Person(Integer.MAX_VALUE, prename, lastname, birthdate, status);
        main.getdBQuery().insertPerson(person); 
        persons = main.getdBQuery().getAllPersons();
        tblPersons.getItems().clear();
        tblPersons.getItems().addAll(persons);
    }

    public void setMain(Preparation main) {
        this.main = main;
    }
    
}
