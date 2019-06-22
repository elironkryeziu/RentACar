/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rent.a.car;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data {

    private final StringProperty vetura;
    private final StringProperty modeli;
    private final StringProperty km;
    private final StringProperty targat;
    private final StringProperty ngjyra;
    private final StringProperty disponueshmeria;
    private final StringProperty id;
   
    

    //default constructor
     public Data(String id, String vetura, String modeli, String km, String targat, String ngjyra, String disponueshmeria) {
        this.id = new SimpleStringProperty(id);
        this.vetura = new SimpleStringProperty(vetura);
        this.modeli = new SimpleStringProperty(modeli);
        this.km = new SimpleStringProperty(km);
        this.targat = new SimpleStringProperty(targat);
        this.ngjyra = new SimpleStringProperty(ngjyra);
        //this.disponueshmeria = new SimpleStringProperty(disponueshmeria);
        if(disponueshmeria.equals("1"))
        this.disponueshmeria = new SimpleStringProperty("I lire");
        else{
            this.disponueshmeria = new SimpleStringProperty("I nxene");           
        }
    }


    //Getters
    public String getId() {
        return id.get();
    }

    public String getVetura() {
        return vetura.get();
    }

    public String getModeli() {
        return modeli.get();
    }


    public String getKm() {
        return km.get();
    }

    public String getTargat() {
        return targat.get();
    }

    public String getNgjyra() {
        return ngjyra.get();
    }

    public String getDisponueshmeria() {
        return disponueshmeria.get();
    }

    //Setters
    public void setId(String vlera) {
        id.set(vlera);
    }

    public void setVetura(String vlera) {
        vetura.set(vlera);
    }

    public void setModeli(String vlera) {
        modeli.set(vlera);
    }


    public void setKm(String vlera) {
        km.set(vlera);
    }

    public void setTargat(String vlera) {
        targat.set(vlera);
    }

    public void setNgjyra(String vlera) {
        ngjyra.set(vlera);
    }

    public void setDisponueshmeria(String vlera) {
        disponueshmeria.set(vlera);
    }

    //Property values
    public StringProperty idProperty() {
        return id;
    }

    public StringProperty veturaProperty() {
        return vetura;
    }

    public StringProperty modeliProperty() {
        return modeli;
    }


    public StringProperty kmProperty() {
        return km;
    }

    public StringProperty targatProperty() {
        return targat;
    }

    public StringProperty ngjyraProperty() {
        return ngjyra;
    }

    public StringProperty disponueshmeriaProperty() {
        return disponueshmeria;
    }

}
