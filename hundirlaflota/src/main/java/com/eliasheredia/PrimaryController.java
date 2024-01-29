package com.eliasheredia;

import java.net.URL;
import java.util.ResourceBundle;

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
        for (Node node : this.gridMapa.getChildren()) {
            // if (GridPane.getColumnIndex(node) == 5 && GridPane.getRowIndex(node) == 5) {
            node.setStyle("-fx-background-color: red;");
            // }
        }

        // INSERTADO BOTON DE EJEMPLO, NO FUNCIONA POR NODOS, SE ACCEDE DIRECTAMENTE A
        // LA CELDA DESDE EL ID FXML.

        // TO DO: rellenar todas las casillas con minipanes para pintarlos, para que
        // cuando tengamos barcos, que al clicarlos resalte. No hacerlos grandes, hace
        // falta
        // ver impacto

        Button ejemplo = new Button("Ejemplo");
        // GridPane.setColumnIndex(ejemplo, 5);
        // GridPane.setRowIndex(ejemplo, 5);
        // GridPane.setRowSpan(ejemplo, 2);
        gridMapa.add(ejemplo, 7, 7);
        ejemplo.setPrefWidth(300);
        GridPane.setColumnSpan(ejemplo, 3);
        ejemplo.setStyle("-fx-background-color: red;");
    }

}
