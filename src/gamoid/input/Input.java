package gamoid.input;

import android.content.Context;
import android.view.View;

public class Input 
{
	public Input(Context context, View view, float scaleX, float scaleY)
	{
		//accelerometer = new AccelerometerHandler(context);
		touch = new MultiTouchInput(view, scaleX, scaleY);
	}
	
	public boolean isTouchDown(int pointer)
	{
		return touch.isTouchDown(pointer);
	}
	
	public int getTouchX(int pointer)
	{
		return touch.getTouchX(pointer);
	}
	
	public int getTouchY(int pointer)
	{
		return touch.getTouchY(pointer);
	}
	
	public float getAccelX() {
		return accelerometer.getAccelX();
	}

	public float getAccelY() {
		return accelerometer.getAccelY();
	}

	public float getAccelZ() {
		return accelerometer.getAccelZ();
	}
	
	private AccelerometerHandler accelerometer;
	private MultiTouchInput touch;
}
