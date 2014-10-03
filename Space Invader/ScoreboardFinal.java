   //Name______________________________ Date_____________
   import javax.swing.*;
   import java.awt.event.*;
   import java.awt.*;
   import javax.swing.JOptionPane;
   import java.io.*;
   import java.util.*;
	/**************************************************************************
	* The scoreboard keeps track of the points earned and displayed them. If
	*the game is won or the ship is destroyed, the score and time are reset to 0. 
	*It can also keep track of the high score. Completing the game in under 45 
	*seconds makes the scoreboard give a bonus.
	*****************************************************************************/
   public class ScoreboardFinal extends JPanel
   {
      private JLabel scoreboard;
      private int score,time;
      private String[] scores;
      private String highScore, yourScore;
      private javax.swing.Timer t;
   	/***********************************************************************
   	* Instantiates a ScoreboardFinal with a time, score, and highscore of
   	*0. The scoreboard's background is black, to match the rest of the panel.
   	*JLabels displaying the score, time and highscore are also created. Finally,
   	*a timer that keeps track of the seconds spent is instantiated and started.
   	***************************************************************************/
      public ScoreboardFinal()
      {
         setLayout(new GridLayout(1, 2));
         time=0;
         score = 0;
         setBackground(Color.BLACK);
         highScore = "None.........., 0";
         setForeground(Color.WHITE);      
         JLabel score = new JLabel("Score: ", SwingConstants.RIGHT);
         score.setBackground(Color.BLACK);
         score.setForeground(Color.WHITE); 
         add(score);
         scoreboard = new JLabel("0     Time:0");
         scoreboard.setBackground(Color.BLACK);
         scoreboard.setForeground(Color.WHITE); 
         scoreboard.setHorizontalAlignment(SwingConstants.LEFT);
         t = new javax.swing.Timer(1000, new Listener());
         t.start();
         add(scoreboard);
      }
   	/********************************************************************************
   	* This method causes the score to increase by 100. Since the method is called
   	*from the panel whenever a SpaceInvader is destroyed, and there are 24 enemies,
   	*the score caps at 2400 and the bonus is added if appropriate.
   	*********************************************************************************/
      public void update()
      {
         score += 100;
         scoreboard.setForeground(Color.WHITE);
         scoreboard.setText("" + score+"     Time"+time);
         if(score==2400)
         {
            t.stop();
            scoreboard.setText(""+(score+this.getBonus())+"    Time"+time);
         }
      }
   	/******************************************************************************
   	* Updates the time on the JPanel. Stops the timer when score caps at 2400.
   	*******************************************************************************/
      public void updateTime()
      {
         scoreboard.setForeground(Color.WHITE);
         scoreboard.setText("" + score+"     Time:"+time);
         if(score==2400)
         {
            t.stop();
            scoreboard.setText(""+(score+this.getBonus())+"    Time:"+time);
         }
      }
   	/************************************************************
   	* Resets the scoreboard to a score and time of 0 when called.
   	*************************************************************/
      public void reset()
      {
         score = 0;
         time=0;
         scoreboard.setForeground(Color.WHITE);
         scoreboard.setText("" + score);
      }
   	/************************************************************
   	* Adds the bonus to the score.
   	*@return score after adding bonus
   	************************************************************/
      public int getScoreWithBonus()
      {
         return score+getBonus();
      }
   	/************************************************************
   	* Returns the private int score. @return score
   	************************************************************/
      public int getScore()
      {
         return score;
      }
      public int getTime()
      {
         return time;
      }
   	/**********************************************************************
   	* Calculates and returns the bonus, depending on the time taken to win.
   	*@return bonus
   	***********************************************************************/
      public int getBonus()
      {
         if(time <= 45)
         {
            return 5000;
         }
         if (time<=50)
         {
            return 1000;
         }
         else if (time<60)
         {
            return 600;
         }
         else if(time<80)
         {
            return 300;
         }
         else
         {
            return 0;
         }
      }
      public void EndlessScore()
      {
         score+=10;
         scoreboard.setText("" + score+"     Time"+time);
      }
   	/***********************************************************
   	* Adds 1 to time every 1000 milliseconds (1 second).
   	************************************************************/
      private class Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {       
            time++;
         }
      }        
   }
