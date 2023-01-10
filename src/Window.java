import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Window extends JFrame implements ActionListener {  
  JLabel label = new JLabel();

  public Window() {
    setSize(800, 600);  
    setLayout(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    
    // Start Menu Background
    ImageIcon start_menu_img = new ImageIcon("src/img/burger_brawlers.png");
    JLabel start_menu = new JLabel("", start_menu_img, JLabel.CENTER);
    start_menu.setBounds(0,0,800,600);

    // Start Button
    JButton start = new JButton(new ImageIcon("src/img/start.png"));
    start.setBounds(240,327,320,100);
    start.setBorder(BorderFactory.createEmptyBorder());

    getContentPane().add(start_menu);
    getContentPane().add(start);

    start.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent evt) {
         start.setIcon(new ImageIcon("src/img/start_h.png"));
      }
      public void mouseExited(MouseEvent evt) {
        start.setIcon(new ImageIcon("src/img/start.png"));
      }
    });

    setVisible(true);  
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // if(e.getSource() == button) 
    //   System.out.println("button1");
    // else if(e.getSource() == button2)
    //   System.out.println("button2");
  }
}  