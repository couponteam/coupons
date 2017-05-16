package com.masou.coupon.common.struct;

/**
 * Created by july on 2016/11/19.
 */
public class QA {

    private String question;
    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public QA(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public QA() {
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
