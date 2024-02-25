package com.eliasheredia.models;

public class PosicionFlota {

    private int posicionX;
    private int posicionY;
    private EstadosPosicion estado;
    private Class<?> tipoBarco; // Añadir tipo de barco

    public PosicionFlota(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public int getFila() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getColumna() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public EstadosPosicion getEstado() {
        return estado;
    }

    public void setEstado(EstadosPosicion estado) {
        this.estado = estado;
    }

    public Class<?> getTipoBarco() { // Método para obtener el tipo de barco
        return tipoBarco;
    }

    public void setTipoBarco(Class<?> tipoBarco) { // Método para establecer el tipo de barco
        this.tipoBarco = tipoBarco;
    }
}
