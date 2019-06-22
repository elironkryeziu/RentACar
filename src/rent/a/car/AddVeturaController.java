/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rent.a.car;


import Connectivity.ConnectionClass;
import rent.a.car.RentACarController;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Eliron
 */
public class AddVeturaController implements Initializable {
    ConnectionClass connectionClass1 = new ConnectionClass();
    Connection connection1 = connectionClass1.getConnection();
    RentACarController obj = new RentACarController();
    
    @FXML
    public Button btnAdd;
    
    @FXML
    public AnchorPane addPane;
    
    @FXML
    public TextField txtVetura;
    public TextField txtModeli;
    public TextField txtKm;
    public TextField txtTargat;
    public TextField txtNgjyra;
    
    public void addVetura() throws SQLException{
        String sql = "INSERT INTO veturat "
                    + "VALUES(null,'" + txtVetura.getText() + "','" + txtModeli.getText() + "','" + txtKm.getText() + "','" + txtTargat.getText() + "','"
                    + txtNgjyra.getText() + "',1);";
            Statement statement = connection1.createStatement();
            statement.executeUpdate(sql);
            clearForm();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Te dhenat u regjistruan ne databaze");
            alert.show();
            clearForm();
            //obj.fillTblVeturat();
            
    }
    
    public void clearForm(){
        txtVetura.clear();txtModeli.clear();txtKm.clear();txtNgjyra.clear();txtTargat.clear();   
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
