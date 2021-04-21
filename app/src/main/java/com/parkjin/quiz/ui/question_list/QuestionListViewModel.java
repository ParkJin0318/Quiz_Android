package com.parkjin.quiz.ui.question_list;

import androidx.lifecycle.MutableLiveData;

import com.parkjin.quiz.base.BaseViewModel;

public class QuestionListViewModel extends BaseViewModel {

    public MutableLiveData<String> passwordText = new MutableLiveData<>("");
    public MutableLiveData<Boolean> isVisible = new MutableLiveData<>(false);

    public void onClickStart() {
        if (passwordText.getValue().equals("1234")) {
            isVisible.setValue(true);
        }
    }
}
