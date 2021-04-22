package com.parkjin.quiz.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.parkjin.quiz.R;
import com.parkjin.quiz.base.BaseActivity;
import com.parkjin.quiz.databinding.ActivityMainBinding;
import com.parkjin.quiz.ui.question_list.QuestionListActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected MainViewModel viewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.modeText.setValue(getString(R.string.message_select_mode));
    }

    @Override
    public void observeEvent() {
        viewModel.easyRadio.observe(this, isChecked -> {
            if (isChecked) {
                viewModel.modeText.setValue(getString(R.string.text_easy));
            }
        });

        viewModel.hardRadio.observe(this, isChecked -> {
            if (isChecked) {
                viewModel.modeText.setValue(getString(R.string.text_hard));
            }
        });

        viewModel.onSettingEvent.observe(this, empty -> {
            Intent intent = new Intent(this, QuestionListActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}