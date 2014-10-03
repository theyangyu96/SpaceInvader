   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import java.awt.Image; 
   import java.util.ArrayList;
	/*********************************************************************************************
	*The SpaceInvader is the classic enemy of the game.
	*It keeps track of its position and movement distance with ints, and is able to fire
	*bullets at your ship. They move in a uniform pattern and maintain a constant formation. Each
	*SpaceInvader has an array of Bullets as well.
	**********************************************************************************************/
   public class SpaceInvader
   {
   	/*****************************************************************
   	* Private ints that change the speed and location of the alien
   	*****************************************************************/
      private int dY, myX, myY, dX;
     /*****************************************************************
   	* Private ints that change the speed and location of the bullet 
   	*that is made by the alien
   	*****************************************************************/ 
      private int bDY, bX, bulletX;
   	/*****************************************************************
   	* Private ints that is the number of bullets fired by an alien
   	*****************************************************************/
      int numbullets = 0;
   	/*****************************************************************
   	* Declares and array of bullets.
   	*****************************************************************/
      public Bullet[] bullets; 
   	/*****************************************************************
   	* Makes a boolean telling an alien to fire or not.
   	*****************************************************************/
      private boolean fireAlien = false;
   	/*****************************************************************
   	* Image that gets the file from the ImageIcon
   	*****************************************************************/
      private Image myI;
   	/*****************************************************************
   	* Timer controling the length of time the explosion pic. lasts on
   	*the screen.
   	*****************************************************************/
      private Timer t1;
   	/*****************************************************************
   	*ImageIcon that gets the image from the comp.
   	*****************************************************************/
      private ImageIcon myII;
   	/*****************************************************************
   	* Private int that shows whether or not the image of the alien has
   	*been changed. 
   	*****************************************************************/
      private int myIII = 0;
   	/*******************************************************************************
   	*A SpaceInvader is constructed at the coordinates (x, y). It is given an image, 
   	*movement distances,and a standard arsenal of Bullets.
   	*@param x		initial x-coordinate
   	*@param y		initial y-coordinate
   	**********************************************************************************/
      public SpaceInvader(int x, int y)
      {
         myX = x;
         bDY = 1;
         bullets = new Bullet[200];
         myY = y;
         myII = new ImageIcon("spaceinvader.jpg");
         myI = myII.getImage();
         dX = 8;
         dY = 40;
      }
   	/************************************************************************************
   	* When the SpaceInvader is destroyed, its image is replaced by a fiery explosion.*
   	************************************************************************************/
      public void setImage()
      {
         myII = new ImageIcon("Explode.gif");
         myI = myII.getImage();
         myIII = 1;
      }
   	/************************************************************************************
   	* Moves the SpaceInvader a little bit to the right, as long as it is not too close
   	*to the right edge.
   	************************************************************************************/
      public void moveRight()
      {
         if(myX < 476)
         {
            myX += dX;
         }
      }
   	/************************************************************************************
   	* Moves the SpaceInvader a little bit to the left, as long as it is not too close
   	*to the left edge.
   	************************************************************************************/
      public void moveLeft()
      {
         if(myX > 49)
         {
            myX -= dX;
         }
      }
   	/************************************************************************************
   	* Moves the SpaceInvader one row down.
   	************************************************************************************/
      public void moveDown()
      {
         myY += dY;
      }
   	/************************************************************************************
   	* Returns an int which denotes whether or not the SpaceInvader has been destroyed.
   	*@return int which shows wheter or not SpaceInvader has exploded
   	************************************************************************************/
      public int getImageOfExplosion()
      {
         return myIII;
      }
   	/************************************************************************************
   	* Returns the Image of the SpaceInvader.
   	*@return image of SpaceInvader
   	************************************************************************************/
      public Image getImage()
      {
         return myI;
      }
   	/************************************************************************************
   	* Returns the private x-coordinate.
   	*@return x-coordinate
   	************************************************************************************/
      public int getX()
      {
         return myX;
      }
   	/************************************************************************************
   	* Returns the private y-coordinate.
   	*@return y-coordinate
   	************************************************************************************/
      public int getY()
      {
         return myY;
      }
   	/************************************************************************************
   	* Returns the private increment of horizontal movement.
   	*@return increment of horizontal movement
   	************************************************************************************/
      public int getdx() 
      { 
         return dX; 
      }
   	/************************************************************************************
   	* Changes the private x-coordinate to the value of a.
   	*@param a		assigns a to x-coordinate
   	************************************************************************************/
      public void setX(int a)
      {
         myX = a;
      }
   	/************************************************************************************
   	* Changes the private y-coordinate to the value of a.
   	*@param a		assigns a to y-coordinate
   	************************************************************************************/
      public void setY(int a)
      {
         myY = a;
      }
      /************************************************************************************
   	* Changes the private increment of horizontal movement to the value of a.
   	*@param a	assigns a to increment of horizontal movement
   	************************************************************************************/
      public void setDx(int b)
      {
         dX = b;
      }
   	/***********************************************************
   	*Changes the invaders y coord. to 800
   	***********************************************************/
      public void collide(String a)
      {
         setY(800);
      }
   	/************************************************************************************
   	* Sets the image to an explosion, then sets a timer which lasts until the explosion stops.
   	************************************************************************************/
      public void collide()
      {
         this.setImage();
         t1 = new Timer(1300, new Listener1()); 
         t1.start();
      }
   	/************************************************************************************
   	* After the explosion stops, the timer sets the y-coordinate to an off-screen value, making
   	*the invader appear to be destroyed.
   	************************************************************************************/
      private class Listener1 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {       
            t1.stop();
            setY(800);
         }
      }
   
   	/************************************************************************************
   	* Returns boolean fireAlien.
   	*@return fireAlien
   	************************************************************************************/
      public boolean getFireAlien()
      {
         return fireAlien;
      }
   	/************************************************************************************
   	* Sets fireAlien equal to b.
   	*@param b		assigns b to fireAlien
   	************************************************************************************/
      public void setFireAlien(Boolean b)
      {
         fireAlien = b;
      }
   	/************************************************************************************
   	* Returns int bulletX, the x-coordinate of the bullet being fired.
   	*@return bulletX
   	************************************************************************************/
      public int getbX()
      {
         return bulletX;
      }
   	/************************************************************************************
   	* Returns the bY of bullets[a].
   	*@return bullets[a]'s bY
   	************************************************************************************/
      public int getbY(int a)
      {
         if(a < numbullets)
            return bullets[a].getbY();
         else
            return -1000;
      }
   	/************************************************************************************
   	* Returns the bX of bullets[a].
   	*@return bullets[a]'s bX
   	************************************************************************************/
      public int getbX(int a)
      {
         if(a < numbullets)
            return bullets[a].getbX();
         else
            return -1000;
      }
   	/************************************************************************************
   	* Sets bulletX equal to myX, so the bullet will fire in a vertical line.
   	************************************************************************************/
      public void setBulletX()
      {
         bulletX = myX;
      }
   	/************************************************************************************
   	* Makes new bullets , even if numBullets fills up.
   	************************************************************************************/
      public void makeBullet()
      {
         if(numbullets == 199)
            numbullets = 0;
         bullets[numbullets] = new Bullet();
         bullets[numbullets].setbX(bulletX);
         bullets[numbullets].setbY(myY);
      }
   	/************************************************************************************
   	* Increases numBullets by an increment of 1.
   	************************************************************************************/
      public void setNumBullets()
      {
         numbullets++;
      }
   	/************************************************************************************
   	* Makes the SpaceInvader fire. The firing method is called by a Bullet.
   	************************************************************************************/
      public void fire()
      {
         for(int a = 0; a < numbullets; a++)
         {    
            bullets[a].fireAlien();	
         }
      }
   	/************************************************************************************
   	* Draws bullets as they move across the screen.
   	*@param g		Graphics object that draws the bullets
   	************************************************************************************/
      public void drawBullet(Graphics g)
      {
         for(int b = 0; b < numbullets; b++)
         {
            if(bullets[b].getbY() > 0 && bullets[b] != null)
               bullets[b].drawBullet(g);     
         }
      }
   	/************************************************************************************
   	* Makes the bullet call method collide() if hit by a bullet. @param c		
   	************************************************************************************/
      public void collide(int c)
      {
         if(c < numbullets)
            bullets[c].collide();
      }
   	/************************************************************************************
   	* Tells if the SpaceInvader has been destroyed and sent to an offscreen
   	*portion of the panel.
   	*@return 0 if dead, 1 if still onscreen.
   	************************************************************************************/
      public int areYouDead()
      {
         if(myY>500)
         {
            return 0;
         }
         else
         {
            return 1;
         }
      }
   }
   
