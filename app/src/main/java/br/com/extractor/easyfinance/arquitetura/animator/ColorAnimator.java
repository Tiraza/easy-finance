package br.com.extractor.easyfinance.arquitetura.animator;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;

public class ColorAnimator {

    public static void colorTransition(final String srcColor, final String dstColor, final View
            view, final long duration) {

        final float[] from = new float[3];
        final float[] to = new float[3];
        final float[] hsv = new float[3];

        Color.colorToHSV(Color.parseColor(srcColor), from);
        Color.colorToHSV(Color.parseColor(dstColor), to);

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                hsv[0] = from[0] + (to[0] - from[0]) * animation.getAnimatedFraction();
                hsv[1] = from[1] + (to[1] - from[1]) * animation.getAnimatedFraction();
                hsv[2] = from[2] + (to[2] - from[2]) * animation.getAnimatedFraction();

                view.setBackgroundColor(Color.HSVToColor(hsv));
            }
        });

        anim.start();
    }

}
