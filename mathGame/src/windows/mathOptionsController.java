package windows;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class mathOptionsController {
    @FXML
    private CheckBox addCheck;
    @FXML
    private CheckBox subCheck;
    @FXML
    private CheckBox mulCheck;
    @FXML
    private CheckBox divCheck;
    @FXML
    private CheckBox simplificationCheck;
    @FXML
    private CheckBox trigonometryCheck;

    @FXML
    void playBtnClick(ActionEvent e){
        QuestionType []questionTypeArray;

        ArrayList<QuestionType> qType = new ArrayList<>();
        if(addCheck.isSelected())
            qType.add(QuestionType.add);
        if(subCheck.isSelected())
            qType.add(QuestionType.sub);
        if(mulCheck.isSelected())
            qType.add(QuestionType.mul);
        if(divCheck.isSelected())
            qType.add(QuestionType.div);
        if(simplificationCheck.isSelected())
            qType.add(QuestionType.simplification);
        if(trigonometryCheck.isSelected())
            qType.add(QuestionType.trigonometry);

        questionTypeArray = new QuestionType[qType.size()];
        for (int i = 0; i < qType.size(); i++){
            questionTypeArray[i] = qType.get(i);
        }

        Main.setQuestionTypes(questionTypeArray);

        Main.setQuestions();
        questionPanelController.setQuestionPanel();
        Main.setScene(Main.squestionPanel);
    }
}
