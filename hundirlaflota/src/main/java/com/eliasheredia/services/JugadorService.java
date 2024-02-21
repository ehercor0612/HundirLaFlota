package com.eliasheredia.services;

import java.util.ArrayList;
import java.util.Random;

import com.eliasheredia.models.EstadosPosicion;
import com.eliasheredia.models.PosicionFlota;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import com.eliasheredia.models.EstadosPosicion;

public class JugadorService extends Thread {

    // VARIABLES
    private int numJugador;
    private GridPane gridMapa;
    private GridPane gridRadar;
    private Text jugador;
    private Text labelLog;
    boolean estaEnTurno = false;
    boolean jugadorEnemigoEnTurno = false;
    ArrayList<PosicionFlota> posicionFlotas = new ArrayList<PosicionFlota>();

    public JugadorService(int numJugador, boolean empiezaPartida, GridPane gridMapa, Text jugador, Text labelLog) {
        this.numJugador = numJugador;
        this.estaEnTurno = empiezaPartida;
        this.gridMapa = gridMapa;
        this.jugador = jugador;
        this.labelLog = labelLog;
    }

    public void setNumJugador(int numJugador) {
        this.numJugador = numJugador;
    }

    public void setGridMapa(GridPane gridMapa) {
        this.gridMapa = gridMapa;
    }

    public void setJugador(Text jugador) {
        this.jugador = jugador;
    }

    public void setlabelLog(Text labelLog) {
        this.labelLog = labelLog;
    }

    // ACCIÓN DE THREADS
    public void run() {
        while (!hayGanador()) {
            if (estaEnTurno) {
                seleccionarCasillaAleatoria();
                estaEnTurno = false;
                declararJugador();
                System.out.println("Turno del jugador: " + Thread.currentThread().getName());
            }
        }
    }

    // SELECCIÓN DE CASILLA
    private void seleccionarCasillaAleatoria() {
        Random rand = new Random();
        int fila = rand.nextInt(11);
        int columna = rand.nextInt(11);

        System.out.println("Jugador: " + Thread.currentThread().getName() + " seleccionó la casilla: (" + fila + ", "
                + columna + ")");

        EstadosPosicion estado = verificarCasilla(fila, columna);
        declararLog(fila, columna, estado);
        cambiarColorCasilla(fila, columna);
    }

    private void cambiarColorCasilla(int fila, int columna) {
        Platform.runLater(() -> {
            Button ejemplo = new Button("");
            gridMapa.add(ejemplo, fila, columna);
            ejemplo.setPrefWidth(100);
            ejemplo.setPrefHeight(100);
            GridPane.setColumnSpan(ejemplo, 1);
            ejemplo.setStyle("-fx-background-color: red; -fx-border-color: #000000;");
            ejemplo.setOnMouseEntered(e -> {
                ejemplo.setStyle(
                        "-fx-background-color: red; -fx-border-color: #000000; -fx-border-width: 2px; -fx-cursor: hand;");
            });
            ejemplo.setOnMouseExited(e -> {
                ejemplo.setStyle(
                        "-fx-background-color: red; -fx-border-color: #000000;");
            });
        });
    }

    private EstadosPosicion verificarCasilla(int fila, int columna) {
        Node nodo = getNodeFromGridPane(gridMapa, columna, fila); // Obtiene el nodo en la casilla especificada
        return nodo == null ? EstadosPosicion.AGUA : EstadosPosicion.TOCADO; // Si el nodo es null, la casilla está //
    }

    private Node getNodeFromGridPane(GridPane gridPane, int colIndex, int rowIndex) {
        for (Node nodo : gridPane.getChildren()) {
            Integer columnIndex = GridPane.getColumnIndex(nodo);
            Integer rowIndexValue = GridPane.getRowIndex(nodo);
            if (columnIndex != null && rowIndexValue != null && columnIndex.intValue() == colIndex
                    && rowIndexValue.intValue() == rowIndex) {
                return nodo;
            }
        }
        return null;
    }

    // CAMBIO DE NOMBRE y ENTRADA A LOG
    private void declararJugador() {
        Platform.runLater(() -> {
            jugador.setText("Turno del jugador " + this.numJugador);
        });
    }

    private void declararLog(int fila, int columna, EstadosPosicion estado) {
        Platform.runLater(() -> {
            String mensaje;
            switch (estado) {
                case AGUA:
                    mensaje = "AGUA";
                    break;
                case TOCADO:
                    mensaje = "TOCADO";
                    break;
                case TOCADO_HUNDIDO:
                    mensaje = "TOCADO HUNDIDO";
                    break;
                default:
                    mensaje = "Estado desconocido";
                    break;
            }
            labelLog.setText("Jugador: " + this.numJugador + " seleccionó la casilla: (" + fila + ", "
                    + columna + ") - Estado: " + mensaje);
        });
    }

    // DECLARAR GANADOR
    private boolean hayGanador() {
        return false;
    }

}
