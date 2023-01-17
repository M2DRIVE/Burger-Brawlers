import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;

public class Window extends JFrame implements ActionListener {  
  //private Clip clip = AudioSystem.getClip();
  private JLabel main_menu = new JLabel();
  private JLabel game = new JLabel();
  private JLabel transition;
  private JButton start;
  private JButton punch;
  private JButton kick;
  private JButton stab;
  private JLabel red_blink;
  private JLabel yellow_blink;
  
  public Window() throws Exception {
    startFrame();
  }

  public void startFrame() throws Exception {
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
  
    // AudioInputStream main_menu_wav = getAudioFile("main_menu.wav");
    // clip.open(main_menu_wav);      
    // clip.loop(Clip.LOOP_CONTINUOUSLY);
    // clip.start();
        
    // Adds Labels to Frame
    transition = new JLabel(getIcon("src/img/menu/transition.png"));
    transition.setBounds(0,600,800,1800);    
    add(transition);
        
    main_menu.setBounds(getBounds());
    main_menu.add(start);
    main_menu.add(start_menu_background);
    
    loadGameAssets();
    add(main_menu);
    add(game);
  }

  public void playTransition() throws Exception {
    // AudioInputStream select = getAudioFile("select.wav");
    // Clip clip = AudioSystem.getClip();
    // clip.open(select);  
    // clip.start();

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
          if(i == -600)
            main_menu.setVisible(false);
        }
      }
    }).start();
  }

  public void startGame() throws Exception {
    // AudioInputStream music = getAudioFile("music.wav");
    // Clip clip = AudioSystem.getClip();
    // clip.open(music);
    // clip.start(start);

    // Blinking
    new Thread(new Runnable() {
      @Override
      public void run() {
        while(true) {
          try {
            Thread.sleep(5000);
            yellow_blink.setVisible(true);
            Thread.sleep(500);
            yellow_blink.setVisible(false);
            Thread.sleep(5000);
            red_blink.setVisible(true);
            Thread.sleep(75);
            red_blink.setVisible(false);
            
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }).start();
  }

  public void loadGameAssets() {
    // -180 -40 -30
    JLabel background = new JLabel(getIcon("src/img/game/background.gif"));
    background.setBounds(0,0,800,600);
    JLabel red = new JLabel(getIcon("src/img/game/red.png"));
    red.setBounds(36,127,126,259);
    red_blink = new JLabel(getIcon("src/img/game/red_blink.png"));
    red_blink.setBounds(36,127,126,259);
    JLabel red_shadow = new JLabel(getIcon("src/img/game/shadow.png"));
    red_shadow.setBounds(31,370,111,30);
    JLabel red_health = new JLabel("100");
    red_health.setBounds(122, 30, 25, 10);
    JLabel yellow = new JLabel(getIcon("src/img/game/yellow.png"));
    yellow.setBounds(625,118,133,266);
    yellow_blink = new JLabel(getIcon("src/img/game/yellow_blink.png"));
    yellow_blink.setBounds(625,118,133,266);
    JLabel yellow_shadow = new JLabel(getIcon("src/img/game/shadow.png"));
    yellow_shadow.setBounds(650, 367,111,30);
    JLabel yellow_health = new JLabel("100");
    yellow_health.setBounds(647, 30, 25, 10);

    punch = new JButton(new ImageIcon("src/img/game/fist.png"));
    punch.setBounds(80,440,78,108);
    punch.setBorder(BorderFactory.createEmptyBorder());

    kick = new JButton(new ImageIcon("src/img/game/foot.png"));
    kick.setBounds(215, 435, 74, 113);
    kick.setBorder(BorderFactory.createEmptyBorder());

    stab = new JButton(new ImageIcon("src/img/game/knife.png"));
    stab.setBounds(350, 440, 99, 112);
    stab.setBorder(BorderFactory.createEmptyBorder());

    punch.addActionListener(this);
    kick.addActionListener(this);
    stab.addActionListener(this);
    
    game.setBounds(0,0,800,600);
    red_health.setForeground(Color.RED);
    yellow_health.setForeground(Color.RED);
    game.add(punch);
    game.add(kick);
    game.add(stab);
    game.add(red_health);
    game.add(yellow_health);
    game.add(red_blink);
    game.add(red);
    game.add(red_shadow);
    game.add(yellow_blink);
    game.add(yellow);
    game.add(yellow_shadow);
    game.add(background);

    red_blink.setVisible(false);
    yellow_blink.setVisible(false);
  }
  
  public AudioInputStream getAudioFile(String filename) throws Exception {
    return AudioSystem.getAudioInputStream(new File("src/aud/" + filename).getAbsoluteFile());
  }

  public ImageIcon getIcon(String filepath) {
    return new ImageIcon(filepath);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if(event.getSource() == start) {
      //clip.stop();
      try {
        playTransition();
        startGame();
      } catch (Exception e) {
        e.printStackTrace();
      } 
    }

    else if(event.getSource() == punch) {
      
    }

    else if(event.getSource() == kick) {
      
    }

    else if(event.getSource() == stab) {
      
    }
  }
}  