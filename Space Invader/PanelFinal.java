   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.Image;
   import javax.swing.JOptionPane;


   public class PanelFinal extends JPanel implements KeyListener
   {
   /****************************************************
   *Graphics G is used in the paintCompoent in order to 
   *paint individual items on to the panel
   ****************************************************/
      private Graphics g;
   /*******************************************************
   *Int key and p are used in the keyPressed, keyTyped, 
   *and keyReleased method to allow movement of the spaceShip
   *******************************************************/
      private int key, p;
    /*********************************************************
    *Int d ise used by the spaceInvader class only.
    *spaceInvader ultilize d to determine which direction to
    *move the Alien.
    *********************************************************/
      private int d;
    /*********************************************************
    *Timer t which is used for detecting different types of
    *collision, runs every 5 milliseconds.  
    *********************************************************/
      private Timer t;
    /*********************************************************
    *Timer f which is used for a cooldown mechanism for the 
    *spaceShip firing. 
    *********************************************************/
      private Timer f;
   
    /*********************************************************
    *Timer t2 runs every 600 milliseconds. It determines the 
    *direction of which the alien moves, and resulting the
    *spaceInvader to be in a different position
    *********************************************************/
      private Timer t2;
   
    /*********************************************************
    *Timer tExplosion is used to display the GIF file of 
    *explosion.
    *********************************************************/
      private Timer tExplosion;
   
    /*********************************************************
    *Timer tAlien triggers the method fireAlien(), which makes
    *a random alien fire back at the spaceShip. The Timer goes
    *off every 700 milliseconds.  
    *********************************************************/
      private Timer tAlien, t2Alien;
   
    /*********************************************************
    *Timer sTimer is used to trigger the updateTime() method in
    *scoreboard.
    *********************************************************/
      private Timer sTimer;
    /*********************************************************
    *Spaceship you is the Spaceship you will play as in this game
    *********************************************************/
      private Spaceship you;
    /*********************************************************
    *Barricade[][] b creates a 8x4 matrix of barricade, which
    *protects the player from enemy fire.
    *********************************************************/
      private Barricade[][] b = new Barricade[4][8];
      
    /*********************************************************
    *Barricade[][] b1 creates a 8x4 matrix of barricade, which
    *protects the player from enemy fire.
    *********************************************************/
      private Barricade[][] b1 = new Barricade[4][8];
      
    /*********************************************************
    *Barricade[][] b2 creates a 8x4 matrix of barricade, which
    *protects the player from enemy fire.
    *********************************************************/
      private Barricade[][] b2 = new Barricade[4][8];
      
    /*********************************************************
    *Barricade[][] b3 creates a 8x4 matrix of barricade, which
    *protects the player from enemy fire.
    *********************************************************/
      private Barricade[][] b3 = new Barricade[4][8];
     
    /*********************************************************
    *Barricade[][] b4 creates a 8x4 matrix of barricade, which
    *protects the player from enemy fire.
    *********************************************************/
      private Barricade[][] b4 = new Barricade[4][8];
      
    /*********************************************************
    *JButton reset will only appear under certain condition,
    *pressing it allows the user to restart a new game.
    *********************************************************/
      private JButton reset;
      
    /*********************************************************
    *The booleans menu, gif, win and lose are used to tell the
    *computer whether to display a certain part or not. The 
    *other booleans are to tell the program whether to move, 
    *or let the ship fire again.  
    *********************************************************/
      private boolean fired,recentFired, recentFiredAlien, movedRight, gif, menu, win, lose;
    /*******************************************************************
    *Scoreboard s is where the score and the time are stored and changed
    ********************************************************************/
      private ScoreboardFinal s;
   /*********************************************************
    *SpaceInvaders[][] enemies declares a matrix where the enemies
    *will be instantiated.
    *********************************************************/
      private SpaceInvader[][] enemies;
   /*********************************************************
    *The menuInvader is an SpaceInvader that appears in the menu
    *and when hit, move the game to the next screen.
    *********************************************************/
      private SpaceInvader menuInvader;
   /***********************************
   *In the panel, the makeBoard method is first called, instantiating everything that is needed. 
   *The layouts are also set and the timers needed are instantiated. 
   *The reset method is then called to set the screen to it's starting position
   *A key listener is also added.
   **********************************/
      public PanelFinal()
      {
         makeBoard();
         setLayout(new BorderLayout());
         s = new ScoreboardFinal();
         add(s, BorderLayout.SOUTH);
         add(reset, BorderLayout.NORTH);
         setFocusable(true);
         addKeyListener(this);  
         menuInvader = new SpaceInvader(250, 250);    
         t = new Timer(5, new Listener());
         f = new Timer(800, new Listener2());	
         t2 = new Timer(600, new Listener3());
         tExplosion = new Timer(3000, new Listener4());
         tAlien = new Timer(700, new Listener6());
         t2Alien = new Timer(700, new Listener7());
         sTimer = new Timer(1000, new Listener8()); 
         reset();
         menu = true;  
      }
   	/*********************************************************
    *This method resets the board, scoreboard, and the timers.
    *********************************************************/
      public void reset()
      {
         menu = false;
         p = 0;
         d = 1;
         gif = false;
         win = false;
         lose = false;
         movedRight = false;
         you.reset();
         s.reset();
         you = new Spaceship();
         for(int r = 1; r <= enemies.length; r++)
         {
            for(int c = 1; c <= enemies[0].length; c++)
            {
               enemies[r-1][c-1] = new SpaceInvader(r * 50, c * 30);
            }
         }
         for(int num = 0; num <8; num++)
         {
            for(int col = 0; col < 4; col++)
            {
               b[col][num] = new Barricade(num*5+75,col*5+400,5,5);
               b1[col][num] = new Barricade(num*5+150,col*5+400,5,5);
               b2[col][num] = new Barricade(num*5+225,col*5+400,5,5);
               b3[col][num] = new Barricade(num*5+300,col*5+400,5,5);
               b4[col][num] = new Barricade(num*5+375,col*5+400,5,5);
               
            }
         }
      
         you.setBulletX();
         t.start();
         t2.start();
         f.start();
         tAlien.start();
         t2Alien.start();
         sTimer.start();
      	
      }
   /*********************************************************************
    *This method paints everything onto the screen by calling 
    *numerous submethods such as menuPaint, win, lose, and runGamePaint()
    ********************************************************************/
      public void paintComponent(Graphics g)
      {
         if(menu == true)
         {
            menuPaint(g);
         }
                  
         else
         {
            if(win == true)
            {
               winPaint(g);                     
            }
            else if(lose == true)
            {
               losePaint(g);
            }
            else
            {
               runGamePaint(g);
            }
         		
         }
      }
   /*********************************************************
    *This listener is for timer t, and repaints the board
    *every 5 ms. It also changes the position of the ship, bullets
    *and aliens. It checks for bullet collisions as well.
    *********************************************************/
      private class Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {       
            you.move();
            you.setImage("untitled.jpg");
            if(fired == true)
            {
               you.fire();
            }
         	
            for(int r = 0; r < enemies.length; r++)
            {
               for(int c = 0; c < enemies[0].length; c++)
               {
                  if(enemies[r][c].getFireAlien() == true)
                     enemies[r][c].fire();
                        	
               	                  
               }
            }
            for(int r = 0; r < enemies.length; r++)
            {
            
               for(int c = 0; c < enemies[0].length; c++)
               {
                  for(int z = 0; z < 200; z ++)
                  {
                     if(enemies[r][c].getImageOfExplosion() != 1)
                     {  
                        if(you.getbX(z) < menuInvader.getX() + 15  && you.getbX(z) > menuInvader.getX() - 15 && you.getbY(z) < menuInvader.getY() + 15  && you.getbY(z) > menuInvader.getY() - 15)
                        {
                           you.collide(z);
                           menuInvader.collide();
                           menu = false;
                           reset();
                        
                        }
                        if(you.getbX(z) < enemies[r][c].getX() + 15  && you.getbX(z) > enemies[r][c].getX() - 15 && you.getbY(z) < enemies[r][c].getY() + 15  && you.getbY(z) > enemies[r][c].getY() - 15 )
                        {
                           you.collide(z);
                           enemies[r][c].collide();
                           s.update();
                        }
                        if(you.getX() < enemies[r][c].getX() + 15  && you.getX() > enemies[r][c].getX() - 15 && enemies[r][c].getY() >= 450  && enemies[r][c].getY() <= 550 )
                        {
                           gif = true;
                           reset.setVisible(true);
                           enemies[r][c].collide();
                           if(c < enemies[0].length-1)
                           {                
                              enemies[r][c+1].collide();
                              if(r < enemies.length -1)
                                 enemies[r+1][c+1].collide();
                           }              
                           if(r < enemies.length -1) 
                              enemies[r+1][c].collide();
                           you.setImage("Explode.gif");
                           lose = true;
                           reset.setVisible(false);
                        }
                        if(you.getX()-25 <= enemies[r][c].getbX(z)  && you.getX()+25 > enemies[r][c].getbX(z) && enemies[r][c].getbY(z) >= 470 && enemies[r][c].getbY(z) <= 500)
                        {
                           gif = true;
                           reset.setVisible(true);
                           you.setImage("Explode.gif");
                           enemies[r][c].collide(z);
                           lose = true;
                           reset.setVisible(false);
                        }
                     }
                  }
               }
               int deathValue=0;
               for(int t = 1; t <= enemies.length; t++)
               {
                  for(int y = 1; y <= enemies[0].length; y++)
                  {
                     deathValue+=enemies[t-1][y-1].areYouDead();
                  }
               }
               if (deathValue==0)
               {
                  win = true;
                  reset.setVisible(false);
               }
            
            }
            detectCollide(b, you);
            detectCollide(b1, you);
            detectCollide(b2, you);
            detectCollide(b3, you);
            detectCollide(b4, you);
            for(int r = 1; r <= enemies.length; r++)
            {
               for(int c = 1; c <= enemies[0].length; c++)
               {	
                  detectAlienCollide(b, enemies, r-1,c-1);
                  detectAlienCollide(b1, enemies, r-1,c-1);
                  detectAlienCollide(b2, enemies, r-1,c-1);
                  detectAlienCollide(b3, enemies, r-1,c-1);
                  detectAlienCollide(b4, enemies, r-1,c-1);
               }
               repaint();
            }
         }
      }
   /*********************************************************
    *This listener is for timer f, and prevents the Spaceship obj. 
    * from firing repeatedly. 
    *********************************************************/
      private class Listener2 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {       
            recentFired=false;
         }
      }
   	/*********************************************************
    *This listener is for timer t2, and moves the enemies left,
    *right, or down, depending on their positions. 
    *********************************************************/
      private class Listener3 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            if(d == 0)
            {
               movedRight = false;
               for(int r = 1; r <= enemies.length; r++)
               {
                  for(int c = 1; c <= enemies[0].length; c++)
                  {
                     enemies[r-1][c-1].moveDown();	
                  }
               }
               d++;
            }
            else if(movedRight == false && d < 23)
            {
               for(int r = 1; r <= enemies.length; r++)
               {
                  for(int c = 1; c <= enemies[0].length; c++)
                  {
                     enemies[r-1][c-1].moveRight();	
                     //    enemies[r-1][c-1].moveDown();            
                  }
               }
               d++;
            }
            else if(d == 23)
            {
               movedRight = true;
               for(int r = 1; r <= enemies.length; r++)
               {
                  for(int c = 1; c <= enemies[0].length; c++)
                  {
                     enemies[r-1][c-1].moveDown();	
                     
                  }
               }
               d--;
            }
            else if(movedRight == true && d > 0)
            {
               for(int r = 1; r <= enemies.length; r++)
               {
                  for(int c = 1; c <= enemies[0].length; c++)
                  {
                     enemies[r-1][c-1].moveLeft();	
                     //   enemies[r-1][c-1].moveDown();               
                  }
               }
               d--;
            }
            repaint();
         }
      }
   	/*********************************************************
    *This listener acts based on timer tExplosion, and stops the 
    *explosion of the ship. It also sets the ships pictures to a 
    *blank one so that the explosion doesn't start again
    *********************************************************/
      private class Listener4 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {          
            gif = false;
            you.setImage("blank.jpg");
            repaint();
            tExplosion.stop();
            
         }
      }
   /*********************************************************
    *This listener acts based on button reset, and calls the 
    *method reset. 
    *********************************************************/
      private class Listener5 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            reset();
            reset.setVisible(false);
         }
      }
   /*********************************************************
    *This listener acts based on timer tAlien, and calls the method
    *fireAlien().
    *********************************************************/
      private class Listener6 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            fireAlien();
         }
      }
   	  /*********************************************************
    *This listener acts based on timer t2Alien, and sets 
     *recentFiredAlien to false.
    *********************************************************/
   
      private class Listener7 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {       
            recentFiredAlien=false;
         }
      }
   /*********************************************************
    *This listener acts based on timer sTimer, and calls the
    *method updateTime in scoreboard.
    *********************************************************/
      private class Listener8 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {       
            s.updateTime();
         }
      }		 	
      	
      /*************************************************
   	*This method checks for the different key inputs,
   	*determining wheather the Spaceship will move right,
   	move left, or fire.
   	*************************************************/
      public void keyPressed(KeyEvent e) 
      { 
         key = e.getKeyCode(); 
         if (key == KeyEvent.VK_LEFT) 
         { 
            you.shouldMoveLeft=true;
         } 
         if (key == KeyEvent.VK_RIGHT) 
         {
            you.shouldMoveRight=true;
         } 
         if (key == KeyEvent.VK_SPACE) 
         { 
            if (recentFired==true)
            {
               
            }
            else
            {
               you.setBulletX();
               fired = true;        
               you.makeBullet(); 
               you.setNumBullets();
               recentFired=true;
            }
         }
         
         
      } 
      /*************************************************
   	*This method checks for release of the key, if the
   	*directinal keys are removed, then the Spaceship
   	*will stop it's current movement.
   	*************************************************/
      public void keyReleased(KeyEvent e) 
      { 
         key = e.getKeyCode(); 
         if (key == KeyEvent.VK_LEFT) 
         { 
            you.shouldMoveLeft=false;
         } 
         if (key == KeyEvent.VK_RIGHT) 
         {
            you.shouldMoveRight=false;
         } 
      } 
     /**************************************************************
     *This method will make a respond when a certain key is pressed.
     *However, the benefits of this method is minimum but still needed
     **************************************************************/
      public void keyTyped(KeyEvent e) 
      { 
         key = e.getKeyCode(); 
         if(key == KeyEvent.VK_T)
         {
            p = 0;
         }
      }
      /***************************************************************
   	*This method randomly chooses a SpaceInvader that is not yet hit,
   	*creates and fire a bullet at the Spaceship 
   	***************************************************************/
      public void fireAlien()
      {
         int r = (int)(Math.random()*6);
         int c = (int)(Math.random()*4);
         if(recentFiredAlien == false)
         {
            if(enemies[r][c].getY() < 450)
            {
               enemies[r][c].setFireAlien(true);
               enemies[r][c].setBulletX(); 
               enemies[r][c].makeBullet();
               enemies[r][c].setNumBullets();
               recentFiredAlien = true;
            }      
         }
         
      }
      /******************************************************************
   	*This method takes a Barricade[][] and Graphics arguement, therefore
   	*is able to paint the Barricade[][] on to the panel using the Graphics 
   	******************************************************************/
      public void drawBarricade(Barricade[][] barricade, Graphics buffer)  
      {
         for(int num = 0; num <8; num++)
         {
            for(int col = 0; col <4; col++)
               buffer.drawImage(barricade[col][num].getImage(),barricade[col][num].getX(),barricade[col][num].getY(),barricade[col][num].getXWidth(),barricade[col][num].getYWidth(),this);
         }
         
      }
   	/*****************************************************************************
   	*This method checks if the Spaceship arg. has collided with the barricade.
   	*If so, it destroys both the bullet and the part of the barricade that was hit. 
   	*****************************************************************************/
      public void detectCollide(Barricade[][] b, Spaceship you)
      {
         for (int num =0; num<8; num ++)
         {
            for(int col = 0; col <4; col++)
               for(int z = 0; z < 200; z ++)
               {
                  
                  if (you.getbX(z)>= b[col][num].getX() && you.getbX(z)<=b[col][num].getX()+5  && you.getbY(z)>= b[col][num].getY() && you.getbY(z)<=b[col][num].getY()+5)
                  {
                     you.collide(z);
                     b[col][num].collide();
                     
                  }
               }
         }
         
      }
   	/*****************************************************************************
   	*This method checks if the alien specified by the args. x and y in the SpaceInvader 
   	*matrix bullets have collided with the barricades.
   	*If so, then it destroys both that alien's bullets and part of the barricade.   
   	*****************************************************************************/
      public void detectAlienCollide(Barricade[][] b,SpaceInvader[][] invader,int x, int y )
      {
         for (int num =0; num<8; num ++)
         {
            for(int col = 0; col <4; col++)
            {
               for(int z = 0; z < 200; z ++)
               {
                     
                  if (invader[x][y].getbX(z)>= b[col][num].getX() && invader[x][y].getbX(z)<=b[col][num].getX()+5 && invader[x][y].getbY(z)>= b[col][num].getY() && invader[x][y].getbY(z)<=b[col][num].getY()+5)
                  {
                     invader[x][y].collide(z);
                     b[col][num].collide();
                        
                  }
               }
            }
         }
      }
   	/*****************************************************************************
   	*This method draws the winning screen. It stops all the timers, and prints
   	*out the score and makes the reset button visible.  
   	*****************************************************************************/
      public void winPaint(Graphics g)
      {
         t.stop();
         t2.stop();
         f.stop();
         tAlien.stop();
         t2Alien.stop(); 
         sTimer.stop();   
         tExplosion.stop();  
         g.setColor(Color.BLACK.brighter());
         g.fillRect(0,0,600,600);
         g.setColor(Color.WHITE);
         g.setFont(new Font("Serif", Font.BOLD, 40));
         g.drawString("CONGRATULATIONS", 30, 100);
         g.setFont(new Font("Serif", Font.BOLD, 20));        
         g.drawString("You win. Press the reset button to play again", 50, 200); 
         g.drawString("Your Score:2400", 70, 300); 
         g.drawString("Your Bonus Score:" +s.getBonus(), 70, 350);
         g.drawString("Your Total Score:"  + s.getScoreWithBonus(), 70, 400); 
         reset.setVisible(true);
         repaint();
      }
   	/*****************************************************************************
   	*This method draws the losing screen. It stops all the timers, and prints
   	*out the score and makes the reset button visible.  
   	*****************************************************************************/
      public void losePaint(Graphics g)
      {
         t.stop();
         t2.stop();
         f.stop();
         tAlien.stop();
         t2Alien.stop(); 
         sTimer.stop();   
         tExplosion.stop();    
         g.setColor(Color.BLACK.brighter());
         g.fillRect(0,0,600,600);
         g.setColor(Color.WHITE);
         g.setFont(new Font("Serif", Font.BOLD, 40));
         g.drawString("Sorry", 190, 100);
         g.setFont(new Font("Serif", Font.BOLD, 20));        
         g.drawString("You lose. Press the reset button to play again", 50, 200); 
         g.setFont(new Font("Serif", Font.BOLD, 20));   
         g.drawString("Your Total Score:"  + s.getScore(), 50, 280);
      			  
         reset.setVisible(true);
         repaint();
      }
   	/*****************************************************************************
   	*This method paints the menu. It destroys the enemies created by the panel,
   	*and draws your ship, the in-game directions, and the bullets fired by your 
   	*ship in the menu screen.
   	*****************************************************************************/
      public void menuPaint(Graphics g)
      {
         for(int r = 1; r <= enemies.length; r++)
         {
            for(int c = 1; c <= enemies[0].length; c++)
            {
               enemies[r-1][c-1].collide("a");        
            }
         }
         g.setColor(Color.BLACK.brighter());
         g.fillRect(0,0,600,600);
         g.setColor(Color.WHITE);
         g.setFont(new Font("Serif", Font.BOLD, 20));
         g.drawString("Fire through the barricade and hit the alien to move", 30, 25);
         g.drawString("on to the game. Use the space bar to fire.", 50, 75); 
         g.drawImage(menuInvader.getImage(), menuInvader.getX()-20, menuInvader.getY(), 30, 15, this);
         g.drawImage(you.getImage(), you.getX()-25, 475,45, 15, this);
         drawBarricade(b,g);
         drawBarricade(b1,g);
         drawBarricade(b2,g);
         drawBarricade(b3,g);
         drawBarricade(b4,g);
         if(fired == true)
            you.drawBullet(g);
      }
   	/*****************************************************************************
   	*This method paints everything needed to run the game, on the screen. It draws
   	* the barricades, enemies, bullets, background, and ship. It also draws the 
   	* explosion of your ship, and if this occurs, it stops all the timers as well
   	*****************************************************************************/
      public void runGamePaint(Graphics g)
      {
         g.setColor(Color.BLACK.brighter());
         g.fillRect(0,0,600,600);
         g.setColor(Color.WHITE);
         if(fired == true)
            you.drawBullet(g);
         drawBarricade(b,g);
         drawBarricade(b1,g);
         drawBarricade(b2,g);
         drawBarricade(b3,g);
         drawBarricade(b4,g);
         for(int r = 1; r <= enemies.length; r++)
         {
            for(int c = 1; c <= enemies[0].length; c++)
            {
               g.drawImage(enemies[r-1][c-1].getImage(), enemies[r-1][c-1].getX()-20, enemies[r-1][c-1].getY(), 30, 15, this);
               if(enemies[r-1][c-1].getFireAlien() == true)
               {
                  enemies[r-1][c-1].drawBullet(g);    
               }
            }
         }
         g.drawImage(you.getImage(), you.getX()-25, 475,45, 15, this); // ----for jpg pic
         if(gif == true)
         {
            t.stop();
            t2.stop();
            tAlien.stop();
            t2Alien.stop(); 
            sTimer.stop();       
            tExplosion.start();
            g.drawImage(you.getImage(), you.getX()-25, 440,65, 65, this); // ----for jpg pic
         }
      }
   	/***************************************************************************************************
   	*This method instantiates a reset button, the enemies array, the enemies, and the barricades, and the ship.
   	***************************************************************************************************/
      public void makeBoard()
      {
         reset = new JButton();
         reset.setForeground(Color.WHITE);
         reset.setBackground(Color.BLACK);
         reset.setText("RESET");
         reset.setVisible(false);
         reset.addActionListener(new Listener5());
         enemies = new SpaceInvader[6][4];
         you = new Spaceship();
         for(int r = 1; r <= enemies.length; r++)
         {
            for(int c = 1; c <= enemies[0].length; c++)
            {
               enemies[r-1][c-1] = new SpaceInvader(r * 50, c * 30);
            }
         }
         for(int num = 0; num <8; num++)
         {
            for(int col = 0; col < 4; col++)
            {
               b[col][num] = new Barricade(num*5+75,col*5+400,5,5);
               b1[col][num] = new Barricade(num*5+150,col*5+400,5,5);
               b2[col][num] = new Barricade(num*5+225,col*5+400,5,5);
               b3[col][num] = new Barricade(num*5+300,col*5+400,5,5);
               b4[col][num] = new Barricade(num*5+375,col*5+400,5,5);
            }
         }
      }
   	
   }
	
