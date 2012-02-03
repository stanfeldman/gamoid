package gamoid.game.models;

import gamoid.game.models.PlayerMover.Direction;

import java.util.ArrayList;
import java.util.List;

// абстрактный участник игры
public abstract class Player
{
	public Player(World world)
	{
		this.world = world;
	}
	
	public abstract void draw();
	
	public void forward() 
	{
		if(mover != null)
			mover.forward();
	}

	public void back() 
	{
		if(mover != null)
			mover.back();
	}

	public void left()
	{
		if(mover != null)
			mover.left();
	}

	public void right() 
	{
		if(mover != null)
			mover.right();
	}
	
	public void kill()
	{
		world.getPlayers().remove(this);
		isAlive = false;
	}
	
	public boolean isAlive() { return isAlive; }
	
	public void setDirection(Direction direction) { this.direction = direction; }
	
	public Direction getDirection() { return direction; }
	
	public List<Cell> getPlace() { return place; }
	
	// средство передвижения участника игры
	protected PlayerMover mover;
	protected Direction direction = Direction.UP;
	// место на игровом поле, в котором находится участник игры(набор ячеек)
	protected List<Cell> place = new ArrayList<Cell>();
	protected World world;
	protected boolean isAlive = true;
}
