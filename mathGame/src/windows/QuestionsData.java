package windows;

import java.io.Serializable;

public class QuestionsData implements Serializable {
    Question question[];
    int totalQuestions;
    QuestionDifficulty questionDifficulty;
    QuestionType []questionType;

    QuestionsData(Question []qn, int tq, QuestionDifficulty qd, QuestionType []qt){
        question = qn;
        totalQuestions = tq;
        questionDifficulty = qd;
        questionType = qt;
    }


    public Question[] getQuestion() {
        return question;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public QuestionDifficulty getQuestionDifficulty() {
        return questionDifficulty;
    }

    public QuestionType []getQuestionType() {
        return questionType;
    }

    public void setQuestion(Question[] question) {
        this.question = question;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public void setQuestionDifficulty(QuestionDifficulty questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }

    public void setQuestionType(QuestionType []questionType) {
        this.questionType = questionType;
    }
}
