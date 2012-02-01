package gamoid.core;

public abstract class State
{
	public State(StateMachine<? extends State> stateMachine)
	{
		this.stateMachine = stateMachine;
	}
	
	public void before() {}
	
	public void after() {}
	
	protected StateMachine<? extends State> stateMachine;
}
