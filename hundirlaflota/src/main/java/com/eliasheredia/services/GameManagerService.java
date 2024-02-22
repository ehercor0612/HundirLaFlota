package com.eliasheredia.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.eliasheredia.models.PosicionFlota;
import com.eliasheredia.models.barcos.jugador1.Barca1;
import com.eliasheredia.models.barcos.jugador1.Crucero1;
import com.eliasheredia.models.barcos.jugador1.Lancha1;
import com.eliasheredia.models.barcos.jugador1.Submarino1;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameManagerService {

    private GridPane gridMapa;
    private GridPane gridRadar;
    private List<JugadorService> jugadores = new ArrayList<>();

    private boolean partidaEnCurso = false;

    public GameManagerService(List<JugadorService> jugadores, GridPane gridMapa, GridPane gridRadar) {
        this.jugadores = jugadores;
        this.gridMapa = gridMapa;
        this.gridRadar = gridRadar;
    }

    public void iniciarPartida() {
        if (partidaEnCurso) {
            return; // La partida ya está en curso
        }

        partidaEnCurso = true;
        for (JugadorService jugador : jugadores) {
            añadirBarcos(jugador);
            jugador.start(); // Iniciar el turno del jugador
        }
    }

    public void terminarPartida() {
        partidaEnCurso = false;
    }

    public void añadirBarcos(JugadorService jugadorActual) {
        Platform.runLater(() -> {
            try {
                Random rand = new Random();
                int fila = rand.nextInt(11);
                int columna = rand.nextInt(11);

                // Elegir una orientación aleatoria (0 para horizontal, 1 para vertical)
                int orientacion = rand.nextInt(2);

                // Crear una lista de las clases de barcos
                List<Class<?>> listaBarcos = Arrays.asList(Barca1.class, Crucero1.class, Lancha1.class,
                        Submarino1.class);

                // Seleccionar aleatoriamente una clase de barco
                Class<?> claseBarco = listaBarcos.get(rand.nextInt(listaBarcos.size()));

                // Crear una instancia de la clase de barco seleccionada
                Object barco = claseBarco.getDeclaredConstructor().newInstance();

                // Obtener el tamaño del barco
                int tamanoBarco = (int) claseBarco.getDeclaredField("tamano").get(barco);

                // Determinar las posiciones a ocupar por el barco en función de su tamaño y
                // orientación
                for (int i = 0; i < tamanoBarco; i++) {
                    // Calcular las coordenadas de la casilla actual en función de la orientación
                    int filaActual = orientacion == 0 ? fila : fila + i;
                    int columnaActual = orientacion == 1 ? columna : columna + i;

                    // Ruta de la imagen
                    String rutaImagen = "src\\main\\resources\\com\\eliasheredia\\imagenes\\gato.png";

                    // Cargar la imagen desde el archivo
                    Image imagen = new Image(new FileInputStream(rutaImagen));

                    // Crear ImageView y asignar la imagen
                    ImageView imageView = new ImageView(imagen);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);

                    // Determinar en qué grid añadir la imagen según el jugador actual
                    GridPane grid = jugadorActual == jugadores.get(0) ? gridMapa : gridRadar;

                    // Añadir la imagen a la casilla correspondiente
                    grid.add(imageView, columnaActual, filaActual);
                    GridPane.setColumnSpan(imageView, 1);
                }
            } catch (Exception e) {
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
