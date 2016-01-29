package com.loopcupcakes.apps.polls.viewmodel.utils;

import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by evin on 1/28/16.
 */
public class xAxisFormatter implements XAxisValueFormatter {

    public xAxisFormatter() {
        // maybe do something here or provide parameters in constructor
    }

    @Override
    public String getXValue(String original, int index, ViewPortHandler viewPortHandler) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.CustomDateFormat, Locale.US);
        DateFormat dateFormat = new SimpleDateFormat(Constants.ConvertToDateFormat, Locale.US);
        String result;

        try {
            result = dateFormat.format(simpleDateFormat.parse(original));
        } catch (ParseException e) {
            result = original;
        }

        return result;
    }
}