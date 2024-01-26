package com.eliasheredia.services;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class JugadorService extends Thread {

    // TO DO: Hacer lista de los barcos y sus posiciones

    // VARIABLES
    GridPane gridPane;
    boolean estaEnTurno = false;
    boolean jugadorEnemigoEnTurno = false;

    private JugadorService(boolean empiezaPartida, GridPane gridPane) {
        this.estaEnTurno = empiezaPartida;
        this.gridPane = gridPane;
    }

    // ACCIÓN DE THREADS
    public void run() {
        if (estaEnTurno) {
            this.ejecutarAccion(this.gridPane);
        }
    }

    // EJECUTAR ATAQUE EN TURNO
    private Node ejecutarAccion(GridPane gridPane) {

        int cordenadaX = (int) (Math.random() * 11) + 1;
        int cordenadaY = (int) (Math.random() * 11) + 1;

        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == cordenadaY && GridPane.getRowIndex(node) == cordenadaX) {
                this.atacar(node);
            }
        }
        return null;

    }

    private void atacar(Node node) {

    }

}
