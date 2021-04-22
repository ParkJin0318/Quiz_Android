package com.parkjin.quiz.mapper;

import com.parkjin.quiz.database.entity.QuizEntity;
import com.parkjin.quiz.model.Quiz;
import com.parkjin.quiz.model.QuizType;

import java.util.ArrayList;
import java.util.List;

public class QuizMapper {
    /**
     * Entity -> Model
     */
    public Quiz toModel(QuizEntity quizEntity) {
        return new Quiz(
                quizEntity.idx,
                quizEntity.title,
                quizEntity.score,
                toType(quizEntity.type),
                quizEntity.createAt,
                quizEntity.quiz1,
                quizEntity.quiz2,
                quizEntity.quiz3,
                quizEntity.quiz4,
                quizEntity.answer
        );
    }

    /**
     * Model -> Entity
     */
    public QuizEntity toEntity(Quiz quiz) {
        QuizEntity entity = new QuizEntity();
        entity.idx = quiz.getIdx();
        entity.title = quiz.getTitle();
        entity.score = quiz.getScore();
        entity.type = toValue(quiz.getType());
        entity.createAt = quiz.getCreateAt();
        entity.quiz1 = quiz.getQuiz1();
        entity.quiz2 = quiz.getQuiz2();
        entity.quiz3 = quiz.getQuiz3();
        entity.quiz4 = quiz.getQuiz4();
        entity.answer = quiz.getAnswer();
        return entity;
    }

    /**
     * EntityList -> ModelList
     */
    public List<Quiz> toModelList(List<QuizEntity> entityList) {
        ArrayList<Quiz> modelList = new ArrayList<>();

        for (QuizEntity entity : entityList) {
            modelList.add(toModel(entity));
        }
        return modelList;
    }

    /**
     * ModelList -> EntityList
     */
    public List<QuizEntity> toEntityList(List<Quiz> modelList) {
        ArrayList<QuizEntity> entityList = new ArrayList<>();

        for (Quiz model : modelList) {
            entityList.add(toEntity(model));
        }
        return entityList;
    }

    /**
     * Enum -> Int
     */
    public int toValue(QuizType type) {
        if (type == QuizType.TEXT) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Int -> Enum
     */
    public QuizType toType(int value) {
        if (value == 0) {
            return QuizType.TEXT;
        } else {
            return QuizType.IMAGE;
        }
    }

    /**
     * Boolean -> Enum
     */
    public QuizType toType(boolean value) {
        if (!value) {
            return QuizType.TEXT;
        } else {
            return QuizType.IMAGE;
        }
    }
}
