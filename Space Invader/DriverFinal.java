   import javax.swing.JFrame;
	/************************************************************
	*The Driver's job is to instantiate the panel, to view the
	*program, and run the program.
	*************************************************************/
   public class DriverFinal
   {
      static JFrame frame = new JFrame();
   
      public static void main(String[] args)
      { 
         frame= new JFrame("Space Invader");
         frame.setSize(510, 550);
         frame.setLocation(100,50);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new Menu());
         frame.setVisible(true);
      }
      public static void start()
      {
         frame= new JFrame("Space Invader");
         frame.setSize(510, 550);
         frame.setLocation(100,50);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new Menu());
         frame.setVisible(true);
      
      }
      public static void dispose()
      {
         frame.dispose();
         System.gc();
      }
   
   }