/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author TOSHIBA
 */
public class BoardCanvas extends java.awt.Canvas
{
    String boardImageFileName ="board.gif";
    String redPieceImageFileName = "red_piece.gif";
    
    ImageIcon boardIcon=null;
    ImageIcon redPiece = null;
    
    BoardCanvas()
    {     
        try {
            boardIcon = new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(boardImageFileName)));
            redPiece = new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(redPieceImageFileName)));
        } catch (IOException ex) {
            Logger.getLogger(BoardCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public void paint(Graphics grphcs) {
        
        
        //grphcs.drawImage(a.getImage(), 0, 0, this);
        grphcs.drawImage(boardIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        grphcs.drawImage(redPiece.getImage(), 40, 30, 50, 50, this);
        //grphcs.drawRect(5, 5, 5, 5);
        //super.paint(grphcs); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
