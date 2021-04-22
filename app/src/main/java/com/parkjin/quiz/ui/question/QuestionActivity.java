package com.parkjin.quiz.ui.question;

import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.parkjin.quiz.R;
import com.parkjin.quiz.base.BaseActivity;
import com.parkjin.quiz.databinding.ActivityQuestionBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionActivity extends BaseActivity<ActivityQuestionBinding, QuestionViewModel> {

    @Override
    protected QuestionViewModel viewModel() {
        return new ViewModelProvider(this).get(QuestionViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_question;
    }

    @Override
    public void observeEvent() {
        viewModel.onSuccessEvent.observe(this, unit -> {
            this.finish();
        });

        viewModel.onErrorEvent.observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });
    }
}