package com.parkjin.quiz.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quiz_table")
public class QuizEntity {
    @PrimaryKey(autoGenerate = true)
    public int idx;
    public String title;
    public int score;
    public int type;
    public String createAt;

    public String question1;
    public String question2;
    public String question3;
    public String question4;
    public int answer;

    public QuizEntity(int idx, String title, int score, int type, String createAt,
                      String question1, String question2, String question3, String question4, int answer) {
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
}