
   import javax.swing.*;
   import java.io.*;
   import java.util.*;

   public class HighScore 
   {
      private int score;
      private String player;
      public HighScore(int hs , String name)
      {
         score = hs;
         player = name;
      }
      public int CompareTo(HighScore hs)
      {
         return score-hs.getScore();
      }
      public String getName()
      {
         return player;
      }
      public int getScore()
      {
         return score;
      }
   }