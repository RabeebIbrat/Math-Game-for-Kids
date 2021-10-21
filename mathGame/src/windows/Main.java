package windows;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    Button button;
    static Stage mainStage;
    static Parent root, gameMode, mathOptions, playerChoose, questionPanel, result, multiPlayer, hostWindow, joinWindow;
    public static Scene sroot, sgameMode, smathOptions, splayerChoose, squestionPanel, sresult, smultiPlayer, shostWindow, sjoinWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        primaryStage.setTitle("Game App");

        root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        gameMode = FXMLLoader.load(getClass().getResource("gameMode.fxml"));
        mathOptions = FXMLLoader.load(getClass().getResource("mathOptions.fxml"));
        playerChoose = FXMLLoader.load(getClass().getResource("playerChoose.fxml"));
        questionPanel = FXMLLoader.load(getClass().getResource("questionPanel.fxml"));
        result = FXMLLoader.load(getClass().getResource("result.fxml"));
        multiPlayer = FXMLLoader.load(getClass().getResource("multiPlayer.fxml"));
        hostWindow = FXMLLoader.load(getClass().getResource("hostWindow.fxml"));
        joinWindow = FXMLLoader.load(getClass().getResource("joinWindow.fxml"));

        sroot = new Scene(root);
        sgameMode = new Scene(gameMode);
        smathOptions = new Scene(mathOptions);
        splayerChoose = new Scene(playerChoose);
        squestionPanel = new Scene(questionPanel);
        sresult = new Scene(result);
        smultiPlayer = new Scene(multiPlayer);
        shostWindow = new Scene(hostWindow);
        sjoinWindow = new Scene(joinWindow);


        primaryStage.setScene(sroot);
        primaryStage.show();
    }

    public static void setScene(Scene s){
        mainStage.setScene(s);
    }



    private static QuestionType[]questionTypes;
    private static QuestionDifficulty questionDifficulty;
    private static int totalQuestion = 10;
    private static Question[]questions;
    private static int correctAnswers = 0;

    public static Question[] getQuestions() {
        return questions;
    }
    public static QuestionType[] getQuestionTypes() {
        return questionTypes;
    }
    public static QuestionDifficulty getQuestionDifficulty() {
        return questionDifficulty;
    }
    public static int getTotalQuestion() {
        return totalQuestion;
    }
    public static int getCorrectAnswers(){return correctAnswers;}

    public static void setQuestionTypes(QuestionType[] questionTypes) {
        Main.questionTypes = questionTypes;
    }
    public static void setQuestionDifficulty(QuestionDifficulty questionDifficulty) {
        Main.questionDifficulty = questionDifficulty;
    }
    public static void setTotalQuestion(int totalQuestion) {
        Main.totalQuestion = totalQuestion;
    }
    public static void setCorrectAnswers(int correctAnswers){Main.correctAnswers = correctAnswers;}
    public static void setQuestions(){
        questions = new Question[totalQuestion];
        for(int i = 0; i < totalQuestion; i++){
            questions[i] = new Question(questionDifficulty, questionTypes);
        }
    }
    public static void setQuestions(Question []questions){
        Main.questions = questions;
    }




    public static void main(String[] args) {
        launch(args);
    }
}
