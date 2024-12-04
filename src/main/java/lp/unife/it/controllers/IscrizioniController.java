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
    private TableView<Iscrizione> iscrizioniTable;
    @FXML
    private TableColumn<Iscrizione, String> atletaColumn;
    @FXML
    private TableColumn<Iscrizione, String> attivitàColumn;
    @FXML
    private Label nomeLabel;
    @FXML
    private Label descrizioneLabel;
    @FXML
    private Label cognomeLabel;
    @FXML
    private Label nomeAttivitàLabel;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        iscrizioniTable.setItems(mainApp.polisportiva.getIscrizioni());
    }

    @FXML
    private void initialize() 
    {
        // Initialize the iscrizioni table with the two columns.
        atletaColumn.setCellValueFactory(cellData ->
        cellData.getValue().getAtleta().nomeProperty());
        attivitàColumn.setCellValueFactory(cellData ->
        cellData.getValue().getAttivita().nomeProperty());

        // Clear iscrizione details.
        showIscrizioneDetails(null);
        // Listen for selection changes and show the iscrizione details when changed.
        iscrizioniTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showIscrizioneDetails(newValue));
    }

    public void showIscrizioneDetails(Iscrizione iscrizione) 
    {
        if (iscrizione != null) 
        {
            // Fill the labels with info from the iscrizione object.
            nomeLabel.setText(iscrizione.getAtleta().getNome());
            cognomeLabel.setText(iscrizione.getAtleta().getCognome());
            nomeAttivitàLabel.setText(iscrizione.getAttivita().getNome());
            descrizioneLabel.setText(iscrizione.getAttivita().getDescrizione());
        } else {
            // Iscrizione is null, remove all the text.
            nomeLabel.setText("");
            cognomeLabel.setText("");
            nomeAttivitàLabel.setText("");
            descrizioneLabel.setText("");
        }
    }

}
