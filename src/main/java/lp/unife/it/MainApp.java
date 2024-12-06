package lp.unife.it;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lp.unife.it.models.Atleta;
import lp.unife.it.models.AttivitaSportiva;
import lp.unife.it.models.Iscrizione;
import lp.unife.it.models.Polisportiva;
import lp.unife.it.controllers.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


/**
 * JavaFX App
 */
public class MainApp extends Application {

    //Polisportiva polisportiva
    public Polisportiva polisportiva;
    private static Scene scene;
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("AddressApp");
        initRootLayout();
        if (rootLayout != null) {
            showAtleti();
            showAttivita();
            showIscrizioni();
        }
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(500);
    }

    public MainApp()
    {
        //Polisportiva polisportiva = Polisportiva.getInstance();
        polisportiva = new Polisportiva();
        //System.out.println(polisportiva.getAtleti());
    }

    /**
    * Initializes the root layout.
    */
    public void initRootLayout() 
    {
        try 
        {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
            .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // // Try to load last opened person file.
        // File file = getPersonFilePath();
        // if (file != null) {
        //     loadPersonDataFromFile(file);
        // }
    }

    private void showAtleti() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AtletiView.fxml"));
            AnchorPane atletiView = (AnchorPane) loader.load();

            // Trova il TabPane e il Tab per Atleti
            TabPane tabPane = (TabPane) rootLayout.getCenter();
            Tab atletiTab = tabPane.getTabs().get(0);
            AnchorPane atletiContent = (AnchorPane) atletiTab.getContent();

            // Aggiungi la view degli atleti al contenuto del tab
            atletiContent.getChildren().clear();
            atletiContent.getChildren().add(atletiView);
            AnchorPane.setTopAnchor(atletiView, 0.0);
            AnchorPane.setBottomAnchor(atletiView, 0.0);
            AnchorPane.setLeftAnchor(atletiView, 0.0);
            AnchorPane.setRightAnchor(atletiView, 0.0);


            // Dai accesso al controller alla main app
            AtletiController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAttivita() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AttivitaView.fxml"));
            AnchorPane attivitaView = (AnchorPane) loader.load();

            // Trova il TabPane e il Tab per Attività
            TabPane tabPane = (TabPane) rootLayout.getCenter();
            Tab attivitaTab = tabPane.getTabs().get(1);
            AnchorPane attivitaContent = (AnchorPane) attivitaTab.getContent();

            // Aggiungi la view delle attività al contenuto del tab
            attivitaContent.getChildren().clear();
            attivitaContent.getChildren().add(attivitaView);

            // Dai accesso al controller alla main app
            AttivitaController controller = loader.getController();
            controller.setMainApp(this);
            controller.setAttivitaData(polisportiva.getAttivita());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showIscrizioni()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/IscrizioniView.fxml"));
            AnchorPane iscrizioniView = (AnchorPane) loader.load();

            // Trova il TabPane e il Tab per Attività
            TabPane tabPane = (TabPane) rootLayout.getCenter();
            Tab iscrizioniTab = tabPane.getTabs().get(2);
            AnchorPane iscrizioniContent = (AnchorPane) iscrizioniTab.getContent();

            // Aggiungi la view delle attività al contenuto del tab
            iscrizioniContent.getChildren().clear();
            iscrizioniContent.getChildren().add(iscrizioniView);

            // Dai accesso al controller alla main app
            IscrizioniController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() 
    {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch();
    }


    public boolean showAttivitaEditDialog(AttivitaSportiva tempAttività) 
    {
        try 
        {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AttivitaEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Aggiungi attività");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AttivitaEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAttivita(tempAttività);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean IscrizioniEditDialog(Iscrizione iscrizione) 
    {
        return false;
    }


    public void showStatistics()
    {
        
    }

    public boolean showAtletaEditDialog(Atleta tempAtleta) 
    {
       try 
        {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AtletiEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Aggiungi atleta");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AtletiEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAtleta(tempAtleta);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    public void saveAtletiDataToFile(File file) 
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.registerModule(new JavaTimeModule());
            mapper.writeValue(file, polisportiva.getAtleti());
            // Save the file path to the registry.
            setFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }

    public File getFilePath() 
    {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setFilePath(File file)
    {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");
            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }

    public void loadAtletiDataFromFile(File file) 
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
             // Registra il modulo per supportare java.time.LocalDate
            mapper.registerModule(new JavaTimeModule());
            //leggo lista di persone dal file poi la trasformo in observable list
            System.out.println("sto loaddando");
            polisportiva.setAtleti(FXCollections.observableArrayList( mapper.readValue(file, new TypeReference<List<Atleta>>() { })));
            setFilePath(file);
            showAtleti();
        } catch (Exception e) { // catches ANY exception
            System.out.println(e.getMessage());
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }
    }

}