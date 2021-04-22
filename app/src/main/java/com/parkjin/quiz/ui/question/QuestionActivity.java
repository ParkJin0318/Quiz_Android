package com.parkjin.quiz.ui.question;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.parkjin.quiz.R;
import com.parkjin.quiz.base.BaseActivity;
import com.parkjin.quiz.databinding.ActivityQuestionBinding;
import com.parkjin.quiz.ui.main.MainActivity;

import java.io.File;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionActivity extends BaseActivity<ActivityQuestionBinding, QuestionViewModel> {

    @Override
    protected QuestionViewModel viewModel() {
        return new ViewModelProvider(this).get(QuestionViewModel.class);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_question;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.idx = getIntent().getIntExtra("idx", 0);
        viewModel.getQuiz();
    }

    @Override
    public void observeEvent() {
        viewModel.onSuccessEvent.observe(this, unit -> {
            this.finish();
        });

        viewModel.onErrorEvent.observe(this, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        viewModel.onImageOpenEvent.observe(this, this::openGallery);
    }

    private void openGallery(int quiz) {
        Intent intent = new Intent()
                .setType("image/*")
                .setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(intent, quiz);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;

        String uri = data.getData().toString();
        viewModel.setUri(requestCode, uri);
    }
}