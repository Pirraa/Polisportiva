package lp.unife.it.models;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AttivitaSportiva {
    private IntegerProperty id;
    private StringProperty nome;
    private StringProperty descrizione;
    private StringProperty orari;
    private ObservableList<Giorno> giorni;

    public AttivitaSportiva(int id,String nome, String descrizione, String orari, ArrayList<Giorno> giorni) {
         this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.descrizione = new SimpleStringProperty(descrizione);
        this.orari = new SimpleStringProperty(orari);
        this.giorni = FXCollections.observableArrayList(giorni);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    // Getter e Setter per nome
    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    // Getter e Setter per descrizione
    public String getDescrizione() {
        return descrizione.get();
    }

    public void setDescrizione(String descrizione) {
        this.descrizione.set(descrizione);
    }

    public StringProperty descrizioneProperty() {
        return descrizione;
    }

    // Getter e Setter per orari
    public String getOrari() {
        return orari.get();
    }

    public void setOrari(String orari) {
        this.orari.set(orari);
    }

    public StringProperty orariProperty() {
        return orari;
    }

    // Getter e Setter per giorni
    public ObservableList<Giorno> getGiorni() {
        return giorni;
    }

    public void setGiorni(ArrayList<Giorno> giorni) {
        this.giorni.setAll(giorni);
    }

    @Override
    public String toString() {
        return "AttivitaSportiva{" +
                "nome=" + nome.get() +
                ", descrizione=" + descrizione.get() +
                ", orari=" + orari.get() +
                ", giorni=" + giorni +
                '}';
    }
}
