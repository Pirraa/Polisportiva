package lp.unife.it.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import lp.unife.it.MainApp;
import javafx.scene.control.TableView;
import lp.unife.it.models.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class IscrizioniController 
{
    private MainApp mainApp;
    @FXML
    private TableView<Atleta> atletiTable;
    @FXML
    private TableColumn<Atleta, String> nomeAtletaColumn;
    @FXML
    private TableColumn<Atleta, String> cognomeAtletaColumn;
    @FXML
    private TableColumn<Atleta, Integer> idAtletaColumn;
    @FXML
    private TableView<AttivitaSportiva> attivitaTable;
    @FXML
    private TableColumn<AttivitaSportiva, String> nomeAttivitaColumn;
    @FXML
    private TableColumn<AttivitaSportiva, String> descrizioneAttivitaColumn;
    @FXML
    private TableColumn<AttivitaSportiva, Integer> idAttivitaColumn;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the tables
        //System.out.println(mainApp.polisportiva.getAtleti());
        atletiTable.setItems(mainApp.polisportiva.getAtleti());
    }

    @FXML
    private void initialize() 
    {
        // Initialize the atleti table with the two columns.
        nomeAtletaColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        cognomeAtletaColumn.setCellValueFactory(cellData -> cellData.getValue().cognomeProperty());
        idAtletaColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        // Initialize the attivita table with the two columns.
        nomeAttivitaColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        descrizioneAttivitaColumn.setCellValueFactory(cellData -> cellData.getValue().descrizioneProperty());
        idAttivitaColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        // Clear attivita details.
        showAttivitaDetails(null);
        // Listen for selection changes and show the attivita details when changed.
        atletiTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showAttivitaDetails(newValue));
    }

    public void showAttivitaDetails(Atleta atleta) 
    {
        if (atleta != null) 
        {
            // Fill the attivita table with activities from the atleta object.
            attivitaTable.setItems(mainApp.polisportiva.getAttivitaPerAtleta(atleta));
        } else {
            // Atleta is null, clear the attivita table.
            attivitaTable.setItems(null);
        }
    }

    @FXML
    private void handleDeleteIscrizione() 
    {
        int selectedIndex = attivitaTable.getSelectionModel().getSelectedIndex();
        Atleta selectedAtleta = atletiTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            AttivitaSportiva selectedAttivita = attivitaTable.getItems().get(selectedIndex);
            attivitaTable.getItems().remove(selectedIndex);
            mainApp.polisportiva.rimuoviIscrizione(selectedAtleta, selectedAttivita);
            showAttivitaDetails(selectedAtleta);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Nessuna Selezione");
            alert.setHeaderText("Nessuna Attività relativa all'iscrizione Selezionata");
            alert.setContentText("Per favore seleziona un'attività nella tabella.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewIscrizione() 
    {
        Iscrizione tempIscrzione = new Iscrizione();
        boolean okClicked = mainApp.showIscrizioniEditDialog(tempIscrzione);
        if (okClicked) 
        {
            mainApp.polisportiva.getIscrizioni().add(tempIscrzione);
        }
    }

    //@FXML
    //private void handleEditIscrizione() 
    //{
        
    //}

    public void setIscrizioniaData(ObservableList<Iscrizione> iscrizioniData) {
        
    }
}
