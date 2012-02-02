package gamoid.game.controllers;

import java.util.HashMap;
import java.util.Map;

public class StateMachine<S extends State> extends HashMap<String, S>
{
	public void changeState(String newState)
	{
		if(currentState != null)
			currentState.after();
		currentState = get(newState);
		currentState.before();
	}
	
	public Map<String, Object> getExtra() { return extra; }
	
	protected S currentState;
	protected Map<String, Object> extra = new HashMap<String, Object>();
}
