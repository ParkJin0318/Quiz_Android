package com.parkjin.quiz.ui.question_list;

import androidx.lifecycle.MutableLiveData;

import com.parkjin.quiz.base.BaseViewModel;
import com.parkjin.quiz.model.Quiz;
import com.parkjin.quiz.model.QuizType;

public class QuestionItemViewModel extends BaseViewModel {

    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> createAt = new MutableLiveData<>();
    public MutableLiveData<String> type = new MutableLiveData<>();

    public void bind(Quiz quiz) {
        title.setValue(quiz.getTitle());
        createAt.setValue(quiz.getCreateAt());
        type.setValue(quiz.getType() == QuizType.TEXT ? "TEXT" : "IMAGE");
    }
}