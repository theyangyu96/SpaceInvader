   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import java.awt.Image;

/*****************************************************************
*The Bullet class makes a bullet, which fires on a vertical axis.
*It uses the ints bDY, bX, and bY. It contains the methods fire, 
*fireAlien, drawBullet, collide, and modifiers and acessers. 	  
******************************************************************/
    public class Bullet 
   { 
   /********************************************************************
   * The ints bX and bY contain the value of the x and y coordinates of 
   *the bullet. Int bDY is the bullets delta y value, or it's vertical 
   *speed 
   *********************************************************************/
      public int bDY, bX, bY;
   	
   /*****************************************************************
   *This is the default constructor for Bullet. It sets the bullet's 
   *speed to 5, and the bullets y coordinate to 450.   
   ******************************************************************/
       public Bullet()
      {
         bDY = 5;
         bY = 450;
      }
   /****************************************************************************
   * This is the method controling the bullet's movement. When called, the
   *  bullet's y value changes by -bDY, moving the bullet upwards on the screen.   
   ****************************************************************************/
       public void fire()
      { 
         bDY = 5;
         bY = bY - bDY;	
      }
   	/*************************************************************************
     * This is the method controling the bullet's movement. When called, the
     *  bullet's y value chages by bDY, moving the bullet down on the screen.
     * It is called fireAlien because the Alien is the only object that requires a
     * bullet to move down on the screen.     
     *****************************************************************************/
       public void fireAlien()
      {
         bDY = 5;
         bY = bY + bDY;
      }
   /****************************************************************************
   * Returns the value of bDY, or the speed of the bullet.
   ****************************************************************************/
       public int getbDY()
      {
         return bDY;
      }
   /****************************************************************************
   * Returns the value of bY, or bullets Y coordinate. 
   ****************************************************************************/
       public int getbY()
      {
         return bY;
      }
	/******************************************************  
   * Returns the value of bX, the bullets Y coordinate.
   ******************************************************/
       public int getbX()
      {
         return bX;
      }
   /***************************************************************************************
   * Sets the value of bDY, bullets speed, to int z, the argument that this method requires
   ****************************************************************************************/
       public void setbDY(int z)
      {
         bDY=z;
      }
	/**********************************************************************************************
   * Sets the value of bX, bullets x coordinate, to int a, the argument that this method requires
   ***********************************************************************************************/
       public void setbX(int a)
      {
         bX = a;
      }
	/**********************************************************************************************
   * Sets the value of bY, bullets y coordinate, to int a, the argument that this method requires
   ***********************************************************************************************/
       public void setbY(int a)
      {
         bY = a;
      }
	/**********************************************************************************************
   * Draws the bullet as a filled oval with a width and height of 5. It's x coordinate is the value of 
	* bX - 7, and it's y coordinate is the value of bY. 
   ***********************************************************************************************/
       public void drawBullet(Graphics g)
      {
         g.fillOval(bX-7, bY, 5, 5);
      }
	/**********************************************************************************************
   * Sets the value of bY and bX (it's x and y coord.) to -500, moving the bullet off the screen.
   ***********************************************************************************************/
       public void collide()
      {
         bY = -500;
         bX = -500;
      }
   }
