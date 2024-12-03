package lp.unife.it.models;

import java.lang.reflect.Array;
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

        AttivitaSportiva calcio = new AttivitaSportiva("Calcio", "Gioco di squadra", "18:00-20:00",giorni);
        AttivitaSportiva nuoto = new AttivitaSportiva("Nuoto", "Attività in piscina", "10:00-12:00",giorni);

        // Creazione di oggetti Atleta
        Atleta atleta1 = new Atleta("Mario", "Rossi", "01/01/1990", "Via Roma 1", "1234567890", "mario.rossi@example.com");
        atleta1.aggiungiAttivitaPreferita(calcio);

        Atleta atleta2 = new Atleta("Luigi", "Verdi", "02/02/1985", "Via Milano 2", "0987654321", "luigi.verdi@example.com");
        atleta2.aggiungiAttivitaPreferita(nuoto);

        // Creazione di oggetti Iscrizione
        Iscrizione iscrizione1 = new Iscrizione(atleta1, calcio);
        Iscrizione iscrizione2 = new Iscrizione(atleta2, nuoto);

        // Aggiungi gli oggetti alle liste
        atleti.add(atleta1);
        atleti.add(atleta2);
        attivita.add(calcio);
        attivita.add(nuoto);
        iscrizioni.add(iscrizione1);
        iscrizioni.add(iscrizione2);
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

    public void iscriviAtleta(Atleta atleta, AttivitaSportiva attivitaSportiva) {
        iscrizioni.add(new Iscrizione(atleta, attivitaSportiva));
    }

    public ObservableList<Iscrizione> getIscrizioniPerAtleta(Atleta atleta) {
        ObservableList<Iscrizione> result = FXCollections.observableArrayList();
        for (Iscrizione iscrizione : iscrizioni) {
            if (iscrizione.getAtleta().equals(atleta)) {
                result.add(iscrizione);
            }
        }
        return result;
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