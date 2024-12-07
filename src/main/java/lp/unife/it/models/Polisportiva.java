package lp.unife.it.models;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.HashMap;

public class Polisportiva {
    private ObservableList<Atleta> atleti = FXCollections.observableArrayList();
    private ObservableList<AttivitaSportiva> attivita = FXCollections.observableArrayList();
    private ObservableList<Iscrizione> iscrizioni = FXCollections.observableArrayList();

    public void setAtleti(ObservableList<Atleta> atleti) {
        this.atleti = atleti;
    }

    public void setAttivita(ObservableList<AttivitaSportiva> attivita) {
        this.attivita = attivita;
    }

    public void setIscrizioni(ObservableList<Iscrizione> iscrizioni) {
        this.iscrizioni = iscrizioni;
    }
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

        // Crea la mappa degli orari per la prima attività sportiva
        Map<Giorno, List<String>> orariPerGiorno1 = new HashMap<>();
        orariPerGiorno1.put(Giorno.LUNEDI, Arrays.asList("09:00 - 10:00", "14:00 - 15:00"));
        orariPerGiorno1.put(Giorno.MERCOLEDI, Arrays.asList("10:00 - 11:00"));

        // Crea la prima attività sportiva
        AttivitaSportiva calcio = new AttivitaSportiva("Calcio", "Allenamento di calcio", orariPerGiorno1);

        // Crea la mappa degli orari per la seconda attività sportiva
        Map<Giorno, List<String>> orariPerGiorno2 = new HashMap<>();
        orariPerGiorno2.put(Giorno.MARTEDI, Arrays.asList("11:00 - 12:00", "16:00 - 17:00"));
        orariPerGiorno2.put(Giorno.GIOVEDI, Arrays.asList("12:00 - 13:00"));

        // Crea la seconda attività sportiva
        AttivitaSportiva nuoto = new AttivitaSportiva("Nuoto", "Allenamento di nuoto", orariPerGiorno2);

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

    public ObservableList<AttivitaSportiva> getAttivitaPerAtleta(Atleta atleta) {
        ObservableList<AttivitaSportiva> result = FXCollections.observableArrayList();
        for (Iscrizione iscrizione : iscrizioni) {
            if (iscrizione.getAtleta().equals(atleta)) {
                result.add(iscrizione.getAttivita());
            }
        }
        return result;
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

    public ObservableList<Iscrizione> getIscrizioniPerAttivita(AttivitaSportiva attivitaSportiva) {
        ObservableList<Iscrizione> result = FXCollections.observableArrayList();
        for (Iscrizione iscrizione : iscrizioni) {
            if (iscrizione.getAttivita().equals(attivitaSportiva)) {
                result.add(iscrizione);
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