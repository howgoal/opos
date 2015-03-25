package PuzzlePackage;

import java.util.HashMap;
import java.util.Random;

import android.R.integer;
import android.content.Context;
import android.util.Log;

public class PuzzleController {
	private Context context;
	private int bitMax = 4;
	private int priorityMax = 1000;
	private int puzzleShpaeTotal = 13;
	private int puzzleMapSolution[][] = {{1,2,3,4,5,6,7,8,9},{1,2,3,4,5,6,7,8,9},{1,2,3,4,5,6,7,8,9}};
	private int isSapcePuzzle[][] = {{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1}};
	private HashMap<Integer, Integer> mapShapeImageID;
	private int puzzleKey[] = {1,2,4,8,3,6,12,9,7,11,14,13,15};
	private static PuzzleController puzzleControllerInstance = null;
	private PuzzleController() {
		String test ="";
		//Log.v("string", String.valueOf(isSapcePuzzle.length));
		for (int i = 0; i < isSapcePuzzle.length; i++) {
			for (int j = 0; j < isSapcePuzzle[0].length; j++) {
				test += puzzleMapSolution[i][j]+" ";
			}
			Log.v("test", test);
			test = "";
			
		}
		//mapShapeImageID = new HashMap<Integer, Integer>();
		
	}
	public static PuzzleController getInstance() {
		if(puzzleControllerInstance == null) {
			puzzleControllerInstance = new PuzzleController();
		}
		return puzzleControllerInstance;
	}
	public void setContext(Context _context) {
		context = _context;
	}
	public void init() {
		decomposePuzzle();
	}
	public void puzzleMappintImageID () {
		//mapShapeImageID.put(key, value)
	}
	public void puzzleHashMapping() {
		
	}
	public void decomposePuzzle() {
		for (int i = 0; i < isSapcePuzzle.length-1; i++) {
			for (int j = 0; j < isSapcePuzzle[0].length-1; j++) {
				//Read bit 
				int total = getAvailableNumber(isSapcePuzzle,i,j);
				int count = getSpaceCount(total);
				int shape = getAssignedShape(total, count);
				PuzzleImageView puzzleImageView = new PuzzleImageView(context);
				puzzleImageView.setShapeNumber(shape);
				puzzleImageView.setPriority((int)(Math.random()*priorityMax));
				
			}
		}
		Log.v("end","end");
	}
	
	public int getAssignedShape(int shapeSpace,int spaceCount) {
		int shape = 0;
		int availableCount = 0;
		int available[] = new int[puzzleShpaeTotal];
		if(spaceCount == 1) {
			shape = shapeSpace;
		} 
		else {
			for (int i = 0; i < available.length; i++) {
				
				if( (shapeSpace&puzzleKey[i]) ==  puzzleKey[i]) {
					available[availableCount] = puzzleKey[i];
					availableCount += 1;
				}
			}
			int temp = (int)(Math.random()*availableCount);
			shape = available[temp];
		}
		
		return shape;
	}
	public int getSpaceCount(int number) {
		int count = 0;
		int result = 0;
		while (number > 0) {
			count += number % 2;
			number =  number / 2;
		}
		return count;
	}
	

	public int getAvailableNumber(int bit[][],int i,int j) {
		int total = 0;
		total += 1*bit[i][j];
		total += Math.pow(1, 2)*bit[i][j+1];
		total += Math.pow(2, 2)*bit[i+1][j+1];
		total += Math.pow(3, 2)*bit[i+1][j+1];
		return total;
	}

}
