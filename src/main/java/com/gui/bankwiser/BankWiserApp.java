package com.gui.bankwiser;

import com.logic.bankwiser.facade.Facade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Starting class for the entire project.
 *
 * @author Burak Askan
 * @author Chanisra Magnusson
 * @author Daniel Dovhun
 * @author Dragos Florinel Isar
 * @author Kevin Collins
 * @author Mathias Hallander
 * @author Sejal Kanaskar
 */
public class BankWiserApp extends Application {
    private static Stage stg;

    /**
     * Initializes the UI.
     *
     * @param stage accepts JavaFX stages
     * @throws IOException IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/gui/bankwiser/LoadingScreen.fxml"));
            Scene scene1 = new Scene(root);

            //stage.setHeight(760); // Do not use setHeight This does not actually set the Height
            //stage.setWidth(1296);
            stage.setMinHeight(720);
            stage.setMinWidth(1280);
            stage.setTitle("BankWiser App");
            stage.setResizable(false);
            stage.setScene(scene1);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Facilitates the changing of scenes in JavaFX.
     *
     * @param fxml accepts fxml files
     * @throws IOException IOException
     */
    public void changeScene(String fxml) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            stg.setScene(new Scene(root));
            stg.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the program and adds a shutdown hook to attempt to store all information
     * in the event of a shutdown.
     *
     * @param args Startup arguments
     */
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Facade.getInstance().storeAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        launch();
    }
}