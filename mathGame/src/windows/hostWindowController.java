package windows;

import Networking.NetworkUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class hostWindowController {

    @FXML
    Pane paneHost;

    public static Pane paneDemo;

    @FXML
    public void initialize(){
        paneDemo = paneHost;
    }

    @FXML
    void nextButtonClick(ActionEvent e){

        QuestionsData questionsData;

        Main.setQuestionTypes(QuestionType.values());
        Main.setQuestionDifficulty(QuestionDifficulty.hard);
        Main.setTotalQuestion(10);
        Main.setQuestions();

        questionsData = new QuestionsData(Main.getQuestions(), Main.getTotalQuestion(), Main.getQuestionDifficulty(), Main.getQuestionTypes());

        HashMap<String, NetworkUtil>connections = multiPlayerController.server.connections;
        Set set = connections.keySet();

        Iterator<String> itr = set.iterator();
        while (itr.hasNext()){
            String key = itr.next();
            NetworkUtil nc = (NetworkUtil) connections.get(key);
            nc.write(questionsData);
        }

        questionPanelController.setQuestionPanel();
        Main.setScene(Main.squestionPanel);
    }

}
