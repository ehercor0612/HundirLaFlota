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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import com.eliasheredia.models.EstadosPosicion;

public class JugadorService extends Thread {

    // VARIABLES
    private int numJugador;
    private GridPane gridMapa;
    private GridPane gridRadar;
    private Text labelLog;
    private ScrollPane scrollPanel;
    boolean estaEnTurno = false;
    boolean jugadorEnemigoEnTurno = false;
    ArrayList<PosicionFlota> posicionFlotas = new ArrayList<PosicionFlota>();

    public JugadorService(int numJugador, boolean empiezaPartida, GridPane gridMapa, GridPane gridRadar,
            Text labelLog, ScrollPane scrollPanel) {
        this.numJugador = numJugador;
        this.estaEnTurno = empiezaPartida;
        this.gridMapa = gridMapa;
        this.labelLog = labelLog;
        this.scrollPanel = scrollPanel;
    }

    public void setNumJugador(int numJugador) {
        this.numJugador = numJugador;
    }

    public void setGridMapa(GridPane gridMapa) {
        this.gridMapa = gridMapa;
    }

    public void setGridRadar(GridPane gridRadar) {
        this.gridRadar = gridRadar;
    }

    public void setlabelLog(Text labelLog) {
        this.labelLog = labelLog;
    }

    public int getNumJugador() {
        return numJugador;
    }

    public GridPane getGridMapa() {
        return gridMapa;
    }

    public GridPane getGridRadar() {
        return gridRadar;
    }

    public Text getLabelLog() {
        return labelLog;
    }

    public ScrollPane getScrollPanel() {
        return scrollPanel;
    }

    public ArrayList<PosicionFlota> getPosicionFlotas() {
        return posicionFlotas;
    }

}
