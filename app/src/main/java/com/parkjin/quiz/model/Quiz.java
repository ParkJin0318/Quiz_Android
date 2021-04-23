package com.parkjin.quiz.model;

public class Quiz {
    private final int idx;
    private final String title;
    private final int score;
    private final QuizType type;
    private final String createAt;

    private final String question1;
    private final String question2;
    private final String question3;
    private final String question4;
    private final int answer;

    public Quiz(
            int idx, String title, int score, QuizType type, String createAt,
            String question1, String question2, String question3, String question4, int answer
    ) {
        this.idx = idx;
        this.title = title;
        this.score = score;
        this.type = type;
        this.createAt = createAt;

        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.answer = answer;
    }

    public int getIdx() {
        return idx;
    }

    public String getTitle() {
        return title;
    }

    public int getScore() {
        return score;
    }

    public QuizType getType() {
        return type;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getQuestion1() {
        return question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public String getQuestion4() {
        return question4;
    }

    public int getAnswer() {
        return answer;
    }
}
