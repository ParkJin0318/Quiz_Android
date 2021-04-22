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

    public String quiz1;
    public String quiz2;
    public String quiz3;
    public String quiz4;
    public int answer;
}