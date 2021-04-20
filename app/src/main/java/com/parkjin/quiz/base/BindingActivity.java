package com.parkjin.quiz.base;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.parkjin.quiz.BR;

public abstract class BindingActivity<VB extends ViewDataBinding> extends AppCompatActivity {

    protected VB binding;

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void observeLiveData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setBinding();
        this.observeLiveData();
    }

    private void setBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        binding.setVariable(BR.view, this);
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }
}
