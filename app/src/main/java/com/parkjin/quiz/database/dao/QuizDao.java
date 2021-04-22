package com.parkjin.quiz.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.parkjin.quiz.database.entity.QuizEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertQuiz(QuizEntity quizEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertQuiz(List<QuizEntity> quizEntities);

    @Query("SELECT * FROM quiz_table")
    Single<List<QuizEntity>> getAllQuiz();

    @Delete
    Completable deleteQuiz(QuizEntity quizEntity);

    @Query("DELETE FROM quiz_table")
    Completable deleteAllQuiz();
}