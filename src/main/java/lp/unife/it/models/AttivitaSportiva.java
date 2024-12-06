package lp.unife.it.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class AttivitaSportiva {
    private static int lastId = 0;
    private IntegerProperty id;
    private StringProperty nome;
    private StringProperty descrizione;
    private ObservableMap<Giorno, ObservableList<String>> orariPerGiorno;

    public AttivitaSportiva(String nome, String descrizione, Map<Giorno, List<String>> orariPerGiorno) {
        this.id = new SimpleIntegerProperty(++lastId); 
        this.nome = new SimpleStringProperty(nome);
        this.descrizione = new SimpleStringProperty(descrizione);
        this.orariPerGiorno = FXCollections.observableHashMap();
        orariPerGiorno.forEach((giorno, orari) -> this.orariPerGiorno.put(giorno, FXCollections.observableArrayList(orari)));
    }

    public AttivitaSportiva() {
        this( "", "", new HashMap<>());
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

    // Getter e Setter per orariPerGiorno
    public ObservableMap<Giorno, ObservableList<String>> getOrariPerGiorno() {
        return orariPerGiorno;
    }

    public void setOrariPerGiorno(Map<Giorno, List<String>> orariPerGiorno) {
        this.orariPerGiorno.clear();
        orariPerGiorno.forEach((giorno, orari) -> this.orariPerGiorno.put(giorno, FXCollections.observableArrayList(orari)));
    }

    @Override
    public String toString() {
        return "AttivitaSportiva{" +
                "nome=" + nome.get() +
                ", descrizione=" + descrizione.get() +
                ", orariPerGiorno=" + orariPerGiorno +
                '}';
    }
    }

