package com.example.opos;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class sudokuLayout extends RelativeLayout {
	private ImageView imageViewSudokuQues;
	private ImageView imageViewSudokuPuzzleBar;
	
	public sudokuLayout(Context context) {
		super(context);
		imageViewSudokuQues = new ImageView(context);
		imageViewSudokuPuzzleBar = new ImageView(context);
		initLayout();
		// TODO Auto-generated constructor stub
	}
	public void initLayout() {
		imageViewSudokuQues.setImageResource(R.drawable.question2);
		imageViewSudokuQues.setX(15);
		imageViewSudokuQues.setY(60);
		addView(imageViewSudokuQues);
		imageViewSudokuPuzzleBar.setImageResource(R.drawable.bar);
		imageViewSudokuPuzzleBar.setX(40);
		imageViewSudokuPuzzleBar.setY(530);
		addView(imageViewSudokuPuzzleBar);
	}

}
