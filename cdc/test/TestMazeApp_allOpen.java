package cdc.test;

import cdc.app.MazeApp;
import cdc.maze.MazeCreator;
import cdc.maze.MazeLocation;
import cdc.maze.Node;
import cdc.search.ManhattanHeuristic;
import cdc.search.Search;

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
		
		// Get the maze's start and end nodes.
		Node startNode = MazeCreator.getEntrance(maze);
		setStart(startNode);
		Node exitNode = MazeCreator.getExit(maze);
		setExit(exitNode);
		
		// Perform an A* search, and get the path from the start to the goal.
		setSearchResult(Search.aStar(startNode, exitNode, new ManhattanHeuristic()));
		
		// Print the path to the console.
		System.out.println("Path: " + getSearchResult().toString());

		// Repaint the JFrame to ensure that the visualization of the search appears.
		repaint();
	}
}
