package com.wholepunk.android.testanimationapplication;

import android.animation.TypeEvaluator;

import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;

public class MoveEvaluator implements TypeEvaluator<ViewGroup.MarginLayoutParams> {

	@Override
	public MarginLayoutParams evaluate(float fraction, MarginLayoutParams startValue, MarginLayoutParams endValue) 
	{
		int endLeftMargin = endValue.leftMargin;
		int startLeftMargin = startValue.leftMargin;
		
		int totalChange = endLeftMargin - startLeftMargin;
				
		float movementIncrement = fraction * totalChange;
		
		int newLeftMargin = (int) movementIncrement;
		
		startValue.setMargins(startValue.leftMargin + newLeftMargin, startValue.topMargin, startValue.rightMargin, startValue.bottomMargin);
				
		return startValue;
	}

}
