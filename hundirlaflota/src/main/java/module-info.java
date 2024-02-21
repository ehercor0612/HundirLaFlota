module com.eliasheredia {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.eliasheredia to javafx.fxml;

    exports com.eliasheredia;
}
