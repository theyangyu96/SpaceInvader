   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.Image;


   public class Endless extends JPanel implements KeyListener
   {
      private Graphics g;
      private int key, p, d;
      private int gifX = 0, gifY = 0;
      private Timer t,tExplosion, tAlien, tBoard;
      private Spaceship yu;
      private JButton reset = new JButton();
      private boolean  gif;
      private ScoreboardFinal s;
      private SpaceInvader[][] enemies;
      public Endless()
      {
         p = 0;
         d = 1;
         gif = false;
         reset.setForeground(Color.WHITE);
         reset.setBackground(Color.BLACK);
         reset.setText("RESET");
         reset.setVisible(false);
         reset.addActionListener(new Listener5());
         add(reset);
         yu = new Spaceship();
         enemies = new SpaceInvader[9][4];      
         for(int r = 1; r <= enemies.length; r++)
         {
            for(int c = 1; c <= enemies[0].length; c++)
            {
               enemies[r-1][c-1] = new SpaceInvader(r * 55-18, c * 30);
            }
         }
          
         setLayout(new BorderLayout());
         s = new ScoreboardFinal();
         add(s, BorderLayout.SOUTH);
         setFocusable(true);
         addKeyListener(this);
         t = new Timer(25, new Listener());
         t.start();	
         tExplosion = new Timer(3000, new Listener4());
         tAlien = new Timer(600,new Listener2());
         tAlien.start();
         tBoard = new Timer(100,new Listener3());
         tBoard.start();
      }
   	
      public void reset()
      {
         p = 0;
         d = 1;
         gif = false;
         yu = new Spaceship();
         for(int r = 1; r <= enemies.length; r++)
         {
            for(int c = 1; c <= enemies[0].length; c++)
            {
               enemies[r-1][c-1] = new SpaceInvader(r * 50, c * 30);
            }
         }
         t.start();
         s.reset();
      }
   	
      public void paintComponent(Graphics g)
      {
         g.setColor(Color.BLACK.brighter());
         g.fillRect(0,0,600,600);
         g.setColor(Color.WHITE);
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
         g.drawImage(yu.getImage(), yu.getX()-25, 475,45, 15, this); // ----for jpg pic
         if(gif == true)
         {
            t.stop();
            tExplosion.start();
            g.drawImage(yu.getImage(), yu.getX()-25, 440,65, 65, this); // ----for jpg pic
         
         }
      }
   	
      private class Listener implements ActionListener{
         public void actionPerformed(ActionEvent e){       
            yu.move();
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
                     if(yu.getbX(z) < enemies[r][c].getX() + 15  && yu.getbX(z) > enemies[r][c].getX() - 15 && yu.getbY(z) < enemies[r][c].getY() + 15  && yu.getbY(z) > enemies[r][c].getY() - 15 )
                     {
                        yu.collide(z);
                        enemies[r][c].collide();
                        s.update();
                     }
                     if(yu.getX() < enemies[r][c].getX() + 15  && yu.getX() > enemies[r][c].getX() - 15 && enemies[r][c].getY() >= 450  && enemies[r][c].getY() <= 550 )
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
                        yu.setImage("Explode.gif");
                        tBoard.stop();
                        reset.setVisible(false);
                     }
                     if(yu.getX()-25 <= enemies[r][c].getbX(z)  && yu.getX()+25 > enemies[r][c].getbX(z) && enemies[r][c].getbY(z) >= 470 && enemies[r][c].getbY(z) <= 500)
                     {
                        gif = true;
                        reset.setVisible(true);
                        yu.setImage("Explode.gif");
                        tBoard.stop();
                        enemies[r][c].collide(z);
                        reset.setVisible(false);
                     }
                  }
               }
            }
            repaint();
         }
          
         
      }
      
       
      private class Listener3 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            s.EndlessScore();
         }
      }
   
   	
     
      private class Listener4 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            tExplosion.stop();
            gif = false;
            yu.setImage("blank.jpg");
            repaint();
         }
      }
      private class Listener5 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            reset();
            reset.setVisible(false);
         }
      }
   	//keyPress Code
      public void keyPressed(KeyEvent e) 
      { 
         key = e.getKeyCode(); 
         if (key == KeyEvent.VK_LEFT) 
         { 
            yu.shouldMoveLeft=true;
         } 
         if (key == KeyEvent.VK_RIGHT) 
         {
            yu.shouldMoveRight=true;
         }
         
      } 
   	//keyRelease code
      public void keyReleased(KeyEvent e) 
      { 
         key = e.getKeyCode(); 
         if (key == KeyEvent.VK_LEFT) 
         { 
            yu.shouldMoveLeft=false;
         } 
         if (key == KeyEvent.VK_RIGHT) 
         {
            yu.shouldMoveRight=false;
         } 
      } 
   	//keyTyped code
      public void keyTyped(KeyEvent e) 
      { 
         key = e.getKeyCode(); 
         if(key == KeyEvent.VK_T)
         {
            p = 0;
         }
      }
      private class Listener2 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            fireAlien();
            if (tAlien.getDelay()>10)
            {
               tAlien.setDelay((tAlien.getDelay()-(int)(Math.random()*5))); 
               System.out.println(""+tAlien.getDelay());
            }
         }
      }
      public void fireAlien()
      {
         int r = (int)(Math.random()*9);
         int c = (int)(Math.random()*4);
      
         enemies[r][c].setFireAlien(true);
         enemies[r][c].setBulletX(); 
         enemies[r][c].makeBullet();
         enemies[r][c].setNumBullets();
         if (s.getTime()>30)
         {	
            int y = (int)(Math.random()*9);
            int z = (int)(Math.random()*4);
         
            enemies[y][z].setFireAlien(true);
            enemies[y][z].setBulletX(); 
            enemies[y][z].makeBullet();
            enemies[y][z].setNumBullets();
         }     
         if (s.getTime()>60)
         {	
            int a = (int)(Math.random()*9);
            int b = (int)(Math.random()*4);
         
            enemies[a][b].setFireAlien(true);
            enemies[a][b].setBulletX(); 
            enemies[a][b].makeBullet();
            enemies[a][b].setNumBullets();
         }    
         
         
      }
   
   }