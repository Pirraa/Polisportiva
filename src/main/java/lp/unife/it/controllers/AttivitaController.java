package lp.unife.it.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import lp.unife.it.MainApp;
import javafx.scene.control.TableView;
import lp.unife.it.models.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AttivitaController 
{
    private MainApp mainApp;
    @FXML
    private TableView<AttivitaSportiva> attivitaTable;
    @FXML
    private TableColumn<AttivitaSportiva, String> nomeColumn;
    @FXML
    private TableColumn<AttivitaSportiva, String> descrizioneColumn;
    @FXML
    private TableColumn<Atleta, Integer> idColumn;
    @FXML
    private Label nomeLabel;
    @FXML
    private Label descrizioneLabel;
    @FXML
    private Label orariLabel;
    @FXML
    private Label giorniLabel;
    @FXML
    private ListView listGiorniOrari;

     private ObservableList<String> giorniOrariList = FXCollections.observableArrayList();
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        attivitaTable.setItems(mainApp.polisportiva.getAttivita());
    }

    @FXML
    private void initialize() 
    {
        // Initialize the person table with the two columns.
        nomeColumn.setCellValueFactory(cellData ->
        cellData.getValue().nomeProperty());
        descrizioneColumn.setCellValueFactory(cellData ->
        cellData.getValue().descrizioneProperty());
        idColumn.setCellValueFactory(cellData ->
        cellData.getValue().idProperty().asObject());

        /*se metto intero nelle celle devo convertire con asobject
         * myIntegerColumn.setCellValueFactory(cellData ->
            cellData.getValue().myIntegerProperty().asObject());
         */
        // Clear person details.
        showAttivitaDetails(null);
        // Listen for selection changes and show the person details when changed.
        //ogni volta che utente seleziona persona nella tabella la mostro 
        attivitaTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showAttivitaDetails(newValue));

        listGiorniOrari.setItems(giorniOrariList);
    }

    public void showAttivitaDetails(AttivitaSportiva attività) 
    {
        if (attività != null) 
        {
            // Fill the labels with info from the atleta object.
            nomeLabel.setText(attività.getNome());
            descrizioneLabel.setText(attività.getDescrizione());
            orariLabel.setText(attività.getOrariPerGiorno().values().toString());
            giorniLabel.setText(attività.getOrariPerGiorno().keySet().toString());
            attività.getOrariPerGiorno().forEach((giorno, orari) -> {
                for (String orario : orari) {
                    String giornoOrario = giorno + ": " + orario;
                    giorniOrariList.add(giornoOrario);
                }
            });
        } else {
            // Atleta is null, remove all the text.
            nomeLabel.setText("");
            descrizioneLabel.setText("");
            orariLabel.setText("");
            giorniLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteAttivita() 
    {
        int selectedIndex = attivitaTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            attivitaTable.getItems().remove(selectedIndex);
            System.out.println(mainApp.polisportiva.getAttivita());
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Nessuna Selezione");
            alert.setHeaderText("Nessuna Attività Selezionata");
            alert.setContentText("Per favore seleziona un'attività nella tabella.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewAttivita() 
    {
        AttivitaSportiva tempAttivita = new AttivitaSportiva();
        boolean okClicked = mainApp.showAttivitaEditDialog(tempAttivita);
        if (okClicked) 
        {
            mainApp.polisportiva.getAttivita().add(tempAttivita);
        }
    }

    @FXML
    private void handleEditAttivita() 
    {
        AttivitaSportiva selectedAttivita = attivitaTable.getSelectionModel().getSelectedItem();
        if (selectedAttivita != null) 
        {
            boolean okClicked = mainApp.showAttivitaEditDialog(selectedAttivita);
            if (okClicked) 
            {
                showAttivitaDetails(selectedAttivita);
            }
        } else 
        {
            // Nulla selezionato.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Nessuna Selezione");
            alert.setHeaderText("Nessuna Attività Selezionata");
            alert.setContentText("Per favore seleziona un'attività nella tabella.");
            alert.showAndWait();
        }
    }

    public void setAttivitaData(ObservableList<AttivitaSportiva> attivitaData) {
        attivitaTable.setItems(attivitaData);
    }
}
