package lp.unife.it.controllers;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import lp.unife.it.DateUtil;
import lp.unife.it.MainApp;
import lp.unife.it.models.Atleta;
import lp.unife.it.models.AttivitaSportiva;
import lp.unife.it.models.Giorno;
import lp.unife.it.models.Iscrizione;

public class IscrizioniEditDialogController {
    private Stage dialogStage;
    @FXML
    private ComboBox<AttivitaSportiva> attivitaCombo;
    @FXML
    private ComboBox<Atleta> atletaCombo;
    private MainApp mainapp;
    private boolean okClicked = false;
    private Iscrizione iscrizione;

    private ObservableList<AttivitaSportiva> attivitaData = FXCollections.observableArrayList();
    private ObservableList<Atleta> atletaData = FXCollections.observableArrayList();
    private ObservableList<Iscrizione> iscrizioneData = FXCollections.observableArrayList();

    @FXML
    private void initialize() 
    {
    
    }

    
    public void setData(ObservableList<AttivitaSportiva> attivitaData, ObservableList<Atleta> atletiData, ObservableList<Iscrizione> iscrizioniData)
    {
        this.attivitaData = attivitaData;
        this.atletaData = atletiData;
        this.iscrizioneData = iscrizioniData;
        // Initialize the CheckComboBox
        attivitaCombo.setItems(FXCollections.observableArrayList(attivitaData));
        atletaCombo.setItems(FXCollections.observableArrayList(atletiData));
        // Select the athlete's preferred activities in the CheckComboBox
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setIscrizione(Iscrizione tempIscrizione) {
        this.iscrizione = tempIscrizione;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

     @FXML
    private void handleOk() 
    {
        if (isInputValid() && mainapp.polisportiva.cercaIscrizione(atletaCombo.getValue(), attivitaCombo.getValue())==false)
        {
            iscrizione.setAtleta(atletaCombo.getValue());
            iscrizione.setAttivita(attivitaCombo.getValue());
            okClicked = true;
            dialogStage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Iscrizione già presente");
            alert.setHeaderText("cambia atleta o attività");
            alert.setContentText("Attività"+attivitaCombo.getValue().getNome()+" già presente per l'atleta "+atletaCombo.getValue().getNome());

            alert.showAndWait();

        }
    }
          
    @FXML
    private void handleCancel() 
    {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (attivitaCombo.getValue() == null) {
            errorMessage += "Nessuna attività selezionata!\n";
        }
        if (atletaCombo.getValue() == null) {
            errorMessage += "Nessun atleta selezionato!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campi non validi");
            alert.setHeaderText("Per favore correggi i campi non validi");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public void setMainApp(MainApp mainapp) {
        this.mainapp = mainapp;
    }
    
}
