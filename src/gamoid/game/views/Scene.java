package gamoid.game.views;

import gamoid.game.models.Game;

public abstract class Scene 
{
	public Scene(Game game)
	{
		this.game = game;
	}
	
	public abstract void update(float deltaTime);
	
	public abstract void draw(float deltaTime);
	
	public void pause() {}
	
	public void resume() {}
	
	public void dispose() {}
	
	public boolean back() { return false; }
	
	protected Game game;
}
