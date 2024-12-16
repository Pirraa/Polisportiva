package lp.unife.it.controllers;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import lp.unife.it.MainApp;
import lp.unife.it.models.Atleta;
import lp.unife.it.models.AttivitaSportiva;
import lp.unife.it.models.Iscrizione;

public class StatisticheController {
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    private ObservableList<String> attivitàNames = FXCollections.observableArrayList();
        private MainApp mainApp;
        /**
        * Initializes the controller class. This method is automatically called
        * after the fxml file has been loaded
        .*/
    
        public void setData(ObservableList<Atleta> atleti, ObservableList<AttivitaSportiva> attivita, ObservableList<Iscrizione> iscrizioni) 
        {
            //attivitàNames=attivita.stream().map(AttivitaSportiva::getNome).collect(Collectors.toCollection(FXCollections::observableArrayList));
            attivitàNames = attivita.stream()
                .map(a -> a.getNome() + " - " + a.getDescrizione())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
            attivitàNames = attivitàNames.stream().distinct().collect(Collectors.toCollection(FXCollections::observableArrayList));
            xAxis.setCategories(attivitàNames);

            // Pulisci i dati del grafico prima di aggiungere nuovi dati
            barChart.getData().clear();
    
            for(int i=0; i<attivita.size(); i++)
            {
                XYChart.Series<String, Integer> series = new XYChart.Series<>();
                series.setName(attivitàNames.get(i));
                int num=mainApp.polisportiva.numeroAtletiIscrittiPerAttivita(attivita.get(i));
                System.out.println(num);
                series.getData().add(new XYChart.Data<>(attivitàNames.get(i), num));
                barChart.getData().add(series);
            }
        }
    
        @FXML
        private void initialize() 
        {
           
        }
    
        public void setMainApp(MainApp mainApp) {
            this.mainApp=mainApp;
    }
    
}
