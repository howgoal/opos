package PuzzlePackage;

import com.example.opos.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.ImageView;

@SuppressLint("DrawAllocation")
public class PuzzleImageView extends ImageView implements OnDragListener{
	private int width = 108 ;
	private int height = 108;
	private int imageID;
	private int shapeNumber;
	public String solutionBit="0000";
	private String shapeBit;
	private String numberBit;
	private int priority = 0;
	private int drawLocationX[]= {0,width/2,width/2,0};
	private int drawLocationY[]= {0,0,height/2,height/2};
	private Resources resources;
 	

	public PuzzleImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setOnDragListener(this);
		resources = getResources();
	}
	public void setShapeBit(String _shape) {
		shapeBit = _shape;
		Log.v("onDraw","onDraw"); 
	}
	public void setImageID() {
		imageID = resources.getIdentifier("block"+shapeNumber, "drawable",getContext().getPackageName());
	
	}
	public void setNumberBit(String _number) {
		numberBit = _number;
	}
	public void setPriority(int _priority) {
		priority = _priority;
	}
	public void setShapeNumber(int _number) {
		shapeNumber = _number;
	}
	public void setSolution(String _solution){
		solutionBit =_solution;
	}
	public void drawImage() {
		//Log.v("shapeNumber", String.valueOf(shapeNumber));
		setImageResource(imageID);
	}
	public String getNumberBit() {
		return numberBit;
	}
	public String getShapeBit() {
		return shapeBit;
	}
	public int getPriority() {
		return priority;
	}
	public String getSolutionBit() {
		return solutionBit;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		for (int j = 0; j < solutionBit.length(); j++) {
			if(solutionBit.charAt(j) != '0') {
				String numberID = "number0"+solutionBit.charAt(j);
				Bitmap bmp=BitmapFactory.decodeResource(resources, resources.getIdentifier(numberID, "drawable",getContext().getPackageName()));
				Paint paint = new Paint();
				canvas.drawBitmap(bmp, drawLocationX[j], drawLocationY[j],paint);
			}
			
		}
	}
	@Override
	public boolean onDrag(View v, DragEvent event) {
		// TODO Auto-generated method stub
		
		return true;
	}
	
}
