package com.parkjin.quiz.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.parkjin.quiz.BR;

public abstract class BaseActivity<VB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity {

    protected VB binding;
    protected VM viewModel;

    protected abstract VM viewModel();

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void observeEvent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setBinding();
        this.observeEvent();
    }

    private void setBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        viewModel = getViewModel();
        binding.setVariable(BR.viewModel, viewModel);
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
    }

    private VM getViewModel() {
        if (viewModel != null) {
            return viewModel;
        } else {
            return viewModel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }
}
