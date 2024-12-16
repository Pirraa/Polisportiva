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
import javafx.util.StringConverter;

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
        attivitaCombo.setConverter(new AttivitaSportivaStringConverter());
        atletaCombo.setConverter(new AtletaStringConverter());
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
            alert.setContentText("o l'attività è già presente o nn la hai selezionata o è vuota, stessa cosa per atleta");

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

        if (attivitaCombo.getValue() == null || attivitaCombo.getItems().size() == 0) {
            errorMessage += "Nessuna attività selezionata!\n";
        }
        if (atletaCombo.getValue() == null || attivitaCombo.getItems().size() == 0) {
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



class AtletaStringConverter extends StringConverter<Atleta> {
    @Override
    public String toString(Atleta atleta) {
        if (atleta == null) {
            return "";
        }
        return atleta.getNome() + " " + atleta.getCognome();
    }

    @Override
    public Atleta fromString(String string) {
        // Non è necessario implementare questo metodo per la visualizzazione
        return null;
    }
}


class AttivitaSportivaStringConverter extends StringConverter<AttivitaSportiva> {
    @Override
    public String toString(AttivitaSportiva attivita) {
        if (attivita == null) {
            return "";
        }
        return attivita.getNome() + " - " + attivita.getDescrizione();
    }

    @Override
    public AttivitaSportiva fromString(String string) {
        // Non è necessario implementare questo metodo per la visualizzazione
        return null;
    }
}

