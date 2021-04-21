package com.parkjin.quiz.ui.question_list;

import androidx.lifecycle.ViewModelProvider;
import com.parkjin.quiz.R;
import com.parkjin.quiz.base.BindingActivity;
import com.parkjin.quiz.databinding.ActivityQuestionListBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionListActivity extends BindingActivity<ActivityQuestionListBinding, QuestionListViewModel> {

    @Override
    protected QuestionListViewModel viewModel() {
        return new ViewModelProvider(this).get(QuestionListViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_question_list;
    }

    @Override
    public void observeEvent() { }
}