package com.example.enterc.practiceinter.Model.Helper;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;

import com.example.enterc.practiceinter.Listener.CallBack;
import com.example.enterc.practiceinter.Listener.StringCallback;

public class AnimationHelper {
    private static final long DURATION = 200;
    private static final float SCALE = 0.94f;

    public static void ScaleAnimation(final View view, final CallBack animationListener, float scale) {
        ViewCompat.animate(view)
                .setDuration(DURATION)
                .scaleX(scale != 0 ? scale : SCALE)
                .scaleY(scale != 0 ? scale : SCALE)
                .setInterpolator(new CycleInterpolator())
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        // some thing
                        animationListener.execute();
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                })
                .withLayer()
                .start();
    }




    public static void ScaleAnimation(final View view, final StringCallback animationListener, final String word) {
        ViewCompat.animate(view)
                .setDuration(DURATION)
                .scaleX(SCALE)
                .scaleY(SCALE)
                .setInterpolator(new CycleInterpolator())
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        // some thing
                        animationListener.execute(word);
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                })
                .withLayer()
                .start();
    }

    private static class CycleInterpolator implements android.view.animation.Interpolator {

        private final float mCycles = 0.5f;

        @Override
        public float getInterpolation(final float input) {
            return (float) Math.sin(2.0f * mCycles * Math.PI * input);
        }
    }


}
