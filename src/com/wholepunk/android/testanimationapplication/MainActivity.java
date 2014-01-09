package com.wholepunk.android.testanimationapplication;

import java.util.ArrayList;

import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements AnimatorUpdateListener, AnimatorListener {

	boolean menuOpen = true;
	boolean transitionInProgress = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			
		ListView lv = (ListView) findViewById(R.id.square);
		
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
		        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
		        "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
		        "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
		        "Android", "iPhone", "WindowsMobile" };

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
		      list.add(values[i]);
		}
		    
		TestAdapter adapter = new TestAdapter(getApplicationContext(), R.layout.list_item);
		adapter.addData(list);    
		
		lv.setAdapter(adapter);
		
		Button moveButton = (Button) findViewById(R.id.moveButton);
		
		moveButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v)
			{
				if (!transitionInProgress)
				{
					transitionInProgress = true;
					
					performMoveActionOnMenu();
				}
			}
		});
	}
	
	public void performMoveActionOnMenu()
	{		
		ListView lv = (ListView) findViewById(R.id.square);
		
		ViewGroup.MarginLayoutParams endParams = new ViewGroup.MarginLayoutParams((ViewGroup.MarginLayoutParams) lv.getLayoutParams());
						
		int currentLeft = endParams.leftMargin;
		
		if (menuOpen)
		{
			endParams.setMargins(currentLeft + 600, endParams.topMargin, endParams.rightMargin, endParams.bottomMargin);
			
			menuOpen = false;
		}
		else
		{
			endParams.setMargins(currentLeft - 600, endParams.topMargin, endParams.rightMargin, endParams.bottomMargin);
			
			menuOpen = true;
		}		
		
		ValueAnimator anim = ValueAnimator.ofObject(new MoveEvaluator(), (ViewGroup.MarginLayoutParams) lv.getLayoutParams(), endParams);
		anim.addUpdateListener(this);
		anim.addListener(this);
		anim.setDuration(250);
		anim.start();
	}
	
	@Override
	public void onAnimationUpdate(ValueAnimator animation) 
	{				
		ListView lv = (ListView) findViewById(R.id.square);
		
		lv.setLayoutParams((LayoutParams) animation.getAnimatedValue());
		
		lv.invalidate();
	}

	@Override
	public void onAnimationCancel(Animator animation) 
	{
	}

	@Override
	public void onAnimationEnd(Animator animation) 
	{
		ListView lv = (ListView) findViewById(R.id.square);
		
		ViewGroup.MarginLayoutParams finalParams = new ViewGroup.MarginLayoutParams((ViewGroup.MarginLayoutParams) lv.getLayoutParams());
								
		if (menuOpen)
		{
			finalParams.setMargins(700, finalParams.topMargin, finalParams.rightMargin, finalParams.bottomMargin);
		}
		else
		{
			finalParams.setMargins(100, finalParams.topMargin, finalParams.rightMargin, finalParams.bottomMargin);
		}	
		
		lv.setLayoutParams((LayoutParams)finalParams);
		
		lv.invalidate();
		
		transitionInProgress = false;
	}

	@Override
	public void onAnimationRepeat(Animator animation) 
	{
	}

	@Override
	public void onAnimationStart(Animator animation) 
	{		
	}
}
