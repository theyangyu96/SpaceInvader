   import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
   import java.awt.Image; 
/*********************************************************************
*Spaceship is the object that you control. It contains methods to 
*control its movement, and fire bullets from the object.  
*********************************************************************/
    public class Spaceship { 
   /*********************************************************************
   * Private ints x and y are the coordinates of the location of the object. 
   *Int dx controls ship speed, and bDX controls the speed of the bullet
   *initiated by the ship. Int bx controls the x coord. of the bullet 
   *initiated, and bulletX also controls the x coord. of the bullet.      
   *********************************************************************/
      private int x, dx, y, bDY, bX, bulletX;
   /*********************************************************************
   *The image of the object
   *********************************************************************/
      private Image myI;
   /*********************************************************************
   *ImageIcon that gets the image of the object from a file
   *********************************************************************/
      private ImageIcon myII;
   /*********************************************************************
   *This in represents the number of bullets fired by the ship. Set to 0 
   *when the game is first started
   *********************************************************************/
      int numbullets = 0;
   /*********************************************************************
   *Declares an array of bullets 
   *********************************************************************/
      public Bullet[] bullets;
   /*********************************************************************
   *Makes a boolean that tells the object whether it should move left or not. 
   *********************************************************************/ 
      public boolean shouldMoveLeft = false;
   /*********************************************************************
   *Makes a boolean that tells the object whether it should move right or not. 
   *********************************************************************/ 
      public boolean shouldMoveRight = false;
   /*********************************************************************
   *Makes a boolean that tells the object whether it should fire bullets or not. 
   *********************************************************************/ 	
      public boolean fire = false;
   /*********************************************************************
   *Default constructor for Spaceship. Sets the coordinate values to the 
   *center of the bottom of the screen, and the speed of it and the bullets
   *it will fire. It also instantiates the bullet array, and sets the image
   *of the object to untitled.jpg. 
   *********************************************************************/ 
       public Spaceship()
      { 
         x = 250; 
         dx = 5;
         bDY = 10;
         bullets = new Bullet[200];
         y = 450; 
         myII = new ImageIcon("untitled.jpg");    
         myI = myII.getImage();
      }
   /*********************************************************************
	*When called, this method makes a bullet in the bullet array at index
	*numbullet. It also sets the x coord. of bullet to bulletX. 
	*********************************************************************/ 
       public void makeBullet()
      {
         if(numbullets == 199)
            numbullets = 0;
         bullets[numbullets] = new Bullet();
         bullets[numbullets].setbX(bulletX);
      }
/*********************************************************************
*Increments the value of numbullets by 1
*********************************************************************/ 
       public void setNumBullets()
      {
         numbullets++;
      }
/*********************************************************************
*Calls the fire method of all bullets in the array under the index of
*numbullets.
*********************************************************************/ 
       public void fire()
      {
         for(int a = 0; a < numbullets; a++)
         {
            if(bullets[a].getbY() > 0)
            {
               bullets[a].fire();	
            }
         }
      }
/*********************************************************************
*Calls the drawBullet method of all bullets in the bullet array under
*the index of numbullets. 
*********************************************************************/ 
       public void drawBullet(Graphics g)
      {
         for(int b = 0; b < numbullets; b++)
         {
            if(bullets[b].getbY() > 0 && bullets[b] != null){
               bullets[b].drawBullet(g);
            }
         }
      }
/*********************************************************************
*Calls the moveRight or moveLeft button depending on the booleans 
*shouldMoveRight and shouldMoveLeft
**********************************************************************/ 
       public void move()
      {
         if(shouldMoveRight)
            moveRight();
         if(shouldMoveLeft)
            moveLeft();
      }
/*********************************************************************
*Changes the x coord. value of the ship by the value dX, moving it towards
*the right on the screen. 
*********************************************************************/ 
       public void moveRight() 
      { 
         if (x < 500-21)
         {
            x+=dx;
         }
      } 
/*********************************************************************
*Changes the x coord. value of the ship by the value -dX, moving it towards
*the left on the screen. 
*********************************************************************/ 		
       public void moveLeft()
      { 
         if (x > 0+30)
         {
            x-=dx;
         }
      } 
/*********************************************************************
*Returns the Image myY, the image of the object. 
*********************************************************************/ 
       public Image getImage()
      {
         return myI;
      }
/*********************************************************************
*Sets the ImageIcon myII to the file specified by the arg. String a. 
*It then sets the Image mI to the image of myII. 
*********************************************************************/ 
       public void setImage(String a)
      {
         myII = new ImageIcon(a);
         myI = myII.getImage();
      }
/*********************************************************************
*Returns x, the x coord. of the ship. 
*********************************************************************/ 
       public int getX()
      { 
         return x; 
      } 
/*********************************************************************
*Returns dx, the horrizontal speed of the ship. 
*********************************************************************/ 
       public int getdx() 
      { 
         return dx; 
      } 
/*********************************************************************
*Returns int by, the y coordinate of the last bullet fired. 
*********************************************************************/ 
       public int getbY()
      {
         int b = bullets[numbullets].getbY();
         return b;
      }
/*********************************************************************
*Returns buulletX, the x coord. of the last bullet fired. 
*********************************************************************/ 
       public int getbX()
      {
         return bulletX;
      }
/*********************************************************************
*Returns int by of the bullet in index int a of the bullet array,
* if int a it less than numbullets 
*********************************************************************/ 
       public int getbY(int a)
      {
         if(a < numbullets)
            return bullets[a].getbY();
         else
            return -1000;
      }
/*********************************************************************
*Returns int bx of the bullet in index int a of the bullet array,
* if int a it less than numbullets  
*********************************************************************/ 
       public int getbX(int a)
      {
         if(a < numbullets)
            return bullets[a].getbX();
         else
            return -1000;
      }
/*********************************************************************
*Sets the value of x, the x coord. of ship, to the given arg  int a.  
*********************************************************************/ 
       public void setX(int a)
      {
         x = a;
      }
/*********************************************************************************
*Sets the value of dx, the horrizontal speed of the ship, to the given arg, int b.  
*********************************************************************************/ 
       public void setDx(int b)
      {
         dx = b;
      }
/*********************************************************************************
*Sets the x coord. of the next bullet to the current x coord. of the ship obj.
*********************************************************************************/ 
       public void setBulletX()
      {
         bulletX = x;
      }
/*********************************************************************************
*Calls the collide method of the bullet obj at the index of the arg. if c is less 
*than numbullets. 
*********************************************************************************/ 
       public void collide(int c)
      {
         if(c <= numbullets)
            bullets[c].collide();
      }
/*********************************************************************************
*Sets the ship objects x coord. to 1000, and y coord. to - 1000, a location off
*of the screen. 
*********************************************************************************/ 
       public void reset()
      {
         x = 1000;
         y = -1000;
      }
   
   }
