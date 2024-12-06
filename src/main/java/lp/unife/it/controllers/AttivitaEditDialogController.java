package lp.unife.it.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import lp.unife.it.models.AttivitaSportiva;
import lp.unife.it.models.Giorno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

public class AttivitaEditDialogController {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField descrizioneField;
    @FXML
    private TextField orarioInizioField;
    @FXML
    private TextField orarioFineField;
    @FXML
    private ComboBox<Giorno> giorniComboBox;
    @FXML
    private ListView<String> giorniOrariListView;

    private Stage dialogStage;
    private AttivitaSportiva attivita;
    private boolean okClicked = false;
    private ObservableList<String> giorniOrariList = FXCollections.observableArrayList();
    private Map<Giorno, List<String>> giorniOrariMap = new HashMap<>();

    @FXML
    private void initialize() {
        // Inizializza il ComboBox con i giorni della settimana
        giorniComboBox.setItems(FXCollections.observableArrayList(Giorno.values()));
        giorniOrariListView.setItems(giorniOrariList);

        // Aggiungi il TextFormatter per limitare l'input ai soli numeri e ai due punti
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("[0-9:]*")) {
                return change;
            }
            return null;
        };

        TextFormatter<String> textFormatterInizio = new TextFormatter<>(filter);
        TextFormatter<String> textFormatterFine = new TextFormatter<>(filter);
        orarioInizioField.setTextFormatter(textFormatterInizio);
        orarioFineField.setTextFormatter(textFormatterFine);

        // Aggiungi un listener per gestire i click sulla ListView
        giorniOrariListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> handleListViewClick(newValue));
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAttivita(AttivitaSportiva attivita) {
        this.attivita = attivita;
        nomeField.setText(attivita.getNome());
        descrizioneField.setText(attivita.getDescrizione());
        giorniOrariList.clear();
        giorniOrariMap.clear();
        
        // Itera sulla mappa orariPerGiorno e aggiorna la ListView e la Map giorniOrariMap
        attivita.getOrariPerGiorno().forEach((giorno, orari) -> {
            for (String orario : orari) {
                String giornoOrario = giorno + ": " + orario;
                giorniOrariList.add(giornoOrario);
                giorniOrariMap.computeIfAbsent(giorno, k -> new ArrayList<>()).add(orario);
            }
        });
        
        // Imposta i giorni selezionati nel ComboBox
        if (!attivita.getOrariPerGiorno().isEmpty()) {
            giorniComboBox.getSelectionModel().select(attivita.getOrariPerGiorno().keySet().iterator().next());
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleAddGiornoOrario() {
        Giorno giorno = giorniComboBox.getSelectionModel().getSelectedItem();
        String orarioInizio = orarioInizioField.getText();
        String orarioFine = orarioFineField.getText();
        if (giorno != null && isOrarioValido(orarioInizio) && isOrarioValido(orarioFine)) {
            String giornoOrario = giorno + ": " + orarioInizio + " - " + orarioFine;
            giorniOrariList.add(giornoOrario);
            giorniOrariMap.computeIfAbsent(giorno, k -> new ArrayList<>()).add(orarioInizio + " - " + orarioFine);
            giorniOrariList.removeIf(String::isEmpty); // Rimuovi eventuali elementi vuoti
            System.out.println("Elemento aggiunto alla lista giorniOrariList: " + giornoOrario);
            System.out.println(giorniOrariList + "  " + giorniOrariList.size());
            orarioInizioField.clear();
            orarioFineField.clear();
        } else {
            showAlert("Errore", "Input non valido", "Per favore inserisci un giorno e orari di inizio e fine validi.");
        }
    }

    @FXML
    private void handleDeleteGiornoOrario() {
        String selectedItem = giorniOrariListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            giorniOrariList.remove(selectedItem);
            String[] parts = selectedItem.split(": ");
            Giorno giorno = Giorno.valueOf(parts[0].toUpperCase());
            String orario = parts[1];
            giorniOrariMap.get(giorno).remove(orario);
            if (giorniOrariMap.get(giorno).isEmpty()) {
                giorniOrariMap.remove(giorno);
            }
        } else {
            showAlert("Errore", "Nessuna selezione", "Per favore seleziona un elemento dalla lista.");
        }
    }

    private void handleListViewClick(String selectedItem) {
        if (selectedItem != null) {
            String[] parts = selectedItem.split(": ");
            Giorno giorno = Giorno.valueOf(parts[0].toUpperCase());
            String[] orari = parts[1].split(" - ");
            giorniComboBox.getSelectionModel().select(giorno);
            orarioInizioField.setText(orari[0]);
            orarioFineField.setText(orari[1]);
        }
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            attivita.setNome(nomeField.getText());
            attivita.setDescrizione(descrizioneField.getText());

            // Aggiorna la mappa orariPerGiorno dell'attività
            attivita.getOrariPerGiorno().clear();
            giorniOrariMap.forEach((giorno, orari) -> {
                attivita.getOrariPerGiorno().put(giorno, FXCollections.observableArrayList(orari));
            });

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder();
        System.out.println("Stato della lista giorniOrariList: " + giorniOrariList);
        System.out.println("Dimensione della lista giorniOrariList: " + giorniOrariList.size());

        if (nomeField.getText() == null || nomeField.getText().length() == 0) {
            errorMessage.append("Nome non valido!\n");
        }
        if (descrizioneField.getText() == null || descrizioneField.getText().length() == 0) {
            errorMessage.append("Descrizione non valida!\n");
        }
        if (giorniOrariList.isEmpty()) {
            errorMessage.append("Giorni e orari non validi!\n");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campi non validi");
            alert.setHeaderText("Per favore correggi i campi non validi");
            alert.setContentText(errorMessage.toString());

            alert.showAndWait();

            return false;
        }
    }

    private boolean isOrarioValido(String orario) {
        if (orario == null || orario.isEmpty()) {
            return false;
        }
        String[] parts = orario.split(":");
        if (parts.length != 2) {
            return false;
        }
        try {
            int ore = Integer.parseInt(parts[0]);
            int minuti = Integer.parseInt(parts[1]);
            return ore >= 0 && ore < 24 && minuti >= 0 && minuti < 60;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}