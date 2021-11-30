package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InitialController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Hullo!");
    }
}