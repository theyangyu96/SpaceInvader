   import javax.swing.JFrame;
	/************************************************************
	*The Driver's job is to instantiate the panel, to view the
	*program, and run the program.
	*************************************************************/
   public class HighScoreDriver
   {
      static JFrame frame = new JFrame();
   
      public static void start()
      {
         frame= new JFrame("HighScore");
         frame.setSize(510, 550);
         frame.setLocation(100,50);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new HighScorePanel());
         frame.setVisible(true);
      }
      public static void dispose()
      {
         frame.dispose();
         System.gc();
      }
   }