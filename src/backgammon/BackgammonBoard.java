/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon;

import java.util.LinkedList;

/**
 *
 * @author tsipiripo
 */
public class BackgammonBoard 
{
    LinkedList<Integer>[] board = new LinkedList[26];
    public static int PORTES=0, PLAKOTO=1, ASODIO=3;
    private int type;
    private Player currentPlayer;
    
    
    BackgammonBoard()
    {
        type=PORTES;
        for (int i = 0; i < board.length; i++) {
           board[i]= new LinkedList<Integer>();
            
        }
    }
    
    public void initialiseBoard()
    {
        if(type==PORTES)
        {
            //TODO sthsimo
        }
    }
    
    public boolean doMove(int position,int dice)
    {
        if(isValidMove(position,dice))
        {
            //TODO kane move
            return true;
        }
        return false;
    }

    private boolean isValidMove(int position, int dice)
    {
        if()
            
        if  (
                (!board[position].isEmpty())&&
                board[position].getLast()==currentPlayer.getId()&&
                board[position+dice]
            )
        {
            
           
        }
    }
    
    private boolean isPlayerInBearOffPhase()
    {
        int startPosition=1, finishPosition=18;
        //int way=1;
        if(currentPlayer.getId()==1)
        {
            startPosition=7;
            finishPosition=24;
        }
        if(hasPlayerCapturedPiece())
        {
            return false;
        }
        for (int i = startPosition; i < board.length; i++)
        {
            LinkedList<Integer> board1 = board[i];
            
        }
   
    }

    private boolean hasPlayerCapturedPiece()
    {
//        if (currentPlayer.getId()==0)
//        {
//            
//        }
        return !board[currentPlayer.getId()*25].isEmpty();
    }
    
    
    
}
