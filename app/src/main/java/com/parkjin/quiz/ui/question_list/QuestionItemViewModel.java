package com.parkjin.quiz.ui.question_list;

import androidx.lifecycle.MutableLiveData;

import com.parkjin.quiz.base.BaseItemViewModel;
import com.parkjin.quiz.model.Quiz;
import com.parkjin.quiz.model.QuizType;

public class QuestionItemViewModel extends BaseItemViewModel<QuestionItemNavigator> {

    public Quiz quiz;

    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> createAt = new MutableLiveData<>();
    public MutableLiveData<String> type = new MutableLiveData<>();

    public void bind(Quiz quiz) {
        this.quiz = quiz;
        title.setValue(quiz.getTitle());
        createAt.setValue(quiz.getCreateAt());
        type.setValue(quiz.getType() == QuizType.TEXT ? "TEXT" : "IMAGE");
    }

    public void onClickItem() {
        getNavigator().onClickItem(quiz);
    }
}