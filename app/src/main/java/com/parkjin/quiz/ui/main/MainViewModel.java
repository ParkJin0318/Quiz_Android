package com.parkjin.quiz.ui.main;

import androidx.lifecycle.MutableLiveData;
import com.parkjin.quiz.base.BaseViewModel;
import com.parkjin.quiz.util.SingleLiveEvent;

public class MainViewModel extends BaseViewModel {

    public MutableLiveData<String> modeText = new MutableLiveData<>("");
    public MutableLiveData<Boolean> easyRadio = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> hardRadio = new MutableLiveData<>(false);

    public SingleLiveEvent<Void> onSettingEvent = new SingleLiveEvent<>();

    public void onClickSettings() {
        onSettingEvent.call();
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