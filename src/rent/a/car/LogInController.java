/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rent.a.car;
import javafx.scene.input.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import Connectivity.ConnectionClass;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.A;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LogInController implements Initializable {
    private static String user;
    private static boolean status=false;
    
    @FXML
    public Button BtnKycu;
    public TextField txtNumriIdentifikues;
    public TextField txtFjalekalimi;
    public Label lblError;
    public ProgressIndicator progress;
    
    @FXML
    public void keyTyped(KeyEvent event) {
           
        String Key = event.getCharacter();
        char Key1 = Key.charAt(0);
        if (!(Character.isDigit(Key1))  ){
          //  Toolkit.getDefaultToolkit().beep();
            event.consume();
        }
        int Nr = txtNumriIdentifikues.getLength();
        if (Nr==10){
            event.consume();
        }
    } 
    
    AnchorPane A= new AnchorPane();
    
   
    private int check() throws SQLException, IOException, Exception{
        String id =txtNumriIdentifikues.getText();
        String password = txtFjalekalimi.getText();
        int log=1;

            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            progress.setVisible(true);
            Statement st;
            ResultSet rs = null;
            String SQL_LIST = "Select * from punetoret";
            st = connection.createStatement();
            rs = st.executeQuery(SQL_LIST);
            while (rs.next())
            {
               if(rs.getString(1).equals(id) && rs.getString(4).equals(password))
               {
                   if(rs.getBoolean("status")){
                       setStatus(true);
                   }
                   String username = rs.getString(2).toLowerCase()+rs.getString(3).toLowerCase();
                   setUser(username);
                   log = 0;
                   break;
               }
           }   
            return log;   
        }
    
    public String getUser(){
        return user;
    }
    
    public void setUser(String user){
        this.user=user;
    }
    
    public boolean getStatus(){
        return status;
    }
    
    public void setStatus(boolean status){
        this.status = status;
    }
    
    public void openNewPage() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("RentACar.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Rent a car");
        stage.getIcons().add(new Image("img/icon.png"));
        stage.setResizable(false);
        stage.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    txtFjalekalimi.setOnKeyReleased(event -> {
  if (event.getCode() == KeyCode.ENTER){
      try {
          if(check()==0){
              lblError.setText("");
              openNewPage();
              ((Node)(event.getSource())).getScene().getWindow().hide();
          }
          else{
              progress.setVisible(false);
              lblError.setText("Te dhenat jane gabim");
          }
      } catch (Exception ex) {
          Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
      }
     
  }
});    
    
    BtnKycu.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent event) {
        try {
            if(check()==0){
                    lblError.setText("");
                    openNewPage();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                }
                else{
                    lblError.setText("Te dhenat jane gabim");
                    progress.setVisible(false);
                }
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    });
    }

   
}
