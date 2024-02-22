package com.eliasheredia;

import java.net.URL;
import java.util.ResourceBundle;

import com.eliasheredia.services.JugadorService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML
    private GridPane gridMapa;

    @FXML
    private GridPane gridRadar;

    @FXML
    private Text jugador;

    @FXML
    private Text labelLog;

    @FXML
    private Button primaryButton;

    @FXML
    void switchToSecondary(ActionEvent event) {

    }

    @FXML
    public void initialize() {

        JugadorService jugador1 = new JugadorService(1, true, gridMapa, gridRadar, jugador, labelLog);

        for (Node node : this.gridMapa.getChildren()) {
            // if (GridPane.getColumnIndex(node) == 5 && GridPane.getRowIndex(node) == 5) {
            node.setStyle("-fx-background-color: red;");
            // }
        }

        // MAPA
        for (int rowX = 0; rowX < 11; rowX++) {
            for (int rowY = 0; rowY < 11; rowY++) {
                Button ejemplo = new Button("");
                gridMapa.add(ejemplo, rowX, rowY);
                ejemplo.setPrefWidth(100);
                ejemplo.setPrefHeight(100);
                GridPane.setColumnSpan(ejemplo, 1);
                ejemplo.setStyle("-fx-background-color: lightblue; -fx-border-color: #000000;");
                ejemplo.setOnMouseEntered(e -> {
                    ejemplo.setStyle(
                            "-fx-background-color: lightblue; -fx-border-color: #000000; -fx-border-width: 2px; -fx-cursor: hand;");
                });
                ejemplo.setOnMouseExited(e -> {
                    ejemplo.setStyle(
                            "-fx-background-color: lightblue; -fx-border-color: #000000;");
                });
            }
        }

        // RADAR
        for (int rowX = 0; rowX < 11; rowX++) {
            for (int rowY = 0; rowY < 11; rowY++) {
                Button ejemplo = new Button("");
                gridRadar.add(ejemplo, rowX, rowY);
                ejemplo.setPrefWidth(100);
                ejemplo.setPrefHeight(100);
                GridPane.setColumnSpan(ejemplo, 1);
                ejemplo.setStyle("-fx-background-color: black; -fx-border-color: #00ff00;");
                ejemplo.setOnMouseEntered(e -> {
                    ejemplo.setStyle(
                            "-fx-background-color: black; -fx-border-color: #00ff00; -fx-border-width: 3px; -fx-cursor: hand;");
                });
                ejemplo.setOnMouseExited(e -> {
                    ejemplo.setStyle(
                            "-fx-background-color: black; -fx-border-color: #00ff00;");
                });
            }
        }

    }

}
