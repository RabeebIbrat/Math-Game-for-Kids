package windows;

import java.io.Serializable;
import java.util.ArrayList;

enum QuestionDifficulty{
    easy, medium, hard
}

enum QuestionType{
    add, sub, mul, div, trigonometry, simplification
}



public class Question implements Serializable{
    private String question;
    private String []options;
    private int optionCount = 4;
    private int correctAnswer;
    private QuestionType questionType;
    private int qsTypes = 6;

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getOptionCount() {
        return optionCount;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    Question(){
        int rand = (int)(Math.random()*100)%qsTypes;
        switch (rand){
            case 0:
                qsAddSubMulDiv(QuestionType.add, 3);
                break;
            case 1:
                qsAddSubMulDiv(QuestionType.sub, 3);
                break;
            case 2:
                qsAddSubMulDiv(QuestionType.mul, 3);
                break;
            case 3:
                qsAddSubMulDiv(QuestionType.div, 3);
                break;
            case 4:
                qsTrigonometry(4);
                break;
            case 5:
                qsSimplification(5, 2);
                break;
        }

    }

    Question(QuestionDifficulty qD, QuestionType...qT){
        if(qD == QuestionDifficulty.easy){
            setEasy(qT);
        }else if(qD == QuestionDifficulty.medium){
            setMedium(qT);
        }else{
            setHard(qT);
        }
    }

    private void setEasy(QuestionType...qT){
        int rand = (int)(Math.random()*100)% qT.length;
        QuestionType qType = qT[rand];

        switch (qType){
            case add:
            case sub:
            case div:
                qsAddSubMulDiv(qType, 2);
                break;
            case mul:
                qsAddSubMulDiv(qType, 1);
                break;
            case trigonometry:
                qsTrigonometry(0);
                break;
            case simplification:
                qsSimplification(3, 1);
                break;
            default:
                qsAddSubMulDiv(QuestionType.add, 2);
        }
    }

    private void setMedium(QuestionType...qT){
        int rand = (int)(Math.random()*100)% qT.length;
        QuestionType qType = qT[rand];

        switch (qType){
            case add:
            case sub:
            case div:
                qsAddSubMulDiv(qType, 3);
                break;
            case mul:
                qsAddSubMulDiv(qType, 2);
                break;
            case trigonometry:
                qsTrigonometry(5);
                break;
            case simplification:
                qsSimplification(4, 2);
                break;
            default:
                qsAddSubMulDiv(QuestionType.add, 2);
        }
    }

    private void setHard(QuestionType...qT){
        int rand = (int)(Math.random()*100)% qT.length;
        QuestionType qType = qT[rand];

        switch (qType){
            case add:
            case sub:
            case div:
                qsAddSubMulDiv(qType, 4);
                break;
            case mul:
                qsAddSubMulDiv(qType, 3);
                break;
            case trigonometry:
                qsTrigonometry(10);
                break;
            case simplification:
                qsSimplification(5, 2);
                break;
            default:
                qsAddSubMulDiv(QuestionType.add, 2);
        }
    }

    private void qsAddSubMulDiv(QuestionType qt, int numberMaxDigits){
        int mulResultFactor, divResultFactor, opFactor, resultFactor;
        int num1, num2;
        long answer = 0;
        char operator = ' ';
        opFactor = (int)(Math.pow(10, numberMaxDigits));
        mulResultFactor = opFactor*opFactor;
        divResultFactor = opFactor/opFactor*10;

        resultFactor = opFactor;

        String ans;
        num1 = (int)(Math.random()*opFactor);
        num2 = (int)(Math.random()*opFactor);

        if(qt == QuestionType.add) {
            operator = '+';
            answer = num1 + num2;
        }else if(qt == QuestionType.sub) {
            operator = '-';
            answer = num1 - num2;
        }else if(qt == QuestionType.mul){
            resultFactor = mulResultFactor;
            operator = '*';
            answer = (long)num1*(long)num2;
        }else if(qt == QuestionType.div){
            resultFactor = divResultFactor;
            operator = '/';
            int large, small;
            large = (num1>num2)?num1:num2;
            small = (num1<num2)?num1:num2;
            num1 = large;
            num2 = small;
            if(num2 == 0) num2++;
            num1 = num1 - num1%num2;
            answer = num1/num2;
        }

        question = num1 + " " + operator + " " + num2 + " = ?";

        options = new String[optionCount];
        correctAnswer = ((int)(Math.random()*10))%optionCount;
        options[correctAnswer] = answer + "";



        for(int i = 0; i < correctAnswer;){
            options[i] = (int)(Math.random()*resultFactor) + "";
            if(!options[i].equals(options[correctAnswer]))
                i++;
        }

        for(int i = correctAnswer+1; i < optionCount;){
            options[i] = (int)(Math.random()*resultFactor) + "";
            if(!options[i].equals(options[correctAnswer]))
                i++;
        }
    }

    private void qsSimplification(int numbersCount, int numberMaxDigits){
        ArrayList<Long>numbers = new ArrayList<Long>();
        ArrayList<Character>operators = new ArrayList<>();
        long answer;
        long resultFactor = (long)Math.pow(10, numberMaxDigits);

        for(int i = 0; i < numbersCount; i++){
            numbers.add((long)(Math.random()*resultFactor));
        }

        for(int i = 0; i < numbersCount - 1; i++){
            int temp = (int)(Math.random()*10)%3;
            switch (temp){
                case 0:
                    operators.add('+');
                    break;
                case 1:
                    operators.add('-');
                    break;
                case 2:
                    operators.add('*');
                    break;
            }

        }
        question = "";

        for(int i = 0; i < numbersCount - 1; i++){
            question += numbers.get(i) + " " + operators.get(i) + " ";
        }

        question += numbers.get(numbersCount - 1) + " = ?";

        for(int i = 0; i < operators.size();){
            if(operators.get(i).toString().equals("*")){
                long res = numbers.get(i)*numbers.get(i+1);
                numbers.set(i, res);
                numbers.remove(i+1);
                operators.remove(i);
            }else{
                i++;
            }
        }

        for(int i = 0; i < operators.size();){
            long res = 0;
            if(operators.get(i).toString().equals("+")){
                res = numbers.get(i)+numbers.get(i+1);
            }else if(operators.get(i).toString().equals("-")){
                res = numbers.get(i)-numbers.get(i+1);
            }
            numbers.set(i, res);
            numbers.remove(i+1);
            operators.remove(i);
        }

        answer = numbers.get(0);

        options = new String[optionCount];
        correctAnswer = ((int)(Math.random()*10))%optionCount;
        options[correctAnswer] = answer + "";



        for(int i = 0; i < correctAnswer;){
            options[i] = (int)(Math.random()*(resultFactor*10)) + "";
            if(!options[i].equals(options[correctAnswer]))
                i++;
        }

        for(int i = correctAnswer+1; i < optionCount;){
            options[i] = (int)(Math.random()*(resultFactor*10)) + "";
            if(!options[i].equals(options[correctAnswer]))
                i++;
        }
    }

    private void qsTrigonometry(int maxFactor90){
        int angs[] = {30, 45, 60, 90};
        int angle;
        double answer = 0;
        if(maxFactor90 == 0){
            angle = angs[(int)(Math.random()*10)%4];
        }else {
            angle = 90 * ((int)(Math.random() * 10)%maxFactor90) + angs[(int) (Math.random() * 10) % 4];
        }

        int opt;
        opt = (int)(Math.random()*10)%3;
        switch (opt){
            case 0:
                question = "Sin(" + angle + ") = ?";
                answer = Math.sin(Math.toRadians(angle));
                break;
            case 1:
                question = "Cos(" + angle + ") = ?";
                answer = Math.cos(Math.toRadians(angle));
                break;
            case 2:
                if(Math.cos(Math.toRadians(angle)) != 0){
                    question = "Tan(" + angle + ") = ?";
                    answer = Math.tan(Math.toRadians(angle));
                }else{
                    question = "Sin(" + angle + ") = ?";
                    answer = Math.sin(Math.toRadians(angle));
                }
                break;
        }


        options = new String[optionCount];
        correctAnswer = ((int)(Math.random()*10))%optionCount;
        options[correctAnswer] = String.format("%.3f", answer);

        for(int i = 0; i < correctAnswer;){
            options[i] = String.format("%.3f", Math.random());
            if(!options[i].equals(options[correctAnswer]))
                i++;
        }

        for(int i = correctAnswer+1; i < optionCount;){
            options[i] = String.format("%.3f", Math.random());
            if(!options[i].equals(options[correctAnswer]))
                i++;
        }

    }

    public String toString(){
        String temp =  question + "\n";
        for(int i = 0; i < optionCount; i++){
            temp += (char)(i+65) + ") " + options[i] + ". ";
        }

        temp += "\n" + "Correct Answer : " + options[correctAnswer];
        return temp;
    }

    public static void main(String [] args){
        Question []a = new Question[10];
        QuestionType []qT = new QuestionType[6];
        qT[0] = QuestionType.add;
        qT[1] = QuestionType.sub;
        qT[2] = QuestionType.mul;
        qT[3] = QuestionType.div;
        qT[4] = QuestionType.simplification;
        qT[5] = QuestionType.trigonometry;

        for(int i = 0; i < 10; i++){
            a[i] = new Question(QuestionDifficulty.hard, qT);
            System.out.println((i+1) + ". " + a[i].toString());

        }
    }
}
