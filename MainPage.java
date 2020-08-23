import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;
import java.io.IOException;

import java.util.Calendar;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;
import java.io.IOException;

import java.util.Calendar;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPage extends JFrame
{

    AudioInputStream audioInputStream;

    Calendar javaCalendar;

    public static Clip clip;

    ImageIcon Background;
    ImageIcon welcomePage;
    JLabel Title;

    public MainPage()
    {
        try
        {
              audioInputStream = AudioSystem.getAudioInputStream(new File("background1.wav").getAbsoluteFile());
              clip = AudioSystem.getClip();
              clip.open(audioInputStream);
              clip.loop(Clip.LOOP_CONTINUOUSLY);
         }
         catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
         {
         }

         welcomePage = new ImageIcon("chessWelcomePage.png");
         Title = new JLabel(welcomePage);
         add(Title, BorderLayout.CENTER);

        Title.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                PlayGame playFrame = new PlayGame();

                playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                playFrame.setSize(1200, 700);
                playFrame.setTitle("CHESS GAME");
                playFrame.setResizable(false);
                playFrame.setLocationRelativeTo(null);
                playFrame.setVisible(true);

               dispose();
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        });
    }

    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
        }

        MainPage mainFrame = new MainPage();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("Chess G_12");
        mainFrame.setSize(1200, 700);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}