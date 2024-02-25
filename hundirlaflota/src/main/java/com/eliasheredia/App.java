package com.eliasheredia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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

    private static GridPane gridMapa;

    private static GridPane gridRadar;

    private static ScrollPane scrollPanel;

    private static Text labelLog;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();

        // Obtiene una referencia al gridMapa desde el controlador FXML
        gridMapa = (GridPane) scene.lookup("#gridMapa");
        gridRadar = (GridPane) scene.lookup("#gridRadar");
        scrollPanel = (ScrollPane) scene.lookup("#scroll");
        labelLog = (Text) scene.lookup("#labelLog");

        // Crear instancias de los jugadores
        JugadorService jugador1 = new JugadorService(1, true, gridMapa, gridRadar, labelLog, scrollPanel);
        JugadorService jugador2 = new JugadorService(2, false, gridMapa, gridRadar, labelLog, scrollPanel);

        // Configurar gridMapa en cada jugador
        jugador1.setGridMapa(gridMapa);
        jugador2.setGridRadar(gridRadar);

        // Agregar jugadores a la lista de jugadores
        List<JugadorService> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);

        // Crear instancia de GameManagerService
        GameManagerService gameManager = new GameManagerService(jugadores, gridMapa, gridRadar, scrollPanel);

        // Iniciar la partida
        gameManager.iniciarPartida();

    }

    public static void main(String[] args) {
        launch();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
