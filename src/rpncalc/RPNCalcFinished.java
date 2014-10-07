package rpncalc;

import java.awt.*;
import javax.swing.*;


public class RPNCalcFinished
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               RPNCalcFinishedFrame frame = new RPNCalcFinishedFrame();
               frame.setTitle("RPN Calculator");               
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}
