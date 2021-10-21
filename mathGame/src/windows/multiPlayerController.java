package windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Networking.*;

public class multiPlayerController {

    public static Server server;
    public static Client client;

    @FXML
    private TextField nameText;

    @FXML
    public void hostButtonClick(ActionEvent e){
        server = new Server();
        Main.setScene(Main.shostWindow);
    }

    @FXML
    public void joinButtonClick(ActionEvent e){
        client = new Client("127.0.0.1", 33333, nameText.getText());
        Main.setScene(Main.sjoinWindow);
    }
}
