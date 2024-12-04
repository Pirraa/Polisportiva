package lp.unife.it.controllers;

import javafx.fxml.FXML;
import lp.unife.it.MainApp;
import javafx.scene.control.TableView;
import lp.unife.it.models.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;

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
    private Label nomeLabel;
    @FXML
    private Label descrizioneLabel;
    @FXML
    private Label orariLabel;
    @FXML
    private Label giorniLabel;
    
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
    }

    public void showAttivitaDetails(AttivitaSportiva attività) 
    {
        if (attività != null) 
        {
            // Fill the labels with info from the atleta object.
            nomeLabel.setText(attività.getNome());
            descrizioneLabel.setText(attività.getDescrizione());
            orariLabel.setText(attività.getOrari());
            giorniLabel.setText(attività.getGiorni().toString());
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

    }

    @FXML
    private void handleNewAttivita() 
    {

    }

    @FXML
    private void handleEditAttivita() 
    {

    }
}
