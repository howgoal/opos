package com.example.opos;

import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;

public class PuzzleLayout extends RelativeLayout{
	private int index = 0;
	private int puzzleMapQuestion = 0;
	private int indexX = 0;
	private int indexY = 0;
	private int avaliableNumber = 0;
	private int indexMap[] ;
	private boolean isPlacedPuzzle = false;
	private String puzzleBit = "";
	public PuzzleLayout(Context context) {
		super(context);
		indexMap = new int[2];
	}
	public void setIndexXY(int x,int y) {
		indexX = x;
		indexY = y;
	}
	public int getIndexX() {
		return indexX;
	}
	public int getIndexY() {
		return indexY;
	}
	public void setAvaliable(int _number) {
		avaliableNumber = _number;
		Log.v("test",String.valueOf(_number));
	}
	public void setIndexMap(int i , int j) {
		indexMap[0]=i;
		indexMap[1]=j;
	}
	public void setIndex(int _index) {
		index = _index;
	}
	public void setPlacePuzzleBit(String bit) {
		puzzleBit = bit;
	}
	public void setIsPlaced() {
		isPlacedPuzzle = true;
	}
	public void setIsNotPlaced() {
		isPlacedPuzzle = false;
	}
	public int[] getIndexMap() {
		return indexMap;
	}
	public int getAvaliableNumber() {
		return avaliableNumber;
	}
}
