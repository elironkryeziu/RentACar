package rent.a.car;

import Connectivity.ConnectionClass;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rent.a.car.LogInController;


public class RentACarController implements Initializable {
    LogInController obj = new LogInController();
    String user = obj.getUser();
    boolean status = obj.getStatus();
    
    
    @FXML
    public Button rentBtn;
    public Button llogaritBtn;
    public Button btnEdit;
    public Button btnKerko;
    public Button btnRegjistroPuntore;
    public Button btnStartServer;

    @FXML
    public AnchorPane paneRent;
    public AnchorPane paneRent1;
    public AnchorPane paneKlientet;
    public AnchorPane paneVeturat;

    @FXML
    public ComboBox chVetura;
    public ComboBox srtVetura;

    @FXML
    public Label lblPagesa;
    public Label lblError;
    public Label lblUseri;

    @FXML
    public DatePicker txtPrej;
    public DatePicker txtDeri;

    @FXML
    public TextField txtOraPrej;
    public TextField txtCmimi;
    public TextField txtEmri;
    public TextField txtMbiemri;
    public TextField txtNrId;
    public TextField txtNrTel;
    public TextField txtAdresa;
    public TextField txtKerko;
    @FXML
    private TableView<Data> tblView;
    @FXML
    private TableColumn<Data, String> kolId;
    @FXML
    private TableColumn<Data, String> kolVetura;
    @FXML
    private TableColumn<Data, String> kolModeli;
    @FXML
    private TableColumn<Data, String> kolKm;
    @FXML
    private TableColumn<Data, String> kolTargat;
    @FXML
    private TableColumn<Data, String> kolNgjyra;
    @FXML
    private TableColumn<Data, String> kolDisponueshmeria;

    //initialize observable list to hold out database data
    private ObservableList<Data> data;
    public ConnectionClass lidhja;
    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();
    
    @FXML
    private TableView<DataKlient> tblKlientet;
    @FXML
    private TableColumn<DataKlient, String> kolId1;
    @FXML
    private TableColumn<DataKlient, String> kolEmri;
    @FXML
    private TableColumn<DataKlient, String> kolMbiemri;
    @FXML
    private TableColumn<DataKlient, String> kolTel;
    @FXML
    private TableColumn<DataKlient, String> kolVetura1;
    @FXML
    private TableColumn<DataKlient, String> kolCmimi;
    @FXML
    private TableColumn<DataKlient, String> kolAdresa;
    @FXML
    private TableColumn<DataKlient, String> kolDataFillimit;
    @FXML
    private TableColumn<DataKlient, String> kolDataMbarimit;
    @FXML
    private TableColumn<DataKlient, String> kolOra;
    @FXML
    private TableColumn<DataKlient, String> kolNrLet;
    
      private ObservableList<DataKlient> dataKlient;

    public void pagesa() throws SQLException {
       
        if (txtEmri.getText().isEmpty() || txtMbiemri.getText().isEmpty() || txtNrId.getText().isEmpty()
                || chVetura.getValue() == null || txtPrej.getValue() == null || txtDeri.getValue() == null) {
            lblError.setText("Ploteso te gjitha te dhenat");
            
        }else if(!txtCmimi.getText().matches("\\d+\\.\\d+") && !txtCmimi.getText().matches("\\d+")){
            lblError.setText("Cmimi nuk eshte valid");
            lblPagesa.setText("");
        
        }else if(!txtNrId.getText().matches("\\b\\d{10}\\b")){
            lblError.setText("Numri leternjoftimit duhet t'i kete 10 shifra");
            
        }else if(txtDeri.getValue().getDayOfYear() < txtPrej.getValue().getDayOfYear()){
            lblError.setText("Data e mbarimit duhet te jete me e madhe se ajo e fillimit");
        }        
        else {
            int nrDiteve = txtDeri.getValue().getDayOfYear() - txtPrej.getValue().getDayOfYear();
            double cmimi = Double.parseDouble(txtCmimi.getText());
            double pagesa = nrDiteve * cmimi;
            String sql = "INSERT INTO klientet "
                    + "VALUES(null,'" + txtEmri.getText() + "','" + txtMbiemri.getText() + "','" + txtNrId.getText() + "','" + txtNrTel.getText() + "','"
                    + chVetura.getValue() + "','" + pagesa + "','" + txtAdresa.getText() + "','" + txtPrej.getValue() + "','" + txtDeri.getValue() + "','"
                    + txtOraPrej.getText() + "','"+ user +"');";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            String vetura = chVetura.getValue().toString();
            String targat = "";
            for (int i = vetura.length() - 9; i < vetura.length(); i++) {
                targat += (vetura.charAt(i));
            }
            sql = "UPDATE veturat SET Disponueshmeria = 0 WHERE Targat = '" + targat + "';";
            statement.executeUpdate(sql);
            txtEmri.clear();
            txtMbiemri.clear();
            txtNrId.clear();
            txtNrTel.clear();
            chVetura.valueProperty().setValue(null);
            txtCmimi.clear();
            txtAdresa.clear();
            txtOraPrej.clear();
            txtPrej.valueProperty().setValue(null);
            txtDeri.valueProperty().setValue(null);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("Te dhenat u regjistruan ne databaze");
            lblError.setText("");
            lblPagesa.setText("");
            alert.showAndWait();
            fillComboBox();
            fillTblVeturat();
            fillTblKlientet("SELECT * FROM klientet order by id desc;");
        }
    }

