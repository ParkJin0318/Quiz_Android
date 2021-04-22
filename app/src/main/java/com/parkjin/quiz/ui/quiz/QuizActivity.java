package com.parkjin.quiz.ui.quiz;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.parkjin.quiz.R;
import com.parkjin.quiz.base.BaseActivity;
import com.parkjin.quiz.databinding.ActivityQuizBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuizActivity extends BaseActivity<ActivityQuizBinding, QuizViewModel> {

    @Override
    protected QuizViewModel viewModel() {
        return new ViewModelProvider(this).get(QuizViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_quiz;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String mode = getIntent().getStringExtra("mode");
        viewModel.isEasyMode.setValue(mode.equals("Easy"));
        viewModel.getQuiz();
    }

    @Override
    public void observeEvent() {
        viewModel.onRightEvent.observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        viewModel.onErrorEvent.observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });
    }
}