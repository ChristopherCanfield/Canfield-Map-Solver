package cdc.app;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickController extends MouseAdapter
{
	private MazeApp app = null;
	
	ClickController(MazeApp app)
	{
		this.app = app;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		app.run();
	}
}
