package com.parkjin.quiz.ui.question;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.parkjin.quiz.base.BaseViewModel;
import com.parkjin.quiz.mapper.DateMapper;
import com.parkjin.quiz.mapper.QuizMapper;
import com.parkjin.quiz.model.Quiz;
import com.parkjin.quiz.model.QuizType;
import com.parkjin.quiz.repository.QuizRepository;
import com.parkjin.quiz.util.SingleLiveEvent;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QuestionViewModel extends BaseViewModel {

    public MutableLiveData<String> title = new MutableLiveData<>("");
    public MutableLiveData<String> score = new MutableLiveData<>("");
    public MutableLiveData<Boolean> isQuizType = new MutableLiveData<>(false);

    public MutableLiveData<String> quiz1 = new MutableLiveData<>("");
    public MutableLiveData<String> quiz2 = new MutableLiveData<>("");
    public MutableLiveData<String> quiz3 = new MutableLiveData<>("");
    public MutableLiveData<String> quiz4 = new MutableLiveData<>("");

    public MutableLiveData<Boolean> quiz1Checked = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> quiz2Checked = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> quiz3Checked = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> quiz4Checked = new MutableLiveData<>(false);
    public MutableLiveData<Integer> answer = new MutableLiveData<>();

    public SingleLiveEvent<Void> onSuccessEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<String> onErrorEvent = new SingleLiveEvent<>();

    private final QuizRepository repository;
    private final QuizMapper quizMapper = new QuizMapper();
    private final DateMapper dateMapper = new DateMapper();

    @ViewModelInject
    public QuestionViewModel(QuizRepository repository) {
        this.repository = repository;
    }

    public void getQuiz(int idx) {
        addDisposable(repository.getQuiz(idx)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::setQuizInfo,
                        error -> onErrorEvent.setValue(error.getMessage())
                )
        );
    }

    private void setQuizInfo(Quiz quiz) {
        title.setValue(quiz.getTitle());
        score.setValue(String.valueOf(quiz.getScore()));
        isQuizType.setValue(quiz.getType() == QuizType.IMAGE);

        quiz1.setValue(quiz.getQuiz1());
        quiz2.setValue(quiz.getQuiz2());
        quiz3.setValue(quiz.getQuiz3());
        quiz4.setValue(quiz.getQuiz4());
        onClickQuiz(quiz.getAnswer());
    }

    public void insertQuiz(Quiz quiz) {
        addDisposable(repository.insertQuiz(quiz)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> onSuccessEvent.call(),
                        error -> onErrorEvent.setValue(error.getMessage())
                )
        );
    }

    public void onClickSave() {
        if (title.getValue().isEmpty() || score.getValue().isEmpty() || quiz1.getValue().isEmpty() ||
            quiz2.getValue().isEmpty() || quiz3.getValue().isEmpty() || quiz4.getValue().isEmpty()) {

            onErrorEvent.setValue("빈 칸 없이 입력해주세요");
            return;
        }

        Quiz quiz = new Quiz(
                0,
                title.getValue(),
                Integer.parseInt(score.getValue()),
                quizMapper.toType(isQuizType.getValue()),
                dateMapper.toString(new Date()),

                quiz1.getValue(),
                quiz2.getValue(),
                quiz3.getValue(),
                quiz4.getValue(),
                answer.getValue()
        );

        insertQuiz(quiz);
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
}
