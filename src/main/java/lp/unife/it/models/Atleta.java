package lp.unife.it.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Atleta {
    private static int lastId = 0; // Variabile statica per tenere traccia dell'ultimo ID utilizzato
    private IntegerProperty id;
    private StringProperty nome;
    private StringProperty cognome;
    private ObjectProperty<LocalDate> dataNascita;
    private StringProperty indirizzo;
    private StringProperty telefono;
    private StringProperty email;
    private List<AttivitaSportiva> attivitaPreferite;

    public Atleta(String nome, String cognome, LocalDate dataNascita, String indirizzo, String telefono, String email) {
        this.id = new SimpleIntegerProperty(++lastId); 
        this.nome = new SimpleStringProperty(nome);
        this.cognome = new SimpleStringProperty(cognome);
        this.dataNascita = new SimpleObjectProperty<>(dataNascita);
        this.indirizzo = new SimpleStringProperty(indirizzo);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
        this.attivitaPreferite = new ArrayList<>();
    }

    public Atleta() {
        this(null, null, null, null, null, null);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId() {
        id.set(++lastId);
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

    public ObjectProperty<LocalDate> dataNascitaProperty() {
        return dataNascita;
    }

    public LocalDate getDataNascita() {
        return dataNascita.get();
    }

    public void setDataNascita(LocalDate dataNascita) {
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

    @Override
    public String toString() {
        return "Atleta{" +
                "id=" + id.get() +
                ", nome=" + nome.get() +
                ", cognome=" + cognome.get() +
                ", dataNascita=" + dataNascita.get() +
                ", indirizzo=" + indirizzo.get() +
                ", telefono=" + telefono.get() +
                ", email=" + email.get() +
                ", attivitaPreferite=" + attivitaPreferite +
                '}';
    }
}
