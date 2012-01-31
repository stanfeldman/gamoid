package gamoid.input;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MultiTouchInput implements OnTouchListener
{
	public MultiTouchInput(View view, float scaleX, float scaleY)
	{
		view.setOnTouchListener(this);
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}
	
	public boolean onTouch(View v, MotionEvent event) 
	{
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
		int pointerId = event.getPointerId(pointerIndex);
		TouchEvent te;
		switch(action)
		{
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				touchX[pointerId] = (int)(event.getX(pointerIndex) * scaleX);
				touchY[pointerId] = (int)(event.getY(pointerIndex) * scaleY);
				isTouchDown[pointerId] = true;
				te = new TouchEvent(touchX[pointerId], touchY[pointerId], TouchEvent.TouchType.DOWN, pointerId);
				touchEventsBuffer.add(te);
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
			case MotionEvent.ACTION_CANCEL:
				touchX[pointerId] = (int)(event.getX(pointerIndex) * scaleX);
				touchY[pointerId] = (int)(event.getY(pointerIndex) * scaleY);
				isTouchDown[pointerId] = false;
				te = new TouchEvent(touchX[pointerId], touchY[pointerId], TouchEvent.TouchType.UP, pointerId);
				touchEventsBuffer.add(te);
				break;
			case MotionEvent.ACTION_MOVE:
				touchX[pointerId] = (int)(event.getX(pointerIndex) * scaleX);
				touchY[pointerId] = (int)(event.getY(pointerIndex) * scaleY);
				te = new TouchEvent(touchX[pointerId], touchY[pointerId], TouchEvent.TouchType.DRAGGED, pointerId);
				touchEventsBuffer.add(te);
				break;
		}
		return true;
	}
	
	public boolean isTouchDown(int pointer)
	{
		synchronized (this)
		{
			if(pointer < 0 && pointer >= 20)
				return false;
			else
				return isTouchDown[pointer];
		}
	}
	
	public int getTouchX(int pointer)
	{
		synchronized (this)
		{
			if(pointer < 0 && pointer >= 20)
				return 0;
			else
				return touchX[pointer];
		}
	}
	
	public int getTouchY(int pointer)
	{
		synchronized (this)
		{
			if(pointer < 0 && pointer >= 20)
				return 0;
			else
				return touchY[pointer];
		}
	}
	
	public List<TouchEvent> getTouchEvents() 
	{ 
		touchEvents.clear();
		touchEvents.addAll(touchEventsBuffer);
		touchEventsBuffer.clear();
		return touchEvents;
	}
	
	private float scaleX;
	private float scaleY;
	private List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	private List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
	private int[] touchX = new int[20];
	private int[] touchY = new int[20];
	private boolean[] isTouchDown = new boolean[20];
}
