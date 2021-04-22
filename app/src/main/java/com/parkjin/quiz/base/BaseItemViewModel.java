package com.parkjin.quiz.base;

import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

public class BaseItemViewModel<N> extends ViewModel {

    private WeakReference<N> navigator;

    public N getNavigator() {
        return navigator.get();
    }

    public void setNavigator(N navigator) {
        this.navigator = new WeakReference<N>(navigator);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        navigator.clear();
    }
}
