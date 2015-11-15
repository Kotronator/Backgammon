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
        if(isPlayerInBearOffPhase())
        {
            if(dice==position)
                return true;
            else if (dice>position)
            {
                int startPosition=position+1, finishPosition=6;
                if(currentPlayer.getId()==1)
                {
                    startPosition=19;
                    finishPosition=position-1;
                }
                for (int i =startPosition; i <= finishPosition ; i++)
                {
                    if(!board[i].isEmpty()){
                       continue; 
                    } 
                    else if(hasPiece(i))
                    {
                        return false;
                    }
                }      
            }
            else//dice<position
            {
                hasEnemyDoor
            }
            
        }    
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
        if(hasPlayerCapturedPiece())
        {
            return false;
        }
        
        if(currentPlayer.getId()==1)
        {
            startPosition=7;
            finishPosition=24;
        }
        
        for (int i = startPosition; i < finishPosition; i++)
        {
            if(board[i].isEmpty())
                continue;
            else if(hasPiece(i))
                return false;
            
        }
        return true;
    }

    private boolean hasPlayerCapturedPiece()
    { 
       
        if (board[currentPlayer.getId()*25].isEmpty())
        {
           return false;
        }
        else if(board[currentPlayer.getId()*25].getLast()==currentPlayer.getId())
        {
           return true;
        }
        return false;
 
               
    }
    private boolean hasPiece(int position)
    {
        
        return board[position].getFirst()==currentPlayer.getId()||board[position].getLast()==currentPlayer.getId();
    }
    
    private boolean hasEnemyDoor(int position)
    {
        if (position==0 || position==25)
        {
            return false;
        }
        else
        {
          return board[position].getLast()==currentPlayer.getNextId()&&board[position].size()>=2;
        }
    }
    
    private int calculateMove(int position, int dice)
    {
        return position
    }
    
    
    
}
