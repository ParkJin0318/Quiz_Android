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
        return new Quiz(quizEntity.title, quizEntity.date, toType(quizEntity.type));
    }

    /**
     * Model -> Entity
     */
    public QuizEntity toEntity(Quiz quiz) {
        QuizEntity entity = new QuizEntity();
        entity.title = quiz.getTitle();
        entity.type = toValue(quiz.getType());
        entity.date = quiz.getDate();
        return entity;
    }

    /**
     * EntityList -> ModelList
     */
    public List<Quiz> toModelList(List<QuizEntity> entityList) {
        ArrayList<Quiz> modelList = new ArrayList<>();

        for (QuizEntity entity: entityList) {
            modelList.add(toModel(entity));
        }
        return modelList;
    }

    /**
     * ModelList -> EntityList
     */
    public List<QuizEntity> toEntityList(List<Quiz> modelList) {
        ArrayList<QuizEntity> entityList = new ArrayList<>();

        for (Quiz model: modelList) {
            entityList.add(toEntity(model));
        }
        return entityList;
    }

    /**
     * Enum -> Int
     */
    private int toValue(QuizType type) {
        if (type == QuizType.TEXT) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Int -> Enum
     */
    private QuizType toType(int value) {
        if (value == 0) {
            return QuizType.TEXT;
        } else {
            return QuizType.IMAGE;
        }
    }
}
