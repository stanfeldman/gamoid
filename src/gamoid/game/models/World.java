package gamoid.game.models;

import java.util.ArrayList;
import java.util.List;
import gamoid.game.models.Cell;
import gamoid.game.models.Game;
import gamoid.game.models.Player;

// Игровой мир
public class World 
{
	public World(Game game, int width, int height)
	{
		this.game = game;
		this.width = width;
		this.height = height;
		cells = new boolean[width][height];
	}
	
	public void update(float deltaTime)
	{
		if(game.isOver())
			return;
		// Жизнь в этом мире происходит по правилам
        for(WorldRule rule : rules)
        	rule.apply();
        updateCells();
	}
	
	public void draw(float deltaTime)
	{
		for(Player player : players)
			player.draw();
	}
	
	private void updateCells()
	{
		for(int x = 0; x < width; ++x)
			for(int y = 0; y < height; ++y)
				cells[x][y] = false;
		// В этих ячейках сейчас кто-то есть
		for(Player player : players)
			for(Cell cell : player.getPlace())
				cells[cell.x][cell.y] = true;
	}
	
	public Game getGame() { return game; }
	
	public boolean [][] getCells() { return cells; }
	
	public int getWidth() { return width; }
	
	public int getHeight() { return height; }
	
	public List<Player> getPlayers() { return players; }
	
	public List<WorldRule> getRules() { return rules; }
	
	// логическая ширина нашего мира
	protected final int width;
	// логическая высота нашего мира
	protected final int height;
	protected Game game;
	protected boolean cells[][];
	protected List<Player> players = new ArrayList<Player>();
	protected List<WorldRule> rules = new ArrayList<WorldRule>();
}
