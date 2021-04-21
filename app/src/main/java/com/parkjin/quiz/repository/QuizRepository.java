package com.parkjin.quiz.repository;

import com.parkjin.quiz.model.Quiz;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface QuizRepository {
    Completable insertQuiz(Quiz quiz);

    Completable insertQuiz(List<Quiz> quizzes);

    Single<List<Quiz>> getAllQuiz();

    Completable deleteQuiz(Quiz quiz);

    Completable deleteAllQuiz();
}