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

public class MazeApp extends JFrame
{
	public static void main(String[] args)
	{
		// Change this to new TestMazeApp_allOpen() or TestMazeApp_allClosed()
		// to run the two test apps.
		MazeApp mazeApp = new TestMazeApp_allClosed();
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
	
	/** 
	 * The result returned by the A* search, which includes the path
	 * as well as the set of searched nodes.
	 */
	private SearchResult searchResult = null;
	
	
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
		int seed;
		try
		{
			// Ask the user for a seed.
			seed = getSeedFromUser();
		}
		catch (NumberFormatException e)
		{
			// If the user cancelled the seed dialog box, or entered an invalid
			// value, cancel the run attempt.
			return;
		}
		
		// Generate the maze using the provided seed.
		maze = MazeCreator.generateMaze(seed);
		
		// Get the maze's start and end nodes.
		Node start = MazeCreator.getEntrance(maze);
		Node end = MazeCreator.getExit(maze);
		
		// Perform an A* search, and get the path from the start to the goal.
		searchResult = Search.aStar(start, end, new ManhattanHeuristic());
		
		// Print the path to the console.
		System.out.println("Path: " + searchResult.toString());

		// Repaint the JFrame to ensure that the visualization of the search appears.
		repaint();
	}
	
	protected void setMaze(Node[][] maze)
	{
		this.maze = maze;
	}
	
	private int getSeedFromUser() throws NumberFormatException
	{
		final String text = "Enter maze seed (integer):";
		final String title = "Maze Seed";
		
		String returnValue = JOptionPane.showInputDialog(this, text, title, JOptionPane.PLAIN_MESSAGE);
		return Integer.parseInt(returnValue);
	}
	
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		if (maze != null)
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
			
			// Draw each node in the maze.
			for (Node[] m : maze)
			{
				for (Node node : m)
				{
					node.draw(g, FIRST_NODE_X, FIRST_NODE_Y, PIXELS_PER_NODE);
				}
			}
		}
		
		g.setColor(Color.BLACK);
		g.drawString("BU MET CS664 | Christopher Canfield", 
				150, FIRST_NODE_Y + 30 + PIXELS_PER_NODE * 10);
	}
}
