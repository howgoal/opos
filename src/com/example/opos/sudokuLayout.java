package com.example.opos;

import java.io.Console;

import PuzzlePackage.PuzzleImageView;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class sudokuLayout extends RelativeLayout implements OnDragListener {
	private ImageView imageViewQues;
	private ImageView imageViewBar;
	private RelativeLayout relativeLayout;
	private int puzzleMapMax = 8;
	private int initMapX = 15;
	private int initMapY = 20;
	private int blockWidth = 49;
	private int blockHeight = 33;
	private int puzzleShowX = 525;
	private int tempPlace = 3;
	private int sudokuMapMax = 9;
	private int isSapcePuzzle[][] ;
	private PuzzleLayout tempPuzzlePlace[];
	private PuzzleLayout puzzleLayout[][];
	private SudokuController sudokuController;

	public sudokuLayout(Context context) {
		super(context);
		sudokuController = SudokuController.getInstance();
		isSapcePuzzle = new int [sudokuMapMax][sudokuMapMax];
		isSapcePuzzle = sudokuController.getIsSpaceMap();
		tempPuzzlePlace = new PuzzleLayout[tempPlace];
		for (int i = 0; i < tempPuzzlePlace.length; i++) {
			tempPuzzlePlace[i] = new PuzzleLayout(context);
		}
		puzzleLayout = new PuzzleLayout[puzzleMapMax][puzzleMapMax];
		for (int i = 0; i < puzzleLayout.length; i++) {
			for (int j = 0; j < puzzleLayout.length; j++) {
				puzzleLayout[i][j] = new PuzzleLayout(context);
			}
		}
		imageViewQues = new ImageView(context);
		imageViewBar = new ImageView(context);

		setBackgroundResource(R.drawable.initialpage);
		setInit();
		setPuzzleMapLayout();
		// TODO Auto-generated constructor stub
	}

	public void setInit() {
		imageViewQues.setImageResource(R.drawable.question2);
		imageViewQues.setX(initMapX);
		imageViewQues.setY(initMapY);
		addView(imageViewQues);
		imageViewBar.setImageResource(R.drawable.bar);
		imageViewBar.setX(15);
		imageViewBar.setY(480);
		addView(imageViewBar);

	}
	public void setPuzzleMapLayout() {
		for (int i = 0; i < puzzleLayout.length; i++) {
			for (int j = 0; j < puzzleLayout.length; j++) {
				puzzleLayout[i][j].setX(3 + (initMapX + (blockWidth * j)));
				puzzleLayout[i][j].setY(2 + (initMapY + (blockWidth * i)));
				puzzleLayout[i][j].setBackgroundResource(R.drawable.block);
				puzzleLayout[i][j].setIndex((i * 10) + j);
				puzzleLayout[i][j].setIndexMap(i, j);
				puzzleLayout[i][j].setAvaliable(getAvailableNumber(isSapcePuzzle, i, j));
				puzzleLayout[i][j].setOnDragListener(this);
				addView(puzzleLayout[i][j]);
			}
		}
//		for (int i = 0; i < tempPuzzlePlace.length; i++) {
//			tempPuzzlePlace[i].setX(65 + (i * (blockWidth + 20) * 2));
//			tempPuzzlePlace[i].setY(puzzleShowX);
//			tempPuzzlePlace[i].setBackgroundResource(R.drawable.block);
//			tempPuzzlePlace[i].setOnDragListener(this);
//			addView(tempPuzzlePlace[i]);
//		}

	}
	public int getAvailableNumber(int bit[][],int i,int j) {
		int total = 0;
		total += Math.pow(2, 3)*bit[i][j];
		total += Math.pow(2, 2)*bit[i][j+1];
		total += Math.pow(2, 1)*bit[i+1][j+1];
		total += 1*bit[i+1][j];
		return total;
	}
	@Override
	public boolean onDrag(View v, DragEvent dragevent) {
		// TODO Auto-generated method stub
		PuzzleImageView puzzle = (PuzzleImageView) dragevent.getLocalState();// /////
		PuzzleLayout layout = (PuzzleLayout) v;
		int action = dragevent.getAction();
		switch (action) {
		case DragEvent.ACTION_DRAG_STARTED:
			// Log.d(LOGCAT, "Drag event started");
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			// Log.d(LOGCAT, "Drag event entered into "+layoutview.toString());
			break;
		case DragEvent.ACTION_DRAG_EXITED:
			Log.v("ACTION_DRAG_EXITED", String.valueOf(layout.getAvaliableNumber()));
			break;
		case DragEvent.ACTION_DROP:


			int layoutShapeNumber = layout.getAvaliableNumber();
			int puzzleShapeNumber = puzzle.getShapeNumber();
			int temp = (layoutShapeNumber&puzzleShapeNumber);
			ViewGroup owner = (ViewGroup) puzzle.getParent();
		
			// myDragResourceId = view.getId();
			if( temp == puzzleShapeNumber ) {
				
				//layout.setIsPlaced();
				owner.removeView(puzzle);
				puzzle.setX(0);
				puzzle.setY(0);
				puzzle.setPlaced();
				layout.addView(puzzle);
				
			}
			if(this.findViewById(puzzle.getId()) == null) {
				sudokuController.setPuzzleIndexDec();
				sudokuController.setPlacedBoolTrue(puzzle.getPlacedIndex());
				sudokuController.setBarPuzzleCount();
				sudokuController.showPuzzle();
			}
			sudokuController.setMapShowNumber(puzzle.getSolutionBit(), layout.getIndexX(), layout.getIndexY());
			puzzle.setVisibility(View.VISIBLE);

			// //+ "Y: " + Integer.toString((int)layoutview.getY()) + " " +
			// Integer.toString((int) dragevent.getY()));
			// +" " + view.getId() + " " + view.getTag());
			break;
		case DragEvent.ACTION_DRAG_ENDED:
			
			break;
		default:
			break;
		}
		// container.invalidate();
		return true;
	}

}
