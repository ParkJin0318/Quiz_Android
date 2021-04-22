package com.parkjin.quiz.di;

import android.app.Application;

import androidx.room.Room;

import com.parkjin.quiz.database.AppDatabase;
import com.parkjin.quiz.database.dao.QuizDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static AppDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "quiz")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static QuizDao provideQuizDao(AppDatabase database) {
        return database.quizDao();
    }
}
