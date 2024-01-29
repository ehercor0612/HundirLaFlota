package com.eliasheredia.services;

import java.util.ArrayList;

import com.eliasheredia.models.EstadosPosicion;
import com.eliasheredia.models.PosicionFlota;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class JugadorService extends Thread {

    // TO DO: Hacer lista de los barcos y sus posiciones

    // VARIABLES
    GridPane gridPane;
    boolean estaEnTurno = false;
    boolean jugadorEnemigoEnTurno = false;
    ArrayList<PosicionFlota> posicionFlotas = new ArrayList<PosicionFlota>();

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

    // LISTA DE BARCOS

    // RECOGER POSICIÓN DE BARCO

    // EJECUTAR ATAQUE EN TURNO Y SISTEMA DE RECOLECCIÓN DE CORDEENADAS ATACADAS
    private Node ejecutarAccion(GridPane gridPane) {

        int cordenadaX = (int) (Math.random() * 11) + 1;
        int cordenadaY = (int) (Math.random() * 11) + 1;

        if (this.posicionFlotas != null) {
            for (PosicionFlota posicionFlota : posicionFlotas) {
                if (posicionFlota.estado == EstadosPosicion.TOCADO) {

                }
            }
            this.atacar(cordenadaX, cordenadaY);
        } else {
            return null;
        }
        return null;

    }

    public void setTurno(boolean estaEnTurno) {
        this.estaEnTurno = estaEnTurno;
    }

    private void atacar(int x, int y) {
        posicionFlotas.add(new PosicionFlota(x, y));
    }

}

// for (Node node : gridPane.getChildren()) {
// if (GridPane.getColumnIndex(node) == cordenadaY && GridPane.getRowIndex(node)
// == cordenadaX) {
// this.atacar(node);
// }
// }