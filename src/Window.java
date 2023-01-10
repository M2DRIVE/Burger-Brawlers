import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;

public class Window extends JFrame implements ActionListener {  
  private JButton start;
  private Clip clip = AudioSystem.getClip();
  
  public Window() throws Exception {
    setSize(800, 600);  
    setLayout(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    
    // Start Menu Background
    ImageIcon start_menu_img = new ImageIcon("src/img/burger_brawlers.png");
    JLabel start_menu = new JLabel("", start_menu_img, JLabel.CENTER);
    start_menu.setBounds(0,0,800,600);

    // Start Button
    start = new JButton(new ImageIcon("src/img/start.png"));
    start.setBounds(240,327,320,100);
    start.setBorder(BorderFactory.createEmptyBorder());

    // Adds to Frame
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

    start.addActionListener(this);
  
    AudioInputStream main_menu = getAudioFile("main_menu.wav");
    clip.open(main_menu);      
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    clip.start();
    
    setVisible(true);  
  }

  public AudioInputStream getAudioFile(String path) throws Exception {
    return AudioSystem.getAudioInputStream(new File("src/aud/" + path).getAbsoluteFile());
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == start) {
      clip.stop();

      try {
        AudioInputStream select = getAudioFile("select.wav");
        Clip clip = AudioSystem.getClip();
        clip.open(select);  
        clip.start();

        Thread.sleep(500);
      } catch (Exception e1) {}

      
    }
    
  }
}  