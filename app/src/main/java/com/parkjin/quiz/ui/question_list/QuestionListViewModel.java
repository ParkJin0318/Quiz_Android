package com.parkjin.quiz.ui.question_list;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.parkjin.quiz.base.BaseViewModel;
import com.parkjin.quiz.model.Quiz;
import com.parkjin.quiz.repository.QuizRepository;
import com.parkjin.quiz.util.SingleLiveEvent;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QuestionListViewModel extends BaseViewModel {

    public MutableLiveData<String> passwordText = new MutableLiveData<>("");
    public MutableLiveData<Boolean> isVisible = new MutableLiveData<>(false);

    public SingleLiveEvent<String> onErrorEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> onPasswordError = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> onAddEvent = new SingleLiveEvent<>();

    private final QuizRepository repository;

    public final QuestionItemAdapter quizAdapter = new QuestionItemAdapter();
    public final ArrayList<Quiz> quizList = new ArrayList<>();

    @ViewModelInject
    public QuestionListViewModel(QuizRepository repository) {
        this.repository = repository;
        quizAdapter.setQuizList(quizList);
    }

    public void getAllQuiz() {
        addDisposable(repository.getAllQuiz()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    quizList.clear();
                    quizList.addAll(data);
                    quizAdapter.notifyDataSetChanged();
                }, error -> {
                    onErrorEvent.setValue(error.getMessage());
                }));
    }

    public void onClickStart() {
        if (passwordText.getValue().equals("1234")) {
            isVisible.setValue(true);
        } else {
            onPasswordError.call();
        }
    }

    public void onClickQuestion() {
        onAddEvent.call();
    }
}
