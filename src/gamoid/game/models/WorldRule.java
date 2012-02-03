package gamoid.game.models;

// закон природы, о да мы боги))
public abstract class WorldRule<W extends World>
{
	public WorldRule(W world)
	{
		this.world = world;
	}	
	// применить это правило
	public abstract void apply();
	
	protected W world;
}
