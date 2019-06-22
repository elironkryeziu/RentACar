
package chatclient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import rent.a.car.LogInController;


public class ChatClientController implements Initializable {
    LogInController obj = new LogInController();
    String username = obj.getUser();
    private ChatGateway gateway;
    @FXML
    private TextArea textArea;
    @FXML
    private TextArea comment;
    @FXML
    private Button btnDil;
    
           
    
    @FXML
    private void sendComment(ActionEvent event) {
        String text = comment.getText();
        gateway.sendComment(text);
        comment.setText("");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        gateway = new ChatGateway(textArea);
        gateway.sendHandle(username);
        
        new Thread(new TranscriptCheck(gateway,textArea)).start();
        gateway.sendComment("eshte kyqur");
        
        btnDil.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
            try{
            gateway.sendComment(("eshte ckyqur"));
            }catch(Exception e){
                //do nothing
            }
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }});
        
        comment.setOnKeyPressed(event -> {
        if (event.getCode() == KeyCode.ENTER){
            gateway.sendComment(comment.getText());
            comment.setText("");
        }
        });
        }
        catch(Exception e){
            textArea.appendText("Serveri nuk eshte i startuar"+'\n');
            comment.setVisible(false);
        }
}

class TranscriptCheck implements Runnable, chat.ChatConstants {
    private ChatGateway gateway; // Gateway to the server
    private TextArea textArea; // Where to display comments
    private int N; // How many comments we have read
    
    /** Construct a thread */
    public TranscriptCheck(ChatGateway gateway,TextArea textArea) {
      this.gateway = gateway;
      this.textArea = textArea;
      this.N = 0;
    }

    /** Run a thread */
    public void run() {
      while(true) {
          if(gateway.getCommentCount() > N) {
              String newComment = gateway.getComment(N);
              Platform.runLater(()->textArea.appendText(newComment + "\n"));
              N++;
          } else {
              try {
                  Thread.sleep(250);
              } catch(InterruptedException ex) {}
          }
      }
    }
    }
}