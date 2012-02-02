package gamoid.game.controllers;

public abstract class GameState extends State
{
	public GameState(GameStateMachine gameStateMachine)
	{
		super(gameStateMachine);
		this.gameStateMachine = gameStateMachine;
	}
	
	public abstract void update(float deltaTime);
	
	public abstract void draw(float deltaTime);
	
	protected GameStateMachine gameStateMachine;
}
