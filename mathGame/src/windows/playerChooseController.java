package windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class playerChooseController {

    @FXML
    void singlePlayerClick(ActionEvent e){
        Main.setScene(Main.sgameMode);
    }

    @FXML
    void multiPlayerClick(ActionEvent e){
        Main.setScene(Main.smultiPlayer);
    }

}
