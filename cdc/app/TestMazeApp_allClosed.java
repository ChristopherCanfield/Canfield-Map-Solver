package cdc.app;

import cdc.maze.MazeCreator;
import cdc.maze.MazeLocation;
import cdc.maze.Node;

/**
 * An application used for testing the maze. All squares are set to Open.
 * @author Christopher D. Canfield
 */
public class TestMazeApp_allClosed extends MazeApp
{
	private static final long serialVersionUID = 540783521725683744L;

	@Override
	public void run()
	{
		Node[][] maze = MazeCreator.generateAllClosed();
		setMaze(maze);
	}
}
