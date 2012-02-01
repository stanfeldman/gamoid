package gamoid.core;

import gamoid.core.StateMachine;

public class GameStateMachine extends StateMachine<GameState>
{
	public GameStateMachine(Game game)
	{
		this.game = game;
	}
	
	public void update(float deltaTime)
	{
		if(currentState != null)
			currentState.update(deltaTime);
	}
	
	public void draw(float deltaTime)
	{
		if(currentState != null)
			currentState.draw(deltaTime);
	}
	
	public Game getGame() { return game; }
	
	private Game game;
}
