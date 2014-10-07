package rpncalc;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

import javax.swing.*;

public class RPNCalcFinishedPanel extends JPanel
{
   private JLabel display;
   private JPanel buttonPanel;
   private JPanel stackPanel;
   private boolean start = true;
   
   private Stack<Integer> stack = new Stack<Integer>();
   private Color pacManYellow = new Color(255,246,17);
   private Color pacManPink = new Color(255,72,190);
   private Color pacManBlue = new Color(16,67,234);
  

   public RPNCalcFinishedPanel() 
   {
	  setLayout(new BorderLayout());
  
      display = new JLabel("0");
      display.setFont(getArcadeFont(56f));
      display.setHorizontalAlignment(SwingConstants.RIGHT);
      display.setBackground(Color.black);
      display.setOpaque(true);
      display.setForeground(pacManPink);
      display.setBorder(BorderFactory.createLineBorder(pacManBlue, 3, true));
      add(display, BorderLayout.NORTH);
      
      buttonPanel = new JPanel(new GridLayout(4,4));
      
      ActionListener insert = new InsertAction();
      ActionListener command = new CommandAction();
      
      addButton("7", insert,pacManPink);
      addButton("8", insert,pacManPink);
      addButton("9", insert,pacManPink);
      addButton("/", command,pacManYellow);
      
      addButton("4", insert,pacManPink);
      addButton("5", insert,pacManPink);
      addButton("6", insert,pacManPink);
      addButton("*", command,pacManYellow);

      addButton("1", insert,pacManPink);
      addButton("2", insert,pacManPink);
      addButton("3", insert,pacManPink);
      addButton("-", command,pacManYellow);

      addButton("CLR", command,pacManYellow);
      addButton("0", insert,pacManPink);
      addButton("ENT", command,pacManYellow);
      addButton("+", command,pacManYellow);
      
      buttonPanel.setBackground(Color.black);
      add(buttonPanel, BorderLayout.CENTER);
      
	  
      stackPanel = new JPanel(new GridLayout(10,1));
      stackPanel.setBackground(Color.black);
      syncStackLabels();
      add(stackPanel, BorderLayout.EAST);
      
   }
   
   private void syncStackLabels()
   {
	   stackPanel = new JPanel(new GridLayout(stack.size()+1,1));
	   stackPanel.setBackground(Color.black);
	   JLabel stackTitle = new JLabel("Stack");
	   stackTitle.setFont(getArcadeFont(20f));
	   stackTitle.setForeground(pacManYellow);
	   stackTitle.setBorder(BorderFactory.createLineBorder(pacManBlue, 2, true));
	   stackPanel.add(stackTitle);
	   System.out.println(stack.size());
	   for(int i = stack.size()-1; i >=0; i--)
	   {
		   JLabel stackLabel = new JLabel(""+stack.elementAt(i));
		   stackLabel.setFont(getArcadeFont(20f));
		   stackLabel.setHorizontalAlignment(SwingConstants.CENTER);
		   stackLabel.setForeground(pacManYellow);
		   stackLabel.setBorder(BorderFactory.createLineBorder(pacManBlue, 2, true));
		   stackPanel.add(stackLabel);
		   System.out.println(stack.size());
	   }
	   add(stackPanel,BorderLayout.EAST);

   }
   
   private void addButton(String label, ActionListener listener, Color fg)
   {
      JButton button = new JButton(label);
      button.addActionListener(listener);
      button.setFont(getArcadeFont(36f));
      button.setBackground(Color.BLACK);
      button.setForeground(fg);
      button.setBorder(BorderFactory.createLineBorder(pacManBlue, 3, true));
      buttonPanel.add(button);
   }
     
   
   public Dimension getPreferredSize()
   {
	   return new Dimension(700,350);
   
   }
   
   private Font getArcadeFont(float size)
   {
	   Font arcadeFont = new Font("Arial",Font.BOLD,44);
	   try
	   {
	      File f = new File("ARCADE_R.ttf");
	      FileInputStream in = new FileInputStream(f);
	      Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, in);
	      arcadeFont = dynamicFont.deriveFont(size);
	     
	   }
	   catch(Exception e)
	   {
		  System.out.println("Loading Font Didn't Work");
	   }
	   return arcadeFont;
   }
   
   private class InsertAction implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
    	  if(start)
    	  {
    		  display.setText("");
    		  start = false;
    	  }
    	  display.setText(display.getText()+e.getActionCommand());
      }
   }
   
   private class CommandAction implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
        if(event.getActionCommand()=="ENT")
        {
        	try
        	{
        		stack.push(Integer.parseInt(display.getText()));
        	}
        	catch (Exception e)
        	{
        		System.out.println("Exception");
        	}
        	
        }
        else if(event.getActionCommand()=="+")
        {
        	int op2 = stack.pop();
        	int op1 = stack.pop();
        	int op = op1 + op2;
        	display.setText(op1 + " + " + op2 + " = " + op);
        	stack.push(op);
        }
        else if(event.getActionCommand()=="-")
        {
        	int op2 = stack.pop();
        	int op1 = stack.pop();
        	int op = op1 - op2;
        	display.setText(op1 + " - " + op2 + " = " + op);
        	stack.push(op);
        }
        else if(event.getActionCommand()=="*")
        {
        	int op2 = stack.pop();
        	int op1 = stack.pop();
        	int op = op1 * op2;
        	display.setText(op1 + " * " + op2 + " = " + op);
        	stack.push(op);
        	
        }
        else if(event.getActionCommand()=="/")
        {
        	int op2 = stack.pop();
        	int op1 = stack.pop();
        	int op = op1 / op2;
        	display.setText(op1 + " / " + op2 + " = " + op);
        	stack.push(op);
        }
        else if(event.getActionCommand()=="CLR")
        {
        	stack = new Stack<Integer>();
        	display.setText("0");
        
        }
        
    	start = true;
    	syncStackLabels();
   	    stackPanel.revalidate();
      }
      
   }
   
   
}


