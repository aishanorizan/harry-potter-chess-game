import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import java.util.LinkedList;
import javax.swing.JTable;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

public class PlayGame extends JFrame
{
	
    private int gifCount = 0;
    private int pngCount = 0;
    
    private Rectangle square[][] = new Rectangle[11][11];
    private final int positionPushX = 295;
    private final int positionPushY = 10;
    
    private Icon[] White = new Icon[19];
    private Icon[] Black = new Icon[19];
    private Icon[] BKingIcon = new Icon[11];
    private Icon[] BQueenIcon = new Icon[11];
    private Icon[] BBishopIcon = new Icon[11];
    private Icon[] BHorseIcon = new Icon[11];
    private Icon[] BRookIcon = new Icon[11];
    private Icon[] BPawnIcon = new Icon[11];
    private Icon[] WKingIcon = new Icon[11];
    private Icon[] WQueenIcon = new Icon[11];
    private Icon[] WBishopIcon = new Icon[11];
    private Icon[] WHorseIcon = new Icon[11];
    private Icon[] WRookIcon = new Icon[11];
    private Icon[] WPawnIcon = new Icon[11];
    
    private boolean animating = false;
    private boolean blackKingAttack = false;
    private boolean blackQueenAttack = false;
    private boolean blackBishopAttack = false;
    private boolean blackHorseAttack = false;
    private boolean blackRookAttack = false;
    private boolean blackPawnAttack = false;
    private boolean whiteKingAttack = false;
    private boolean whiteQueenAttack = false;
    private boolean whiteBishopAttack = false;
    private boolean whiteHorseAttack = false;
    private boolean whiteRookAttack = false;
    private boolean whitePawnAttack = false;
    
    private int fixedDimension = 55;
    private float alpha = 1f;
    private boolean mouseOver = false;
    private int mouseEnterSquareX = -1;
    private  int mouseEnterSquareY = -1;
    
    private MyListener myAction = new MyListener();
    private MouseMouse myMouse = new MouseMouse();
    private MyDrawer paint = new MyDrawer();
    
    private Color myBlack;
    private Color mouseOverColor;
    private Color myWhite;
    private Color noColor;
    private Color customRed;
    
    private float scale[] = {1f, 1f, 1f, alpha};
    private float offset[] = {0f, 0f, 0f, 0f};
    private RescaleOp ropARGB = new RescaleOp(scale, offset, null);
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    
    private boolean mouseIsPressed = false;
    
    private JButton leaderBoards = new JButton("Leaderboards");
    private JButton restart = new JButton("RESTART GAME");
    
    private ImageIcon background = new ImageIcon("background.jpeg");
    private JLabel label = new JLabel(background);
    
    private JLabel info = new JLabel(" ");
    
    private Timer tm = new Timer(3, myAction);
    private Timer animTimer = new Timer(75, myAction);
    
    private int universalMatchRoundCount = 0;
    
    private AudioInputStream audioInputStream;
    private Clip clip;
    
    private BufferedImage BlackKing;
    private boolean BKingChosen = false;
    private boolean BKingLegalMove = false;
    private boolean BKingChecked = false;
    private boolean BCHECKMATE = false;
    private int BKingCoordinateX = 4;
    private int BKingCoordinateY = 0;
    private int BKingStartX = -1;
    private int BKingStartY = -1;
    private LinkedList<Integer> BKingLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> BKingLegalPathY = new LinkedList<Integer>();
    
    private BufferedImage BlackQueen;
    private boolean BQueenChosen = false;
    private boolean BQueenLegalMove = false;
    private boolean BQueenDestroyed = false;
    private int BQueenCoordinateX = 3;
    private int BQueenCoordinateY = 0;
    private int BQueenStartX = -1;
    private int BQueenStartY = -1;
    private LinkedList<Integer> BQueenLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> BQueenLegalPathY = new LinkedList<Integer>();
        
    private BufferedImage BlackBishop;
    private boolean BBishopChosen = false;
    private boolean BBishopLegalMove = false;
    private boolean BBishopDestroyed = false;
    private int BBishopCoordinateX = 5;
    private int BBishopCoordinateY = 0;
    private int BBishopStartX = -1;
    private int BBishopStartY = -1;
    private LinkedList<Integer> BBishopLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> BBishopLegalPathY = new LinkedList<Integer>();
	
    private BufferedImage BlackHorse;
    private boolean BHorseChosen = false;
    private boolean BHorseLegalMove = false;
    private boolean BHorseDestroyed = false;
    private int BHorseCoordinateX = 6;
    private int BHorseCoordinateY = 0;
    private int BHorseStartX = -1;
    private int BHorseStartY = -1;
    private LinkedList<Integer> BHorseLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> BHorseLegalPathY = new LinkedList<Integer>();
        
    private BufferedImage BlackRook;
    private boolean BRookChosen = false;
    private boolean BRookLegalMove = false;
    private boolean BRookDestroyed = false;
    private int BRookCoordinateX = 7;
    private int BRookCoordinateY = 0;
    private int BRookStartX = -1;
    private int BRookStartY = -1;
    private LinkedList<Integer> BRookLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> BRookLegalPathY = new LinkedList<Integer>();

    private BufferedImage BlackPawn;
    private boolean BPawnChosen = false;
    private boolean BPawnLegalMove = false;
    private boolean BPawnDestroyed = false;
    private int BPawnCoordinateX = 3;
    private int BPawnCoordinateY = 1;
    private boolean BPawnFirstMove = true;// true? 2 steps : 1 step
    private int BPawnStartX = -1;
    private int BPawnStartY = -1;
    private LinkedList<Integer> BPawnLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> BPawnLegalPathY = new LinkedList<Integer>();

    private BufferedImage WhiteKing;
    private boolean WKingChosen = false;
    private boolean WKingLegalMove = false;
     private boolean WKingChecked = false;
    private boolean WKingCHECKMATE = false;
    private int WKingCoordinateX = 7;
    private int WKingCoordinateY = 10;
    private int WKingStartX = -1;
    private int WKingStartY = -1; 
    private LinkedList<Integer> WKingLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> WKingPossiblePathY = new LinkedList<Integer>();

    private BufferedImage WhiteQueen;
    private boolean WQueenChosen = false;
    private boolean WQueenLegalMove = false;
    private boolean WQueenDestroyed = false;
    private int WQueenCoordinateX = 6;
    private int WQueenCoordinateY = 10;
    private int WQueenStartX = -1;
    private int WQueenStartY = -1;
    private LinkedList<Integer> WQueenLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> WQueenLegalPathY = new LinkedList<Integer>();

    private BufferedImage WhiteBishop;
    private boolean WBishopChosen = false;
    private boolean WBishopLegalMove = false;
    private boolean WBishopDestroyed = false;
    private int WBishopCoordinateX = 5;
    private int WBishopCoordinateY = 10;
    private int WBishopStartX = -1;
    private int WBishopStartY = -1;
    private LinkedList<Integer> WBishopLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> WBishopLegalPathY = new LinkedList<Integer>();

    private BufferedImage WhiteHorse;
    private boolean WHorseChosen = false;
    private boolean WHorseLegalMove = false;
    private boolean WHorseDestroyed = false;
    private int WHorseCoordinateX = 4;
    private int WHorseCoordinateY = 10;
    private int WHorseStartX = -1;
    private int WHorseStartY = -1;
    private LinkedList<Integer> WHorseLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> WHorseLegalPathY = new LinkedList<Integer>();

    private BufferedImage WhiteRook;
    private boolean WRookChosen = false;
    private boolean WRookLegalMove = false;
    private boolean WRookDestroyed = false;
    private int WRookCoordinateX = 3;
    private int WRookCoordinateY = 10;
    private int WRookStartX = -1;
    private int WRookStartY = -1;
    private LinkedList<Integer> WRookLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> WRookLegalPathY = new LinkedList<Integer>();
	
    private BufferedImage WhitePawn;
    private boolean WPawnChosen = false;
    private boolean WPawnLegalMove = false;
    private boolean WPawnDestroyed = false;
    private int WPawnCoordinateX = 7;
    private int WPawnCoordinateY = 9;
    private boolean WPawnFirstMove = true;// true? 2 steps : 1 step
    private int WPawnStartX = -1;
    private int WPawnStartY = -1;
    private LinkedList<Integer> WPawnLegalPathX = new LinkedList<Integer>();
    private LinkedList<Integer> WPawnLegalPathY = new LinkedList<Integer>();
	
    public PlayGame() 
    {   
    	
    	//LeaderBoards Tables
    	final String columnNames[] = {"","",""};
		DefaultTableModel tableModel;
		tableModel = new DefaultTableModel(0,10);
		tableModel.setColumnIdentifiers(columnNames);
		
		
		
    	FloatControl gainControl = (FloatControl) MainPage.clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);

        try
        {
            BlackKing = ImageIO.read(new File("blackKing.png"));
            BlackQueen = ImageIO.read(new File("blackQueen.png"));
            BlackBishop = ImageIO.read(new File("blackBishop.png"));
            BlackHorse = ImageIO.read(new File("blackHorse.png"));
            BlackRook = ImageIO.read(new File("blackRook.png"));
            BlackPawn = ImageIO.read(new File("blackPawn.png"));
            WhiteKing = ImageIO.read(new File("whiteKing.png"));
            WhiteQueen = ImageIO.read(new File("whiteQueen.png"));
            WhiteBishop = ImageIO.read(new File("whiteBishop.png"));
            WhiteHorse = ImageIO.read(new File("whiteHorse.png"));
            WhiteRook = ImageIO.read(new File("whiteRook.png"));
            WhitePawn = ImageIO.read(new File("whitePawn.png"));
        }
        catch(IOException e)
        {
        }
        
