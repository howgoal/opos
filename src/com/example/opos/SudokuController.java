package com.example.opos;

import java.util.HashMap;

import PuzzlePackage.PuzzleController;
import PuzzlePackage.PuzzleImageView;
import android.R.integer;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class SudokuController {
	private sudokuLayout sudokuLayout;
	private PuzzleController puzzleController;
	public static SudokuController sudokuControllerInstance = null;
	private int bitMax = 4;
	private int sudokuMapMax = 9;
	private int priorityMax = 1000;
	private int puzzleShpaeTotal = 13;
	private int blockWidth = 49;
	private int blockHeight = 33;
	private int puzzleShowX = 525;
	private int puzzleMapSolution[][] = {};
	private int puzzleMapQuestion[][] = {};
	private int isSapcePuzzle[][] = {};
	private HashMap<Integer, Integer> mapShapeImageID;
	private int puzzleKey[] = { 1, 2, 4, 8, 3, 6, 12, 9, 7, 11, 14, 13, 15 };
	private int mapIndexPaddingX[] = { 0, 0, 1, 1 };
	private int mapIndexPaddingY[] = { 0, 1, 1, 0 };
	private int puzzleShowIndex = 0;
	private int barPuzzleCount = 0;
	private boolean barPlacedBoolean[] = { false, false, false };
	private PuzzleImageView puzzle[];
	private Handler handler;
	private boolean gameOver = false;
	private SudokuController() {
		FileIO fileIO = new FileIO();
		String readMap[] = fileIO.readFile("question1.txt").split(" ");
		puzzleMapSolution = new int[sudokuMapMax][sudokuMapMax];
		puzzleMapQuestion = new int[sudokuMapMax][sudokuMapMax];
		isSapcePuzzle = new int[sudokuMapMax][sudokuMapMax];
		for (int i = 0; i < isSapcePuzzle.length; i++) {
			for (int j = 0; j < isSapcePuzzle[0].length; j++) {
				int temp = Integer.parseInt(readMap[(i * 9) + j]);
				puzzleMapQuestion[i][j] = temp;
				if (temp == 0) {
					isSapcePuzzle[i][j] = 1;

				} else {
					isSapcePuzzle[i][j] = 0;

				}

			}
		}
		FileIO fileIOA = new FileIO();
		String readAnswerMap[] = fileIOA.readFile("anwser1.txt").split(" ");
		for (int i = 0; i < isSapcePuzzle.length; i++) {
			for (int j = 0; j < isSapcePuzzle[0].length; j++) {
				int temp = Integer.parseInt(readAnswerMap[(i * 9) + j]);
				puzzleMapSolution[i][j] = temp;
			}
		}
		for (int i = 0; i < isSapcePuzzle.length; i++) {
			for (int j = 0; j < isSapcePuzzle[0].length; j++) {
				Log.v("sol", String.valueOf(puzzleMapSolution[i][j]));

			}
		}
	}

	public static SudokuController getInstance() {
		if (sudokuControllerInstance == null) {
			sudokuControllerInstance = new SudokuController();
		}
		return sudokuControllerInstance;
	}

	public void init(Context context) {
		sudokuLayout = new sudokuLayout(context);

		puzzleController = new PuzzleController();
		puzzleController.setIsSpaceMap(isSapcePuzzle);
		puzzleController.setSolutionMap(puzzleMapSolution);
		puzzleController.setQuestionMap(puzzleMapQuestion);
		puzzleController.setContext(context);
		// decompose
		puzzleController.init();

		puzzle = new PuzzleImageView[64];
		for (int i = 0; i < puzzle.length; i++) {
			puzzle[i] = new PuzzleImageView(context);
		}
		puzzle = puzzleController.getAllPuzzle();
		setUiHandler();
		showPuzzle();
	}

	public int[][] getIsSpaceMap() {
		return isSapcePuzzle;
	}

	public sudokuLayout getLayout() {
		return sudokuLayout;
	}

	public void addView(ImageView _view) {

		sudokuLayout.addView(_view);
	}

	public void showPuzzle() {
		for (int i = 0; i < 64; i++) {
			if (barPuzzleCount < 3) {
				if (sudokuLayout.findViewById(puzzle[i].getId()) == null && puzzle[i].getPlaced() == false) {
					int index = checkPlacedSpaceIndex();
					if(index >= 0) {
						puzzle[i].setX(65 + (index * (blockWidth + 20) * 2));
						puzzle[i].setY(puzzleShowX);
						puzzle[i].setPlaced();
						puzzle[i].setPlacedIndex(index);
						addView(puzzle[i]);
						barPlacedBoolean[index] = true;
						barPuzzleCount++;
					}
				}
			} 
			else {
				break;
			}

		}
		
		
//		Message msg = new Message();
//		msg.what = 1;
//		handler.sendMessage(msg);

	}

	public void setPuzzleIndexDec() {
		puzzleShowIndex += 1;
	}

	public void setBarPuzzleCount() {
		barPuzzleCount -= 1;
	}
	public void remove (View _view) {
		sudokuLayout.removeView(_view);
	}
	public void setPlacedBoolTrue(int index) {
		barPlacedBoolean[index] = false;
	}
	public int checkPlacedSpaceIndex() {
		int index = -1;
		for (int i = 0; i < barPlacedBoolean.length; i++) {
			if(barPlacedBoolean[i] == false) {
				index=i;
				break;
			}
		}
		return index;
	}
	public void setUiHandler() {
		handler = new Handler() {
			 @Override
		        public void handleMessage(Message msg){
				 
			 }
		};
	}
	public void setMapShowNumber(String shapeNumberBit,int i ,int j) {
		for (int k = 0; k < shapeNumberBit.length(); k++) {
			if(shapeNumberBit.charAt(k) != '0') {
				
				puzzleMapQuestion[i+mapIndexPaddingX[k]][j+mapIndexPaddingY[k]] = shapeNumberBit.charAt(k);
			}
			
		}
		check();
	}
	public void check() {
		int count = 0;
		for (int i = 0; i < isSapcePuzzle.length; i++) {
			for (int j = 0; j < isSapcePuzzle[0].length; j++) {
				Log.v("sol", String.valueOf(puzzleMapQuestion[i][j]));

			}
		}
		for (int i = 0; i < isSapcePuzzle.length; i++) {
			for (int j = 0; j < isSapcePuzzle[0].length; j++) {
				if(puzzleMapQuestion[i][j] == puzzleMapSolution[i][j]) {
					count++;
				}

			}
		}
		if(count ==81) {
			gameOver =true;
			new AlertDialog.Builder(sudokuLayout.getContext())
	     	.setTitle("µ²§ô¹CÀ¸")
	     	.setMessage("")
	     	.setOnCancelListener(new DialogInterface.OnCancelListener() {
		        @Override
		        public void onCancel(DialogInterface dialog) {
		        	
		        }
	    	})
	     	.setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener() {
	     		public void onClick(DialogInterface dialog,int which){
	     			
	     		} 
	     	}).show();
		}
		
		
	}
}
