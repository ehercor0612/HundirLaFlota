package com.eliasheredia;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.eliasheredia.services.GameManagerService;
import com.eliasheredia.services.JugadorService;

/**
 * JavaFX App
 */
public class App extends Application {

    @FXML
    private static GridPane gridMapa;

    @FXML
    private static GridPane gridRadar;

    @FXML
    private Text jugador;

    @FXML
    private Text labelLog;

    @FXML
    private Button primaryButton;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        launch();

        // Crear instancias de los jugadores
        JugadorService jugador1 = new JugadorService(true, gridMapa); // Establecer empiezaPartida a true o false según
        JugadorService jugador2 = new JugadorService(false, gridRadar);

        // Agregar jugadores a la lista de jugadores
        List<JugadorService> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);

        // Crear instancia de GameManagerService
        GameManagerService gameManager = new GameManagerService(jugadores);

        // Iniciar la partida
        gameManager.iniciarPartida();

    }

}