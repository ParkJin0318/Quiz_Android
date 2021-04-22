package com.parkjin.quiz.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.parkjin.quiz.database.dao.QuizDao;
import com.parkjin.quiz.database.entity.QuizEntity;

@Database(
        version = 1,
        entities = {QuizEntity.class},
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuizDao quizDao();
}