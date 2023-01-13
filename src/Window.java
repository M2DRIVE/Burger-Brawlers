import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.io.File;

public class Window extends JFrame implements ActionListener {  
  private JLabel main_menu = new JLabel();
  private JButton start;
  //private Clip clip = AudioSystem.getClip();
  JLabel transition;
  
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
    JLabel start_menu = new JLabel("", start_menu_img, JLabel.CENTER);
    start_menu.setBounds(0,0,800,600);

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
        
    // Adds Label to Frame

    ImageIcon transition_ImageIcon = new ImageIcon("src/img/transition.png");
        transition = new JLabel();
        transition.setIcon(transition_ImageIcon);
        transition.setBounds(0,600,800,1800);
        
        add(transition);
        main_menu.setBounds(getBounds());
        main_menu.add(start);
        main_menu.add(start_menu);
    add(main_menu);
  }
//https://stackoverflow.com/questions/27687427/how-to-create-a-swing-application-with-multiple-pages
//https://stackoverflow.com/questions/9554636/the-use-of-multiple-jframes-good-or-bad-practice?noredirect=1&lq=1

  public AudioInputStream getAudioFile(String filename) throws Exception {
    return AudioSystem.getAudioInputStream(new File("src/aud/" + filename).getAbsoluteFile());
  }
  public void thing() {
    try {
      AudioInputStream select = getAudioFile("select.wav");
      Clip clip = AudioSystem.getClip();
      clip.open(select);  
      clip.start();

      // Timer timer = new Timer(100, this);    
      // timer.setRepeats( true );
  

      // for(int i = 600; i > -1200; i--) {
      //   timer.start();
      //   transition.setBounds(0, i, 800, 1800);
      //   timer.stop();
      // }

      new Thread(new Runnable() {
        @Override
        public void run() {
          for(int i = 600; i > -1200; i--) {
            transition.setBounds(0, i, 800, 1800);
            try {
              Thread.sleep(5);
            } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
          }
        }
      }).start();
    } catch (Exception e1) {}
  }

  public void thing2() {
    Timer timer = new Timer(0, new ActionListener() {
      int y_pos = 600;
      public void actionPerformed(ActionEvent event) {
        transition.setBounds(0, y_pos, 800, 1800);
        y_pos--;
      }
    }); 
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == start) {
      System.out.println("Button Press");
      thing2();
    }
  }
}  