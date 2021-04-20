package com.parkjin.quiz;

import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.MutableLiveData;

import com.parkjin.quiz.base.BindingActivity;
import com.parkjin.quiz.databinding.ActivityMainBinding;

public class MainActivity extends BindingActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    public MutableLiveData<String> modeText = new MutableLiveData<>();
    public MutableLiveData<Boolean> easyRadio = new MutableLiveData<>();
    public MutableLiveData<Boolean> hardRadio = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modeText.setValue(getString(R.string.message_select_mode));
    }

    @Override
    public void observeLiveData() {
        easyRadio.observe(this, isChecked -> {
            if (isChecked) {
                modeText.setValue(getString(R.string.text_easy));
            }
        });

        hardRadio.observe(this, isChecked -> {
            if (isChecked) {
                modeText.setValue(getString(R.string.text_hard));
            }
        });
    }

    public void onClickSettings() {
        Intent intent = new Intent(this, QuestionListActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onClickEasy() {
        easyRadio.setValue(true);
        hardRadio.setValue(false);
    }

    public void onClickHard() {
        easyRadio.setValue(false);
        hardRadio.setValue(true);
    }

    public void onClickStart() {

    }
}