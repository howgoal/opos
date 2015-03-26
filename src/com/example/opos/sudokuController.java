package com.example.opos;

import PuzzlePackage.PuzzleController;
import PuzzlePackage.PuzzleImageView;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class sudokuController {
	public static sudokuController sudokuControllerInstance = null;
	private RelativeLayout relativeLayout;
	private PuzzleController puzzleController;
	private sudokuController() {
		Log.v("test","sudoKuContorller");
		
		puzzleController = PuzzleController.getInstance();

	}
	public static sudokuController getInstance() {
		if(sudokuControllerInstance == null) {
			sudokuControllerInstance = new sudokuController();
		}
		return sudokuControllerInstance;
	}
	public void initLayout(RelativeLayout _relativeLayout) {
		relativeLayout = _relativeLayout;
		puzzleController.setContext(relativeLayout.getContext());
		puzzleController.init();
		PuzzleImageView puzzleImageView = puzzleController.getPuzzle();
		addViewToPuzzleBlock(puzzleImageView);
	}

	public RelativeLayout getLayout() {
		return relativeLayout;
	}
	public void addViewToPuzzleBlock(View view) {
		relativeLayout.addView(view);
	}

}
