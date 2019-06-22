/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rent.a.car;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author ShkodraD
 */
public class DataKlient {

    private final StringProperty id;
    private final StringProperty emri;
    private final StringProperty mbiemri;
    private final StringProperty nrLet;
    private final StringProperty nrTel;
    private final StringProperty vetura;
    private final StringProperty cmimi;
    private final StringProperty adresa;
    private final StringProperty dataFillimit;
    private final StringProperty dataMbarimit;
    private final StringProperty ora;

    //default constructor
    public DataKlient(String id, String emri, String mbiemri, String nrLeternjoftimit,
            String nrTel, String vetura, String cmimi, String adresa, String dataFillimit, String dataMbarimit, String ora) {
        this.id = new SimpleStringProperty(id);
        this.emri = new SimpleStringProperty(emri);
        this.mbiemri = new SimpleStringProperty(mbiemri);
        this.nrLet = new SimpleStringProperty(nrLeternjoftimit);
        this.nrTel = new SimpleStringProperty(nrTel);
        this.vetura = new SimpleStringProperty(vetura);
        this.cmimi = new SimpleStringProperty(cmimi);
        this.adresa = new SimpleStringProperty(adresa);
        this.dataFillimit = new SimpleStringProperty(dataFillimit);
        this.dataMbarimit = new SimpleStringProperty(dataMbarimit);
        this.ora = new SimpleStringProperty(ora);

    }

    public StringProperty getId() {
        return id;
    }

    public StringProperty getEmri() {
        return emri;
    }

    public StringProperty getMbiemri() {
        return mbiemri;
    }

    public StringProperty getNrLeternjoftimit() {
        return nrLet;
    }

    public StringProperty getNrTel() {
        return nrTel;
    }

    public StringProperty getVetura() {
        return vetura;
    }

    public StringProperty getCmimi() {
        return cmimi;
    }

    public StringProperty getAdresa() {
        return adresa;
    }

    public StringProperty getDataFillimit() {
        return dataFillimit;
    }

    public StringProperty getDataMbarimit() {
        return dataMbarimit;
    }
    public StringProperty getOra(){
        return ora;
    }
    
    //Setters
    
    public void setId(String vlera) {
        id.set(vlera);
    }
    public void setEmri(String vlera) {
        emri.set(vlera);
    }
    public void setMbiemri(String vlera) {
        mbiemri.set(vlera);
    }
    public void setNrLeternjoftimit(String vlera) {
        nrLet.set(vlera);
    }
    public void setNrTel(String vlera) {
        nrTel.set(vlera);
    }
    public void setVetura(String vlera) {
        vetura.set(vlera);
    }
    public void setCmimi(String vlera) {
        cmimi.set(vlera);
    }
    public void setAdresa(String vlera) {
        adresa.set(vlera);
    }
    public void setDataFillimit(String vlera) {
        dataFillimit.set(vlera);
    }
    public void setDataMbarimit(String vlera) {
        dataMbarimit.set(vlera);
    }
    public void setOra(String vlera) {
        ora.set(vlera);
    }
    
    //Property values
    public StringProperty idProperty() {
        return id;
    }
    public StringProperty emriProperty() {
        return emri;
    }
    public StringProperty mbiemriProperty() {
        return mbiemri;
    }
    public StringProperty nrLeternjofitmitProperty() {
        return nrLet;
    }
    public StringProperty nrTelProperty() {
        return nrTel;
    }
    public StringProperty veturaProperty() {
        return vetura;
    }
    public StringProperty cmimiProperty() {
        return cmimi;
    }
    public StringProperty adresaProperty() {
        return adresa;
    }
    public StringProperty dataFillimitProperty() {
        return dataFillimit;
    }
    public StringProperty dataMbarimitProperty() {
        return dataMbarimit;
    }
    public StringProperty oraProperty() {
        return ora;
    }


}
