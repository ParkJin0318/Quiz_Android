package com.parkjin.quiz.di;

import com.parkjin.quiz.repository.QuizRepository;
import com.parkjin.quiz.repository.QuizRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class RepositoryModule {

    @Provides
    @Singleton
    public static QuizRepository provideQuizRepository(QuizRepositoryImpl quizRepositoryImpl) {
        return quizRepositoryImpl;
    }
}
