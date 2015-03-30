package PuzzlePackage;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

@SuppressLint("DrawAllocation")
public class PuzzleImageView extends ImageView implements OnDragListener,OnTouchListener {
	private int width = 66;
	private int height = 66;
	private int imageID;
	private int shapeNumber;
	public String solutionBit = "0000";
	private String shapeBit;
	private String numberBit;
	private int priority = 0;
	private int drawLocationX[] = { 0, (width / 2)+16, (width / 2)+16, 0 };
	private int drawLocationY[] = { 0, 0, (height / 2)+16, (height / 2)+16 };
	private Resources resources;
	private boolean isPlaced = false;
	private int placedIndex = 0;
	public PuzzleImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setOnDragListener(this);
		setOnTouchListener(this);
		resources = getResources();
	}
	public void setPlaced() {
		isPlaced = true;
	}
	public boolean getPlaced() {
		return isPlaced;
	}
	public void setShapeBit(String _shape) {
		shapeBit = _shape;
		Log.v("onDraw", "onDraw");
	}
	public void setPlacedIndex(int _index) {
		placedIndex = _index;
	}
	public int getPlacedIndex() {
		return placedIndex;
				
	}
	public void setImageID() {
		imageID = resources.getIdentifier("block" + shapeNumber, "drawable",
				getContext().getPackageName());

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

	public void setSolution(String _solution) {
		solutionBit = _solution;
	}

	public void drawImage() {
		// Log.v("shapeNumber", String.valueOf(shapeNumber));
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
	public int getShapeNumber() {
		return shapeNumber;
	}
	public String getSolutionBit() {
		return solutionBit;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		for (int j = 0; j < solutionBit.length(); j++) {
			if (solutionBit.charAt(j) != '0') {
				String numberID = "number0" + solutionBit.charAt(j);
				Bitmap bmp = BitmapFactory.decodeResource(resources, resources
						.getIdentifier(numberID, "drawable", getContext()
								.getPackageName()));
				Paint paint = new Paint();
				canvas.drawBitmap(bmp,  drawLocationX[j],drawLocationY[j], paint);
			}

		}
	}

	@Override
	public boolean onDrag(View v, DragEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();
		switch (action) {
		case DragEvent.ACTION_DRAG_STARTED:
			// Log.d(LOGCAT, "Drag event started");
			break;
		case DragEvent.ACTION_DRAG_ENTERED:
			// Log.d(LOGCAT, "Drag event entered into "+layoutview.toString());
			break;
		case DragEvent.ACTION_DRAG_EXITED:

			break;
		case DragEvent.ACTION_DROP:
												// from View
			break;
		case DragEvent.ACTION_DRAG_ENDED:

			break;
		default:
			break;
		}
		// container.invalidate();
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
		v.startDrag(null, shadowBuilder, v, 0);
		if (event.getAction() == MotionEvent.ACTION_DOWN) 
		{
			return true;
		}
		else if(event.getAction() == MotionEvent.ACTION_MOVE)
		{
			
			return true;
		}
		else if(event.getAction() == MotionEvent.ACTION_UP)
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}

}
