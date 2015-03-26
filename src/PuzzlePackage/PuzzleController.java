package PuzzlePackage;

import java.util.HashMap;

import android.R;
import android.R.xml;
import android.content.Context;
import android.util.Log;

public class PuzzleController {
	private Context context;
	private int bitMax = 4;
	private int sudokuMapMax = 9;
	private int priorityMax = 1000;
	private int puzzleShpaeTotal = 13;
	private int puzzleMapSolution[][] = {{1,2,3,4,5,6,7,8,9},{4,5,6,4,5,6,7,8,9},{1,2,3,4,5,6,7,8,9}};
	private int isSapcePuzzle[][] = {{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1}};
	private HashMap<Integer, Integer> mapShapeImageID;
	private int puzzleKey[] = {1,2,4,8,3,6,12,9,7,11,14,13,15};
	private int mapIndexPaddingX[] ={0,0,1,1};
	private int mapIndexPaddingY[] ={0,1,1,0};
	private PuzzleImageView puzzleImageView;
	private static PuzzleController puzzleControllerInstance = null;
	private PuzzleController() {
		String test ="";
		
		for (int i = 0; i < isSapcePuzzle.length; i++) {
			for (int j = 0; j < isSapcePuzzle[0].length; j++) {
				test += puzzleMapSolution[i][j]+" ";
			}
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
				puzzleImageView = new PuzzleImageView(context);
				int shapeNumber = getAssignedShapeNumber(total, count);		
				puzzleImageView.setShapeNumber(shapeNumber);	
				puzzleImageView.setShapeBit(changeNumberToShapeBit(shapeNumber));
				puzzleImageView.setSolution(getSolutionBit(shapeNumber,i, j));
				puzzleImageView.setPriority((int)(Math.random()*priorityMax));
				puzzleImageView.setImageID();
				
			}
		
		}
	}
	/**
	 * Get the shape number like 1 = 0001 ;
	 * @param shapeSpace
	 * @param spaceCount
	 * @return
	 */
	public int getAssignedShapeNumber(int shapeSpace,int spaceCount) {
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
	/**
	 * if shape number = 4  ,we could get space count = 1 by binary (0100)
	 * The binary 1 mean space (which could place number)
	 * @param number
	 * @return
	 */
	public int getSpaceCount(int number) {
		String bit = changeNumberToShapeBit(number);
		int count = 0;
		for (int i = 0; i < bit.length(); i++) {
			if(bit.charAt(i) == '1') {
				count+=1;
			}
		}
		return count;
	}
	
	/**
	 * Binary number change to decimal
	 * @param bit
	 * @param i
	 * @param j
	 * @return
	 */
	public int getAvailableNumber(int bit[][],int i,int j) {
		int total = 0;
		total += 1*bit[i][j];
		total += Math.pow(1, 2)*bit[i][j+1];
		total += Math.pow(2, 2)*bit[i+1][j+1];
		total += Math.pow(3, 2)*bit[i+1][j+1];
		return total;
	}
	public String getSpaceBit() {
		String space = "";
		return "0";
	}
	public String getSolutionBit(int shapeNumber,int i,int j) {
		String spaceBit = changeNumberToShapeBit(shapeNumber);
		String soultion = "";
		for (int k = 0; k < bitMax; k++) {
			if(spaceBit.charAt(k) == '1') {
				int x = j+mapIndexPaddingX[k];
				int y = i+mapIndexPaddingY[k];
				soultion += String.valueOf(puzzleMapSolution[x][y]);
			}
			else {
				soultion += "0";
			}
		}
		
		return soultion;
	}
	/**	
	 * Example  4 to binary => 0100
	 * @param shapeNumber
	 * @return
	 */
	public String changeNumberToShapeBit(int shapeNumber) {
		String bit = "";
		int count = 0;
		int result = 0;
		while (shapeNumber > 0) {
			bit = String.valueOf(shapeNumber % 2)+bit;
			shapeNumber =  shapeNumber / 2;
		}
		while (bit.length() < 4) {
			bit = "0"+bit;		
		}
		return bit;
	}
	public PuzzleImageView getPuzzle() {
		return puzzleImageView;
	}

}
