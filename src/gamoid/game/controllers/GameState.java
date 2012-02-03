package gamoid.game.controllers;

import gamoid.game.models.World;

public abstract class GameState extends State
{
	public GameState(GameStateMachine<? extends World> gameStateMachine)
	{
		super(gameStateMachine);
		this.gameStateMachine = gameStateMachine;
	}
	
	public abstract void update(float deltaTime);
	
	public abstract void draw(float deltaTime);
	
	protected GameStateMachine<? extends World> gameStateMachine;
}
