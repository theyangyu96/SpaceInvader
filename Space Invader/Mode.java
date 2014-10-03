   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import java.applet.*; 
   
   public class Mode extends JPanel
   {
      ImageIcon menu = new ImageIcon("mode.JPG");
   	
      public Mode()
      {     
         setLayout(null);    
        
         JButton button1 = new JButton();      
         button1.addActionListener(new Listener1());
         button1.setHorizontalAlignment(SwingConstants.CENTER);
         button1.setBounds(200, 180,100 ,50 );
         button1.setOpaque(false);
         button1.setContentAreaFilled(false);
         button1.setBorderPainted(false);
         add(button1);
      
         JButton button2 = new JButton();   
         button2.addActionListener(new Listener2());
         button2.setHorizontalAlignment(SwingConstants.CENTER);
         button2.setBounds(200, 320, 100, 50);
         button2.setOpaque(false);
         button2.setContentAreaFilled(false);
         button2.setBorderPainted(false);      
         add(button2);
      //ARCADE
         // JButton button3 = new JButton();
         // button3.addActionListener(new Listener3());
         // button3.setHorizontalAlignment(SwingConstants.CENTER);
         // button3.setBounds(200, 240, 100, 50);
         // button3.setOpaque(false);
         // button3.setContentAreaFilled(false);
         // button3.setBorderPainted(false);  
         // add(button3);
      	
         addKeyListener( new Key() );
         setFocusable(true);
         
      }
   
      public void paintComponent(Graphics g)
      {      
         g.drawImage(menu.getImage(), 0, 0, getWidth(), getHeight(), null);
      }
   
      private class Key extends KeyAdapter
      {
         public void keyPressed(KeyEvent e)
         {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
               System.exit(0);
            }
         }
      }
   	
      private class Listener1 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            Game.start();
            ModeSelect.dispose();
         }
      }
      
      private class Listener2 implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            EndlessMode.start();
            ModeSelect.dispose();
            
         }
      }
      
      // private class Listener3 implements ActionListener
      // {
         // public void actionPerformed(ActionEvent e) 
         // {
            // try{
               // HighScore.start();
               // Driver.dispose();
            // }
               // catch(Exception a)
               // {
               // }
         // }
      // } 
   }
