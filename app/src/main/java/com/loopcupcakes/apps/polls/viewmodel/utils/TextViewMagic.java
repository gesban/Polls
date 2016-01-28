package com.loopcupcakes.apps.polls.viewmodel.utils;

import android.view.View;
import android.widget.TextView;

import com.loopcupcakes.apps.polls.model.entities.huffpost.Chart;
import com.loopcupcakes.apps.polls.model.entities.huffpost.Estimate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by evin on 1/28/16.
 */
public class TextViewMagic {

    public static void validateIfDateSet(String electionDate, TextView textViewDate, TextView textViewElection) {
        if (electionDate == null || electionDate.length() == 0){
            textViewDate.setVisibility(View.GONE);
            textViewElection.setVisibility(View.GONE);
        }else {
            textViewDate.setText(electionDate);
        }
    }

    public static void formatDate(String lastUpdatedDate, TextView textViewUpdated) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.ParseDateFormat, Locale.US);
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String result;

        try {
            result = dateFormat.format(simpleDateFormat.parse(lastUpdatedDate));
        } catch (ParseException e) {
            result = lastUpdatedDate;
        }

        textViewUpdated.setText(result);
    }

    public static void setWinnerLoser(TextView textViewWinner, TextView textViewLoser, TextView textViewWinnerValue, TextView textViewLoserValue, Chart chart) {
        List<Estimate> estimates = chart.getEstimates();
        if (estimates.size() > 1){
            Estimate winner = estimates.get(0);
            Estimate loser = estimates.get(1);
            textViewWinner.setText(winner.getChoice());
            textViewWinnerValue.setText(String.format("%.1f", winner.getValue()));
            textViewLoser.setText(loser.getChoice());
            textViewLoserValue.setText(String.format("%.1f", loser.getValue()));
        }
    }
}
