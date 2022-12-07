module com.example.boulderdash {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.boulderdash to javafx.fxml;
    exports com.example.boulderdash;
}