        for(int e = 0, f = 18; e <= 18 && f >= 0; e++)
        {
            String darkFileDirectory = "explosiveAnimations/Dark/";
            String darkFileEnd = ".gif";
            
            Black[f] = new ImageIcon(darkFileDirectory + e + darkFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 18; e <= 18 && f >=0; e++)
        {
            String lightFileDirectory = "explosiveAnimations/Light/";
            String lightFileEnd = ".gif";
            
            White[f] = new ImageIcon(lightFileDirectory + e + lightFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String blackKingFileDirectory = "explosiveAnimations/BlackKing/";
            String blackKingFileEnd = ".png";
            
            BKingIcon[f] = new ImageIcon(blackKingFileDirectory + e + blackKingFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String blackQueenFileDirectory = "explosiveAnimations/BlackQueen/";
            String blackQueenFileEnd = ".png";
            
            BQueenIcon[f] = new ImageIcon(blackQueenFileDirectory + e + blackQueenFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String blackBishopFileDirectory = "explosiveAnimations/BlackBishop/";
            String blackBishopFileEnd = ".png";
            
            BBishopIcon[f] = new ImageIcon(blackBishopFileDirectory + e + blackBishopFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String blackHorseFileDirectory = "explosiveAnimations/BlackKnight/";
            String blackHorseFileEnd = ".png";
            
            BHorseIcon[f] = new ImageIcon(blackHorseFileDirectory + e + blackHorseFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String blackRookFileDirectory = "explosiveAnimations/BlackRook/";
            String blackRookFileEnd = ".png";
            
            BRookIcon[f] = new ImageIcon(blackRookFileDirectory + e + blackRookFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String blackPawnFileDirectory = "explosiveAnimations/BlackPawn/";
            String blackPawnFileEnd = ".png";
            
            BPawnIcon[f] = new ImageIcon(blackPawnFileDirectory + e + blackPawnFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String whiteKingFileDirectory = "explosiveAnimations/WhiteKing/";
            String whiteKingFileEnd = ".png";
            
            WKingIcon[f] = new ImageIcon(whiteKingFileDirectory + e + whiteKingFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String whiteQueenFileDirectory = "explosiveAnimations/WhiteQueen/";
            String whiteQueenFileEnd = ".png";
            
            WQueenIcon[f] = new ImageIcon(whiteQueenFileDirectory + e + whiteQueenFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String whiteBishopFileDirectory = "explosiveAnimations/WhiteBishop/";
            String whiteBishopFileEnd = ".png";
            
            WBishopIcon[f] = new ImageIcon(whiteBishopFileDirectory + e + whiteBishopFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String whiteHorseFileDirectory = "explosiveAnimations/WhiteKnight/";
            String whiteHorseFileEnd = ".png";
            
            WHorseIcon[f] = new ImageIcon(whiteHorseFileDirectory + e + whiteHorseFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String whiteRookFileDirectory = "explosiveAnimations/WhiteRook/";
            String whiteRookFileEnd = ".png";
            
            WRookIcon[f] = new ImageIcon(whiteRookFileDirectory + e + whiteRookFileEnd);
            
            f--;
        }
        
        for(int e = 0, f = 10; e <= 10 && f >=0; e++)
        {
            String whitePawnFileDirectory = "explosiveAnimations/WhitePawn/";
            String whitePawnFileEnd = ".png";
            
            WPawnIcon[f] = new ImageIcon(whitePawnFileDirectory + e + whitePawnFileEnd);
            
            f--;
        }
        
        for(int y = 0; y <= 10; y++)
        {
            for(int x = 0; x <= 10; x++)
            {
                square[y][x] = new Rectangle((x * fixedDimension) + positionPushX, (y * fixedDimension) + positionPushY, fixedDimension, fixedDimension);
            }
        }
        
        this.setLayout(new BorderLayout());
        
            paint.setLayout(new BorderLayout());
            paint.setOpaque(true);
                
            
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                
            paint.add(label, BorderLayout.CENTER);
            paint.add(restart, BorderLayout.SOUTH);
            paint.add(leaderBoards,BorderLayout.WEST);
            info.setBorder(border);
            info.setBackground(Color.WHITE);
            info.setOpaque(true);
            info.setFont(new Font("DialogInput", Font.BOLD, 20));
        
        add(paint, BorderLayout.CENTER);
        add(info, BorderLayout.NORTH);
        
        restart.addActionListener(myAction);
        leaderBoards.addActionListener(myAction);
        paint.addMouseListener(myMouse);
        paint.addMouseMotionListener(myMouse);
    }
    
    private void getMouseMovingLocation(MouseEvent e)
    {
        for(int y = 0; y <= 10; y++)
        {
            for(int x = 0; x <= 10; x++)
            {
                if(square[y][x].contains(e.getX(), e.getY()) && !((x + y) <= 2 || (x == 0 && y == 8)
                || (x == 0 && y == 9) || (x == 0 && y == 10) || (x == 1 && y == 9) || (x == 1 && y == 10)
                || (x == 2 && y == 10) || (x == 8 && y == 0) || (x == 9 && y == 0) || (x == 9 && y == 1)
                || (x == 10 && y == 0) || (x == 10 && y == 1) || (x == 10 && y == 2) || ((x + y) >= 18)))
                {
                    mouseOver = true;
                    mouseEnterSquareX = x;
                    mouseEnterSquareY = y;

                    break;
                }
                else
                {
                    mouseOver = false;
                }
            }
            if(mouseOver)
            {
                break;
            }
        }
    }
	
    private void getMousePressStartingPoint(MouseEvent e)
    {
        boolean skipTheLoop = false;
        
        for(int y = 0; y <= 10; y++)
        {
            for(int x = 0; x <= 10; x++)
            {
                if(square[y][x].contains(e.getX(), e.getY()) && !(((x + y) <= 2) || (x == 0 && y == 8) 
                || (x == 0 && y == 9) || (x == 0 && y == 10) || (x == 1 && y == 9) || (x == 1 && y == 10)
                || (x == 2 && y == 10) || (x == 8 && y == 0) || (x == 9 && y == 0) || (x == 9 && y == 1)
                || (x == 10 && y == 0) || (x == 10 && y == 1) || (x == 10 && y ==2) || ((x + y) >= 18)))
                {
                    getPiecePosition(x, y);
                    
                    skipTheLoop = true;
                    
                    break;
                }
                else
                {
                    skipTheLoop = false;
                }
            }
            if(skipTheLoop)
            {
                break;
            }
        }
    }
	
    private void getPiecePosition(int x, int y) // WHEN MOUSE IS PRESSED
    {
        if(!BCHECKMATE && !WKingCHECKMATE)
        {
            if(universalMatchRoundCount % 2 == 0 ) //PLAYER 1's turn
            {
                if(BKingCoordinateX == x && BKingCoordinateY == y)
                {
                    BKingStartX = x;
                    BKingStartY = y;
                    
                    info.setText(" Black King is being selected.");
                    
                    BKingLegalMove = false;
                    BKingChosen = true;
                }
                else if(BHorseCoordinateX == x && BHorseCoordinateY == y)
                {
                    BHorseStartX = x;
                    BHorseStartY = y;
                    
                    info.setText(" Black Knight is being selected.");
                    
                    BHorseLegalMove = false;
                    BHorseChosen = true;
                }
                else if(BQueenCoordinateX == x && BQueenCoordinateY == y)
                {
                    BQueenStartX = x;
                    BQueenStartY = y;
                    
                    info.setText(" Black queen is being selected.");
                    
                    BQueenLegalMove = false;
                    BQueenChosen = true;
                }
                else if(BBishopCoordinateX == x && BBishopCoordinateY == y)
                {
                    BBishopStartX = x;
                    BBishopStartY = y;
                    
                    info.setText(" Black bishop is being selected.");
                    
                    BBishopLegalMove = false;
                    BBishopChosen = true;
                }
                else if(BRookCoordinateX == x && BRookCoordinateY == y)
                {
                    BRookStartX = x;
                    BRookStartY = y;
                    
                    info.setText(" Black rook is being selected.");
                    
                    BRookLegalMove = false;
                    BRookChosen = true;
                }
                else if(BPawnCoordinateX == x && BPawnCoordinateY == y)
                {
                    BPawnStartX = x;
                    BPawnStartY = y;
                    
                    info.setText(" Black pawn is being selected.");
                    
                    BPawnLegalMove = false;
                    BPawnChosen = true;
                }
                else
                {
                    BHorseChosen = false;
                    BKingChosen = false;
                    BQueenChosen = false;
                    BBishopChosen = false;
                    BRookChosen = false;
                    BPawnChosen = false;
                    WPawnChosen = false;
                    WQueenChosen = false;
                    WRookChosen = false;
                    WBishopChosen = false;
                    WKingChosen = false;
                    WHorseChosen = false;
                }
            }
            else
            {
                if(WPawnCoordinateX == x && WPawnCoordinateY == y)
                {
                    WPawnStartX = x;
                    WPawnStartY = y;
                    
                    info.setText(" White pawn is being selected.");
                    
                    WPawnLegalMove = false;
                    WPawnChosen = true;
                }
                else if(WQueenCoordinateX == x && WQueenCoordinateY == y)
                {
                    WQueenStartX = x;
                    WQueenStartY = y;
                    
                    info.setText(" White pawn is being selected.");
                    
                    WQueenLegalMove = false;
                    WQueenChosen = true;
                }
                else if(WRookCoordinateX == x && WRookCoordinateY == y)
                {
                    WRookStartX = x;
                    WRookStartY = y;
                    
                    info.setText(" White rook is being selected.");
                    
                    WRookLegalMove = false;
                    WRookChosen = true;
                }
                else if(WBishopCoordinateX == x && WBishopCoordinateY == y)
                {
                    WBishopStartX = x;
                    WBishopStartY = y;
                    
                    info.setText(" White bishop is being selected.");
                    
                    WBishopLegalMove = false;
                    WBishopChosen = true;
                }
                else if(WKingCoordinateX == x && WKingCoordinateY == y)
                {
                    WKingStartX = x;
                    WKingStartY = y;
                    
                    info.setText(" White king is being selected.");
                    
                    WKingLegalMove = false;
                    WKingChosen = true;
                }
                else if(WHorseCoordinateX == x && WHorseCoordinateY == y)
                {
                    WHorseStartX = x;
                    WHorseStartY = y;
                    
                    info.setText(" White king is being selected.");
                    
                    WHorseLegalMove = false;
                    WHorseChosen = true;
                }
                else
                {
                    BHorseChosen = false;
                    BKingChosen = false;
                    BQueenChosen = false;
                    BBishopChosen = false;
                    BRookChosen = false;
                    BPawnChosen = false;
                    WPawnChosen = false;
                    WQueenChosen = false;
                    WRookChosen = false;
                    WBishopChosen = false;
                    WKingChosen = false;
                    WHorseChosen = false;
                }
            }
        }
    }

    private boolean darkPiecesIsBlocked(int x, int y)
    {
        if((x == BHorseCoordinateX && y == BHorseCoordinateY) || ( x == BKingCoordinateX && y == BKingCoordinateY )
        || (x == BQueenCoordinateX && y == BQueenCoordinateY)|| (x == BRookCoordinateX && y == BRookCoordinateY)
        || (x == BBishopCoordinateX && y == BBishopCoordinateY) || (x == BPawnCoordinateX && y == BPawnCoordinateY)
        || ((x + y) <= 2) || (x == 0 && y == 8) || (x == 0 && y == 9)
        || (x == 0 && y == 10) || (x == 1 && y == 9) || (x == 1 && y == 10)
        || (x == 2 && y == 10) || (x == 8 && y == 0) || (x == 9 && y == 0)
        || (x == 9 && y == 1) || (x == 10 && y == 0) || (x == 10 && y == 1)
        || (x == 10 && y == 2) || ((x + y) >= 18)
        || (x < 0) || (y < 0) || (x >= 11) || (y >= 11))
        {
            return true;
        }
        else
        {
            return false;
        }
    }	
		
    private boolean darkPiecesCapturing(int x, int y)
    {
        if((x == WHorseCoordinateX && y == WHorseCoordinateY) || (x == WKingCoordinateX && y == WKingCoordinateY)
        || (x == WQueenCoordinateX && y == WQueenCoordinateY)|| (x == WRookCoordinateX && y == WRookCoordinateY)
        || (x == WBishopCoordinateX && y == WBishopCoordinateY)|| (x == WPawnCoordinateX && y == WPawnCoordinateY))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void setDarkKingPath()
    {
        int x;
        int y;
        
        x = BKingStartX - 1;
        y = BKingStartY - 1;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(isDarkKingCapturedPath(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        else
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        
        x = BKingStartX;
        y = BKingStartY - 1;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(isDarkKingCapturedPath(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        else
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        
        x = BKingStartX + 1;
        y = BKingStartY - 1;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(isDarkKingCapturedPath(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        else
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        
        x = BKingStartX + 1;
        y = BKingStartY;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(isDarkKingCapturedPath(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        else
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        
        x = BKingStartX + 1;
        y = BKingStartY + 1;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(isDarkKingCapturedPath(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        else
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        
        x = BKingStartX;
        y = BKingStartY + 1;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(isDarkKingCapturedPath(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        else
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        
        x = BKingStartX - 1;
        y = BKingStartY + 1;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(isDarkKingCapturedPath(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        else
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        
        x = BKingStartX - 1;
        y = BKingStartY;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(isDarkKingCapturedPath(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
        else
        {
            BKingLegalPathX.add(x);
            BKingLegalPathY.add(y);
        }
    }
	
    private void setDarkQueenPath()
    {
        int x;
        int y;
        
        for(int constant = 1; constant <= 10; constant++)
        {
            x = BQueenStartX - constant;
            y = BQueenStartY - constant;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BQueenStartX;
            y = BQueenStartY - constant;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BQueenStartX + constant;
            y = BQueenStartY - constant;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BQueenStartX + constant;
            y = BQueenStartY;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
               
                break;
            }
            else
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
            }
        }

        for(int constant=1; constant<=10 ; constant++)
        {
                x=BQueenStartX+constant;
                y=BQueenStartY+constant;
                if(darkPiecesIsBlocked(x,y))
                {break;}
                else if(darkPiecesCapturing(x,y))
                {
                        BQueenLegalPathX.add(x);
                        BQueenLegalPathY.add(y);
                        break;
                }
                else
                {
                        BQueenLegalPathX.add(x);
                        BQueenLegalPathY.add(y);
                }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
                x = BQueenStartX;
                y = BQueenStartY + constant;

                if(darkPiecesIsBlocked(x, y))
                {
                    break;
                }
                else if(darkPiecesCapturing(x, y))
                {
                    BQueenLegalPathX.add(x);
                    BQueenLegalPathY.add(y);
                    
                    break;
                }
                else
                {
                    BQueenLegalPathX.add(x);
                    BQueenLegalPathY.add(y);
                }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BQueenStartX - constant;
            y = BQueenStartY + constant;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BQueenStartX - constant;
            y = BQueenStartY;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                BQueenLegalPathX.add(x);
                BQueenLegalPathY.add(y);
            }
        }
    }
	
    private void setDarkBishopPath()
    {
        int x;
        int y;
        
        for(int constant = 1; constant <= 10; constant++)
        {
            x = BBishopStartX - constant;
            y = BBishopStartY - constant; 
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BBishopLegalPathX.add(x);
                BBishopLegalPathY.add(y);
                
                break;
            }
            else
            {
                BBishopLegalPathX.add(x);
                BBishopLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BBishopStartX + constant;
            y = BBishopStartY - constant;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BBishopLegalPathX.add(x);
                BBishopLegalPathY.add(y);
                
                break;
            }
            else
            {
                BBishopLegalPathX.add(x);
                BBishopLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BBishopStartX + constant;
            y = BBishopStartY + constant;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BBishopLegalPathX.add(x);
                BBishopLegalPathY.add(y);
                
                break;
            }
            else
            {
                BBishopLegalPathX.add(x);
                BBishopLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BBishopStartX - constant;
            y = BBishopStartY + constant;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BBishopLegalPathX.add(x);
                BBishopLegalPathY.add(y);
                
                break;
            }
            else
            {
                BBishopLegalPathX.add(x);
                BBishopLegalPathY.add(y);
            }
        }
    }
    
    private void setDarkHorsePath()
    {
        int x;
        int y;

        x = BHorseStartX - 1;
        y = BHorseStartY - 2;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        else
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        
        x = BHorseStartX + 1;
        y = BHorseStartY - 2;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        else
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        
        x = BHorseStartX + 2;
        y = BHorseStartY - 1;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        else
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        
        x = BHorseStartX + 2;
        y = BHorseStartY + 1;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        else
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        
        x = BHorseStartX + 1;
        y = BHorseStartY + 2;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        else
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        
        x = BHorseStartX - 1;
        y = BHorseStartY + 2;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        else
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        
        x = BHorseStartX - 2;
        y = BHorseStartY + 1;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        else
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        
        x = BHorseStartX - 2;
        y = BHorseStartY - 1;
        
        if(darkPiecesIsBlocked(x, y))
        {
        }
        else if(darkPiecesCapturing(x, y))
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
        else
        {
            BHorseLegalPathX.add(x);
            BHorseLegalPathY.add(y);
        }
    }
    
    private void setDarkRookPath()
    {
        int x;
        int y;

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BRookStartX;
            y = BRookStartY - constant;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BRookLegalPathX.add(x);
                BRookLegalPathY.add(y);
                
                break;
            }
            else
            {
                BRookLegalPathX.add(x);
                BRookLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BRookStartX + constant;
            y = BRookStartY;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BRookLegalPathX.add(x);
                BRookLegalPathY.add(y);
                
                break;    
            }
            else
            {
                BRookLegalPathX.add(x);
                BRookLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BRookStartX;
            y = BRookStartY + constant;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BRookLegalPathX.add(x);
                BRookLegalPathY.add(y);
                
                break;
            }
            else
            {
                BRookLegalPathX.add(x);
                BRookLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = BRookStartX - constant;
            y = BRookStartY;
            
            if(darkPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(darkPiecesCapturing(x, y))
            {
                BRookLegalPathX.add(x);
                BRookLegalPathY.add(y);
                
                break;
            }
            else
            {
                BRookLegalPathX.add(x);
                BRookLegalPathY.add(y);
            }
        }
    }
    
    private void setDarkPawnPath()
    {
        int x;
        int y;
        
        if(BPawnFirstMove)
        {
            for(int constant = 1; constant <= 2; constant++)
            {
                x = BPawnStartX;
                y = BPawnStartY + constant;
                
                if(darkPiecesIsBlocked(x, y))
                {
                    break;
                }
                else if(darkPiecesCapturing(x, y))
                {
                    break;
                }
                else
                {
                    BPawnLegalPathX.add(x);
                    BPawnLegalPathY.add(y);
                }
            }
        }
        else
        {
            x = BPawnStartX;
            y = BPawnStartY + 1;
            
            if(darkPiecesIsBlocked(x, y))
            {
            }
            else if (darkPiecesCapturing(x, y))
            {
            }
            else
            {
                BPawnLegalPathX.add(x);
                BPawnLegalPathY.add(y);
            }
        }
        
        x = BPawnStartX + 1;
        y = BPawnStartY + 1;
        
        if(darkPiecesCapturing(x, y))
        {
            BPawnLegalPathX.add(x);
            BPawnLegalPathY.add(y);
        }
        else
        {
        }
        
        x = BPawnStartX - 1;
        y = BPawnStartY + 1;
        
        if(darkPiecesCapturing(x, y))
        {
            BPawnLegalPathX.add(x);
            BPawnLegalPathY.add(y);
        }
        else
        {
        }
    }
	
    private boolean lightPiecesIsBlocked(int x, int y)
    {
        if((x == WHorseCoordinateX && y == WHorseCoordinateY) || (x == WKingCoordinateX && y == WKingCoordinateY)
        || (x == WQueenCoordinateX && y == WQueenCoordinateY) || (x == WRookCoordinateX && y == WRookCoordinateY)
        || (x == WBishopCoordinateX && y == WBishopCoordinateY)|| (x == WPawnCoordinateX && y == WPawnCoordinateY)
        || ((x + y) <= 2) || (x == 0 && y == 8) || (x == 0 && y == 9)
        || (x == 0 && y == 10) || (x == 1 && y == 9) || (x == 1 && y == 10)
        || (x == 2 && y == 10) || (x == 8 && y == 0) || (x == 9 && y == 0)
        || (x == 9 && y == 1) || (x == 10 && y == 0) || (x == 10 && y == 1)
        || (x == 10 && y == 2) || ((x + y) >= 18)
        || (x < 0) || (y < 0) || (x >= 11) || (y >= 11))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean lightPiecesCapturing(int x , int y)
    {
        if((x == BHorseCoordinateX && y == BHorseCoordinateY) || (x == BKingCoordinateX && y == BKingCoordinateY)
        || (x == BQueenCoordinateX && y == BQueenCoordinateY) || (x == BRookCoordinateX && y == BRookCoordinateY)
        || (x == BBishopCoordinateX && y == BBishopCoordinateY)|| (x == BPawnCoordinateX && y == BPawnCoordinateY))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
	
    private void setLightKingPath()
    {
        int x;
        int y;
        
        x = WKingStartX - 1;
        y = WKingStartY - 1;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(isLightKingCapturedPath(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        else
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        
        x = WKingStartX;
        y = WKingStartY - 1;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(isLightKingCapturedPath(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        else
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        
        x = WKingStartX + 1;
        y = WKingStartY - 1;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(isLightKingCapturedPath(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        else
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        
        x = WKingStartX + 1;
        y = WKingStartY;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(isLightKingCapturedPath(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        else
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        
        x = WKingStartX + 1;
        y = WKingStartY + 1;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(isLightKingCapturedPath(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        else
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        
        x = WKingStartX;
        y = WKingStartY + 1;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(isLightKingCapturedPath(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        else
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        
        x = WKingStartX - 1;
        y = WKingStartY + 1;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(isLightKingCapturedPath(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        else
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        
        x = WKingStartX - 1;
        y = WKingStartY;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(isLightKingCapturedPath(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
        else
        {
            WKingLegalPathX.add(x);
            WKingPossiblePathY.add(y);
        }
    }
    
    private void setLightQueenPath()
    {
        int x;
        int y;
        
        for(int constant = 1; constant <= 10; constant++)
        {
            x = WQueenStartX - constant;
            y = WQueenStartY - constant; 
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WQueenStartX;
            y = WQueenStartY - constant;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WQueenStartX + constant;
            y = WQueenStartY - constant;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WQueenStartX + constant;
            y = WQueenStartY;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WQueenStartX + constant;
            y = WQueenStartY + constant;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WQueenStartX;
            y = WQueenStartY + constant;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WQueenStartX - constant;
            y = WQueenStartY + constant;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WQueenStartX - constant;
            y = WQueenStartY;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
                
                break;
            }
            else
            {
                WQueenLegalPathX.add(x);
                WQueenLegalPathY.add(y);
            }
        }
    }
    
    private void setLightBishopPath()
    {
        int x;
        int y;
        
        for(int constant = 1; constant <= 10; constant++)
        {
            x = WBishopStartX - constant;
            y = WBishopStartY - constant; 
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WBishopLegalPathX.add(x);
                WBishopLegalPathY.add(y);
                
                break;
            }
            else
            {
                WBishopLegalPathX.add(x);
                WBishopLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WBishopStartX + constant;
            y = WBishopStartY - constant;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WBishopLegalPathX.add(x);
                WBishopLegalPathY.add(y);
                
                break;
            }
            else
            {
                WBishopLegalPathX.add(x);
                WBishopLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WBishopStartX + constant;
            y = WBishopStartY + constant;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WBishopLegalPathX.add(x);
                WBishopLegalPathY.add(y);
                
                break;
            }
            else
            {
                WBishopLegalPathX.add(x);
                WBishopLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WBishopStartX - constant;
            y = WBishopStartY + constant;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WBishopLegalPathX.add(x);
                WBishopLegalPathY.add(y);
            }
            else
            {
                WBishopLegalPathX.add(x);
                WBishopLegalPathY.add(y);
            }
        }
    }

    private void setLightHorsePath()
    {
        int x;
        int y;

        x = WHorseStartX - 1;
        y = WHorseStartY - 2;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        else
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        
        x = WHorseStartX + 1;
        y = WHorseStartY - 2;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        else
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        
        x = WHorseStartX + 2;
        y = WHorseStartY - 1;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        else
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        
        x = WHorseStartX + 2;
        y = WHorseStartY + 1;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        else
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        
        x = WHorseStartX + 1;
        y = WHorseStartY + 2;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        else
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        
        x = WHorseStartX - 1;
        y = WHorseStartY + 2;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        else
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        
        x = WHorseStartX - 2;
        y = WHorseStartY + 1;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        else
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        
        x = WHorseStartX - 2;
        y = WHorseStartY - 1;
        
        if(lightPiecesIsBlocked(x, y))
        {
        }
        else if(lightPiecesCapturing(x, y))
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
        else
        {
            WHorseLegalPathX.add(x);
            WHorseLegalPathY.add(y);
        }
    }
    
    private void setLightRookPath()
    {
        int x;
        int y;
        
        for(int constant = 1; constant <= 10; constant++)
        {
            x = WRookStartX;
            y = WRookStartY - constant;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WRookLegalPathX.add(x);
                WRookLegalPathY.add(y);
                
                break;
            }
            else
            {
                WRookLegalPathX.add(x);
                WRookLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WRookStartX + constant;
            y = WRookStartY;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WRookLegalPathX.add(x);
                WRookLegalPathY.add(y);
                
                break;
            }
            else
            {
                WRookLegalPathX.add(x);
                WRookLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WRookStartX;
            y = WRookStartY + constant;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WRookLegalPathX.add(x);
                WRookLegalPathY.add(y);
                
                break;
            }
            else
            {
                WRookLegalPathX.add(x);
                WRookLegalPathY.add(y);
            }
        }

        for(int constant = 1; constant <= 10; constant++)
        {
            x = WRookStartX - constant;
            y = WRookStartY;
            
            if(lightPiecesIsBlocked(x, y))
            {
                break;
            }
            else if(lightPiecesCapturing(x, y))
            {
                WRookLegalPathX.add(x);
                WRookLegalPathY.add(y);
                
                break;
            }
            else
            {
                WRookLegalPathX.add(x);
                WRookLegalPathY.add(y);
            }
        }
    }
	
    private void setLightPawnPath()
    {
        int x;
        int y;
        
        if(WPawnFirstMove)
        {
            for(int constant = 1; constant <=2; constant++)
            {
                x = WPawnStartX;
                y = WPawnStartY - constant;
                
                if(lightPiecesIsBlocked(x, y))
                {
                    break;
                }
                else if(lightPiecesCapturing(x, y))
                {
                    break;
                }
                else
                {
                    WPawnLegalPathX.add(x);
                    WPawnLegalPathY.add(y);
                }
            }
        }
        else
        {
            x = WPawnStartX;
            y = WPawnStartY - 1;
            
            if(lightPiecesIsBlocked(x, y))
            {
            }
            else if(lightPiecesCapturing(x, y))
            {
            }
            else
            {
                WPawnLegalPathX.add(x);
                WPawnLegalPathY.add(y);
            }
        }
        
        x = WPawnStartX + 1;
        y = WPawnStartY - 1;
        
        if(lightPiecesCapturing(x, y))
        {
            WPawnLegalPathX.add(x);
            WPawnLegalPathY.add(y);
        }
        else
        {
        }
        
        x = WPawnStartX - 1;
        y = WPawnStartY - 1;
        
        if(lightPiecesCapturing(x, y))
        {
            WPawnLegalPathX.add(x);
            WPawnLegalPathY.add(y);
        }
        else
        {
        }
    }

    private void getMouseDraggingPoint(MouseEvent e)
    {
        for(int y = 0; y <= 10; y++)
        {
            for(int x = 0; x <= 10; x++)
            {
                if(square[y][x].contains(e.getX(), e.getY()) && !(((x + y) <= 2) || (x == 0 && y == 8)
                || (x == 0 && y == 9) || (x == 0 && y == 10) || (x == 1 && y == 9) || (x == 1 && y == 10)
                || (x == 2 && y == 10) || (x == 8 && y == 0) || (x == 9 && y == 0) || (x == 9 && y == 1)
                || (x == 10 && y == 0) || (x == 10 && y == 1) || (x == 10 && y == 2) ||((x + y) >= 18)))
                {
                    if(BKingChosen)
                    {
                        BKingCoordinateX = x;
                        BKingCoordinateY = y;
                        BKingLegalMove = darkKingMove(x, y);
                    }
                    else if(BQueenChosen)
                    {
                        BQueenCoordinateX = x;
                        BQueenCoordinateY = y;
                        BQueenLegalMove = darkQueenMove(x, y);
                    }
                    else if(BBishopChosen)
                    {
                        BBishopCoordinateX = x;
                        BBishopCoordinateY = y;
                        BBishopLegalMove = darkBishopMove(x, y);
                    }
                    else if(BHorseChosen)
                    {
                        BHorseCoordinateX = x;
                        BHorseCoordinateY = y;
                        BHorseLegalMove = darkHorseMove(x, y);
                    }
                    else if(BRookChosen)
                    {
                        BRookCoordinateX = x;
                        BRookCoordinateY = y;
                        BRookLegalMove = darkRookMove(x, y);
                    }
                    else if(BPawnChosen)
                    {
                        BPawnCoordinateX = x;
                        BPawnCoordinateY = y;
                        BPawnLegalMove = darkPawnMove(x, y);
                    }
                    else if(WKingChosen)
                    {
                        WKingCoordinateX = x;
                        WKingCoordinateY = y;
                        WKingLegalMove = lightKingMove(x, y);
                    }
                    else if(WQueenChosen)
                    {
                        WQueenCoordinateX = x;
                        WQueenCoordinateY = y;
                        WQueenLegalMove = lightQueenMove(x, y);
                    }
                    else if(WBishopChosen)
                    {
                        WBishopCoordinateX = x;
                        WBishopCoordinateY = y;
                        WBishopLegalMove = lightBishopMove(x, y);
                    }
                    else if(WHorseChosen)
                    {
                        WHorseCoordinateX = x;
                        WHorseCoordinateY = y;
                        WHorseLegalMove = lightHorseMove(x, y);
                    }
                    else if(WRookChosen)
                    {
                        WRookCoordinateX = x;
                        WRookCoordinateY = y;
                        WRookLegalMove = lightRookMove(x, y);
                    }
                    else if(WPawnChosen)
                    {
                        WPawnCoordinateX = x;
                        WPawnCoordinateY = y;
                        WPawnLegalMove = lightPawnMove(x, y);
                    }
                }
                else
                {
                }
            }
        }
    }
	
    private boolean darkKingMove(int x, int y)
    {
        for(int index = 0; index <= BKingLegalPathX.size() - 1 && index <= BKingLegalPathY.size() - 1; index++)
        {
            if(x == BKingLegalPathX.get(index) && y == BKingLegalPathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving dark king.");
        
        return false;
    }

    private boolean darkHorseMove(int x, int y)
    {
        for(int index = 0; index <= BHorseLegalPathX.size() - 1 && index <= BHorseLegalPathY.size() - 1; index++)
        {
            if(x == BHorseLegalPathX.get(index) && y == BHorseLegalPathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving dark knight.");
        
        return false;
    }
	
    private boolean darkQueenMove(int x, int y)
    {
        for(int index = 0; index <= BQueenLegalPathX.size() - 1 && index <= BQueenLegalPathY.size() - 1; index++)
        {
            if(x == BQueenLegalPathX.get(index) && y == BQueenLegalPathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving dark queen.");
        
        return false;
    }
	
    private boolean darkBishopMove(int x, int y)
    {
        for(int index = 0; index <= BBishopLegalPathX.size() - 1 && index <= BBishopLegalPathY.size() - 1; index++)
        {
            if(x == BBishopLegalPathX.get(index) && y == BBishopLegalPathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving dark bishop.");
        
        return false;
    }
	
    private boolean darkRookMove(int x, int y)
    {
        for(int index = 0; index <= BRookLegalPathX.size() - 1 && index <= BRookLegalPathY.size() - 1; index++)
        {
            if(x == BRookLegalPathX.get(index) && y == BRookLegalPathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving dark rook.");
        
        return false;
    }

    private boolean darkPawnMove(int x, int y)
    {
        for(int index = 0; index <= BPawnLegalPathX.size() - 1 && index <= BPawnLegalPathY.size() - 1; index++)
        {
            if(x == BPawnLegalPathX.get(index) && y == BPawnLegalPathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving dark pawn.");
        
        return false;
    }

    private boolean lightPawnMove(int x, int y)
    {
        for(int index = 0; index <= WPawnLegalPathX.size() - 1 && index <= WPawnLegalPathY.size() - 1; index++)
        {
            if(x == WPawnLegalPathX.get(index) && y == WPawnLegalPathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving light pawn.");
        
        return false;
    }
	
    private boolean lightQueenMove(int x, int y)
    {
        for(int index = 0; index <= WQueenLegalPathX.size() - 1 && index <= WQueenLegalPathY.size() - 1; index++)
        {
            if(x == WQueenLegalPathX.get(index) && y == WQueenLegalPathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving light queen.");
        
        return false;
    }
	
    private boolean lightRookMove(int x, int y)
    {
        for(int index = 0; index <= WRookLegalPathX.size() - 1 && index <= WRookLegalPathY.size() - 1; index++)
        {
            if(x == WRookLegalPathX.get(index) && y == WRookLegalPathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving light rook.");
        
        return false;
    }
	
    private boolean lightBishopMove(int x, int y)
    {
        for(int index = 0; index <= WBishopLegalPathX.size() - 1 && index <= WBishopLegalPathY.size() - 1; index++)
        {
            if(x == WBishopLegalPathX.get(index) && y == WBishopLegalPathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving light bishop.");
        
        return false;
    }
	
    private boolean lightKingMove(int x, int y)
    {
        for(int index = 0; index <= WKingLegalPathX.size() - 1 && index <= WKingPossiblePathY.size() - 1; index++)
        {
            if(x == WKingLegalPathX.get(index) && y == WKingPossiblePathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving light king.");
        
        return false;
    }
    
    private boolean lightHorseMove(int x, int y)
    {
        for(int index = 0; index <= WHorseLegalPathX.size() - 1 && index <= WHorseLegalPathY.size() - 1; index++)
        {
            if(x == WHorseLegalPathX.get(index) && y == WHorseLegalPathY.get(index))
            {
                info.setText(" Valid move.");
                
                return true;
            }
        }
        
        info.setText(" Invalid moving light horse.");
        
        return false;
    }
	
	
    private void confirmSetPosition()
    {
        if(BKingChosen)
        {
            BKingChosen = false;
            
            if(BKingLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;

                BKingStartX = BKingCoordinateX;
                BKingStartY = BKingCoordinateY;
                
                validCapture=tryCaptureLight(BKingCoordinateX,BKingCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    AvadraSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    blackKingAttack = true;
                    
                    animTimer.start();
                }
            }
            else
            {
                invalidMoveSound();

                BKingCoordinateX = BKingStartX;
                BKingCoordinateY = BKingStartY;
            }
        }
        else if(BHorseChosen)
        {
            BHorseChosen = false;
            
            clearAllPath();
            setAllPath();
            setCheckedPath();
            
            if(BHorseLegalMove && BKingChecked)
            {
                invalidMoveSound();

                BHorseCoordinateX = BHorseStartX;
                BHorseCoordinateY = BHorseStartY;
            }
            else if(BHorseLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;

                BHorseStartX = BHorseCoordinateX;
                BHorseStartY = BHorseCoordinateY;
                
                validCapture = tryCaptureLight(BHorseCoordinateX,BHorseCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    AvadraSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    blackHorseAttack = true;
                    
                    animTimer.start();
                }
            }
            else
            {
                invalidMoveSound();

                BHorseCoordinateX = BHorseStartX;
                BHorseCoordinateY = BHorseStartY;
            }
        }
        else if(BQueenChosen)
        {
            BQueenChosen=false;
            
            clearAllPath();
            setAllPath();
            setCheckedPath();
            
            if(BQueenLegalMove && BKingChecked)
            {
                invalidMoveSound();

                BQueenCoordinateX = BQueenStartX;
                BQueenCoordinateY = BQueenStartY;
            }
            else if(BQueenLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;

                BQueenStartX = BQueenCoordinateX;
                BQueenStartY = BQueenCoordinateY;
                
                validCapture = tryCaptureLight(BQueenCoordinateX,BQueenCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    AvadraSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    blackQueenAttack = true;
                    
                    animTimer.start();
                }
            }
            else
            {
                invalidMoveSound();

                BQueenCoordinateX = BQueenStartX;
                BQueenCoordinateY = BQueenStartY;
            }
        }
        else if(BBishopChosen)
        {
            BBishopChosen = false;
            
            clearAllPath();
            setAllPath();
            setCheckedPath();
            
            if(BBishopLegalMove && BKingChecked)
            {
                invalidMoveSound();

                BBishopCoordinateX = BBishopStartX;
                BBishopCoordinateY = BBishopStartY;
            }
            else if(BBishopLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;

                BBishopStartX = BBishopCoordinateX;
                BBishopStartY = BBishopCoordinateY;
                
                validCapture = tryCaptureLight(BBishopCoordinateX,BBishopCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    AvadraSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    blackBishopAttack = true;
                    
                    animTimer.start();
                }
            }
            else
            {
                invalidMoveSound();

                BBishopCoordinateX = BBishopStartX;
                BBishopCoordinateY = BBishopStartY;
            }
        }
        else if(BRookChosen)
        {
            BRookChosen = false;
            
            clearAllPath();
            setAllPath();
            setCheckedPath();
            
            if(BRookLegalMove && BKingChecked)
            {
                invalidMoveSound();

                BRookCoordinateX = BRookStartX;
                BRookCoordinateY = BRookStartY;
            }
            else if(BRookLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;

                BRookStartX = BRookCoordinateX;
                BRookStartY = BRookCoordinateY;
            
                validCapture = tryCaptureLight(BRookCoordinateX,BRookCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    AvadraSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    blackRookAttack = true;
                    
                    animTimer.start();
                }
            }
            else
            {
                invalidMoveSound();

                BRookCoordinateX = BRookStartX;
                BRookCoordinateY = BRookStartY;
            }
        }
        else if(BPawnChosen)
        {
            BPawnChosen = false;
            
            clearAllPath();
            setAllPath();
            setCheckedPath();
            
            if(BPawnLegalMove && BKingChecked)
            {
                invalidMoveSound();

                BPawnCoordinateX = BPawnStartX;
                BPawnCoordinateY = BPawnStartY;
            }
            else if(BPawnLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;

                BPawnStartX = BPawnCoordinateX;
                BPawnStartY = BPawnCoordinateY;
                BPawnFirstMove = false;
                
                validCapture = tryCaptureLight(BPawnCoordinateX,BPawnCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    AvadraSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    blackPawnAttack = true;
                   
                    animTimer.start();
                }

            }
            else
            {
                invalidMoveSound();

                BPawnCoordinateX = BPawnStartX;
                BPawnCoordinateY = BPawnStartY;
            }
        }
        else if(WPawnChosen)
        {
            WPawnChosen = false;
            
            clearAllPath();
            setAllPath();
            setCheckedPath();
            
            if(WPawnLegalMove && WKingChecked)
            {
                invalidMoveSound();

                WPawnCoordinateX = WPawnStartX;
                WPawnCoordinateY = WPawnStartY;
            }
            else if(WPawnLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;

                WPawnStartX = WPawnCoordinateX;
                WPawnStartY = WPawnCoordinateY;
                WPawnFirstMove = false;
                
                validCapture = tryCaptureDark(WPawnCoordinateX,WPawnCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    ExpectoSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    whitePawnAttack = true;
                    
                    animTimer.start();
                }
            }
            else
            {
                invalidMoveSound();

                WPawnCoordinateX = WPawnStartX;
                WPawnCoordinateY = WPawnStartY;
            }
        }
        else if(WQueenChosen)
        {
            WQueenChosen = false;
            
            clearAllPath();
            setAllPath();
            setCheckedPath();
            
            if(WQueenLegalMove && WKingChecked)
            {
                invalidMoveSound();

                WQueenCoordinateX = WQueenStartX;
                WQueenCoordinateY = WQueenStartY;
            }
            else if(WQueenLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;

                WQueenStartX = WQueenCoordinateX;
                WQueenStartY = WQueenCoordinateY;
                
                validCapture = tryCaptureDark(WQueenCoordinateX,WQueenCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    ExpectoSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    whiteQueenAttack = true;
                    
                    animTimer.start();
                }
            }
            else
            {
                invalidMoveSound();

                WQueenCoordinateX = WQueenStartX;
                WQueenCoordinateY = WQueenStartY;
            }
        }
        else if(WRookChosen)
        {
            WRookChosen = false;
            
            clearAllPath();
            setAllPath();
            setCheckedPath();
            
            if(WRookLegalMove && WKingChecked)
            {
                invalidMoveSound();

                WRookCoordinateX = WRookStartX;
                WRookCoordinateY = WRookStartY;
            }
            else if(WRookLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;
                
                WRookStartX = WRookCoordinateX;
                WRookStartY = WRookCoordinateY;
                
                validCapture = tryCaptureDark(WRookCoordinateX,WRookCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    ExpectoSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    whiteRookAttack = true;
                    
                    animTimer.start();
                }
            }
            else
            {
                invalidMoveSound();

                WRookCoordinateX = WRookStartX;
                WRookCoordinateY = WRookStartY;
            }
        }
        else if(WBishopChosen)
        {
            WBishopChosen = false;
            
            clearAllPath();
            setAllPath();
            setCheckedPath();
            
            if(WBishopLegalMove && WKingChecked)
            {
                invalidMoveSound();

                WBishopCoordinateX = WBishopStartX;
                WBishopCoordinateY = WBishopStartY;
            }
            else if(WBishopLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;

                WBishopStartX = WBishopCoordinateX;
                WBishopStartY = WBishopCoordinateY;
                
                validCapture = tryCaptureDark(WBishopCoordinateX,WBishopCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    ExpectoSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    whiteBishopAttack = true;
                    
                    animTimer.start();
                }
            }
            else
            {
                invalidMoveSound();

                WBishopCoordinateX = WBishopStartX;
                WBishopCoordinateY = WBishopStartY;
            }
        }
        else if(WKingChosen)
        {
            WKingChosen = false;
            
            if(WKingLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;

                WKingStartX = WKingCoordinateX;
                WKingStartY = WKingCoordinateY;
                
                validCapture = tryCaptureDark(WKingCoordinateX,WKingCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    ExpectoSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    whiteKingAttack = true;
                    
                    animTimer.start();
                }
            }
            else
            {
                invalidMoveSound();

                WKingCoordinateX = WKingStartX;
                WKingCoordinateY = WKingStartY;
            }
        }
        else if(WHorseChosen)
        {
            WHorseChosen = false;
            
            clearAllPath();
            setAllPath();
            setCheckedPath();
            
            if(WHorseLegalMove && WKingChecked)
            {
                invalidMoveSound();

                WHorseCoordinateX = WHorseStartX;
                WHorseCoordinateY = WHorseStartY;
            }
            else if(WHorseLegalMove)
            {
                validMoveSound();

                boolean validCapture = false;

                WHorseStartX = WHorseCoordinateX;
                
                WHorseStartY = WHorseCoordinateY;
                validCapture = tryCaptureDark(WHorseCoordinateX,WHorseCoordinateY);
                
                universalMatchRoundCount++;

                if(validCapture)
                {
                    ExpectoSound();
                    
                    alpha = 0f;
                    pngCount = 10;
                    whiteHorseAttack = true;
                    
                    animTimer.start();
                }
            }
            else
            {
                invalidMoveSound();

                WHorseCoordinateX = WHorseStartX;
                WHorseCoordinateY = WHorseStartY;
            }
        }
        else
        {
        }
    }
    
        private boolean tryCaptureLight(int x, int y)            
	{
        if(x == WKingCoordinateX && y == WKingCoordinateY)	{
        	
        	WKingCHECKMATE=true;
        	return true;
        }	
		if(x==WQueenCoordinateX && y==WQueenCoordinateY)
		{
                    
                    
			WQueenDestroyed=true;
			WQueenCoordinateX=-1;
			WQueenCoordinateY=-1;
			return true;
		}
		else if(x==WHorseCoordinateX && y==WHorseCoordinateY)
		{
			WHorseDestroyed=true;
			WHorseCoordinateX=-1;
			WHorseCoordinateY=-1;
			return true;
		}
		else if(x==WRookCoordinateX && y==WRookCoordinateY)
		{
			WRookDestroyed=true;
			WRookCoordinateX=-1;
			WRookCoordinateY=-1;
			return true;
		}
		else if(x==WBishopCoordinateX && y==WBishopCoordinateY)
		{
			WBishopDestroyed=true;
			WBishopCoordinateX=-1;
			WBishopCoordinateY=-1;
			return true;
		}
		else if(x==WPawnCoordinateX && y==WPawnCoordinateY)
		{
			WPawnDestroyed=true;
			WPawnCoordinateX=-1;
			WPawnCoordinateY=-1;
			return true;
		}
		else
			{
				return false;
			}
		
		
	}
	

	private boolean tryCaptureDark(int x, int y)
	{
		
		if(x == BKingCoordinateX && y== BKingCoordinateY)
		{	
			BCHECKMATE=true;
			return true;
		}	
		if(x==BQueenCoordinateX && y==BQueenCoordinateY)
		{
			BQueenDestroyed=true;
			BQueenCoordinateX=-1;
			BQueenCoordinateY=-1;
			return true;
		}
		else if(x==BHorseCoordinateX && y==BHorseCoordinateY)
		{
			BHorseDestroyed=true;
			BHorseCoordinateX=-1;
			BHorseCoordinateY=-1;
			return true;
		}
		else if(x==BRookCoordinateX && y==BRookCoordinateY)
		{
			BRookDestroyed=true;
			BRookCoordinateX=-1;
			BRookCoordinateY=-1;
			return true;
		}
		else if(x==BBishopCoordinateX && y==BBishopCoordinateY)
		{
			BBishopDestroyed=true;
			BBishopCoordinateX=-1;
			BBishopCoordinateY=-1;
			return true;
		}
		else if(x==BPawnCoordinateX && y==BPawnCoordinateY)
		{
			BPawnDestroyed=true;
			BPawnCoordinateX=-1;
			BPawnCoordinateY=-1;
			return true;
		}
		else
                {
                    return false;
                }
		
	}
	
	
	private void clearAllPath()
	{
		BKingLegalPathX.clear();
		BKingLegalPathY.clear();
		BHorseLegalPathX.clear();
		BHorseLegalPathY.clear();
		BQueenLegalPathX.clear();
		BQueenLegalPathY.clear();
		BBishopLegalPathX.clear();
		BBishopLegalPathY.clear();
		BRookLegalPathX.clear();
		BRookLegalPathY.clear();
		BPawnLegalPathX.clear();
		BPawnLegalPathY.clear();
		WPawnLegalPathX.clear();
		WPawnLegalPathY.clear();
		WQueenLegalPathX.clear();
		WQueenLegalPathY.clear();
		WRookLegalPathX.clear();
		WRookLegalPathY.clear();
		WBishopLegalPathX.clear();
		WBishopLegalPathY.clear();
		WKingLegalPathX.clear();
		WKingPossiblePathY.clear();
		WHorseLegalPathX.clear();
		WHorseLegalPathY.clear();
	}
	
	
	private void resetPosition()
	{
		if(!BBishopDestroyed){
		BBishopStartX=BBishopCoordinateX;
		BBishopStartY=BBishopCoordinateY;
		}
		if(!BRookDestroyed){
		BRookStartX=BRookCoordinateX;
		BRookStartY=BRookCoordinateY;
		}
		if(!BQueenDestroyed){
		BQueenStartX=BQueenCoordinateX;
		BQueenStartY=BQueenCoordinateY;
		}
		if(!BHorseDestroyed){
			BHorseStartX=BHorseCoordinateX;
			BHorseStartY=BHorseCoordinateY;
		}
		if(!BPawnDestroyed)
		{
			BPawnStartX=BPawnCoordinateX;
			BPawnStartY=BPawnCoordinateY;
		}
		BKingStartX=BKingCoordinateX;
		BKingStartY=BKingCoordinateY;
		WKingStartX=WKingCoordinateX;
		WKingStartY=WKingCoordinateY;
		if(!WBishopDestroyed)
		{
			WBishopStartX=WBishopCoordinateX;
			WBishopStartY=WBishopCoordinateY;
		}
		if(!WRookDestroyed)
		{
		WRookStartX=WRookCoordinateX;
		WRookStartY=WRookCoordinateY;
		}
		if(!WQueenDestroyed)
		{		
			WQueenStartX=WQueenCoordinateX;
			WQueenStartY=WQueenCoordinateY;
		}
		if(!WHorseDestroyed)
		{
			WHorseStartX=WHorseCoordinateX;
			WHorseStartY=WHorseCoordinateY;
		}
		if(!WPawnDestroyed)
		{
			WPawnStartX=WPawnCoordinateX;
		WPawnStartY=WPawnCoordinateY;
		}
	}
	
	private void setAllPath()
	{
		
		if(!BBishopDestroyed)
                    setDarkBishopPath();
		if(!BHorseDestroyed)
                    setDarkHorsePath();
		if(!BPawnDestroyed)
                    setDarkPawnPath();
		if(!BQueenDestroyed)
                    setDarkQueenPath();
		if(!BRookDestroyed)
                    setDarkRookPath();
                if(!WBishopDestroyed)
                    setLightBishopPath();
		if(!WHorseDestroyed)
                    setLightHorsePath();
		if(!WPawnDestroyed)
                    setLightPawnPath();
		if(!WQueenDestroyed)
                    setLightQueenPath();
		if(!WRookDestroyed)
                    setLightRookPath();
                    
                setDarkKingPath();
                setLightKingPath();
	}
	
	
	private void setCheckedPath()
	{
		boolean searchFound=false;
		for(int index = 0; index<=WQueenLegalPathX.size()-1 && index<=WQueenLegalPathY.size()-1 ; index++)
		{
			if(WQueenLegalPathX.get(index)==BKingCoordinateX && WQueenLegalPathY.get(index) == BKingCoordinateY)
			{
				BKingChecked=true;
				searchFound=true;
				break;
			}
		}
		for(int index = 0; index<=WRookLegalPathX.size()-1 && index<=WRookLegalPathY.size()-1 ; index++)
		{
			if(searchFound)break;
			else if(WRookLegalPathX.get(index)==BKingCoordinateX && WRookLegalPathY.get(index) == BKingCoordinateY)
			{
				BKingChecked=true;
				searchFound=true;
				break;
			}
		}
		for(int index = 0; index<=WBishopLegalPathX.size()-1 && index<=WBishopLegalPathY.size()-1 ; index++)
		{
			if(searchFound)break;
			else if(WBishopLegalPathX.get(index)==BKingCoordinateX && WBishopLegalPathY.get(index) == BKingCoordinateY)
			{
				BKingChecked=true;
				searchFound=true;
				break;
			}
		}
		for(int index = 0; index<=WPawnLegalPathX.size()-1 && index<=WPawnLegalPathY.size()-1 ; index++)
		{
			if(searchFound)break;
			else if(WPawnLegalPathX.get(index)==BKingCoordinateX && WPawnLegalPathY.get(index) == BKingCoordinateY)
			{
				BKingChecked=true;
				searchFound=true;
				break;
			}
		}
		for(int index = 0; index<=WHorseLegalPathX.size()-1 && index<=WHorseLegalPathY.size()-1 ; index++)
		{
			if(searchFound)break;
			else if(WHorseLegalPathX.get(index)==BKingCoordinateX && WHorseLegalPathY.get(index) == BKingCoordinateY)
			{
				BKingChecked=true;
				searchFound=true;
				break;
			}
		}
		for(int index = 0; index<=WKingLegalPathX.size()-1 && index<=WKingPossiblePathY.size()-1 ; index++)
		{
			if(searchFound)break;
			else if(WKingLegalPathX.get(index)==BKingCoordinateX && WKingPossiblePathY.get(index) == BKingCoordinateY)
			{
				BKingChecked=true;
				searchFound=true;
				break;
			}
		}
		if(!searchFound)
		{
			BKingChecked=false;
		}
		
		
		
		////////////////////////////////////////
		searchFound=false;
		for(int index = 0; index<=BQueenLegalPathX.size()-1 && index<=BQueenLegalPathY.size()-1 ; index++)
		{
			if(BQueenLegalPathX.get(index)==WKingCoordinateX && BQueenLegalPathY.get(index) == WKingCoordinateY)
			{
				WKingChecked=true;
				searchFound=true;
				break;
			}
		}
		for(int index = 0; index<=BRookLegalPathX.size()-1 && index<=BRookLegalPathY.size()-1 ; index++)
		{
			if(searchFound)break;
			else if(BRookLegalPathX.get(index)==WKingCoordinateX && BRookLegalPathY.get(index) == WKingCoordinateY)
			{
				WKingChecked=true;
				searchFound=true;
				break;
			}
		}
		for(int index = 0; index<=BBishopLegalPathX.size()-1 && index<=BBishopLegalPathY.size()-1 ; index++)
		{
			if(searchFound)break;
			else if(BBishopLegalPathX.get(index)==WKingCoordinateX && BBishopLegalPathY.get(index) == WKingCoordinateY)
			{
				WKingChecked=true;
				searchFound=true;
				break;
			}
		}
		for(int index = 0; index<=BPawnLegalPathX.size()-1 && index<=BPawnLegalPathY.size()-1 ; index++)
		{
			if(searchFound)break;
			else if(BPawnLegalPathX.get(index)==WKingCoordinateX && BPawnLegalPathY.get(index) == WKingCoordinateY)
			{
				WKingChecked=true;
				searchFound=true;
				break;
			}
		}
		for(int index = 0; index<=BHorseLegalPathX.size()-1 && index<=BHorseLegalPathY.size()-1 ; index++)
		{
			if(searchFound)break;
			else if(BHorseLegalPathX.get(index)==WKingCoordinateX && BHorseLegalPathY.get(index) == WKingCoordinateY)
			{
				WKingChecked=true;
				searchFound=true;
				break;
			}
		}
		for(int index = 0; index<=BKingLegalPathX.size()-1 && index<=BKingLegalPathY.size()-1 ; index++)
		{
			if(searchFound)break;
			else if(BKingLegalPathX.get(index)==WKingCoordinateX && BKingLegalPathY.get(index) == WKingCoordinateY)
			{
				WKingChecked=true;
				searchFound=true;
				break;
			}
		}
		if(!searchFound)
		{
			WKingChecked=false;
		}
	}
	
	
	private boolean isDarkKingCapturedPath(int x, int  y)
	{
		for(int index = 0; index<=WQueenLegalPathX.size()-1 && index<=WQueenLegalPathY.size()-1 ; index++)
		{
			if(WQueenLegalPathX.get(index)==x && WQueenLegalPathY.get(index) == y)
			{
				return true;
			}
		}
		for(int index = 0; index<=WRookLegalPathX.size()-1 && index<=WRookLegalPathY.size()-1 ; index++)
		{
			if(WRookLegalPathX.get(index)==x && WRookLegalPathY.get(index) == y)
			{
				return true;
			}
		}
		for(int index = 0; index<=WBishopLegalPathX.size()-1 && index<=WBishopLegalPathY.size()-1 ; index++)
		{
			if(WBishopLegalPathX.get(index)==x && WBishopLegalPathY.get(index) == y)
			{
				return true;
			}
		}
		for(int index = 0; index<=WPawnLegalPathX.size()-1 && index<=WPawnLegalPathY.size()-1 ; index++)
		{
			if(WPawnLegalPathX.get(index)==x && WPawnLegalPathY.get(index) == y)
			{
				return true;
			}
		}
		for(int index = 0; index<=WHorseLegalPathX.size()-1 && index<=WHorseLegalPathY.size()-1 ; index++)
		{
			if(WHorseLegalPathX.get(index)==x && WHorseLegalPathY.get(index) == y)
			{
				return true;
			}
		}
		for(int index = 0; index<=WKingLegalPathX.size()-1 && index<=WKingPossiblePathY.size()-1 ; index++)
		{
			if(WKingLegalPathX.get(index)==x && WKingPossiblePathY.get(index) == y)
			{
				return true;
			}
		}
		return false;
	}
		
		
	private boolean isLightKingCapturedPath(int x, int  y)
	{
		for(int index = 0; index<=BQueenLegalPathX.size()-1 && index<=BQueenLegalPathY.size()-1 ; index++)
		{
			if(BQueenLegalPathX.get(index)==x && BQueenLegalPathY.get(index) == y)
			{
				return true;
			}
		}
		for(int index = 0; index<=BRookLegalPathX.size()-1 && index<=BRookLegalPathY.size()-1 ; index++)
		{
			if(BRookLegalPathX.get(index)==x && BRookLegalPathY.get(index) == y)
			{
				return true;
			}
		}
		for(int index = 0; index<=BBishopLegalPathX.size()-1 && index<=BBishopLegalPathY.size()-1 ; index++)
		{
			if(BBishopLegalPathX.get(index)==x && BBishopLegalPathY.get(index) == y)
			{
				return true;
			}
		}
		for(int index = 0; index<=BPawnLegalPathX.size()-1 && index<=BPawnLegalPathY.size()-1 ; index++)
		{
			if(BPawnLegalPathX.get(index)==x && BPawnLegalPathY.get(index) == y)
			{
				return true;
			}
		}
		for(int index = 0; index<=BHorseLegalPathX.size()-1 && index<=BHorseLegalPathY.size()-1 ; index++)
		{
			if(BHorseLegalPathX.get(index)==x && BHorseLegalPathY.get(index) == y)
			{
				return true;
			}
		}
		for(int index = 0; index<=BKingLegalPathX.size()-1 && index<=BKingLegalPathY.size()-1 ; index++)
		{
			if(BKingLegalPathX.get(index)==x && BKingLegalPathY.get(index) == y)
			{
				return true;
			}
		}
		return false;
	}
	


	private  int getPixelPositionX(int X)
	{
		return (X*fixedDimension + positionPushX);
	}
	
	private  int getPixelPositionY(int Y)
	{
		return (Y*fixedDimension + positionPushY);
	}
	
	private void setupChessBoard(Graphics2D gg)
	{
		gg.setStroke(new BasicStroke(5 , BasicStroke.CAP_SQUARE , BasicStroke.JOIN_MITER));
		
			for(int y=0 ; y <=10 ; y++)
			{
				for( int x =0 ; x <=10 ; x++)
				{
					if(((x+y)%2 == 0)  &&  !(x+y <= 2 || 
					(x==0&&y==8) || (x==0&&y==9) || (x==0&&y==10) || (x==1&&y==9) || (x==1&&y==10) || (x==2&&y==10) ||
					(x==8&&y==0) || (x==9&&y==0) || (x==9&&y==1) || (x==10&&y==0) || (x==10&&y==1) || (x==10&&y==2) ||
					x+y >= 18))
					{
						gg.setColor(myWhite);
						gg.fill(square[y][x]);
						gg.setColor(new Color ( 0.3f , 0.3f , 0.3f , alpha ));
						gg.draw(square[y][x]);
					}
					else if(((x+y)%2 == 1)  &&  !(x+y <= 2 || 
					(x==0&&y==8) || (x==0&&y==9) || (x==0&&y==10) || (x==1&&y==9) || (x==1&&y==10) || (x==2&&y==10) ||
					(x==8&&y==0) || (x==9&&y==0) || (x==9&&y==1) || (x==10&&y==0) || (x==10&&y==1) || (x==10&&y==2) ||
					x+y >= 18))
					{
						gg.setColor(myBlack);
						gg.fill(square[y][x]);
						gg.setColor(new Color ( 0.3f , 0.3f , 0.3f , alpha ));
						gg.draw(square[y][x]);
					}
					
				}
			}
	}
	
	
	
	
	
	
	// PAINT PAINT PAINT PAINT PAINT PAINT PAINT PAINT PAINT PAINT PAINT
	
	private class MyDrawer extends JPanel
	{
		public void paint(Graphics g)
		{
			Graphics2D gg = (Graphics2D) g;
			super.paint(gg);
			
			myBlack = new Color( 0.5f , .5f , .5f , alpha );
			myWhite = new Color( 1f , 1f , 1f , alpha);
			mouseOverColor = new Color( .75f , .75f , .75f , alpha*0.5f );
			noColor = new Color( 1f, 1f , 1f , 0f);
			customRed = new Color(1f , 0f , 0f , alpha*0.5f);
			scale[3] = alpha;
			ropARGB = new RescaleOp(scale , offset , null);
			setBackground(Color.BLACK);

			setupChessBoard(gg);
			
			if(mouseOver && !mouseIsPressed)
			{
				gg.setColor(mouseOverColor);
				gg.fill(square[mouseEnterSquareY][mouseEnterSquareX]);
			}
			
			
			gg.drawImage(BlackKing , ropARGB , getPixelPositionX(BKingCoordinateX) , getPixelPositionY(BKingCoordinateY));
			if(!BHorseDestroyed)
				gg.drawImage(BlackHorse , ropARGB , getPixelPositionX(BHorseCoordinateX) , getPixelPositionY(BHorseCoordinateY));
			if(!BQueenDestroyed)
				gg.drawImage(BlackQueen , ropARGB , getPixelPositionX(BQueenCoordinateX) , getPixelPositionY(BQueenCoordinateY));
			if(!BBishopDestroyed)
				gg.drawImage(BlackBishop , ropARGB , getPixelPositionX(BBishopCoordinateX) , getPixelPositionY(BBishopCoordinateY));
			if(!BRookDestroyed)
				gg.drawImage(BlackRook , ropARGB , getPixelPositionX(BRookCoordinateX) , getPixelPositionY(BRookCoordinateY));
			if(!BPawnDestroyed)
				gg.drawImage(BlackPawn , ropARGB , getPixelPositionX(BPawnCoordinateX) , getPixelPositionY(BPawnCoordinateY));
			if(!WPawnDestroyed)
				gg.drawImage(WhitePawn , ropARGB , getPixelPositionX(WPawnCoordinateX) , getPixelPositionY(WPawnCoordinateY));
			if(!WQueenDestroyed)
				gg.drawImage(WhiteQueen , ropARGB , getPixelPositionX(WQueenCoordinateX) , getPixelPositionY(WQueenCoordinateY));
			if(!WRookDestroyed)
				gg.drawImage(WhiteRook , ropARGB , getPixelPositionX(WRookCoordinateX) , getPixelPositionY(WRookCoordinateY));
			if(!WBishopDestroyed)
				gg.drawImage(WhiteBishop , ropARGB , getPixelPositionX(WBishopCoordinateX) , getPixelPositionY(WBishopCoordinateY));
			gg.drawImage(WhiteKing , ropARGB , getPixelPositionX(WKingCoordinateX) , getPixelPositionY(WKingCoordinateY));
			if(!WHorseDestroyed)
				gg.drawImage(WhiteHorse , ropARGB , getPixelPositionX(WHorseCoordinateX) , getPixelPositionY(WHorseCoordinateY));
			
			drawPossiblePathOfSelectedPiece(gg);
			drawSelectedPieceBackground(gg);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void drawPossiblePathOfSelectedPiece(Graphics2D gg)
	{
		int index = 0;
		gg.setColor(new Color(0f, 1f, 0f, alpha*0.5f));
		if(BKingChosen)
		{
			for(index = 0 ; (index<=BKingLegalPathX.size()-1 && index<=BKingLegalPathY.size()-1) ; index++)
			{
				gg.fill(square[BKingLegalPathY.get(index)][BKingLegalPathX.get(index)]);
			}
		}
		else if(BHorseChosen)
		{
			for(index = 0 ; (index<=BHorseLegalPathX.size()-1 && index<=BHorseLegalPathY.size()-1) ; index++)
			{
				gg.fill(square[BHorseLegalPathY.get(index)][BHorseLegalPathX.get(index)]);
			}
		}
		else if(BQueenChosen)
		{
			for(index = 0 ; (index<=BQueenLegalPathX.size()-1 && index<=BQueenLegalPathY.size()-1) ; index++)
			{
				gg.fill(square[BQueenLegalPathY.get(index)][BQueenLegalPathX.get(index)]);
			}
		}
		else if(BBishopChosen)
		{
			for(index = 0 ; (index<=BBishopLegalPathX.size()-1 && index<=BBishopLegalPathY.size()-1) ; index++)
			{
				gg.fill(square[BBishopLegalPathY.get(index)][BBishopLegalPathX.get(index)]);
			}
		}
		else if(BRookChosen)
		{
			for(index = 0 ; (index<=BRookLegalPathX.size()-1 && index<=BRookLegalPathY.size()-1) ; index++)
			{
				gg.fill(square[BRookLegalPathY.get(index)][BRookLegalPathX.get(index)]);
			}
		}
		else if(BPawnChosen)
		{
			for(index = 0 ; (index<=BPawnLegalPathX.size()-1 && index<=BPawnLegalPathY.size()-1) ; index++)
			{
				gg.fill(square[BPawnLegalPathY.get(index)][BPawnLegalPathX.get(index)]);
			}
		}
		else if(WPawnChosen)
		{
			for(index = 0 ; (index<=WPawnLegalPathX.size()-1 && index<=WPawnLegalPathY.size()-1) ; index++)
			{
				gg.fill(square[WPawnLegalPathY.get(index)][WPawnLegalPathX.get(index)]);
			}
		}
		else if(WQueenChosen)
		{
			for(index = 0 ; (index<=WQueenLegalPathX.size()-1 && index<=WQueenLegalPathY.size()-1) ; index++)
			{
				gg.fill(square[WQueenLegalPathY.get(index)][WQueenLegalPathX.get(index)]);
			}
		}
		else if(WRookChosen)
		{
			for(index = 0 ; (index<=WRookLegalPathX.size()-1 && index<=WRookLegalPathY.size()-1) ; index++)
			{
				gg.fill(square[WRookLegalPathY.get(index)][WRookLegalPathX.get(index)]);
			}
		}
		else if(WBishopChosen)
			for(index = 0; (index<=WBishopLegalPathX.size()-1 && index<=WBishopLegalPathY.size()-1) ; index++)
				gg.fill(square[WBishopLegalPathY.get(index)] [WBishopLegalPathX.get(index)] );
		else if(WKingChosen)
			for(index = 0; (index<=WKingLegalPathX.size()-1 && index<=WKingPossiblePathY.size()-1) ; index++)
				gg.fill(square[WKingPossiblePathY.get(index)] [WKingLegalPathX.get(index)] );
		else if(WHorseChosen)
			for(index = 0; (index<=WHorseLegalPathX.size()-1 && index<=WHorseLegalPathY.size()-1) ; index++)
				gg.fill(square[WHorseLegalPathY.get(index)] [WHorseLegalPathX.get(index)] );
		else;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void drawSelectedPieceBackground(Graphics2D gg)
	{
		if(BKingChosen)
		{
			if(BKingLegalMove)
			{
				gg.setColor(noColor);
			}
			else
			{
				gg.setColor(customRed);
			}
			gg.fill(square[BKingCoordinateY][BKingCoordinateX]);
			gg.drawImage(BlackKing , ropARGB , getPixelPositionX(BKingCoordinateX) , getPixelPositionY(BKingCoordinateY));
		}
		else if(BHorseChosen)
		{
			if(BHorseLegalMove)
			{
				gg.setColor(noColor);
			}
			else
			{
				gg.setColor(customRed);
			}
			gg.fill(square[BHorseCoordinateY][BHorseCoordinateX]);
			gg.drawImage(BlackHorse , ropARGB , getPixelPositionX(BHorseCoordinateX) , getPixelPositionY(BHorseCoordinateY));
		}
		else if(BQueenChosen)
		{
			if(BQueenLegalMove)
			{
				gg.setColor(noColor);
			}
			else
			{
				gg.setColor(customRed);
			}
			gg.fill(square[BQueenCoordinateY][BQueenCoordinateX]);
			gg.drawImage(BlackQueen , ropARGB , getPixelPositionX(BQueenCoordinateX) , getPixelPositionY(BQueenCoordinateY));
		}
		else if(BBishopChosen)
		{
			if(BBishopLegalMove)
				gg.setColor(noColor);
			else
				gg.setColor(customRed);
			gg.fill(square[BBishopCoordinateY][BBishopCoordinateX]);
			gg.drawImage(BlackBishop , ropARGB , getPixelPositionX(BBishopCoordinateX) , getPixelPositionY(BBishopCoordinateY));
		}
		else if(BRookChosen)
		{
			if(BRookLegalMove)
				gg.setColor(noColor);
			else
				gg.setColor(customRed);
			gg.fill(square[BRookCoordinateY][BRookCoordinateX]);
			gg.drawImage(BlackRook , ropARGB , getPixelPositionX(BRookCoordinateX) , getPixelPositionY(BRookCoordinateY));
		}
		else if(BPawnChosen)
		{
			if(BPawnLegalMove)
				gg.setColor(noColor);
			else
				gg.setColor(customRed);
			gg.fill(square[BPawnCoordinateY][BPawnCoordinateX]);
			gg.drawImage(BlackPawn , ropARGB , getPixelPositionX(BPawnCoordinateX) , getPixelPositionY(BPawnCoordinateY));
		}
		else if(WPawnChosen)
		{
			if(WPawnLegalMove)
				gg.setColor(noColor);
			else
				gg.setColor(customRed);
			gg.fill(square[WPawnCoordinateY][WPawnCoordinateX]);
			gg.drawImage(WhitePawn , ropARGB , getPixelPositionX(WPawnCoordinateX) , getPixelPositionY(WPawnCoordinateY));
		}
		else if(WQueenChosen)
		{
			if(WQueenLegalMove)
				gg.setColor(noColor);
			else
				gg.setColor(customRed);
			gg.fill(square[WQueenCoordinateY][WQueenCoordinateX]);
			gg.drawImage(WhiteQueen , ropARGB , getPixelPositionX(WQueenCoordinateX) , getPixelPositionY(WQueenCoordinateY));
		}
		else if(WRookChosen)
		{
			if(WRookLegalMove)
				gg.setColor(noColor);
			else
				gg.setColor(customRed);
			gg.fill(square[WRookCoordinateY][WRookCoordinateX]);
			gg.drawImage(WhiteRook , ropARGB , getPixelPositionX(WRookCoordinateX) , getPixelPositionY(WRookCoordinateY));
		}
		else if(WBishopChosen)
		{
			if(WBishopLegalMove)
				gg.setColor(noColor);
			else
				gg.setColor(customRed);
			gg.fill(square[WBishopCoordinateY][WBishopCoordinateX]);
			gg.drawImage(WhiteBishop , ropARGB , getPixelPositionX(WBishopCoordinateX) , getPixelPositionY(WBishopCoordinateY));
		}
		else if(WKingChosen)
		{
			if(WKingLegalMove)
				gg.setColor(noColor);
			else
				gg.setColor(customRed);
			gg.fill(square[WKingCoordinateY][WKingCoordinateX]);
			gg.drawImage(WhiteKing , ropARGB , getPixelPositionX(WKingCoordinateX) , getPixelPositionY(WKingCoordinateY));
		}
		else if(WHorseChosen)
		{
			if(WHorseLegalMove)
				gg.setColor(noColor);
			else
				gg.setColor(customRed);
			gg.fill(square[WHorseCoordinateY][WHorseCoordinateX]);
			gg.drawImage(WhiteHorse , ropARGB , getPixelPositionX(WHorseCoordinateX) , getPixelPositionY(WHorseCoordinateY));
		}
	}
	
	
	
	
	
	
	
	

	
	
	private  int turn =0;
	private class MyListener implements ActionListener
	{
		
		@Override
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == restart)
				{
					try
                                        {
                                            PlayGame playFrame = new PlayGame();

                                            playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                            playFrame.setSize(1200, 700);
                                            playFrame.setTitle("JEG CHESS");
                                            playFrame.setResizable(false);
                                            playFrame.setLocationRelativeTo(null);
                                            playFrame.setVisible(true);
                                            
                                            dispose();
					}
					catch(Exception ex)
                                        {
					}
				}
				if(e.getSource() == leaderBoards)
				{
					LeaderBoards.main();
					
				}
                                
                                if(blackKingAttack)
                                {
                                    label.setIcon(BKingIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        KedavraSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        blackKingAttack = false;
                                    }
                                }
                                
                                if(blackQueenAttack)
                                {
                                    label.setIcon(BQueenIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        KedavraSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        blackQueenAttack = false;
                                    }
                                }
                                
                                if(blackBishopAttack)
                                {
                                    label.setIcon(BBishopIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        KedavraSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        blackBishopAttack = false;
                                    }
                                }
                                
                                if(blackHorseAttack)
                                {
                                    label.setIcon(BHorseIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        KedavraSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        blackHorseAttack = false;
                                    }
                                }
                                
                                if(blackRookAttack)
                                {
                                    label.setIcon(BRookIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        KedavraSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        blackRookAttack = false;
                                    }
                                }
				
                                if(blackPawnAttack)
                                {
                                    label.setIcon(BPawnIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        KedavraSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        blackPawnAttack = false;
                                    }
                                }
                                
                                if(whiteKingAttack)
                                {
                                    label.setIcon(WKingIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        PatronumSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        whiteKingAttack = false;
                                    }
                                }
                                
                                if(whiteQueenAttack)
                                {
                                    label.setIcon(WQueenIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        PatronumSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        whiteQueenAttack = false;
                                    }
                                }
                                
                                if(whiteBishopAttack)
                                {
                                    label.setIcon(WBishopIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        PatronumSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        whiteBishopAttack = false;
                                    }
                                }
                                
                                if(whiteHorseAttack)
                                {
                                    label.setIcon(WHorseIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        PatronumSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        whiteHorseAttack = false;
                                    }
                                }
                                
                                if(whiteRookAttack)
                                {
                                    label.setIcon(WRookIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        PatronumSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        whiteRookAttack = false;
                                    }
                                }
                                
                                if(whitePawnAttack)
                                {
                                    label.setIcon(WPawnIcon[pngCount]);
                                    
                                    pngCount--;
                                    
                                    if(pngCount == 0)
                                    {
                                        PatronumSound();
                                        
                                        animating = true;
                                        
                                        gifCount = 18;
                                        
                                        whitePawnAttack = false;
                                    }
                                }
                                
                                if(animating)
				{
					if(universalMatchRoundCount%2==0)
						label.setIcon(White[gifCount]);
					else
						label.setIcon(Black[gifCount]);
					gifCount--;
					if(gifCount == 0)
					{
						label.setIcon(background);
						alpha=1f;
						animating = false;
						animTimer.stop();
					}
					repaint();
					
				}
                                
			}
		
	}
	
	private class MouseMouse implements MouseListener, MouseMotionListener
	{
		public void mouseClicked(MouseEvent e){}
		public void mousePressed(MouseEvent e)
		{
			
			if(!animating)
			{
				clearAllPath();
				tm.stop();
				mouseIsPressed = true;
				getMousePressStartingPoint(e);
				resetPosition();
				setAllPath();
				setCheckedPath();
//				setPossiblePath();
				repaint();
			}
		}
		public void mouseReleased(MouseEvent e)
		{
			if(!animating)
			{
				mouseIsPressed = false;
				confirmSetPosition();
				getMouseMovingLocation(e);
				clearAllPath();
				resetPosition();
				setAllPath();
				setCheckedPath();
				if(BKingLegalPathX.size()==0&&BKingLegalPathY.size()==0&&BKingChecked)
				{
					BCHECKMATE=true;
					info.setText(" BLACK CHECKMATE!");
					
					try
					{
					String name = JOptionPane.showInputDialog("You won the Game !!! Please Enter your name?");
				
					FileWriter fileWriter = new FileWriter("winners.txt", true);
					fileWriter.write("\r\n");
					
					fileWriter.write(name);
					fileWriter.close();
					JOptionPane.showMessageDialog(null, "You have name have been successfuly recorded");
					
					
					
					
					//If a string was returned, say so.
					if ((name != null) && (name.length() > 0)) {
					    setLayout("The winner " + name + "!");
					    return;
					}

					//If you're here, the return value was null/empty.
					setLayout("Please enter you name");
				}
				catch(Exception ae) {
					ae.printStackTrace();
				}}
				else if(WKingLegalPathX.size()==0&&WKingPossiblePathY.size()==0)
				{
					WKingCHECKMATE=true;
					info.setText(" WHITE CHECKMATE!");
					try
					{
					String name = JOptionPane.showInputDialog("You won the Game !!! Please Enter your name?");
				
					FileWriter fileWriter = new FileWriter("winners.txt", true);
					fileWriter.write("\r\n");
					
					fileWriter.write(name);
					fileWriter.close();
					JOptionPane.showMessageDialog(null, "You have name have been successfuly recorded");
					
					
					
					
					//If a string was returned, say so.
					if ((name != null) && (name.length() > 0)) {
					    setLayout("The winner " + name + "!");
					    return;
					}

					//If you're here, the return value was null/empty.
					setLayout("Please enter your name");
				}
				catch(Exception ae) {
					ae.printStackTrace();
				}}
				else if(BKingChecked)
				info.setText(" BLACK KING CHECKED!");
				else if(WKingChecked)
					info.setText(" WHITE KING CHECKED!");
				else;
				repaint();
			}
		}
		private void setLayout(String string) {
			// TODO Auto-generated method stub
			
		}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		
		public void mouseMoved(MouseEvent e)
		{
			if(!animating)
			{
			if(BCHECKMATE)
			{
				info.setText(" BLACK CHECKMATE! ");
			}	
			else if(WKingCHECKMATE)
			{
				info.setText(" WHITE CHECKMATE! ");
			}
			else if(universalMatchRoundCount%2==0)
				info.setText(" Player 1's Turn (Black) :");
			else if(universalMatchRoundCount%2==1)
				info.setText(" Player 2's Turn (White) : ");
				else{}
			getMouseMovingLocation(e);
			repaint();
			}
		}
		public void mouseDragged(MouseEvent e)
		{
			if(!animating)
			{
			
			getMouseDraggingPoint(e);
			repaint();
			}
		}
	}
        
    private void validMoveSound()
    {
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(new File("valid.wav").getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
        }
    }
    
    private void invalidMoveSound()
    {
        Toolkit.getDefaultToolkit().beep();
    }
    
    private void AvadraSound()
    {
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(new File("Avadra.wav").getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
        }
    }
    
    private void KedavraSound()
    {
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(new File("Kedavra.wav").getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
        }
    }
    
    
    private void ExpectoSound()
    {
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(new File("Expecto.wav").getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
        }
    }
    
    
    private void PatronumSound()
    {
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(new File("Patronum.wav.wav").getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
        }
    }
}