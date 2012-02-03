package gamoid.game.controllers;

import gamoid.game.models.World;

public class GameStateMachine<W extends World> extends StateMachine<GameState>
{
	public GameStateMachine(W world)
	{
		this.world = world;
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
	
	public W getWorld() { return world; }
	
	protected W world;
}
