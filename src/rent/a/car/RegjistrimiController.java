/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rent.a.car;

import Connectivity.ConnectionClass;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;

public class RegjistrimiController implements Initializable {
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    @FXML
    public TextField txtNumriPersonal;
    public TextField txtEmri;
    public TextField txtMbiemri;
    public TextField txtNumriTelefonit;
    public TextField txtAdresa;
    public TextField txtEmailAdresa;
    public PasswordField pswFjalekalimi;
    public PasswordField pswKonfirmoFjalekalimin;
    public Button btnRegjistro;
    public Label lblError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnRegjistro.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
                try {
                    lblError.setText("");
                    String sql = "INSERT INTO punetoret VALUES('" + txtNumriPersonal.getText() + "','" + txtEmri.getText() + "','" + txtMbiemri.getText() + "','" + pswFjalekalimi.getText() + "','" + txtNumriTelefonit.getText() + "','" + txtAdresa.getText() + "','" + txtEmailAdresa.getText() + ",0');";
                    System.out.println(sql);
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);
                    txtEmri.clear();
                    txtNumriPersonal.clear();
                    txtMbiemri.clear();
                    pswFjalekalimi.clear();
                    pswKonfirmoFjalekalimin.clear();
                    txtAdresa.clear();
                    txtEmailAdresa.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Te dhenat u regjistruan ne databaze");
                    alert.showAndWait();
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RegjistrimiController.class.getName()).log(Level.SEVERE, null, ex);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Ka ndodhur nje gabim");
                    alert.showAndWait();
                }
        }
        });
    }
}
