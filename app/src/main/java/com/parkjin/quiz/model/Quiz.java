package com.parkjin.quiz.model;

public class Quiz {
    private final int idx;
    private final String title;
    private final int score;
    private final QuizType type;
    private final String createAt;

    private final String quiz1;
    private final String quiz2;
    private final String quiz3;
    private final String quiz4;
    private final int answer;

    public Quiz(
            int idx, String title, int score, QuizType type, String createAt,
            String quiz1, String quiz2, String quiz3, String quiz4, int answer
    ) {
        this.idx = idx;
        this.title = title;
        this.score = score;
        this.type = type;
        this.createAt = createAt;

        this.quiz1 = quiz1;
        this.quiz2 = quiz2;
        this.quiz3 = quiz3;
        this.quiz4 = quiz4;
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

    public String getQuiz1() {
        return quiz1;
    }

    public String getQuiz2() {
        return quiz2;
    }

    public String getQuiz3() {
        return quiz3;
    }

    public String getQuiz4() {
        return quiz4;
    }

    public int getAnswer() {
        return answer;
    }
}
