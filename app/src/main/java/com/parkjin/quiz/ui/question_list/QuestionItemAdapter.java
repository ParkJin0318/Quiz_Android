package com.parkjin.quiz.ui.question_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.parkjin.quiz.R;
import com.parkjin.quiz.databinding.ItemQuizBinding;
import com.parkjin.quiz.model.Quiz;

import java.util.List;

public class QuestionItemAdapter extends RecyclerView.Adapter<QuestionItemAdapter.ViewHolder> {

    private List<Quiz> quizList;

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemQuizBinding binding;
        private final QuestionItemViewModel viewModel = new QuestionItemViewModel();

        public ViewHolder(ItemQuizBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Quiz quiz) {
            viewModel.bind(quiz);
            binding.setViewModel(viewModel);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_quiz,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(quizList.get(position));
    }

    @Override
    public int getItemCount() {
        if (quizList == null) return 0;

        return quizList.size();
    }
}