package windows;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Timer;
import java.util.TimerTask;

public class questionPanelController {

    public static Button sOptions[] = new Button[4];

    public static Label sQuestionLabel, sQuestionNumberLabel;
    public static int currentQuestion  = 0;
    public static Question thisQuestion;
    private boolean waiting = false;

    @FXML
    Button optionA;
    @FXML
    Button optionB;
    @FXML
    Button optionC;
    @FXML
    Button optionD;
    @FXML
    Label questionLabel;
    @FXML
    Label questionNumberLabel;

    public void initialize(){
        sOptions[0] = optionA;
        sOptions[1] = optionB;
        sOptions[2] = optionC;
        sOptions[3] = optionD;
        sQuestionLabel = questionLabel;
        sQuestionNumberLabel = questionNumberLabel;
    }

    @FXML
    void optionClick(ActionEvent e){
        if(waiting)
            return;

        Button thisBtn = (Button)e.getSource();
        int buttonNumber = 0;
        int correctAns = thisQuestion.getCorrectAnswer();

        for(int i = 0; i < 4; i++){
            if(thisBtn.equals(sOptions[i])){
                buttonNumber = i;
                break;
            }
        }

        if(correctAns == buttonNumber){
            sOptions[correctAns].getStyleClass().add("answerBoxCorrect");
            Main.setCorrectAnswers(Main.getCorrectAnswers() + 1);
        }else{
            sOptions[correctAns].getStyleClass().add("answerBoxCorrect");
            sOptions[buttonNumber].getStyleClass().add("answerBoxWrong");
        }

        Timer timer = new Timer();

        waiting = true;

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                for(int i = 0; i < 4; i++) {
                    sOptions[i].getStyleClass().removeAll("answerBoxCorrect", "answerBoxWrong");
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        waiting = false;
                        if(currentQuestion != Main.getTotalQuestion()) {
                            setQuestionPanel();
                        }else {
                            resultController.setResult(Main.getCorrectAnswers(), Main.getTotalQuestion());
                            Main.setScene(Main.sresult);
                        }
                    }
                });
            }
        }, 1000);

    }


    public static void setQuestionPanel(){
        currentQuestion++;

        Question qs = Main.getQuestions()[currentQuestion - 1];
        thisQuestion = qs;

        sQuestionLabel.setText(qs.getQuestion());
        for (int i = 0; i < 4; i++) {
            sOptions[i].setText(qs.getOptions()[i]);
        }
        sQuestionNumberLabel.setText(currentQuestion + "");
    }

}


