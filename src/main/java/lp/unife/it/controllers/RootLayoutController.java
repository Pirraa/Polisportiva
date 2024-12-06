package lp.unife.it.controllers;

import java.io.File;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import lp.unife.it.MainApp;
import lp.unife.it.models.Iscrizione;
import lp.unife.it.models.Atleta;

public class RootLayoutController {

    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleNewAtleti() 
    {
        mainApp.polisportiva.getAtleti().clear();
        mainApp.polisportiva.getIscrizioni().clear();
    }

    @FXML
    private void handleNewAttivita() 
    {
        mainApp.polisportiva.getAttivita().clear();
        mainApp.polisportiva.getIscrizioni().clear();
    }

    @FXML
    private void handleNewIscrizioni() 
    {
        mainApp.polisportiva.getIscrizioni().clear();
    }

    /**
    * Opens a FileChooser to let the user select an address book to load.
    *filechooser permette di scegliere file, aprendo classica finestra
    */ @FXML
    private void handleOpenAtleti() 
    {
        FileChooser fileChooser= new FileChooser();
            
        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Json files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        File personFile= mainApp.getFilePath();
            
        if(personFile != null)
        fileChooser.initialFileNameProperty().set(personFile.getName());  
        // Show save file dialog
        File file= fileChooser.showOpenDialog(mainApp.getPrimaryStage());  
        if (file != null) 
        {
            mainApp.loadAtletiDataFromFile(file);
        }
    }

    /**
    * Savesthe file to the person file that is currently open. If there is no
    * open file, the "save as" dialog is shown
    */ @FXML
    private void handleSaveAtleti() 
    {
        File personFile= mainApp.getFilePath();
        if(personFile != null) {
            mainApp.saveAtletiDataToFile(personFile);
        } else{
            handleSaveAsAtleti();
        }
    }

    /**
    * Opens a FileChooser to let the user select a file to save to.
    */ @FXML
    private void handleSaveAsAtleti() 
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter= new FileChooser .ExtensionFilter("Json files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        File atletiFile= mainApp.getFilePath();
        if(atletiFile != null)
            fileChooser.initialFileNameProperty().set(atletiFile.getName());
        // Show save file dialog
        File file= fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if(file != null) 
        {
            // Make sure it has the correct extension
            if(!file.getPath().endsWith(".json")) {
                file= new File(file.getPath()+ ".json");
            }
            mainApp.saveAtletiDataToFile(file);
        }
    }
    /**
    * Opens an about dialog.
    */
    @FXML
    private void handleAbout() 
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Polisportiva");
        alert.setHeaderText("Informazioni");
        alert.setContentText("Authore: Pirra Francesco\nWebsite: ");
        alert.showAndWait();
    }
    /**
    * Closes the application.
    */
    @FXML
    private void handleExit() 
    {
        System.exit(0);
    }
}
