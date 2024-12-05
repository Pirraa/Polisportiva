package lp.unife.it.controllers;

import javafx.fxml.FXML;
import lp.unife.it.MainApp;
import javafx.scene.control.TableView;
import lp.unife.it.models.*;
import javafx.scene.control.TableColumn;
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
            attivitaTable.setItems(mainApp.polisportiva.getIscrizioniPerAtleta(atleta));
        } else {
            // Atleta is null, clear the attivita table.
            attivitaTable.setItems(null);
        }
    }
}
