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
            int targetPosition=calculateMove(position, dice);

                if (targetPosition==currentPlayer.getNextId()*25)//otan mazevw ta dika mou poulia
                {
                    board[targetPosition].addFirst(currentPlayer.getId());
                    board[position].removeLast();
                }else
                {
                    if (type==PORTES)
                    {
                        if (board[targetPosition].getLast()==currentPlayer.getNextId())//trwme pouli
                        {
                            int piece=board[targetPosition].removeLast();
                            board[currentPlayer.getNextId()*25].addLast(piece);
                        }
                    }
                    board[targetPosition].addLast(currentPlayer.getId());
                    board[position].removeLast();
                    
                    
                }
           
            return true;
        }
        return false;
    }

    private boolean isValidMove(int position, int dice)
    {
        if(isPlayerInBearOffPhase())
        {
            if(dice==getBearOffDistanceOfPosition(position))//(currentPlayer.getId()==1&&dice==position)||(currentPlayer.getId()==0 && dice==6-(position % 19)))
                return true;
            else if (dice>getBearOffDistanceOfPosition(position))
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
                return true;
            }
            else//dice<position
            {
                return !hasEnemyDoor(calculateMove(position, dice));
            }
            
        }else// den eimai se fash mazevw --not bear phase
        {
            if((position<=6&&currentPlayer.getId()==1&&dice==position)||(position>=19&&currentPlayer.getId()==0 && dice==6-(position % 19)))//ama paw na pazepsw den epitrpetai
                return false;//kinhsh mazematos
            if(hasPlayerCapturedPiece())//exw piasmeno
            {
                if(position==25*currentPlayer.getId())// ama eimai 0 pazw apo 0
                {
                    return !hasEnemyDoor(calculateMove(position, dice));//TODO
                }else
                    return false;
            }else
            return !hasEnemyDoor(calculateMove(position, dice));
        }
             
    }
    
    private int getBearOffDistanceOfPosition(int position)
    {
        if(!isPlayerInBearOffPhase())
            throw new UnsupportedOperationException("Klish enw den mazevw");
        if (currentPlayer.getId()==1)
        {
            return position;
        }
        else
        {
            return 6-(position % 19);
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
        if(currentPlayer.getId()==0)
        {
            position+=dice;
            if(position>25)
                position=25;
            
        }else
        {
            position-=dice;
            if(position<0)
                position=0;
        }
        return position;//position+(dice*(currentPlayer.getId()*-2+1));
    }

   
    
    
    
}
