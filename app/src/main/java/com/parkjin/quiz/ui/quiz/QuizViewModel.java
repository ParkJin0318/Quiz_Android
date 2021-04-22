package com.parkjin.quiz.ui.quiz;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.parkjin.quiz.base.BaseViewModel;
import com.parkjin.quiz.model.Quiz;
import com.parkjin.quiz.model.QuizType;
import com.parkjin.quiz.repository.QuizRepository;
import com.parkjin.quiz.util.SingleLiveEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QuizViewModel extends BaseViewModel {

    public MutableLiveData<String> score = new MutableLiveData<>("");
    public MutableLiveData<String> title = new MutableLiveData<>("");

    public MutableLiveData<String> inputAnswer = new MutableLiveData<>("");
    public MutableLiveData<String> quiz1 = new MutableLiveData<>("");
    public MutableLiveData<String> quiz2 = new MutableLiveData<>("");
    public MutableLiveData<String> quiz3 = new MutableLiveData<>("");
    public MutableLiveData<String> quiz4 = new MutableLiveData<>("");

    public MutableLiveData<Boolean> isQuizType = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isLast = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> isEasyMode = new MutableLiveData<>(false);

    public MutableLiveData<Boolean> quiz1Checked = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> quiz2Checked = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> quiz3Checked = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> quiz4Checked = new MutableLiveData<>(false);
    public MutableLiveData<Integer> answer = new MutableLiveData<>();

    public MutableLiveData<String> totalScore = new MutableLiveData<>("");
    public SingleLiveEvent<String> onRightEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<String> onErrorEvent = new SingleLiveEvent<>();

    private int idx = 1;
    private int currentScore = 0;
    private Quiz quiz;

    private final QuizRepository repository;

    @ViewModelInject
    public QuizViewModel(QuizRepository repository) {
        this.repository = repository;
    }

    public void getQuiz() {
        addDisposable(repository.getQuiz(idx)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setQuizInfo, error -> {
                isLast.setValue(true);
            })
        );
    }

    private void setQuizInfo(Quiz quiz) {
        this.quiz = quiz;

        if (!isEasyMode.getValue() && quiz.getType() == QuizType.IMAGE) {
            idx ++;
            getQuiz();
            return;
        }

        title.postValue(quiz.getTitle());
        score.postValue(quiz.getScore() + "점");
        quiz1.postValue(quiz.getQuiz1());
        quiz2.postValue(quiz.getQuiz2());
        quiz3.postValue(quiz.getQuiz3());
        quiz4.postValue(quiz.getQuiz4());
        isQuizType.postValue(quiz.getType() == QuizType.IMAGE);
    }

    public void onClickQuiz(int quiz) {
        answer.setValue(quiz);
        quiz1Checked.setValue(false);
        quiz2Checked.setValue(false);
        quiz3Checked.setValue(false);
        quiz4Checked.setValue(false);

        switch (quiz) {
            case 1:
                quiz1Checked.setValue(true);
                break;
            case 2:
                quiz2Checked.setValue(true);
                break;
            case 3:
                quiz3Checked.setValue(true);
                break;
            case 4:
                quiz4Checked.setValue(true);
                break;
        }
    }

    public void submitQuiz() {
        if (isEasyMode.getValue()) {

            if (answer.getValue() == quiz.getAnswer()) {
                idx++;
                getQuiz();

                onClickQuiz(0);
                currentScore += quiz.getScore();
                totalScore.setValue("총 " + currentScore + "점입니다");
            } else {
                onErrorEvent.setValue("틀렸습니다");
            }
        } else {

            String answer = "";

            switch (quiz.getAnswer()) {
                case 1:
                    answer = quiz.getQuiz1();
                    break;
                case 2:
                    answer = quiz.getQuiz2();
                    break;
                case 3:
                    answer = quiz.getQuiz3();
                    break;
                case 4:
                    answer = quiz.getQuiz4();
                    break;
            }

            if (inputAnswer.getValue().equals(answer)) {
                idx++;
                getQuiz();

                currentScore += quiz.getScore();
                totalScore.setValue("총 " + currentScore + "점입니다");
            } else {
                onErrorEvent.setValue("틀렸습니다");
            }
        }
    }
}
