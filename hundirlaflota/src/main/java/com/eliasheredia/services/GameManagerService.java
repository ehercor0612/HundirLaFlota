package com.eliasheredia.services;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class GameManagerService {

    List<JugadorService> jugadores = new ArrayList<JugadorService>();

    private GameManagerService(List<JugadorService> jugadores) {
        this.jugadores = jugadores;
    };

    public String checkJugada(JugadorService jugadorActual, Node posicionAtacada) {
        for (var jugador : this.jugadores) {
            if (jugador != jugadorActual) {
                return this.checkCasillaAtacada(posicionAtacada, jugadorActual);
            }
        }
        return null;// ELIMINAR
    }

    public String checkCasillaAtacada(Node posicionAtacada, JugadorService jugador) {
        // TO DO: Implementar lógica de ataque
        return null;// ELIMINAR
    }

}
