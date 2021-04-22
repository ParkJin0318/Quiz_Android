package com.parkjin.quiz.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateMapper {
    public String toString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(date);
    }
}
