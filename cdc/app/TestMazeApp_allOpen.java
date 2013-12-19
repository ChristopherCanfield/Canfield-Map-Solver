package cdc.app;

import cdc.maze.MazeCreator;
import cdc.maze.MazeLocation;
import cdc.maze.Node;

/**
 * An application used for testing the maze. All squares are set to Open.
 * @author Christopher D. Canfield
 */
public class TestMazeApp_allOpen extends MazeApp
{
	private static final long serialVersionUID = 540783521725683744L;

	@Override
	public void run()
	{
		MazeLocation start = new MazeLocation(0, 0);
		MazeLocation exit = new MazeLocation(9, 9);
		Node[][] maze = MazeCreator.generateAllOpen(start, exit);
		setMaze(maze);
	}
}
