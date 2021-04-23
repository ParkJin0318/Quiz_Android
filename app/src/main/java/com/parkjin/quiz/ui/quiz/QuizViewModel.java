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

    private int idx = 1;
    private int currentScore = 0;
    private Quiz quiz;

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

    private final QuizRepository repository;

    @ViewModelInject
    public QuizViewModel(QuizRepository repository) {
        this.repository = repository;
    }

    public void getQuiz() {
        addDisposable(repository.getQuiz(idx)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::setQuizInfo,
                    error -> isLast.setValue(true)
            ));
    }

    private void setQuizInfo(Quiz quiz) {
        if (!isEasyMode.getValue() && quiz.getType() == QuizType.IMAGE) {
            idx ++;
            getQuiz();
            return;
        }
        this.quiz = quiz;
        title.postValue(quiz.getTitle());
        score.postValue(quiz.getScore() + "점");

        quiz1.postValue(quiz.getQuestion1());
        quiz2.postValue(quiz.getQuestion2());
        quiz3.postValue(quiz.getQuestion3());
        quiz4.postValue(quiz.getQuestion4());

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

    private String getAnswer(int answer) {
        switch (answer) {
            case 1:
                return quiz.getQuestion1();
            case 2:
                return quiz.getQuestion2();
            case 3:
                return quiz.getQuestion3();
            case 4:
                return quiz.getQuestion4();
        }
        return "";
    }

    private String getInput() {
        if (isEasyMode.getValue()) {
            return getAnswer(this.answer.getValue());
        }
        return this.inputAnswer.getValue();
    }

    public void submitQuiz() {
        String answer = getAnswer(this.quiz.getAnswer());
        String input = getInput();

        if (answer.equals(input)) {
            onRightEvent.setValue("정답!" + quiz.getScore() + "점 획득");
            currentScore += quiz.getScore();
        } else {
            onErrorEvent.setValue("오답!");
        }
        idx += 1;
        getQuiz();

        onClickQuiz(0);
        inputAnswer.setValue("");

        totalScore.setValue("총 " + currentScore + "점입니다");
    }
}
