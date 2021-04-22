package com.parkjin.quiz.ui.question_list;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.parkjin.quiz.R;
import com.parkjin.quiz.base.BaseActivity;
import com.parkjin.quiz.databinding.ActivityQuestionListBinding;
import com.parkjin.quiz.ui.question.QuestionActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionListActivity extends BaseActivity<ActivityQuestionListBinding, QuestionListViewModel> {

    @Override
    protected QuestionListViewModel viewModel() {
        return new ViewModelProvider(this).get(QuestionListViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_question_list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getAllQuiz();
    }

    @Override
    public void observeEvent() {
        viewModel.onPasswordError.observe(this, unit -> {
            this.finish();
        });

        viewModel.onAddEvent.observe(this, unit -> {
            Intent intent = new Intent(this, QuestionActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}