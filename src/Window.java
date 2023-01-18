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
  private JButton start, punch, kick, stab;
  private JLabel fist_color, foot_color, knife_color;
  private JLabel red_blink, yellow_blink;

  Clip clip = AudioSystem.getClip();

  private boolean gameStarted = false;

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
    transition = new JLabel(new ImageIcon("src/img/menu/transition.png"));
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
          if(i == -600)
            main_menu.setVisible(false);
        }
      }
    }).start();
  }

  public void startGame() throws Exception {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(3000);
          AudioInputStream music = getAudioFile("music.wav");
          Clip clip = AudioSystem.getClip();
          clip.open(music);
          clip.loop(Clip.LOOP_CONTINUOUSLY);
          clip.start();

          while(true) {
            Thread.sleep(5000);
            yellow_blink.setVisible(true);
            Thread.sleep(500);
            yellow_blink.setVisible(false);
            Thread.sleep(2500);
            red_blink.setVisible(true);
            Thread.sleep(75);
            red_blink.setVisible(false);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();

    punch.setVisible(true);
    kick.setVisible(true);
    stab.setVisible(true);

    gameStarted = true;
  }

  public void punch() {
    new Thread(new Runnable() {
      @Override
      public void run() {

      }
    }).start();
  }

  public void loadGameAssets() {
    // -180 -40 -30
    // Standard Assets
    JLabel background = new JLabel(getIcon("background.gif"));
    background.setBounds(0,0,800,600);
    JLabel red = new JLabel(getIcon("red.png"));
    red.setBounds(36,127,126,259);
    red_blink = new JLabel(getIcon("red_blink.png"));
    red_blink.setBounds(36,127,126,259);
    JLabel red_shadow = new JLabel(getIcon("shadow.png"));
    red_shadow.setBounds(31,370,111,30);
    JLabel red_health = new JLabel("100");
    red_health.setBounds(122, 30, 25, 10);
    JLabel yellow = new JLabel(getIcon("yellow.png"));
    yellow.setBounds(625,118,133,266);
    yellow_blink = new JLabel(getIcon("yellow_blink.png"));
    yellow_blink.setBounds(625,118,133,266);
    JLabel yellow_shadow = new JLabel(getIcon("shadow.png"));
    yellow_shadow.setBounds(650, 367,111,30);
    JLabel yellow_health = new JLabel("100");
    yellow_health.setBounds(647, 30, 25, 10);    
    
    // Attacks
    fist_color = new JLabel(getIcon("attacks/fist_color.png"));
    foot_color = new JLabel(getIcon("attacks/foot_color.png"));
    knife_color = new JLabel(getIcon("attacks/knife_color.png"));

    fist_color.setVisible(false);
    foot_color.setVisible(false);
    knife_color.setVisible(false);

    JLabel punch_attributes = new JLabel(getIcon("attacks/punch_attributes.png"));
    punch_attributes.setBounds(471,444,277,98);
    JLabel kick_attributes = new JLabel(getIcon("attacks/kick_attributes.png"));
    kick_attributes.setBounds(471,444,277,98);
    JLabel stab_attributes = new JLabel(getIcon("attacks/stab_attributes.png"));
    stab_attributes.setBounds(471,444,277,98);

    punch_attributes.setVisible(false);
    kick_attributes.setVisible(false);
    stab_attributes.setVisible(false);
    
    punch = new JButton(new ImageIcon("src/img/game/attacks/fist.png"));
    punch.setBounds(81,439,78,108);
    punch.setBorder(BorderFactory.createEmptyBorder());

    kick = new JButton(new ImageIcon("src/img/game/attacks/foot.png"));
    kick.setBounds(214, 436, 74, 113);
    kick.setBorder(BorderFactory.createEmptyBorder());

    stab = new JButton(new ImageIcon("src/img/game/attacks/knife.png"));
    stab.setBounds(348, 439, 99, 112);
    stab.setBorder(BorderFactory.createEmptyBorder());

    punch.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent evt) {
        punch.setIcon(new ImageIcon("src/img/game/attacks/fist_select.png"));
        punch.setBounds(76, 432-5, 94, 121);
        punch_attributes.setVisible(true);
      }
      public void mouseExited(MouseEvent evt) {
        punch.setIcon(new ImageIcon("src/img/game/attacks/fist.png"));
        punch.setBounds(81,439,78,108);
        punch_attributes.setVisible(false);
      }
    });

    kick.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent evt) {
        kick.setIcon(new ImageIcon("src/img/game/attacks/foot_select.png"));
        kick.setBounds(205, 432-5, 94, 121);
        kick_attributes.setVisible(true);
      }
      public void mouseExited(MouseEvent evt) {
        kick.setIcon(new ImageIcon("src/img/game/attacks/foot.png"));
        kick.setBounds(214, 436, 74, 113);
        kick_attributes.setVisible(false);
      }
    });
    
    stab.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent evt) {
        stab.setIcon(new ImageIcon("src/img/game/attacks/knife_select.png"));
        stab.setBounds(341, 432-5, 112, 121);
        stab_attributes.setVisible(true);
      }
      public void mouseExited(MouseEvent evt) {
        stab.setIcon(new ImageIcon("src/img/game/attacks/knife.png"));
        stab.setBounds(348, 439, 99, 112);
        stab_attributes.setVisible(false);
      }
    });

    punch.addActionListener(this);
    kick.addActionListener(this);
    stab.addActionListener(this);
    
    punch.setVisible(false);
    kick.setVisible(false);
    stab.setVisible(false);

    game.setBounds(0,0,800,600);
    red_health.setForeground(Color.RED);
    yellow_health.setForeground(Color.RED);
    game.add(fist_color);
    game.add(foot_color);
    game.add(knife_color);
    game.add(punch_attributes);
    game.add(kick_attributes);
    game.add(stab_attributes);
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

  public ImageIcon getIcon(String filename) {
    return new ImageIcon("src/img/game/" + filename);
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

    if(!gameStarted) return;

    else if(event.getSource() == punch) {
      System.out.println("A");
    }

    else if(event.getSource() == kick) {
      System.out.println("B");
    }

    else if(event.getSource() == stab) {
      System.out.println("C");
    }
  }
}  