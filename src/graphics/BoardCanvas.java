/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Iterator;
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
    String greenPieceImageFileName = "green_piece.gif";
    
    ImageIcon boardIcon=null;
    ImageIcon redPiece = null;
    ImageIcon greenPiece = null;
    
    Image[] pieceImages= new Image[2];
    
    
    BoardCanvas()
    {     
        try {
            boardIcon = new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(boardImageFileName)));
            redPiece = new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(redPieceImageFileName)));
            greenPiece = new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(greenPieceImageFileName)));
        } catch (IOException ex) {
            Logger.getLogger(BoardCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        pieceImages[0]=redPiece.getImage();
        pieceImages[1]=greenPiece.getImage();
    }
    
    
    
    @Override
    public void paint(Graphics grphcs) {
        
        grphcs.drawImage(boardIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        
        for (int i = 1; i < 25; i++)
        {
            
            if(backgammon.BackgammonBoard.board[i].isEmpty())
            {
                continue;
            }
            
            if(i<7)
            {
                int numOfPiecesInColum=backgammon.BackgammonBoard.board[i].size();
                int j=0;
                for (Iterator<Integer> it = backgammon.BackgammonBoard.board[i].iterator(); it.hasNext();)
                {
                    
                    Integer pieceId = it.next();
                    grphcs.drawImage(pieceImages[pieceId], 40+(i-1)*55, 30+j*50, 50, 50, this);
                    j++;
                }
//for (int j = 0; j < numOfPiecesInColum; j++) {
                    
                
                
            }
                
            
            
        }
        
        //grphcs.drawImage(a.getImage(), 0, 0, this);
        
//        grphcs.drawImage(redPiece.getImage(), 40, 30, 50, 50, this);
//        for (int i = 1; i <= 4; i++) {
//            grphcs.drawImage(greenPiece.getImage(), 40, 30+i*50, 50, 50, this);
//        }
//        for (int i = 1; i <= 3; i++) {
//        grphcs.drawImage(greenPiece.getImage(), 40, 30+25+i*50, 50, 50, this);
//        }
//        for (int i = 0; i < 10; i++) {
//            
//            grphcs.drawImage(greenPiece.getImage(), 40+55*i, 30, 50, 50, this);
//        }
        
        //grphcs.drawRect(5, 5, 5, 5);
        //super.paint(grphcs); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
