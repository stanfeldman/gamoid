package gamoid.game.models;

// то, что двигает игрока
public abstract class PlayerMover
{
	public enum Direction { UP, LEFT, DOWN, RIGHT }
	
	public PlayerMover(Player player)
	{
		this.player = player;
	}
	
	public abstract void forward();
	
	public abstract void back();
	
	public abstract void left();
	
	public abstract void right();
	
	protected Player player;
}
