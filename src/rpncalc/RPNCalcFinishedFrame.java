package rpncalc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;


public class RPNCalcFinishedFrame extends JFrame
{
   public RPNCalcFinishedFrame()
   {
	   
	   add(new MarqueePanel());
       pack();
   }
}

class MarqueePanel extends JPanel
{
	public MarqueePanel()
	{
		this.setBackground(Color.BLACK);
		this.add(new JLabel(new ImageIcon("mspacmanmarquee.jpg")));
		this.add(new RPNCalcFinishedPanel());
		
	}
	public Dimension getPreferredSize()
	{
		return new Dimension(800,680);
	}
}