    public void llogarit() {
        if (txtPrej.getValue() == null || txtDeri.getValue() == null) {
            lblError.setText("Ploteso datat");
        
        } else if (txtCmimi.getText().isEmpty()) {
            lblError.setText("Percakto cmimin");
            lblPagesa.setText("");
        
        }else if(txtDeri.getValue().getDayOfYear() < txtPrej.getValue().getDayOfYear()){
            lblError.setText("Data e mbarimit duhet te jete me e madhe se ajo e fillimit");
        
        }else if(!txtCmimi.getText().matches("\\d+\\.\\d+") && !txtCmimi.getText().matches("\\d+")){
            lblError.setText("Cmimi nuk eshte valid");
            lblPagesa.setText("");
        
        }  else {
            int nrDiteve = txtDeri.getValue().getDayOfYear() - txtPrej.getValue().getDayOfYear();
            double cmimi = Double.parseDouble(txtCmimi.getText());
            double pagesa = nrDiteve * cmimi;
            lblError.setText("");
            lblPagesa.setText(" " + pagesa + " €");
        }
    }

        public void fillTblKlientet(String sql) {
        
        lidhja = new ConnectionClass();
        try {
            dataKlient = FXCollections.observableArrayList();

            // ekzekutimi i querit dhe ruajtja e te dhenve ne tabel
            ResultSet rs1 = connection.createStatement().executeQuery(sql);
            while (rs1.next()) {
                dataKlient.add(new DataKlient(rs1.getString(4), rs1.getString(2), rs1.getString(3), rs1.getString("nrId"), rs1.getString(5), rs1.getString(6),rs1.getString(7)
                            ,rs1.getString(8),rs1.getString(9),rs1.getString(10),rs1.getString(11)));
                
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        kolId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        kolEmri.setCellValueFactory(new PropertyValueFactory<>("emri"));
        kolMbiemri.setCellValueFactory(new PropertyValueFactory<>("mbiemri"));
        //kolNrLet.setCellValueFactory(new PropertyValueFactory<>("nrLeternjoftimit"));
        kolTel.setCellValueFactory(new PropertyValueFactory<>("nrTel"));
        kolVetura1.setCellValueFactory(new PropertyValueFactory<>("vetura"));
        kolCmimi.setCellValueFactory(new PropertyValueFactory<>("cmimi"));
        kolAdresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        kolDataFillimit.setCellValueFactory(new PropertyValueFactory<>("dataFillimit"));
        kolDataMbarimit.setCellValueFactory(new PropertyValueFactory<>("dataMbarimit"));
        kolOra.setCellValueFactory(new PropertyValueFactory<>("ora"));

        tblKlientet.setItems(null);
        tblKlientet.setItems(dataKlient);



    }
        
    public void kerko(){
        String sql;
        sql = "SELECT * FROM klientet WHERE emri LIKE '%"+txtKerko.getText().trim()+"%' OR mbiemri LIKE '%"+txtKerko.getText().trim()+"%';";
        fillTblKlientet(sql);
    }
        
    public void editPanel() throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("editVetura.fxml"));
        Scene scene = new Scene(root1);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ndrysho veturen");
        stage.setResizable(true);
        stage.show();
    }
    
    public void regjistroPunetore() throws IOException{
        Parent root1 = FXMLLoader.load(getClass().getResource("Regjistrimi.fxml"));
        Scene scene = new Scene(root1);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ndrysho veturen");
        stage.setResizable(true);
        stage.show();
        
    }
    
