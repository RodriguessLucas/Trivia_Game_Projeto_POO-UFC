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

    opens projeto.projeto_poo to javafx.fxml;
    exports projeto.projeto_poo;
    exports projeto.projeto_poo.controller;
    opens projeto.projeto_poo.controller to javafx.fxml;
}