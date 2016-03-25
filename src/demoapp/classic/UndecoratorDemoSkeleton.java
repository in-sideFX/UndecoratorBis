/**
 * Demo purpose In-SideFX (Un)decorator for JavaFX scene License: You can use this code for any kind of purpose,
 * commercial or not.
 */
package demoapp.classic;

import insidefx.undecorator.UndecoratorScene;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author in-sideFX
 */
public class UndecoratorDemoSkeleton extends Application {

    Stage primaryStage;

    @Override
    public void start(final Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("Undecorator Stage");

        // The UI (Client Area) to display
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientAreaNew.fxml"));
        fxmlLoader.setController(this);
        Region root = (Region) fxmlLoader.load();

        // The Undecorator as a Scene
        final UndecoratorScene undecoratorScene = new UndecoratorScene(primaryStage, root);
        
        // Enable fade transition
        undecoratorScene.setFadeInTransition();

        /*
         * Fade out transition on window closing request
         */
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                we.consume();   // Do not hide yet
                undecoratorScene.setFadeOutTransition();
            }
        });

        primaryStage.setScene(undecoratorScene);

        primaryStage.toFront();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
