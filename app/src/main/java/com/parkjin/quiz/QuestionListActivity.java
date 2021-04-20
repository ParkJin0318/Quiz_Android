package com.parkjin.quiz;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;

import com.parkjin.quiz.base.BindingActivity;
import com.parkjin.quiz.databinding.ActivityQuestionListBinding;

public class QuestionListActivity extends BindingActivity<ActivityQuestionListBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_question_list;
    }

    public MutableLiveData<String> passwordText = new MutableLiveData<>("");
    public MutableLiveData<Boolean> isVisible = new MutableLiveData<>(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void observeLiveData() { }

    public void onClickStart() {
        if (passwordText.getValue().equals("1234")) {
            isVisible.setValue(true);
        } else {
            this.finish();
        }
    }
}