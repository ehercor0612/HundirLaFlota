package com.eliasheredia.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.eliasheredia.models.PosicionFlota;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameManagerService {

    private static GridPane gridMapa;

    private static GridPane gridRadar;

    List<JugadorService> jugadores = new ArrayList<JugadorService>();

    List<PosicionFlota> barcosJugador1 = new ArrayList<PosicionFlota>();
    List<PosicionFlota> barcosJugador2 = new ArrayList<PosicionFlota>();

    public GameManagerService(List<JugadorService> jugadores, GridPane gridMapa, GridPane gridRadar) {
        this.jugadores = jugadores;
        this.gridMapa = gridMapa;
        this.gridRadar = gridRadar;
    };

    public void iniciarPartida() {
        for (JugadorService jugador : jugadores) {
            añadirBarcos();
            jugador.start();
        }
    }

    public void añadirBarcos() {
        Platform.runLater(() -> {
            try {
                Random rand = new Random();
                int fila = rand.nextInt(11);
                int columna = rand.nextInt(11);

                // Ruta de la imagen
                String rutaImagen = "src\\main\\resources\\com\\eliasheredia\\imagenes\\gato.png";
                // Cargar la imagen desde el archivo
                Image imagen = new Image(new FileInputStream(rutaImagen));
                // Crear ImageView y asignar la imagen
                ImageView imageView = new ImageView(imagen);

                imageView.setFitWidth(50);
                imageView.setFitHeight(50);

                gridMapa.add(imageView, fila, columna);
                GridPane.setColumnSpan(imageView, 1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    // public String checkJugada(JugadorService jugadorActual, Node posicionAtacada)
    // {
    // for (var jugador : this.jugadores) {
    // if (jugador != jugadorActual) {
    // return this.checkCasillaAtacada(posicionAtacada, jugadorActual);
    // }
    // }
    // return null;// ELIMINAR
    // }

    // public String checkCasillaAtacada(Node posicionAtacada, JugadorService
    // jugador) {
    // // TO DO: Implementar lógica de ataque
    // return null;// ELIMINAR
    // }

}