    public void addPanel() throws IOException{
        Parent root1 = FXMLLoader.load(getClass().getResource("addVetura.fxml"));
        Scene scene = new Scene(root1);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Shto nje veture te re");
        stage.setResizable(true);
        stage.show();
        
    }
       public void startServer() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/chatserver/ChatServer.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Server");
        stage.setOnCloseRequest(event->System.exit(0));
        stage.show();
        
    }
    
    public void fillTblVeturat() {
        lidhja = new ConnectionClass();
        try {
            data = FXCollections.observableArrayList();

        // ekzekutimi i querit dhe ruajtja e te dhenve ne tabel
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM veturat");
            while (rs.next()) {
                data.add(new Data(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
               
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        kolId.setCellValueFactory(new PropertyValueFactory<>("id"));
        kolVetura.setCellValueFactory(new PropertyValueFactory<>("vetura"));
        kolModeli.setCellValueFactory(new PropertyValueFactory<>("modeli"));
        kolKm.setCellValueFactory(new PropertyValueFactory<>("km"));
        kolTargat.setCellValueFactory(new PropertyValueFactory<>("targat"));
        kolNgjyra.setCellValueFactory(new PropertyValueFactory<>("ngjyra"));
        kolDisponueshmeria.setCellValueFactory(new PropertyValueFactory<>("disponueshmeria"));
        
        tblView.setItems(null);
        tblView.setItems(data);

    }  

    public void klientet() throws IOException, SQLException {
        paneRent1.setVisible(false);
        paneVeturat.setVisible(false);
        paneKlientet.setVisible(true);

    }

    public void rent() throws IOException {
        paneKlientet.setVisible(false);
        paneVeturat.setVisible(false);
        paneRent1.setVisible(true);

    }

    public void veturat() throws IOException {
        paneKlientet.setVisible(false);
        paneRent1.setVisible(false);
        paneVeturat.setVisible(true);
    }

    public void chat() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/chatclient/ChatClient.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Chat Client - " + user);
        stage.setOnCloseRequest(event->System.exit(0));
        stage.show();

    }
    
    public void setFree(){
        String idVetura = tblView.getSelectionModel().getSelectedItem().getId();
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setContentText("Konfirmoni qe vetura eshte kthyer");
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.get() == ButtonType.OK){
                            try {
                                String sql = "UPDATE veturat SET Disponueshmeria = 1 WHERE id = '"+idVetura+"';";
                                Statement statement = connection.createStatement();
                                statement.executeUpdate(sql);
                                fillTblVeturat();
                                fillComboBox();
                            } catch (SQLException ex) {
                                Logger.getLogger(RentACarController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            alert.close();
                        }
        
        
        
    }

    public void exit() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setContentText("A jeni i sigurte qe doni te ckyqeni?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == ButtonType.OK){
                        
                            System.exit(0);
                    }else{
                        alert.close();
                    }
        
    }
    
    public void fillComboBox() {
        try {
            chVetura.getItems().clear();
            String fillQuery = "Select * from veturat where Disponueshmeria = 1";
            ResultSet rs = connection.createStatement().executeQuery(fillQuery);
            while (rs.next()) {
                chVetura.getItems().addAll(rs.getString("Vetura") + " " + rs.getString("Modeli") + " - " + rs.getString("Targat"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void fillsrtVetura() throws SQLException{
        srtVetura.getItems().clear();
        srtVetura.getItems().add("Te gjitha");
        String fillQuery = "Select * from veturat";
            ResultSet rs = connection.createStatement().executeQuery(fillQuery);
            while (rs.next()) {
                srtVetura.getItems().addAll(rs.getString("Vetura") + " " + rs.getString("Modeli") + " - " + rs.getString("Targat"));
            }
    }
    
    public void sortVeturat(){
        String vetura = srtVetura.getValue().toString();
        if(vetura.equals("Te gjitha")){
            fillTblKlientet("SELECT * FROM klientet order by id desc;");
        }
        else{
            String sql = "SELECT * FROM klientet where vetura='" + vetura + "';";
            fillTblKlientet(sql);
        }
    }
    
    public void fshijKlientin() throws SQLException{
        String cmimi = tblKlientet.getSelectionModel().getSelectedItem().getCmimi().get();
        String nrLet = tblKlientet.getSelectionModel().getSelectedItem().getId().get();
        String sql = "DELETE FROM klientet where cmimi='"+cmimi+"' and nrId='"+nrLet+"';";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Klienti u fshi nga databaza");
        alert.showAndWait();
        fillTblKlientet("SELECT * FROM klientet order by id desc;");
    }
    
    public void fshijVeturen() throws SQLException{
        String id = tblView.getSelectionModel().getSelectedItem().getId();
        String sql = "DELETE FROM veturat where id='"+id+"';";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Vetura u fshi nga databaza");
        alert.showAndWait();
        fillTblVeturat();
    }
    
    public void refreshTblKlientet(){
        fillTblKlientet("SELECT * FROM klientet order by id desc;");
        srtVetura.setValue("Te gjitha");
        txtKerko.setText("");
    }
    
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblUseri.setText("Pershendetje " + user);
        if(status){
            btnRegjistroPuntore.setVisible(true);
            btnStartServer.setVisible(true);
        }
        try {
            fillComboBox();
            fillTblVeturat();
            fillTblKlientet("SELECT * FROM klientet order by id desc;");
            fillsrtVetura();
            /* tblView.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    if(event.isPrimaryButtonDown() && event.getClickCount() == 2){
                        String idVetura = tblView.getSelectionModel().getSelectedItem().getId();
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setContentText("Konfirmoni qe vetura eshte kthyer");
                        Optional<ButtonType> result = alert.showAndWait();
                        if(result.get() == ButtonType.OK){
                            try {
                                String sql = "UPDATE veturat SET Disponueshmeria = 1 WHERE id = '"+idVetura+"';";
                                Statement statement = connection.createStatement();
                                statement.executeUpdate(sql);
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }else{
                            alert.close();
                        }
                    }
                }
            });*/
        } catch (SQLException ex) {
            Logger.getLogger(RentACarController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        

    }
}
