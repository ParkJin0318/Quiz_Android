package com.parkjin.quiz.base;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {

    private final CompositeDisposable disposables = new CompositeDisposable();

    public void addDisposable(Disposable disposable) {
        this.disposables.add(disposable);
    }

    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }
}