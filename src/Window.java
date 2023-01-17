import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;

public class Window extends JFrame implements ActionListener {  
  private JLabel main_menu = new JLabel();
  private JPanel game = new JPanel();
  private JButton start;
  private Clip clip = AudioSystem.getClip();
  private JLabel transition;
  
  public Window() throws Exception {
    startGame();
  }

  public void startGame() throws Exception {
    setSize(800, 600);  
    setLayout(null);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    
    // Start Menu Background
    ImageIcon start_menu_img = new ImageIcon("src/img/menu/burger_brawlers.png");
    JLabel start_menu_background = new JLabel("", start_menu_img, JLabel.CENTER);
    start_menu_background.setBounds(0,0,800,600);

    // Start Button
    start = new JButton(new ImageIcon("src/img/menu/start.png"));
    start.setBounds(240,327,320,100);
    start.setBorder(BorderFactory.createEmptyBorder());

    start.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent evt) {
        start.setIcon(new ImageIcon("src/img/menu/start_h.png"));
      }
      public void mouseExited(MouseEvent evt) {
        start.setIcon(new ImageIcon("src/img/menu/start.png"));
      }
    });

    start.addActionListener(this);
  
    AudioInputStream main_menu_wav = getAudioFile("main_menu.wav");
    clip.open(main_menu_wav);      
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    clip.start();
        
    // Adds Labels to Frame
    transition = new JLabel(getIcon("src/img/menu/transition.png"));
    transition.setBounds(0,600,800,1800);    
    add(transition);

    JLabel background = new JLabel(getIcon("src/img/game/background.png"));
    background.setBounds(0,0,800,600);
    JLabel red = new JLabel(getIcon("src/img/game/red.png"));
    red.setBounds(36,127,126,259);
    JLabel red_blink = new JLabel(getIcon("src/img/game/red_blink.png"));
    red_blink.setBounds(36,127,126,259);
    JLabel red_shadow = new JLabel(getIcon("src/img/game/shadow.png"));
    red_shadow.setBounds(31,370,111,30);
    JLabel yellow = new JLabel(getIcon("src/img/game/yellow_blink.png"));
    yellow.setBounds(640,118,113,266);
    JLabel yellow_blink = new JLabel(getIcon("src/img/game/yellow_blink.png"));
    yellow_blink.setBounds(640,118,113,266);
    JLabel yellow_shadow = new JLabel(getIcon("src/img/game/shadow.png"));
    yellow_shadow.setBounds(650, 367,111,30);

    game.setBounds(0,0,800,600);
    game.add(red);
    game.add(red_blink);
    game.add(red_shadow);
    game.add(yellow);
    game.add(yellow_blink);
    game.add(yellow_shadow);
    game.add(background);
    
    main_menu.setBounds(getBounds());
    main_menu.add(start);
    main_menu.add(start_menu_background);

    //add(main_menu);
    add(game);
  }

  public AudioInputStream getAudioFile(String filename) throws Exception {
    return AudioSystem.getAudioInputStream(new File("src/aud/" + filename).getAbsoluteFile());
  }

  public ImageIcon getIcon(String filepath) {
    return new ImageIcon(filepath);
  }

  public void playTransition() throws Exception {
    AudioInputStream select = getAudioFile("select.wav");
    Clip clip = AudioSystem.getClip();
    clip.open(select);  
    clip.start();

    new Thread(new Runnable() {
      @Override
      public void run() {
        for(int i = 600; i > -1800; i--) {
          transition.setBounds(0, i, 800, 1800);
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(i);
        }
      }
    }).start();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if(event.getSource() == start) {
      clip.stop();
      try {
        playTransition();

      } catch (Exception e) {
        e.printStackTrace();
      } 
    }
  }
}  