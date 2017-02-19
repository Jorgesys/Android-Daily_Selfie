package com.jorgesys.dailyselfie;

import java.util.Random;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class SelfieAnim {

	private static final String TAG = "SelfieAnim";
	
	public static void setAnimationGrowShrink(final ImageView imgV){
		final Animation animationEnlarge;
		final Animation animationShrink;
		float pivotX = getRandom();
		float pivotY = getRandom();
		Log.e(TAG,"for " + imgV.getTag() + " : X=" + pivotX + " Y="+ pivotY);
		float startScale = 1.0f;
		float endScale = 1.2f;
		animationEnlarge =  new ScaleAnimation(startScale, endScale, startScale, endScale, 
				Animation.RELATIVE_TO_SELF, pivotX, Animation.RELATIVE_TO_SELF, pivotY); 
		animationEnlarge.setStartOffset(2000);
		animationEnlarge.setDuration(3000);		
		animationShrink =  new ScaleAnimation(endScale, startScale, endScale, startScale, 
				Animation.RELATIVE_TO_SELF, pivotX, Animation.RELATIVE_TO_SELF, pivotY);
		animationShrink.setStartOffset(2000);
		animationShrink.setDuration(3000);
		imgV.startAnimation(animationEnlarge);

		animationEnlarge.setAnimationListener(new AnimationListener() {			
			@Override
			public void onAnimationStart(Animation animation) {}

			@Override
			public void onAnimationRepeat(Animation animation) {}

			@Override
			public void onAnimationEnd(Animation animation) {
				imgV.startAnimation(animationShrink);
			}
		});

		animationShrink.setAnimationListener(new AnimationListener() {			
			@Override
			public void onAnimationStart(Animation animation) {}

			@Override
			public void onAnimationRepeat(Animation animation) {}

			@Override
			public void onAnimationEnd(Animation animation) {
				imgV.startAnimation(animationEnlarge);
			}
		});

	}

	private static float getRandom(){
		int min = 0;
		int max = 100;
		Random r = new Random();
		float numRandom = r.nextInt(max - min + 1) + min;
		numRandom = (float) (numRandom * 0.01); 
		return numRandom;
	}
}
