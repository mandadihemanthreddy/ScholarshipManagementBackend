package com.example.ScholarshipApplyApplication.dto;

public class Captcha {
    private int num1;
    private int num2;
    private int answer;
    private String question;

    public Captcha(String question, int num1, int num2, int answer) {
        this.question = question;
        this.num1 = num1;
        this.num2 = num2;
        this.answer = answer;
    }

    // Getters and setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNum1() { return num1; }
    public void setNum1(int num1) { this.num1 = num1; }

    public int getNum2() { return num2; }
    public void setNum2(int num2) { this.num2 = num2; }

    public int getAnswer() { return answer; }
    public void setAnswer(int answer) { this.answer = answer; }
}
