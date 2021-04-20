package com.parkjin.quiz;

import android.os.Bundle;
import androidx.lifecycle.MutableLiveData;
import com.parkjin.quiz.databinding.ActivityMainBinding;

public class MainActivity extends BindingActivity<ActivityMainBinding> {

    @Override
    int getLayoutRes() {
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
    void observeLiveData() {
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