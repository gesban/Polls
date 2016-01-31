package com.loopcupcakes.apps.polls.viewmodel.utils;

import com.github.mikephil.charting.utils.ColorTemplate;

/**
 * Created by evin on 1/28/16.
 */
public class ColorMagic {

    private static final int[] mColors = new int[] {
            ColorTemplate.JOYFUL_COLORS[0],
            ColorTemplate.JOYFUL_COLORS[1],
            ColorTemplate.JOYFUL_COLORS[2],
            ColorTemplate.JOYFUL_COLORS[3],
            ColorTemplate.JOYFUL_COLORS[4],
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2],
            ColorTemplate.VORDIPLOM_COLORS[3],
            ColorTemplate.VORDIPLOM_COLORS[4],
            ColorTemplate.COLORFUL_COLORS[1],
            ColorTemplate.COLORFUL_COLORS[2],
            ColorTemplate.COLORFUL_COLORS[3],
            ColorTemplate.COLORFUL_COLORS[4],
            ColorTemplate.COLORFUL_COLORS[0],
    };

    public static int createColor(int i) {
        return mColors[i % mColors.length];
    }
}
