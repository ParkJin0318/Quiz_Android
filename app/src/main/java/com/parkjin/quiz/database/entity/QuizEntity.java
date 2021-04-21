package com.parkjin.quiz.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quiz_table")
public class QuizEntity {
    @PrimaryKey
    public String title;

    public String date;

    public int type;
}