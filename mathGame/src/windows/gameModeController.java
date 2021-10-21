package windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class gameModeController {

    @FXML
    private CheckBox easyCheck;
    @FXML
    private CheckBox mediumCheck;
    @FXML
    private CheckBox hardCheck;
    @FXML
    private TextField qsNumberText;

    @FXML
    void easyClick(ActionEvent e){
        easyCheck.setSelected(true);
        mediumCheck.setSelected(false);
        hardCheck.setSelected(false);
    }

    @FXML
    void mediumClick(ActionEvent e){
        easyCheck.setSelected(false);
        mediumCheck.setSelected(true);
        hardCheck.setSelected(false);
    }

    @FXML
    void hardClick(ActionEvent e){
        easyCheck.setSelected(false);
        mediumCheck.setSelected(false);
        hardCheck.setSelected(true);
    }

    @FXML
    void nextButtonClick(ActionEvent e){
        int totalQuestion = 10;
        if(easyCheck.isSelected()){
            Main.setQuestionDifficulty(QuestionDifficulty.easy);
        }else if(mediumCheck.isSelected()){
            Main.setQuestionDifficulty(QuestionDifficulty.medium);
        }else{
            Main.setQuestionDifficulty(QuestionDifficulty.hard);
        }

        totalQuestion = Integer.parseInt(qsNumberText.getText());

        Main.setTotalQuestion(totalQuestion);

        Main.setScene(Main.smathOptions);
    }
}
