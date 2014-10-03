// Name: 				Date:

   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import java.awt.Image;
   /***************************************************************
	*The Barricade class is used in the game SpaceInvader. It acts 
	*as a protection for the ship to avoid enemy fire.             
	***************************************************************/
   public class Barricade
   {
    /***************************************************************
   *The int value myX and myY determine the the X and Y position
   *of the Barricade 
   ***************************************************************/
      private int myX, myY;
   /***************************************************************
   *Image myI is used to draw the image in the panel, which it 
   *receives from ImageIcon myII
   ***************************************************************/
      private Image myI;
   /***************************************************************
   *The ImageIcon is used to retreive the image file 
   ***************************************************************/
      private ImageIcon myII;
   /***************************************************************
   *The int myXWidth and myYWidth determine the horizontal and
   *vertical length of the Barricade
   ***************************************************************/
      private int myXWidth, myYWidth;
      
   
   /***************************************************************
   *This is the default constructor for the Barricade class. int myX
   *and myY are both equal to 250. myXWidth and myYWidth are both equal
   *to 35. ImageIcon myII is equal to "Barricade.jpg", while Image myI
   *is set to equal to the image of myII
   ***************************************************************/
      public Barricade()          
      {
         myX = 250;
         myY = 250;
         myXWidth = 35;
         myYWidth = 35;
         myII = new ImageIcon("Barricade.jpg");    
         myI = myII.getImage();
      
      }
   
   /***************************************************************
   *This is a constructor for the Barricade class. It takes arguements
   *for int myX, myY, myXWidth, and myYWidth ImageIcon myII is equal 
   *to "Barricade.jpg", while Image myI is set to equal to the image
   *of myII
   ***************************************************************/	
      public Barricade(int x, int y, int xWidth, int yWidth)
      {
         myX = x;
         myY = y;
         myXWidth = xWidth;
         myYWidth = yWidth;
      
         myII = new ImageIcon("Barricade.jpg");    
         myI = myII.getImage();
      }
      
    /***********************************
    *This method return the int myX
    ***********************************/
      public int getX()
      {
         return myX;
      }
      
    /***********************************
    *This method return the int myY
    ***********************************/
      public int getY()
      {
         return myY;
      }
      
    /***********************************
    *This method return the int myXWidth
    ***********************************/
      public int getXWidth()
      {
         return myXWidth;
      }
      
    /***********************************
    *This method return the int myYWidth
    ***********************************/
      public int getYWidth()
      {
         return myYWidth;
      }
      
    /***********************************
    *This method return the Image myI
    ***********************************/
      public Image getImage()
      {
         return myI;
      }
      
     /******************************************************
     *This method sets myX to int x, the arguement it takes
     ******************************************************/
      public void setX(int x)
      {
         myX = x;
      }
      /******************************************************
     *This method sets myY to int y, the arguement it takes
     ******************************************************/
      public void setY(int y)
      {
         myY = y;
      }
   	/*****************************************************
     *This method sets myXWidth to int xWidth, the arguement
     *it takes
     ******************************************************/
      public void setXWidth(int xWidth)
      {
         myXWidth = xWidth;
      }
     /******************************************************
     *This method sets myYWidth to int yWidth, the arguement
     *it takes
     ******************************************************/
      public void setYWidth(int yWidth)
      {
         myYWidth = yWidth;
      }
   /******************************************************
   *Collide method is used to simulate collision with the 
   *Barricade. It will make the Barricade look and feel 
   *damaged during the process of the game
   ******************************************************/
      public void collide()
      {
         myY = 800;
         myX = 800;
      }
     
    
      
   
       
      	
   }