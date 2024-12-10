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
            attivitàNames=attivita.stream().map(AttivitaSportiva::getNome).collect(Collectors.toCollection(FXCollections::observableArrayList));
            xAxis.setCategories(attivitàNames);
    
            for(AttivitaSportiva a : attivita)
            {
                XYChart.Series<String, Integer> series = new XYChart.Series<>();
                series.setName(a.getNome());
                int num=mainApp.polisportiva.numeroAtletiIscrittiPerAttivita(a);
                series.getData().add(new XYChart.Data<>(a.getNome(), num));
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
