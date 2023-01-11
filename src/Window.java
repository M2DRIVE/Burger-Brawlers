import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;

public class Window extends JFrame implements ActionListener {  
  private JLabel main_menu = new JLabel();
  private JButton start;
  private Clip clip = AudioSystem.getClip();
  
  public Window() throws Exception {
    startGame();
  }

  public void startGame() throws Exception {
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

    start.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent evt) {
         start.setIcon(new ImageIcon("src/img/start_h.png"));
      }
      public void mouseExited(MouseEvent evt) {
        start.setIcon(new ImageIcon("src/img/start.png"));
      }
    });

    start.addActionListener(this);
  
    AudioInputStream main_menu_wav = getAudioFile("main_menu.wav");
    clip.open(main_menu_wav);      
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    clip.start();
        
    // Adds Label to Frame
    main_menu.setBounds(getBounds());
    main_menu.add(start);
    main_menu.add(start_menu);
    add(main_menu);
  }

  public AudioInputStream getAudioFile(String filename) throws Exception {
    return AudioSystem.getAudioInputStream(new File("src/aud/" + filename).getAbsoluteFile());
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

        ImageIcon transition_ImageIcon = new ImageIcon("src/img/transition.png");
        JLabel transition = new JLabel();
        transition.setIcon(transition_ImageIcon);
        transition.setBounds(0,600,800,1800);
        add(transition);
        
        for(int i = 600; i > -1200; i--) {
          transition.setBounds(0, i, 800, 1800);
          Thread.sleep(1);
        }

      } catch (Exception e1) {}
 
    }
  }
}  