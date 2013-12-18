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

public class MazeApp extends JFrame
{
	public static void main(String[] args)
	{
		MazeApp mazeApp = new MazeApp();
		mazeApp.run();
	}
	
	/** For JFrame. */
	private static final long serialVersionUID = -1670419072941353469L;
	
	/** The x pixel location of the first node. **/
	private static final int FIRST_NODE_X = 20;
	/** The y pixel location of the first node. **/
	private static final int FIRST_NODE_Y = 100;
	/** The number of pixels per node. **/
	private static final int PIXELS_PER_NODE = 20;
	
	/** The maze, as an array of nodes. **/
	private Node[][] maze;
	
	public MazeApp()
	{
		super("Canfield A* Maze Solver");
		setSize(400, 500);
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
		// Ask the user for a seed.
		int seed = getSeedFromUser();
		Node[][] maze = MazeCreator.generateMaze(seed);
		Node start = MazeCreator.getEntrance(maze);
		Node end = MazeCreator.getExit(maze);
		
		SearchResult result = Search.aStar(start, end, new ManhattanHeuristic());
		
	}
	
	private int getSeedFromUser()
	{
		final String text = "Enter maze seed:";
		final String title = "Maze Seed";
		
		String returnValue = JOptionPane.showInputDialog(this, text, title, JOptionPane.PLAIN_MESSAGE);
		// TODO: check for null.
		return Integer.parseInt(returnValue);
	}
	
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		if (maze != null)
		{
			// TODO: draw the searched nodes, followed by the path.
			
			// Draw each node in the maze.
			for (Node[] mazeColumn : maze)
			{
				for (Node node : mazeColumn)
				{
					node.draw(g, FIRST_NODE_X, FIRST_NODE_Y, PIXELS_PER_NODE);
				}
			}
		}
	}
}
