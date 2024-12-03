package lp.unife.it.models;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Atleta {
    private StringProperty nome;
    private StringProperty cognome;
    private StringProperty dataNascita;
    private StringProperty indirizzo;
    private StringProperty telefono;
    private StringProperty email;
    private List<AttivitaSportiva> attivitaPreferite;

    public Atleta(String nome, String cognome, String dataNascita, String indirizzo, String telefono, String email) {
        this.nome = new SimpleStringProperty(nome);
        this.cognome = new SimpleStringProperty(cognome);
        this.dataNascita = new SimpleStringProperty(dataNascita);
        this.indirizzo = new SimpleStringProperty(indirizzo);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
        this.attivitaPreferite = new ArrayList<>();
    }

    public void aggiungiAttivitaPreferita(AttivitaSportiva attivita) {
        attivitaPreferite.add(attivita);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty cognomeProperty() {
        return cognome;
    }

    public String getCognome() {
        return cognome.get();
    }

    public void setCognome(String cognome) {
        this.cognome.set(cognome);
    }

    public StringProperty dataNascitaProperty() {
        return dataNascita;
    }

    public String getDataNascita() {
        return dataNascita.get();
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita.set(dataNascita);
    }

    public StringProperty indirizzoProperty() {
        return indirizzo;
    }

    public String getIndirizzo() {
        return indirizzo.get();
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo.set(indirizzo);
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public List<AttivitaSportiva> getAttivitaPreferite() {
        return attivitaPreferite;
    }

    public void setAttivitaPreferite(List<AttivitaSportiva> attivitaPreferite) {
        this.attivitaPreferite = attivitaPreferite;
    }
}