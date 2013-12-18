package cdc.app;

import java.awt.Graphics;

/**
 * An object that can be drawn.
 * @author Christopher D. Canfield
 */
public interface Drawable
{
	/**
	 * Draws the drawable object.
	 * @param g Reference to the Swing graphics object.
	 * @param startX The starting x pixel location for the maze's (0,0) node.
	 * @param startY The starting y pixel location for the maze's (0,0) node.
	 * @param pixelsPerNode The number of pixels per maze node, in one direction.
	 */
	public void draw(Graphics g, int startX, int startY, int pixelsPerNode);
}
