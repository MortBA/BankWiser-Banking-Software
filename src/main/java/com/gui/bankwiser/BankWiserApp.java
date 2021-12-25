package com.gui.bankwiser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BankWiserApp extends Application {
    private static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;

        try{
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeScene(String fxml) throws IOException{
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            stg.setScene(new Scene(root));
            stg.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}