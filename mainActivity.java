package assignment_sysSoft;
	
    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;
    import java.awt.EventQueue;
    import java.awt.FlowLayout;
    import java.awt.GridLayout;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JPanel;
    import javax.swing.Timer;
    import javax.swing.border.EmptyBorder;


    

    import assignment_sysSoft.clockSetup;




    public class mainActivity extends JApplet implements ActionListener {
      // Declare three java clock panels
      private SwitchBoard clockOne, clockTwo, clockThree;
      private JButton startAll, stopAll;
      
      private static final long serialVersionUID = 1L;
     

      public static void main(String[] args) {
        // Create a groupClocks
        JFrame groupClocks = new JFrame("");
        mainActivity applet = new mainActivity();
        groupClocks.add(applet, BorderLayout.CENTER);
     
        applet.init();
        applet.start();
     
        // set size
        groupClocks.setSize(900, 500);
        groupClocks.setLocationRelativeTo(null);
        groupClocks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        groupClocks.setVisible(true);
      }
     
      // launch application
      @Override
      public void init() {
        // Panel panelOne for holding three javaclocks
        JPanel panelOne = new JPanel();
        panelOne.setLayout(new GridLayout(2, 5));
        // Displays clockDesignatedButtons
        panelOne.add(clockOne = new SwitchBoard());
        panelOne.add(clockTwo = new SwitchBoard());
        panelOne.add(clockThree = new SwitchBoard());
     
        // Panel panelTwo for holding two group control buttons
       JPanel panelTwo = new JPanel();
       panelTwo.setLayout(new FlowLayout());
       panelTwo.add(stopAll = new JButton("Stop All"));
       panelTwo.add(startAll = new JButton("Start All"));
       
     
        setLayout(new BorderLayout());
        add(panelOne, BorderLayout.CENTER);
        add(panelTwo, BorderLayout.SOUTH);
     
        // Register listeners for all the buttons used
        startAll.addActionListener(new ActionListener() {
          @Override
            public void actionPerformed(ActionEvent e) {
                  clockOne.resume();
                  clockTwo.resume();
                  clockThree.resume();          
            }
        });
        stopAll.addActionListener(new ActionListener() {
          @Override
            public void actionPerformed(ActionEvent e) {
                  clockOne.suspend();
                  clockTwo.suspend();
                  clockThree.suspend();  
            }
        });
      }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
     // Designated Buttons
    class SwitchBoard extends JPanel {
      private Clock clockDesignatedButtons = new Clock();
      private final JButton stopButton =new JButton("Stop");
      private final JButton startButton = new JButton("Start");
     
      public SwitchBoard() {
        JPanel panel = new JPanel();
        panel.add(startButton);
        panel.add(stopButton);
       
        // Add clockDesignatedButtons
        setLayout(new BorderLayout());
        add(clockDesignatedButtons, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
     
        // Register listeners
        stopButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            clockDesignatedButtons.suspend();
          }        
        });
        startButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            clockDesignatedButtons.resume();
          }        
        });
      }
     
      public void suspend() {
        clockDesignatedButtons.suspend();
      }
     
      public void resume() {
        clockDesignatedButtons.resume();
      }
     
      class Clock extends clockSetup implements Runnable {
        private final Timer clocktimer = new Timer(1000, new Listener());
        private boolean suspended;
        
        
       
        public Clock() {
          clocktimer.start();
          new Thread(this).start();
        }
     
        class Listener implements ActionListener {
          @Override
          public void actionPerformed(ActionEvent e) {
            // Set new time and repaint the clockDesignatedButtons to display current time
            setCurrentTime();
            repaint();
          }
        }
     
        public synchronized void suspend() {
          clocktimer.stop();
        }
     
        public synchronized void resume() {
          clocktimer.start();
       }
      }
		public void actionPerformed(ActionEvent arg) {
			// TODO Auto-generated method stub
			
		}
    }



