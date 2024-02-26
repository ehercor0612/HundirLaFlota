package com.eliasheredia.models;

public class PosicionFlota {

    private int posicionX;
    private int posicionY;
    private EstadosPosicion estado;
    private Class<?> tipoBarco;
    private int vecesTocado = 0;
    private int tamano;

    public PosicionFlota(int posicionX, int posicionY, int tamano) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.tamano = tamano;
    }

    public void incrementarVecesTocado() {
        vecesTocado++;
    }

    public int getVecesTocado() {
        return vecesTocado;
    }

    public int getTamano() {
        return tamano;
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

    public Class<?> getTipoBarco() {
        return tipoBarco;
    }

    public void setTipoBarco(Class<?> tipoBarco) {
        this.tipoBarco = tipoBarco;
    }
}
