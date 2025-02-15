module projeto.projeto_poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires java.desktop;


    opens projeto.projeto_poo to javafx.fxml;
    exports projeto.projeto_poo;
    exports projeto.projeto_poo.view;
    opens projeto.projeto_poo.view to javafx.fxml;
}