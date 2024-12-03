package lp.unife.it.controllers;


import javafx.fxml.FXML;
import lp.unife.it.MainApp;
import javafx.scene.control.TableView;
import lp.unife.it.models.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;

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
    }

    public void showAtletiDetails(Atleta atleta) 
    {
        if (atleta != null) 
        {
            // Fill the labels with info from the atleta object.
            firstNameLabel.setText(atleta.getNome());
            lastNameLabel.setText(atleta.getCognome());
            addressLabel.setText(atleta.getIndirizzo());
            telephoneLabel.setText(atleta.getTelefono());
            emailLabel.setText(atleta.getEmail());
            // TODO: We need a way to convert the birthday into a String!
            //birthdayLabel.setText(DateUtil.format(atleta.getDataNascita()));
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

    }

    @FXML
    private void handleNewAtleta() 
    {

    }

    @FXML
    private void handleEditAtleta() 
    {

    }
}
