package com.parkjin.quiz.ui.main;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import com.parkjin.quiz.base.BaseViewModel;
import com.parkjin.quiz.util.SingleLiveEvent;

public class MainViewModel extends BaseViewModel {

    public MutableLiveData<String> modeText = new MutableLiveData<>("");

    public MediatorLiveData<Boolean> isEnabled = new MediatorLiveData<>();
    public MutableLiveData<Boolean> easyRadio = new MutableLiveData<>();
    public MutableLiveData<Boolean> hardRadio = new MutableLiveData<>();

    public SingleLiveEvent<Void> onSettingEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> onStartEvent = new SingleLiveEvent<>();

    public MainViewModel() {
        isEnabled.addSource(easyRadio, easy -> {
            isEnabled.setValue(easy != null && hardRadio.getValue() != null);
        });
        isEnabled.addSource(hardRadio, hard -> {
            isEnabled.setValue(hard != null && easyRadio.getValue() != null);
        });
    }

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
        onStartEvent.call();
    }
}