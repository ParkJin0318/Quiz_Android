package com.parkjin.quiz.model;

public class Quiz {
    private String title;
    private String date;
    private QuizType type;

    public Quiz(String title, String date, QuizType type) {
        this.title = title;
        this.date = date;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public QuizType getType() {
        return type;
    }

    public void setType(QuizType type) {
        this.type = type;
    }
}
