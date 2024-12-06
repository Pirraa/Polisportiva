module lp.unife.it {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.prefs;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;
    requires java.base;

    opens lp.unife.it to javafx.fxml;
    opens lp.unife.it.controllers to  javafx.fxml;
    opens lp.unife.it.models to com.fasterxml.jackson.databind; 
    exports lp.unife.it;
    exports lp.unife.it.models;
    exports lp.unife.it.controllers to javafx.fxml;
}
