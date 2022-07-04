module formation.rpg {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens formation.rpg to javafx.fxml;
    exports formation.rpg;
    exports formation.rpg.fx;
    opens formation.rpg.fx to javafx.fxml;
}