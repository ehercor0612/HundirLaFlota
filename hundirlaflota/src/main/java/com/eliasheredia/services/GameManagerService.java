package com.eliasheredia.services;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;

public class GameManagerService {

    List<JugadorService> jugadores = new ArrayList<JugadorService>();

    public GameManagerService(List<JugadorService> jugadores) {
        this.jugadores = jugadores;
    };

    public void iniciarPartida() {
        // Iniciar los hilos para cada jugador
        for (JugadorService jugador : jugadores) {
            jugador.start();
        }
    }

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
