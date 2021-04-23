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
                quizEntity.question1,
                quizEntity.question2,
                quizEntity.question3,
                quizEntity.question4,
                quizEntity.answer
        );
    }

    /**
     * Model -> Entity
     */
    public QuizEntity toEntity(Quiz quiz) {
        return new QuizEntity(
                quiz.getIdx(),
                quiz.getTitle(),
                quiz.getScore(),
                toValue(quiz.getType()),
                quiz.getCreateAt(),
                quiz.getQuestion1(),
                quiz.getQuestion2(),
                quiz.getQuestion3(),
                quiz.getQuestion4(),
                quiz.getAnswer()
        );
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
