package com.eliasheredia.services;

public class TurnoTerminadoEvent {
    private JugadorService jugador;

    public TurnoTerminadoEvent(JugadorService jugador) {
        this.jugador = jugador;
    }

    public JugadorService getJugador() {
        return jugador;
    }
}
