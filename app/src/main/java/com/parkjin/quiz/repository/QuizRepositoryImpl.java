package com.parkjin.quiz.repository;

import com.parkjin.quiz.database.dao.QuizDao;
import com.parkjin.quiz.mapper.QuizMapper;
import com.parkjin.quiz.model.Quiz;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class QuizRepositoryImpl implements QuizRepository {

    private final QuizDao dao;
    private final QuizMapper mapper = new QuizMapper();

    @Inject
    public QuizRepositoryImpl(QuizDao dao) {
        this.dao = dao;
    }

    @Override
    public Completable insertQuiz(Quiz quiz) {
        return dao.insertQuiz(mapper.toEntity(quiz));
    }

    @Override
    public Completable insertQuiz(List<Quiz> quizzes) {
        return dao.insertQuiz(mapper.toEntityList(quizzes));
    }

    @Override
    public Single<List<Quiz>> getAllQuiz() {
        return dao.getAllQuiz().map(mapper::toModelList);
    }

    @Override
    public Single<Quiz> getQuiz(int idx) {
        return dao.getQuiz(idx).map(mapper::toModel);
    }

    @Override
    public Completable deleteQuiz(Quiz quiz) {
        return dao.deleteQuiz(mapper.toEntity(quiz));
    }

    @Override
    public Completable deleteAllQuiz() {
        return dao.deleteAllQuiz();
    }
}
