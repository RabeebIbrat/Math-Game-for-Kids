package windows;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {
    @FXML
    Button startGame;

    @FXML
    void startGameClick(ActionEvent e){
        Main.setScene(Main.splayerChoose);
    }
}
