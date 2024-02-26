package com.eliasheredia.services;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.eliasheredia.models.EstadosPosicion;
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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class GameManagerService {

    private GridPane gridMapa;
    private GridPane gridRadar;
    private ScrollPane scrollPanel;
    private List<JugadorService> jugadores = new ArrayList<>();
    private int jugadorActualIndex = 0;
    private Timer timer;

    private Set<String> casillasAtacadas = new HashSet<>();
    private double probabilidadAtaqueExitoso = 1.0;

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

        // Añadir los barcos para ambos jugadores
        for (JugadorService jugador : jugadores) {
            añadirBarcos(jugador);
        }

        // Iniciar el temporizador
        iniciarTemporizador();
    }

    private void iniciarTemporizador() {
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        // Obtener el jugador actual
                        JugadorService jugadorActual = jugadores.get(jugadorActualIndex);

                        // Realizar la selección del jugador actual
                        seleccionarCasillaAleatoria(jugadorActual, 1);

                        // Obtener el siguiente jugador
                        jugadorActualIndex = (jugadorActualIndex + 1) % jugadores.size();
                        JugadorService siguienteJugador = jugadores.get(jugadorActualIndex);

                        // Iniciar el turno del siguiente jugador
                        siguienteJugador.start();
                    });
                }
            }, 0, 1500); // Cambiar el intervalo según sea necesario (en milisegundos)
        }
    }

    public void run() {
        int jugadorActualIndex = 0;

        while (true) {
            // Obtener el jugador actual
            JugadorService jugadorActual = jugadores.get(jugadorActualIndex);

            // Realizar la selección del jugador actual
            seleccionarCasillaAleatoria(jugadorActual, 1);

            // Obtener el siguiente jugador
            jugadorActualIndex = (jugadorActualIndex + 1) % jugadores.size();
            JugadorService siguienteJugador = jugadores.get(jugadorActualIndex);

            // Iniciar el turno del siguiente jugador
            Platform.runLater(() -> {
                siguienteJugador.start();
            });

            // Esperar hasta que el siguiente jugador haya terminado su turno
            try {
                siguienteJugador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void terminarPartida() {
        partidaEnCurso = false;
        // Cancelar el temporizador al finalizar la partida
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        Platform.runLater(() -> {
            // Mostrar un mensaje de fin de partida en el scrollPanel
            Text textoFinPartida = new Text("Fin de la partida");
            scrollPanel.setContent(textoFinPartida);
        });
    }

    // Método para detectar si el jugador actual ha seleccionado una casilla con un
    // barco
    private boolean detectarBarco(JugadorService jugadorActual) {
        for (PosicionFlota posicion : jugadorActual.getPosicionFlotas()) {
            if (posicion.getEstado() == EstadosPosicion.TOCADO) {
                return true;
            }
        }
        return false;
    }

    // Lista bidimensional para almacenar las posiciones de los barcos de cada
    // jugador
    private List<List<PosicionFlota>> posicionesBarcosJugadores = new ArrayList<>();

    // Método para añadir los barcos para ambos jugadores y almacenar sus posiciones
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

                // Nueva lista para almacenar las posiciones de los barcos de este jugador
                List<PosicionFlota> posicionesBarcos = new ArrayList<>();

                for (Class<?> claseBarco : listaBarcosJugador) {
                    // Agregar el nombre de la clase de barco al texto
                    textoClaseBarco.append("Barcos para Jugador ").append(jugadorActual.getNumJugador()).append(":\n");
                    textoClaseBarco.append(claseBarco.getSimpleName()).append("\n");

                    // Crear una instancia de la clase de barco seleccionada
                    Object barco = claseBarco.getDeclaredConstructor().newInstance();

                    // Obtener el tamaño del barco
                    int tamanoBarco = claseBarco.getDeclaredField("tamano").getInt(null);

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

                                posicionesOcupadas.add(new PosicionFlota(filaActual, columnaActual, tamanoBarco));
                                // Agregar la posición del barco a la lista de posiciones de los barcos de este
                                // jugador
                                PosicionFlota nuevaPosicion = new PosicionFlota(filaActual, columnaActual, tamanoBarco);
                                nuevaPosicion.setTipoBarco(claseBarco); // Establecer el tipo de barco en la posición
                                posicionesBarcos.add(nuevaPosicion);

                                // Cargar la imagen desde el archivo correspondiente al grid
                                Image imagen = new Image(new FileInputStream(rutaImagen));

                                // Crear ImageView y asignar la imagen
                                ImageView imageView = new ImageView(imagen);
                                imageView.setFitWidth(50);
                                imageView.setFitHeight(50);

                                // Ajustar las coordenadas según el jugador antes de agregar la imagen al grid
                                if (jugadorActual == jugadores.get(0)) {
                                    grid.add(imageView, filaActual, columnaActual);
                                } else {
                                    grid.add(imageView, columnaActual, filaActual);
                                }
                                GridPane.setColumnSpan(imageView, 1);
                            }
                        }
                    }
                }

                // Añadir la lista de posiciones de los barcos de este jugador a la lista
                // bidimensional
                posicionesBarcosJugadores.add(posicionesBarcos);

                // Añadir el texto al ScrollPane
                Text texto = new Text(textoClaseBarco.toString());
                scrollPanel.setContent(texto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void seleccionarCasillaAleatoria(JugadorService jugadorActual, int turnos) {
        // Realizar el número de turnos especificado
        for (int i = 0; i < turnos; i++) {
            Random rand = new Random();
            int fila = -1;
            int columna = -1;

            // Determinar el jugador rival
            JugadorService jugadorRival = obtenerJugadorRival(jugadorActual);

            // Obtener las posiciones de los barcos del jugador rival
            List<PosicionFlota> posicionesBarcosRival = posicionesBarcosJugadores.get(jugadores.indexOf(jugadorRival));

            // Elegir una posición de los barcos del jugador rival para atacar
            PosicionFlota posicionAtaque = null;
            boolean ataqueExitoso = rand.nextDouble() < probabilidadAtaqueExitoso; // Define probabilidadAtaqueExitoso

            if (ataqueExitoso) {
                // Si el ataque es exitoso, seleccionar una posición de los barcos del jugador
                // rival para atacar
                int intentos = 0;
                do {
                    posicionAtaque = posicionesBarcosRival.get(rand.nextInt(posicionesBarcosRival.size()));
                    fila = posicionAtaque.getFila();
                    columna = posicionAtaque.getColumna();
                    intentos++;
                    // Verificar si la casilla ya ha sido atacada y si se han hecho demasiados
                    // intentos
                } while (casillasAtacadas.contains(fila + "-" + columna) && intentos < 100); // Evitar bucle infinito

                // Si se supera el número máximo de intentos, seleccionar una posición aleatoria
                // que no haya sido atacada
                if (intentos >= 100) {
                    do {
                        fila = rand.nextInt(11); // Número de filas en el tablero
                        columna = rand.nextInt(11); // Número de columnas en el tablero
                    } while (casillasAtacadas.contains(fila + "-" + columna));
                }
            } else {
                // Si el ataque no es exitoso, seleccionar una posición aleatoria que no haya
                // sido atacada
                do {
                    fila = rand.nextInt(11); // Número de filas en el tablero
                    columna = rand.nextInt(11); // Número de columnas en el tablero
                } while (casillasAtacadas.contains(fila + "-" + columna)); // Verificar si la casilla ya ha sido atacada
            }

            // Agregar la casilla al conjunto de casillas atacadas
            casillasAtacadas.add(fila + "-" + columna);

            System.out.println(
                    "Jugador: " + jugadorActual.getNumJugador() + " ataca la casilla: (" + fila + ", " + columna + ")");

            ResultadoVerificacion resultado = verificarCasilla(jugadorRival, fila, columna);
            declararLog(fila, columna, resultado, jugadorActual);
            cambiarColorCasilla(jugadorActual, fila, columna);

            // Retardo entre selecciones de casillas
            try {
                Thread.sleep(1000); // 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Una vez que el jugador ha seleccionado una casilla, ceder el turno al
            // siguiente jugador
            JugadorService siguienteJugador = obtenerSiguienteJugador(jugadorActual);
            Platform.runLater(() -> {
                siguienteJugador.start();
            });
        }
    }

    private JugadorService obtenerJugadorRival(JugadorService jugadorActual) {
        int indexActual = jugadores.indexOf(jugadorActual);
        int indexRival = (indexActual + 1) % jugadores.size();
        return jugadores.get(indexRival);
    }

    private JugadorService obtenerSiguienteJugador(JugadorService jugadorActual) {
        int indexActual = jugadores.indexOf(jugadorActual);
        int siguienteIndex = (indexActual + 1) % jugadores.size();
        return jugadores.get(siguienteIndex);
    }

    // Clase para contener el resultado de la verificación de la casilla
    class ResultadoVerificacion {
        private EstadosPosicion estado;
        private Class<?> tipoBarcoTocado;

        public ResultadoVerificacion(EstadosPosicion estado, Class<?> tipoBarcoTocado) {
            this.estado = estado;
            this.tipoBarcoTocado = tipoBarcoTocado;
        }

        public EstadosPosicion getEstado() {
            return estado;
        }

        public Class<?> getTipoBarcoTocado() {
            return tipoBarcoTocado;
        }
    }

    // Método para verificar el estado de la casilla y qué barco ha sido tocado
    private ResultadoVerificacion verificarCasilla(JugadorService jugadorActual, int fila, int columna) {
        // Recorrer la lista de posiciones de los barcos del jugador rival
        List<PosicionFlota> posicionesBarcos = posicionesBarcosJugadores.get(jugadores.indexOf(jugadorActual));
        for (PosicionFlota posicion : posicionesBarcos) {
            // Comprobar si la coordenada de la casilla coincide con alguna posición de un
            // barco
            if (posicion.getFila() == fila && posicion.getColumna() == columna) {
                // Si la posición coincide con un barco, marcarlo como tocado
                posicion.setEstado(EstadosPosicion.TOCADO);
                posicion.incrementarVecesTocado(); // Incrementar el contador de veces tocado

                // Verificar si todas las posiciones del barco han sido tocadas
                if (barcoCompletoHundido(posicionesBarcos, posicion.getTipoBarco())) {
                    // Devolver TOCADO_HUNDIDO si todas las posiciones del barco están tocadas
                    return new ResultadoVerificacion(EstadosPosicion.TOCADO_HUNDIDO, posicion.getTipoBarco());
                } else {
                    // Devolver TOCADO si solo una posición del barco está tocada
                    return new ResultadoVerificacion(EstadosPosicion.TOCADO, posicion.getTipoBarco());
                }
            }
        }
        // Si no se encuentra un barco en la casilla, devolver AGUA
        return new ResultadoVerificacion(EstadosPosicion.AGUA, null);
    }

    // Método para verificar si todas las posiciones de un barco han sido tocadas
    private boolean barcoCompletoHundido(List<PosicionFlota> posicionesBarcos, Class<?> tipoBarco) {
        // Contador para las posiciones tocadas del barco
        int posicionesTocadas = 0;

        // Contar el número de posiciones tocadas del barco
        for (PosicionFlota posicion : posicionesBarcos) {
            // Verificar si la posición pertenece al barco y está tocada
            if (posicion.getTipoBarco().equals(tipoBarco) && posicion.getEstado() == EstadosPosicion.TOCADO) {
                posicionesTocadas++;
            }
        }

        // Obtener el tamaño total del barco
        int tamanoTotalBarco = Arrays.stream(tipoBarco.getDeclaredFields())
                .filter(field -> field.getName().equals("tamano"))
                .mapToInt(field -> {
                    try {
                        return field.getInt(null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return 0;
                    }
                })
                .findFirst()
                .orElse(0);

        // Verificar si todas las posiciones del barco han sido tocadas
        return posicionesTocadas == tamanoTotalBarco;
    }

    private void cambiarColorCasilla(JugadorService jugadorActual, int fila, int columna) {
        Platform.runLater(() -> {
            Button ejemplo = new Button("");
            if (jugadorActual == jugadores.get(0)) {
                // Si el jugadorActual es el jugador1, añadir el botón en el gridRadar
                gridRadar.add(ejemplo, columna, fila);
            } else {
                // Si el jugadorActual es el jugador2, añadir el botón en el gridMapa
                gridMapa.add(ejemplo, fila, columna);
            }
            ejemplo.setPrefWidth(100);
            ejemplo.setPrefHeight(100);
            GridPane.setColumnSpan(ejemplo, 1);

            // Cambiar el color del botón para que sea visible en el gridRadar
            ejemplo.setStyle(
                    "-fx-background-color: " + Color.rgb(255, 255, 0, 0.5).toString() + "; -fx-border-color: #000000;");

            ejemplo.setOnMouseEntered(e -> {
                ejemplo.setStyle(
                        "-fx-background-color: " + Color.rgb(255, 255, 0, 0.7).toString()
                                + "; -fx-border-color: #000000; -fx-border-width: 2px; -fx-cursor: hand;");
            });

            ejemplo.setOnMouseExited(e -> {
                ejemplo.setStyle(
                        "-fx-background-color: " + Color.rgb(255, 255, 0, 0.5).toString()
                                + "; -fx-border-color: #000000;");
            });
        });
    }

    // Método para declarar el estado de la casilla en el log
    private void declararLog(int fila, int columna, ResultadoVerificacion resultado, JugadorService jugadorActual) {
        Platform.runLater(() -> {
            String mensaje;
            switch (resultado.getEstado()) {
                case AGUA:
                    mensaje = "AGUA";
                    break;
                case TOCADO:
                    mensaje = "TOCADO";
                    break;
                case TOCADO_HUNDIDO:
                    mensaje = "TOCADO HUNDIDO";
                    break;
                default:
                    mensaje = "Estado desconocido";
                    break;
            }
            String tipoBarcoTocado = resultado.getTipoBarcoTocado() != null
                    ? resultado.getTipoBarcoTocado().getSimpleName()
                    : "Ninguno";
            // Obtener el texto actual del ScrollPane
            Text textoActual = (Text) scrollPanel.getContent();
            String textoAnterior = textoActual.getText();
            // Concatenar el nuevo mensaje al texto anterior
            String nuevoTexto = textoAnterior + "\nJugador " + jugadorActual.getNumJugador()
                    + " seleccionó la casilla: ("
                    + (fila + 1)
                    + ", "
                    + (columna + 1) + ") - Estado: " + mensaje + " - Barco Tocado: " + tipoBarcoTocado;
            // Crear un nuevo objeto Text con el nuevo texto y establecerlo en el ScrollPane
            Text nuevoTextoNode = new Text(nuevoTexto);
            scrollPanel.setContent(nuevoTextoNode);
        });
    }
}
