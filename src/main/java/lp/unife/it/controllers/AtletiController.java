package lp.unife.it.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import lp.unife.it.DateUtil;
import lp.unife.it.MainApp;
import javafx.scene.control.TableView;
import lp.unife.it.models.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class AtletiController 
{
    private MainApp mainApp;
    @FXML
    private TableView<Atleta> atletiTable;
    @FXML
    private TableColumn<Atleta, String> firstNameColumn;
    @FXML
    private TableColumn<Atleta, String> lastNameColumn;
    @FXML
    private TableColumn<Atleta, Integer> idColumn;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label telephoneLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private ListView<String> attivitaPreferiteListView;

    private ObservableList<String> attivitaPreferiteList = FXCollections.observableArrayList();

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        atletiTable.setItems(mainApp.polisportiva.getAtleti());
    }

    @FXML
    private void initialize() 
    {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData ->
        cellData.getValue().cognomeProperty());
        lastNameColumn.setCellValueFactory(cellData ->
        cellData.getValue().nomeProperty());
        idColumn.setCellValueFactory(cellData ->
        cellData.getValue().idProperty().asObject());

        /*se metto intero nelle celle devo convertire con asobject
         * myIntegerColumn.setCellValueFactory(cellData ->
            cellData.getValue().myIntegerProperty().asObject());
         */
        // Clear person details.
        showAtletiDetails(null);
        // Listen for selection changes and show the person details when changed.
        //ogni volta che utente seleziona persona nella tabella la mostro 
        atletiTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showAtletiDetails(newValue));

        attivitaPreferiteListView.setItems(attivitaPreferiteList);
    }

    public void showAtletiDetails(Atleta atleta) 
    {
        if (atleta != null) 
        {
            System.out.println(mainApp.polisportiva.getIscrizioniPerAtleta(atleta));
            // Fill the labels with info from the atleta object.
            firstNameLabel.setText(atleta.getNome());
            lastNameLabel.setText(atleta.getCognome());
            addressLabel.setText(atleta.getIndirizzo());
            telephoneLabel.setText(atleta.getTelefono());
            emailLabel.setText(atleta.getEmail());
            birthdayLabel.setText(DateUtil.format(atleta.getDataNascita()));

            // Clear the existing items in the list
            attivitaPreferiteList.clear();

            // Add new items to the list
            for (AttivitaSportiva attivita : atleta.getAttivitaPreferite()) {
                attivitaPreferiteList.add(attivita.getNome()+" "+attivita.getDescrizione());
            }
        } else {
            // Atleta is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            addressLabel.setText("");
            telephoneLabel.setText("");
            emailLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteAtleta() 
    {
        int selectedIndex = atletiTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Atleta atletaSelezionato = atletiTable.getItems().get(selectedIndex);
    
            // Rimuovi le iscrizioni collegate all'atleta
            ObservableList<Iscrizione> iscrizioniAtleta = mainApp.polisportiva.getIscrizioniPerAtleta(atletaSelezionato);
            mainApp.polisportiva.getIscrizioni().removeAll(iscrizioniAtleta);
    
            // Rimuovi l'atleta dalla tabella
            atletiTable.getItems().remove(selectedIndex);
    
            System.out.println(mainApp.polisportiva.getAtleti());
        } 
        else
        {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewAtleta() 
    {
        Atleta tempPerson = new Atleta();
        tempPerson.setId();
        boolean okClicked = mainApp.showAtletaEditDialog(tempPerson);
        if (okClicked) 
        {
            mainApp.polisportiva.getAtleti().add(tempPerson);
        }

    }

    @FXML
    private void handleEditAtleta() 
    {
        Atleta selectedAtleta = atletiTable.getSelectionModel().getSelectedItem();
        if (selectedAtleta != null) 
        {
            boolean okClicked = mainApp.showAtletaEditDialog(selectedAtleta);
            if (okClicked) 
            {
                showAtletiDetails(selectedAtleta);
            }
        }else 
        {
            // Nulla selezionato.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Nessuna Selezione");
            alert.setHeaderText("Nessun Atleta Selezionato");
            alert.setContentText("Per favore seleziona un atleta nella tabella.");
            alert.showAndWait();
        }
    }
}
