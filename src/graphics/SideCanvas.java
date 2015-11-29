/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import backgammon.BackgammonBoard;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author tsipiripo
 */
public class SideCanvas extends java.awt.Canvas
{
    String sideBoardImageFileName = "side.gif";
    String redPieceImageFileName = "red_Side.gif";
    String greenPieceImageFileName = "green_Side.gif";
    
    ImageIcon sideBoardImageIcon=null;
    ImageIcon redPiece = null;
    ImageIcon greenPiece = null;

    Image[] pieceImages= new Image[2];
    
    BackgammonBoard board;
    
    public SideCanvas(BackgammonBoard board)
    {
        this.board=board;
        try {
            sideBoardImageIcon = new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(sideBoardImageFileName)));
            redPiece = new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(redPieceImageFileName)));
            greenPiece = new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(greenPieceImageFileName)));
        } catch (IOException ex) {
            Logger.getLogger(BoardCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pieceImages[0]=redPiece.getImage();
        pieceImages[1]=greenPiece.getImage();
        
        
        
    }

    @Override
    public void paint(Graphics g)
    {
        
        //super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(sideBoardImageIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        if(!board.board[0].isEmpty())
        {
            int i=0;
            while (i<board.board[0].size()&&board.board[0].get(i)==1)
            {                
                g.drawImage(greenPiece.getImage(), 20, 30+i*15, 50, 15, this);
                i++;
            }
        }
        
        if(!board.board[25].isEmpty())
        {
            int i=0;
            while (i<board.board[25].size()&&board.board[25].get(i)==0)
            {                
                g.drawImage(redPiece.getImage(), 20, 600-30-i*15, 50, 15, this);
                i++;
            }
        }
    }
    
    
    
    
}
