package PuzzlePackage;

import android.content.Context;
import android.widget.ImageView;

public class PuzzleImageView extends ImageView{
	private int width ;
	private int height;
	private int imageID ;
	private int shapeNumber;
	private String shapeBit;
	private String numberBit;
	private int priority = 0;
	public PuzzleImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public void setShapeBit(String _shape) {
		shapeBit = _shape;
	}
	public void setImageID(int _imageID) {
		imageID = imageID;
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
	public void drawImage() {
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
}
