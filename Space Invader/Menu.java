   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import java.applet.*; 
   
   public class Menu extends JPanel
   {
      ImageIcon menu = new ImageIcon("menu.JPG");
   	
      public Menu()
      {     
         setLayout(null);    
        
         JButton button1 = new JButton();      
         button1.addActionListener(new Listener1());
         button1.setHorizontalAlignment(SwingConstants.CENTER);
         button1.setBounds(60, 375, 100, 75);
         button1.setOpaque(false);
         button1.setContentAreaFilled(false);
         button1.setBorderPainted(false);
         add(button1);
          
         JButton button3 = new JButton();
         button3.addActionListener(new Listener3());
         button3.setHorizontalAlignment(SwingConstants.CENTER);
         button3.setBounds(250, 375, 100, 75);
         button3.setOpaque(false);
         button3.setContentAreaFilled(false);
         button3.setBorderPainted(false);  
         add(button3);
      	
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
            ModeSelect.start();
            DriverFinal.dispose();
         }
      }
      private class Listener3 implements ActionListener
      {
         public void actionPerformed(ActionEvent e) 
         {
            try{
               HighScoreDriver.start();
               DriverFinal.dispose();
            }
               catch(Exception a)
               {
               }
         }
      } 
   }
