package cdc.app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cdc.maze.MazeCreator;
import cdc.maze.Node;
import cdc.search.ManhattanHeuristic;
import cdc.search.Search;
import cdc.search.SearchResult;
import cdc.test.TestMazeApp_allClosed;
import cdc.test.TestMazeApp_allOpen;

/**
 * The maze solver application.
 * @author Christopher D. Canfield
 */
public class MazeApp extends JFrame
{
	public static void main(String[] args)
	{
		// Change this to new TestMazeApp_allOpen() or TestMazeApp_allClosed()
		// to run the two test apps.
		MazeApp mazeApp = new MazeApp();
		mazeApp.run();
	}
	
	/** For JFrame. */
	private static final long serialVersionUID = -1670419072941353469L;
	
	/** The x pixel location of the first node. **/
	private static final int FIRST_NODE_X = 50;
	/** The y pixel location of the first node. **/
	private static final int FIRST_NODE_Y = 100;
	/** The number of pixels per node. **/
	private static final int PIXELS_PER_NODE = 40;
	
	/** The maze, as an array of nodes. **/
	private Node[][] maze = null;
	
	/** The start Node; used for drawing the node. **/
	private Node start = null;
	/** The exit Node; used for drawing the node. **/
	private Node exit = null;
	
	/** 
	 * The result returned by the A* search, which includes the path
	 * as well as the set of searched nodes.
	 */
	private SearchResult searchResult = null;
	
	/**
	 * Instantiates the maze solver.
	 */
	public MazeApp()
	{
		super("Canfield A* Maze Solver");
		setSize(500, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		// TODO: add a mouse listener if it is needed.
		//addMouseListener();
	}
	
	/**
	 * Prompts the user for a maze seed, generates the maze, and solves it.
	 */
	public void run()
	{
		int mazeNumber;
		try
		{
			// Ask the user for a maze number. The maze number corresponds
			// to the maze definitions in the file.
			mazeNumber = getMazeNumberFromUser();
		}
		catch (NumberFormatException e)
		{
			// If the user cancelled the seed dialog box, or entered an invalid
			// value, cancel the run attempt.
			return;
		}
		
		// Load the maze specified by the user.
		maze = MazeCreator.loadMaze(mazeNumber);
		
		// Get the maze's start and end nodes.
		start = MazeCreator.getEntrance(maze);
		exit = MazeCreator.getExit(maze);
		
		// Perform an A* search, and get the path from the start to the goal.
		searchResult = Search.aStar(start, exit, new ManhattanHeuristic());
		
		// Print the path to the console.
		System.out.println("Path: " + searchResult.toString());

		// Repaint the JFrame to ensure that the visualization of the search appears.
		repaint();
	}
	
	/**
	 * Sets the maze. Used by test applications.
	 * @param maze Reference to the instantiated maze.
	 */
	protected void setMaze(Node[][] maze)
	{
		this.maze = maze;
	}
	
	/**
	 * Sets the search result. Used by test applications.
	 * @param result Reference to an A* search result.
	 */
	protected void setSearchResult(SearchResult result)
	{
		searchResult = result;
	}
	
	/**
	 * Gets the search result. Used by test applications.
	 * @return Reference to the A* search result, or null
	 * if no result exists.
	 */
	protected SearchResult getSearchResult()
	{
		return searchResult;
	}
	
	/**
	 * Sets the start node. Used by test applications.
	 * @param start The maze's start node.
	 */
	protected void setStart(Node start)
	{
		this.start = start;
	}
	
	/**
	 * Sets the exit node. Used by test applications.
	 * @param exit The maze's exit node.
	 */
	protected void setExit(Node exit)
	{
		this.exit = exit;
	}
	
	/**
	 * Prompts the user for a number which corresponds to the maze definition
	 * in the maze file.
	 * @return The maze number.
	 * @throws NumberFormatException If an invalid value is entered by the user,
	 * or if the user cancelled the input box.
	 */
	private int getMazeNumberFromUser() throws NumberFormatException
	{
		final String text = "Enter maze number from mazes.txt file:";
		final String title = "Maze Number";
		
		String returnValue = JOptionPane.showInputDialog(this, text, title, JOptionPane.PLAIN_MESSAGE);
		return Integer.parseInt(returnValue);
	}
	
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		// Write text at the top of the JFrame.
		g.setColor(Color.BLACK);
		g.drawString("Light Green: Entrance", 115, 55);
		g.drawString("Dark Green: Exit", 115, 75);
		g.drawString("Green: Path", 300, 55);
		g.drawString("Blue: Searched", 300, 75);
		
		if (maze != null)
		{
			drawSearchResult(g);
			drawStartAndExit(g);
			drawMaze(g);
		}
		
		// Write text at the bottom of the JFrame.
		g.setColor(Color.BLACK);
		g.drawString("BU MET CS664 | Christopher Canfield", 
				150, FIRST_NODE_Y + 30 + PIXELS_PER_NODE * 10);
	}
	
	/**
	 * Draw the searched set followed by the path.
	 * @param g The graphics context.
	 */
	private void drawSearchResult(Graphics g)
	{
		// Ensure that a searchResult has been returned by the A* algorithm.
		if (searchResult != null)
		{
			// Draw the set of searched nodes.
			for (Node node : searchResult.getSearchedNodes())
			{
				node.draw(g, FIRST_NODE_X, FIRST_NODE_Y, PIXELS_PER_NODE);
			}
			
			// Ensure that a valid path was found by the A* algorithm.
			if (searchResult.getPath() != null)
			{
				// Draw the path.
				for (Node node : searchResult.getPath())
				{
					node.draw(g, FIRST_NODE_X, FIRST_NODE_Y, PIXELS_PER_NODE);
				}
			}
		}
	}
	
	/**
	 * Draw the start and exit node.
	 * @param g The graphics context.
	 */
	private void drawStartAndExit(Graphics g)
	{
		if (start != null) start.draw(g, FIRST_NODE_X, FIRST_NODE_Y, PIXELS_PER_NODE);
		if (exit != null) exit.draw(g, FIRST_NODE_X, FIRST_NODE_Y, PIXELS_PER_NODE);
	}
	
	/**
	 * Draw all nodes in the maze.
	 * @param g The graphics context.
	 */
	private void drawMaze(Graphics g)
	{
		// Draw each node in the maze.
		for (Node[] m : maze)
		{
			for (Node node : m)
			{
				node.draw(g, FIRST_NODE_X, FIRST_NODE_Y, PIXELS_PER_NODE);
			}
		}
	}
}
