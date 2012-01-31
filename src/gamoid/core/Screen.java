package gamoid.core;

public abstract class Screen 
{
	public Screen(Game game)
	{
		this.game = game;
	}
	
	public abstract void update(float deltaTime);
	
	public abstract void draw(float deltaTime);
	
	public void pause() {}
	
	public void resume() {}
	
	public void dispose() {}
	
	protected Game game;
}
