
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.sun.javafx.application.LauncherImpl;
import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import sun.font.Font2D;
import sun.font.Type1Font;

public class Main extends Application {


    static {
        System.loadLibrary("untitled10");
    }



    private EventHandler<MouseEvent> mouseEventConsumer = event -> event.consume();

    public static void main(String[] args) {
        launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();

        // Path to the FXML File
        String fxmlDocPath = "src/layout.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
        // Create the Pane and all Details
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);
        Controller controller = loader.getController();
        controller.setStage(stage);

        // Create the Scene
        Scene scene = new Scene(root, new Color(0.85, 0.85, 0.85, 1.0));
        scene.getStylesheets().add("style.css");
        // Set the Scene to the Stage
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        //stage.setMaximized(true);

        scene.setOnTouchStationary(event -> {
            System.out.println("on stationary");

        });
        scene.setOnTouchPressed(event -> {
            System.out.println("mouse pressed");

        });
        scene.setOnZoom(event ->{
            double zoomFactor = event.getZoomFactor();
            System.out.println("Zoom Factor " + zoomFactor);

        });

        stage.setScene(scene);
        // Set the Title to the Stage
        stage.setTitle("A simple FXML Example");
        // Display the Stage
        stage.show();


    }

}
