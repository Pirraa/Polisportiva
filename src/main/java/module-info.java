module lp.unife.it {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens lp.unife.it to javafx.fxml;
    opens lp.unife.it.controllers to  javafx.fxml;
    exports lp.unife.it;
    exports lp.unife.it.controllers to javafx.fxml;
}
