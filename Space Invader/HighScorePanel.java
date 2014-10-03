//2013ajiang		Final Project

   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import java.io.File;
   import java.util.*;
   
   public class HighScorePanel extends JPanel
   {
      private static final int FRAME = 600;
      private JLabel title;
      private BufferedImage myImage;
      private Graphics myBuffer;
      private ImageIcon background = new ImageIcon("dark.jpg");
   	
      public HighScorePanel()
      {
         myImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
         myBuffer = myImage.getGraphics();
         myBuffer.drawImage(background.getImage(), 0, 0, FRAME, FRAME, null);
      	
         // setLayout(new GridLayout(6, 1));
      // 
         // JLabel title = new JLabel("High Scores");
         // title.setFont(new Font("serif", Font.BOLD, 50));
         // title.setHorizontalAlignment( SwingConstants.CENTER);
         // add(title);
         try{
            Scanner yang = new Scanner(new File("hs.txt"));
            PriorityQueue<HighScore> pq= new PriorityQueue<HighScore>();
            while (yang.hasNext())
            {
               pq.add(new HighScore(Integer.parseInt(yang.next()), yang.next()));
            }
            for (int x =0;x<5;x++)
            {
               System.out.print(yang.next());
               HighScore temp = pq.remove();
               JLabel score  = new JLabel(temp.getScore()+"           " +temp.getName());
               score.setHorizontalAlignment ( SwingConstants.CENTER);
               add (score);
            }
         }
            catch (Exception e)
            {
            
            }     
         addKeyListener( new Key() );
         setFocusable(true);
      }
   	
      private class Key extends KeyAdapter
      {
         public void keyPressed(KeyEvent e)
         {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
               HighScoreDriver.dispose();
               DriverFinal.start(); //add start method
            }   
         }
      }
   }