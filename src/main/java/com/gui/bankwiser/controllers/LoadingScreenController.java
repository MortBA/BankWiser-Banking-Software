package com.gui.bankwiser.controllers;

import com.gui.bankwiser.BankWiserApp;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class to perform functionalities for Home screen also called 'Loading screen'.
 *
 * @author Chanisra
 */
public class LoadingScreenController implements Initializable {
    @FXML
    private AnchorPane BankwiserLogo;

    @FXML
    private Pane SidePanel;

    /**
     * The method initializes necessary transition and animation effects for the screen.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PauseTransition Pause = new PauseTransition(Duration.millis(1000));
        TranslateTransition translateLogo = new TranslateTransition();
        TranslateTransition translateBox = new TranslateTransition();
        FadeTransition fadeBox = new FadeTransition();

        ParallelTransition transition = new ParallelTransition(translateLogo, translateBox, fadeBox);

        //Logo moves to left
        translateLogo.setNode(BankwiserLogo);
        translateLogo.setByX(-309);
        translateLogo.setDuration(Duration.millis(1000));
        translateLogo.setInterpolator(Interpolator.EASE_BOTH);

        //box move to left
        translateBox.setNode(SidePanel);
        translateBox.setByX(-286);
        translateBox.setDuration(Duration.millis(1000));
        translateBox.setInterpolator(Interpolator.EASE_BOTH);

        //fade box
        fadeBox.setNode(SidePanel);
        fadeBox.setDuration(Duration.millis(1000));
        fadeBox.setFromValue(0.0);
        fadeBox.setToValue(1.0);
        fadeBox.setInterpolator(Interpolator.EASE_BOTH);

        SequentialTransition Sequence = new SequentialTransition(Pause, transition);

        Sequence.setOnFinished(e -> {
            BankWiserApp app = new BankWiserApp();
            try {
                app.changeScene("LoginScreen.fxml");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        Sequence.play();
    }
}
