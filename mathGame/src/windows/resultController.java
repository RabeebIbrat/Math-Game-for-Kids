package windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class resultController {

    public static Label sresultLabel;
    @FXML
    public Label resultLabel;

    public void initialize(){
        sresultLabel = resultLabel;
    }

    public static void setResult(int mark, int outOfMark){
        sresultLabel.setText("You score is " + mark + "/" + outOfMark + ".");
    }

    @FXML
    public void mainMenuClick(ActionEvent e){
        Main.setScene(Main.sroot);
    }

    @FXML
    public void exitClick(ActionEvent e){
        System.out.println("Exiting");
    }
}
