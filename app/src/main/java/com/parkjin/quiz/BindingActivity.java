package com.parkjin.quiz;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

abstract class BindingActivity<VB extends ViewDataBinding> extends AppCompatActivity {

    protected VB binding;

    @LayoutRes
    abstract int getLayoutRes();

    abstract void observeLiveData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setBinding();
        this.observeLiveData();
    }

    private void setBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        binding.setVariable(BR.activity, this);
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }
}
