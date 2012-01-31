package gamoid.input;

public class TouchEvent 
{
	public enum TouchType { UP, DOWN, DRAGGED }
	
	public TouchEvent(int x, int y, TouchType type, int pointer)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		this.pointer = pointer;
	}
	
	public int x, y;
	public TouchType type;
	public int pointer;
}
