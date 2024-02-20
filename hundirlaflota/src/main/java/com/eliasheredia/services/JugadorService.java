package com.eliasheredia.services;

import java.util.ArrayList;
import java.util.Random;

import com.eliasheredia.models.EstadosPosicion;
import com.eliasheredia.models.PosicionFlota;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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
        declararLog(fila, columna);
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

    // CAMBIO DE NOMBRE y ENTRADA A LOG
    private void declararJugador() {
        Platform.runLater(() -> {
            jugador.setText("Turno del jugador " + this.numJugador);
        });
    }

    private void declararLog(int fila, int columna) {
        Platform.runLater(() -> {
            labelLog.setText("Jugador: " + Thread.currentThread().getName() + " seleccionó la casilla: (" + fila + ", "
                    + columna + ")");
        });
    }

    // DECLARAR GANADOR
    private boolean hayGanador() {
        return false;
    }

    // public void run() {
    // if (estaEnTurno) {
    // this.ejecutarAccion(this.gridPane);
    // }
    // }

    // RECOGER POSICIÓN DE BARCO

    // EJECUTAR ATAQUE EN TURNO Y SISTEMA DE RECOLECCIÓN DE CORDEENADAS ATACADAS
    // private Node ejecutarAccion(GridPane gridPane) {

    // int cordenadaX = (int) (Math.random() * 11) + 1;
    // int cordenadaY = (int) (Math.random() * 11) + 1;

    // if (this.posicionFlotas != null) {
    // for (PosicionFlota posicionFlota : posicionFlotas) {
    // if (posicionFlota.estado == EstadosPosicion.TOCADO) {

    // }
    // }
    // this.atacar(cordenadaX, cordenadaY);
    // } else {
    // return null;
    // }
    // return null;

    // }

    // public void setTurno(boolean estaEnTurno) {
    // this.estaEnTurno = estaEnTurno;
    // }

    // private void atacar(int x, int y) {
    // posicionFlotas.add(new PosicionFlota(x, y));
    // }

}
