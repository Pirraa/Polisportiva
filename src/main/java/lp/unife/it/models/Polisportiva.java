package lp.unife.it.models;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

public class Polisportiva {
    private ObservableList<Atleta> atleti = FXCollections.observableArrayList();
    private ObservableList<AttivitaSportiva> attivita = FXCollections.observableArrayList();
    private ObservableList<Iscrizione> iscrizioni = FXCollections.observableArrayList();
    public static Polisportiva instance;

    public static Polisportiva getInstance() {
        if (instance == null) {
            instance = new Polisportiva();
        }
        return instance;
    }

    public Polisportiva() {
        // Valori dummy per popolare le liste
        // Creazione di oggetti AttivitaSportiva
        ArrayList<Giorno>giorni=new ArrayList<>();
        giorni.add(Giorno.DOMENICA);
        giorni.add(Giorno.LUNEDI);
        giorni.add(Giorno.SABATO);

        AttivitaSportiva calcio = new AttivitaSportiva(1,"Calcio", "Gioco di squadra", "18:00-20:00",giorni);
        AttivitaSportiva nuoto = new AttivitaSportiva(2,"Nuoto", "Attività in piscina", "10:00-12:00",giorni);

        // Creazione di oggetti Atleta
        Atleta atleta1 = new Atleta("Mario", "Rossi", LocalDate.of(1990, 1, 1), "Via Roma 1", "1234567890", "mario.rossi@example.com");
        atleta1.aggiungiAttivitaPreferita(calcio);

        Atleta atleta2 = new Atleta("Luigi", "Verdi", LocalDate.of(1985, 2, 2), "Via Milano 2", "0987654321", "luigi.verdi@example.com");
        atleta2.aggiungiAttivitaPreferita(nuoto);

        // Creazione di oggetti Iscrizione
        Iscrizione iscrizione1 = new Iscrizione(2,atleta1, calcio);
        Iscrizione iscrizione2 = new Iscrizione(2,atleta2, nuoto);

        // Aggiungi gli oggetti alle liste
        atleti.add(atleta1);
        atleti.add(atleta2);
        attivita.add(calcio);
        attivita.add(nuoto);
        iscrizioni.add(iscrizione1);
        iscrizioni.add(iscrizione2);
    }

    public boolean eliminaAtletaById(int id) {
        Atleta atletaToRemove = null;
        for (Atleta atleta : atleti) {
            if (atleta.getId() == id) {
                atleti.remove(atleta);
                return true;
            }
        }
        return false;
    }
    
    public void aggiungiAtleta(Atleta atleta) {
        atleti.add(atleta);
    }

    public void modificaAtleta(Atleta atleta, String nuovoNome, String nuovoCognome) {
        atleta.setNome(nuovoNome);
        atleta.setCognome(nuovoCognome);
    }

    public void eliminaAtleta(Atleta atleta) {
        atleti.remove(atleta);
    }

    public ObservableList<Atleta> getAtleti() {
        return atleti;
    }

    public void aggiungiAttivita(AttivitaSportiva attivitaSportiva) {
        attivita.add(attivitaSportiva);
    }

    public void modificaAttivita(AttivitaSportiva attivitaSportiva, String nuovoNome, String nuovaDescrizione) {
        attivitaSportiva.setNome(nuovoNome);
        attivitaSportiva.setDescrizione(nuovaDescrizione);
    }

    public void eliminaAttivita(AttivitaSportiva attivitaSportiva) {
        attivita.remove(attivitaSportiva);
    }

    public ObservableList<AttivitaSportiva> getAttivita() {
        return attivita;
    }

    public void iscriviAtleta(int id,Atleta atleta, AttivitaSportiva attivitaSportiva) {
        iscrizioni.add(new Iscrizione(id,atleta, attivitaSportiva));
    }

    public ObservableList<AttivitaSportiva> getIscrizioniPerAtleta(Atleta atleta) {
        ObservableList<AttivitaSportiva> result = FXCollections.observableArrayList();
        for (Iscrizione iscrizione : iscrizioni) {
            if (iscrizione.getAtleta().equals(atleta)) {
                result.add(iscrizione.getAttivita());
            }
        }
        return result;
    }

    public ObservableList<Iscrizione> getIscrizioni() {
        return iscrizioni;
    }

    // Metodi per gestire statistiche
    public int numeroAtletiIscrittiPerAttivita(AttivitaSportiva attivitaSportiva) {
        int count = 0;
        for (Iscrizione iscrizione : iscrizioni) {
            if (iscrizione.getAttivita().equals(attivitaSportiva)) {
                count++;
            }
        }
        return count;
    }
}