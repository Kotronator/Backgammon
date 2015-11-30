/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import backgammon.AIPlayer;
import backgammon.BackgammonBoard;
import java.awt.Color;
import java.awt.Font;
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
    String diceFileName="d";
    ImageIcon boardIcon=null;
    ImageIcon redPiece = null;
    ImageIcon greenPiece = null;
    
    Image[] pieceImages= new Image[2];
    Image[] dices= new Image[6];
    
    BackgammonBoard boardToDraw;
    private AIPlayer.Roll roll;
    BoardCanvas(BackgammonBoard board, AIPlayer.Roll roll)
    {     
        boardToDraw=board;
        this.roll=roll;
        try {
            boardIcon = new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(boardImageFileName)));
            redPiece = new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(redPieceImageFileName)));
            greenPiece = new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(greenPieceImageFileName)));
            for (int i = 0; i <= 5; i++)
            {
                
                dices[i] = (new ImageIcon(ImageIO.read(backgammon.FileLoader.loadFile(diceFileName+(i+1)+".gif")))).getImage();
            }
        } catch (IOException ex) {
            Logger.getLogger(BoardCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        pieceImages[0]=redPiece.getImage();
        pieceImages[1]=greenPiece.getImage();
    }
    
    
    
    @Override
    public void paint(Graphics grphcs) {
        
        grphcs.drawImage(boardIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        
        //grphcs.setColor(Color.red);
        grphcs.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        //grphcs.drawString("TO SCORE DEN METRAEI", 200, 200);
        //System.out.println(this.getHeight());
        if (!boardToDraw.board[0].isEmpty()&& boardToDraw.board[0].getLast()==0)
        {
            int i=0,num=0;
            while (i-1>0&&boardToDraw.board[0].get(boardToDraw.board[0].size()-1-i)==0)
            {                
                num+=1;
                i++;
            }
            grphcs.drawImage(redPiece.getImage(), 375, 175, 50, 50, this);
            grphcs.setColor(Color.BLACK);
            grphcs.drawString(num+"", 385, 207);
        }
        
        if (!boardToDraw.board[25].isEmpty()&& boardToDraw.board[25].getLast()==1)
        {
            int i=0,num=0;
            while (i-1>0&&boardToDraw.board[25].get(boardToDraw.board[25].size()-1-i)==1)
            {                
                num+=1;
                i++;
            }
            grphcs.drawImage(greenPiece.getImage(), 375, 180+200, 50, 50, this);
            grphcs.setColor(Color.WHITE);
            grphcs.drawString(num+"", 385, 207+205);
        }
        
        for (int i = 1; i < 25; i++)
        {
            
            if(boardToDraw.getBoard()[i].isEmpty())
            {
                //continue;
            }
            int x=40,y=30, direction=1, z=i;
            if(i<7)
            {
                x=40;
                y=30;
                
            }
            else if(i<13)
            {
                x=107;
                y=30;
            }
            
            else if(i<19)
            {
                x=107+55*11;
                y=520;
                direction=-1;
                z=i-12;
            }
            else
            {
                 x=37+55*11;
                y=520;
                direction=-1;
                z=i-12;
            }
            grphcs.setColor(Color.BLACK);
            if(i<=12)
                grphcs.drawString(i+"", x+(z-1)*55*direction+15,  25);
            else
                grphcs.drawString(i+"", x+(z-1)*55*direction+10,  y+75);
            int numOfPiecesInColum=boardToDraw.getBoard()[i].size();
                int j=0;
                for (Iterator<Integer> it = boardToDraw.getBoard()[i].iterator(); it.hasNext();)
                {
                    
                    Integer pieceId = it.next();
                    grphcs.drawImage(pieceImages[pieceId], x+(z-1)*55*direction, y+j*50*direction, 50, 50, this);
                    j++;
                }
                
            
            
        }
        //System.out.println(roll.dice0);
       grphcs.drawImage(dices[roll.dice0-1], 180, 270, 40, 40, this);
       grphcs.drawImage(dices[roll.dice1-1], 180+400, 270, 40, 40, this);
        
        
    }
    
    
}
