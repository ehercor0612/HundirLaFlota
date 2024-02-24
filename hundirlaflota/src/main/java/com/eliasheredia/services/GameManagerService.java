package com.eliasheredia.services;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.eliasheredia.models.PosicionFlota;
import com.eliasheredia.models.barcos.jugador1.Barca1;
import com.eliasheredia.models.barcos.jugador1.Crucero1;
import com.eliasheredia.models.barcos.jugador1.Lancha1;
import com.eliasheredia.models.barcos.jugador1.Submarino1;
import com.eliasheredia.models.barcos.jugador2.Barca2;
import com.eliasheredia.models.barcos.jugador2.Crucero2;
import com.eliasheredia.models.barcos.jugador2.Lancha2;
import com.eliasheredia.models.barcos.jugador2.Submarino2;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GameManagerService {

    private GridPane gridMapa;
    private GridPane gridRadar;
    private ScrollPane scrollPanel;
    private List<JugadorService> jugadores = new ArrayList<>();

    private boolean partidaEnCurso = false;

    public GameManagerService(List<JugadorService> jugadores, GridPane gridMapa, GridPane gridRadar,
            ScrollPane scrollPanel) {
        this.jugadores = jugadores;
        this.gridMapa = gridMapa;
        this.gridRadar = gridRadar;
        this.scrollPanel = scrollPanel;
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
                List<Class<?>> listaBarcosJugador;
                GridPane grid;
                String rutaImagen;

                // Determinar la lista de clases de barcos y el grid correspondiente según el
                // jugador actual
                if (jugadorActual == jugadores.get(0)) {
                    listaBarcosJugador = Arrays.asList(Barca1.class, Crucero1.class, Lancha1.class, Submarino1.class);
                    grid = gridMapa;
                    rutaImagen = "src\\main\\resources\\com\\eliasheredia\\imagenes\\gato.png";
                } else {
                    listaBarcosJugador = Arrays.asList(Barca2.class, Crucero2.class, Lancha2.class, Submarino2.class);
                    grid = gridRadar;
                    rutaImagen = "src\\main\\resources\\com\\eliasheredia\\imagenes\\gato2.png";
                }

                // Texto para mostrar qué clase de barcos se generan
                StringBuilder textoClaseBarco = new StringBuilder(scrollPanel.getContent().toString());

                // Lista de posiciones ocupadas por los barcos generados
                List<PosicionFlota> posicionesOcupadas = new ArrayList<>();

                for (Class<?> claseBarco : listaBarcosJugador) {
                    // Agregar el nombre de la clase de barco al texto
                    textoClaseBarco.append("Barcos para Jugador ").append(jugadorActual == jugadores.get(0) ? "1" : "2")
                            .append(":\n");
                    textoClaseBarco.append(claseBarco.getSimpleName()).append("\n");

                    // Crear una instancia de la clase de barco seleccionada
                    Object barco = claseBarco.getDeclaredConstructor().newInstance();

                    // Obtener el tamaño del barco
                    int tamanoBarco = (int) claseBarco.getDeclaredField("tamano").get(barco);

                    boolean generado = false;

                    // Intentar generar una posición aleatoria válida para el barco
                    while (!generado) {
                        // Generar una posición aleatoria dentro de los límites del GridPane
                        Random rand = new Random();
                        int fila = rand.nextInt(11 - tamanoBarco); // Ajustar para que no exceda los límites
                        int columna = rand.nextInt(11 - tamanoBarco); // Ajustar para que no exceda los límites

                        // Verificar si hay un barco en la proximidad
                        boolean posicionValida = true;
                        for (PosicionFlota posicion : posicionesOcupadas) {
                            if (Math.abs(fila - posicion.getFila()) <= 1
                                    && Math.abs(columna - posicion.getColumna()) <= 1) {
                                posicionValida = false;
                                break;
                            }
                        }

                        if (posicionValida) {
                            generado = true;

                            // Registrar las posiciones ocupadas por el nuevo barco
                            for (int j = 0; j < tamanoBarco; j++) {
                                int filaActual = fila;
                                int columnaActual = columna;

                                if (tamanoBarco > 1) {
                                    // Cambiar la fila o columna actual dependiendo de la orientación del barco
                                    if (claseBarco.equals(Crucero1.class) || claseBarco.equals(Crucero2.class)) {
                                        filaActual += j;
                                    } else {
                                        columnaActual += j;
                                    }
                                }

                                posicionesOcupadas.add(new PosicionFlota(filaActual, columnaActual));

                                // Cargar la imagen desde el archivo correspondiente al grid
                                Image imagen = new Image(new FileInputStream(rutaImagen));

                                // Crear ImageView y asignar la imagen
                                ImageView imageView = new ImageView(imagen);
                                imageView.setFitWidth(50);
                                imageView.setFitHeight(50);

                                // Añadir la imagen a la casilla correspondiente
                                grid.add(imageView, columnaActual, filaActual);
                                GridPane.setColumnSpan(imageView, 1);
                            }
                        }
                    }
                }

                // Añadir el texto al ScrollPane
                Text texto = new Text(textoClaseBarco.toString());
                scrollPanel.setContent(texto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
