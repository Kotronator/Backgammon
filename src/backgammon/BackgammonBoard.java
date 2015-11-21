/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon;

import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author tsipiripo
 */
public class BackgammonBoard 
{
    public LinkedList<Integer>[] board = new LinkedList[26];
    public static int PORTES=0, PLAKOTO=1, ASODIO=3;
    private int type;
    private Player currentPlayer;
    
    
    BackgammonBoard()
    {
        type=PORTES;
        for (int i = 0; i < board.length; i++) {
           board[i]= new LinkedList<Integer>();
            
        }
        currentPlayer=PlayerController.getPlayerWithId(0);
    }
    public LinkedList<Integer>[] getBoard()
    {
        return board;
    }
    
    public LinkedList<BackgammonChild> generateChildren(int dice0, int dice1)
    {
        LinkedList<BackgammonChild> childrenList= new LinkedList<>();
        
        boolean isRollDouble=false;
        if(dice1>dice0)
        {
            int temp=dice0;
            temp=dice1;
            dice1=dice0;
            dice0=temp;
        }
        else if(dice0==dice1)
        {
            isRollDouble=true;
        }
        int maxDiceDoublesPlayed=0;//needed for double roll
        boolean canDouble=false,canSingle=false,canBig=false;
        BackgammonChild backgammonChild;//= new BackgammonChild(this);
        if(!isRollDouble)
        {
            for (int i = 0; i < board.length; i++) {

                 backgammonChild= new BackgammonChild(this);

                if (backgammonChild.board.isValidMove(i, dice0)) 
                {// mporw n pai3w tn megalh zaria

                    backgammonChild.diceMove.dice[0]=dice0;
                    backgammonChild.diceMove.position[0]=i;
                    backgammonChild.board.doMove(i, dice0);
                    canSingle=true;
                    canBig=true;
                }
                else
                {
                    backgammonChild.diceMove.dice[0]=BackgammonChild.DiceMove.DICE_IMPOSSIBLE;
                    backgammonChild.diceMove.position[0]=i;
                    if(canBig)//an exdw megalh zaria dn xreiazetai n e3ereunhsw paidia p paizoun me tn mikrh
                    {
                        continue;
                    }

                }

                for (int j = 0; j < board.length; j++) 
                {
                       BackgammonChild finalChild= new BackgammonChild(backgammonChild);
                    if (finalChild.board.isValidMove(j, dice1)) 
                    {// mporw n pai3w tn mikrh zaria

                        finalChild.diceMove.dice[1]=dice1;
                        finalChild.diceMove.position[1]=j;
                        finalChild.board.doMove(j, dice1);
                        if(finalChild.diceMove.dice[0]>0)
                        {
                            canDouble=true;

                        }
                        canSingle=true;

                    }
                    else
                    {
                        finalChild.diceMove.dice[1]=BackgammonChild.DiceMove.DICE_IMPOSSIBLE;
                        finalChild.diceMove.position[1]=j;


                    }
                    if(finalChild.diceMove.dice[0]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE
                            &&finalChild.diceMove.dice[1]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE)
                    {
                        childrenList.add(finalChild);
                    }

                }

            }
        }else //double roll
        {

            for (int i = 0; i < board.length; i++)// 1h apo tis 4
            {

                backgammonChild= new BackgammonChild(this);
                //int flagMaxDicePlayed=0;
                if (backgammonChild.board.isValidMove(i, dice0)) 
                {// mporw n pai3w tn megalh zaria
                    //flagMaxDicePlayed++;
                    backgammonChild.diceMove.maxPlayed++;
                    backgammonChild.diceMove.dice[0]=dice0;
                    backgammonChild.diceMove.position[0]=i;
                    backgammonChild.board.doMove(i, dice0);
                    if(maxDiceDoublesPlayed<2)
                        maxDiceDoublesPlayed=1;
                }
                else
                {
                    backgammonChild.diceMove.dice[0]=BackgammonChild.DiceMove.DICE_IMPOSSIBLE;
                    backgammonChild.diceMove.position[0]=i;
                    if(maxDiceDoublesPlayed==4)//an exdw megalh zaria dn xreiazetai n e3ereunhsw paidia p paizoun me tn mikrh
                    {
                        continue;
                    }

                }
                
                for (int j = 0; j < board.length; j++)/////////----------------------------2h apo 4
                {
                    //LinkedList<Integer> board1 = board[j];
                    BackgammonChild secondChild= new BackgammonChild(backgammonChild);
                    if (secondChild.board.isValidMove(j, dice0))
                    {
                        secondChild.diceMove.maxPlayed++;
                        secondChild.diceMove.dice[1]=dice0;
                        secondChild.diceMove.position[1]=j;
                        secondChild.board.doMove(j, dice0);
                        if(maxDiceDoublesPlayed<3&& secondChild.diceMove.dice[0]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE)
                            maxDiceDoublesPlayed=2;
                    }
                     else
                    {
                        secondChild.diceMove.dice[1]=BackgammonChild.DiceMove.DICE_IMPOSSIBLE;
                        secondChild.diceMove.position[1]=j;
                        if(maxDiceDoublesPlayed> secondChild.diceMove.maxPlayed)//(maxDiceDoublesPlayed==4||(maxDiceDoublesPlayed==3&&secondChild.diceMove.dice[0]==BackgammonChild.DiceMove.DICE_IMPOSSIBLE))//an exdw megalh zaria dn xreiazetai n e3ereunhsw paidia p paizoun me tn mikrh
                        {
                            continue;
                        }
                        

                    }
                    //---------3o for
                    for (int k = 0; k < board.length; k++)/////////----------------------------
                    {
                        BackgammonChild thirdChild= new BackgammonChild(secondChild);
                        //LinkedList<Integer> board1 = board[j];
                        if (thirdChild.board.isValidMove(k, dice0))
                        {
                            thirdChild.diceMove.maxPlayed++;
                            thirdChild.diceMove.dice[2]=dice0;
                            thirdChild.diceMove.position[2]=k;
                            thirdChild.board.doMove(k, dice0);
                            if(maxDiceDoublesPlayed<4 && thirdChild.diceMove.dice[0]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE && thirdChild.diceMove.dice[1]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE)
                                maxDiceDoublesPlayed=3;
                        }
                         else
                        {
                            thirdChild.diceMove.dice[2]=BackgammonChild.DiceMove.DICE_IMPOSSIBLE;
                            thirdChild.diceMove.position[2]=k;
                            if(//maxDiceDoublesPlayed==4
                                    maxDiceDoublesPlayed> thirdChild.diceMove.maxPlayed)
                                    //||(maxDiceDoublesPlayed==3&&thirdChild.diceMove.dice[0]==BackgammonChild.DiceMove.DICE_IMPOSSIBLE))//an exdw megalh zaria dn xreiazetai n e3ereunhsw paidia p paizoun me tn mikrh
                            {
                                continue;
                            }


                        }
                        // --- 4o for
                        for (int w = 0; w < board.length; w++)/////////----------------------------
                        {
                            //LinkedList<Integer> board1 = board[j];
                            BackgammonChild finalChild= new BackgammonChild(thirdChild);
                            if (finalChild.board.isValidMove(w, dice0))
                            {
                                finalChild.diceMove.maxPlayed++;
                                finalChild.diceMove.dice[3]=dice0;
                                finalChild.diceMove.position[3]=w;
                                finalChild.board.doMove(w, dice0);
                                if(finalChild.diceMove.maxPlayed==4)//(maxDiceDoublesPlayed<4 && finalChild.diceMove.dice[0]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE && finalChild.diceMove.dice[1]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE)
                                    maxDiceDoublesPlayed=4;
                            }
                             else
                            {
                                finalChild.diceMove.dice[3]=BackgammonChild.DiceMove.DICE_IMPOSSIBLE;
                                finalChild.diceMove.position[3]=w;
                                if(//maxDiceDoublesPlayed==4
                                        maxDiceDoublesPlayed> finalChild.diceMove.maxPlayed)
                                        //||(maxDiceDoublesPlayed==3&&finalChild.diceMove.dice[0]==BackgammonChild.DiceMove.DICE_IMPOSSIBLE))//an exdw megalh zaria dn xreiazetai n e3ereunhsw paidia p paizoun me tn mikrh
                                {
                                    continue;
                                }
                                

                            }
                            
                            //vale paidi
                            childrenList.add(finalChild);
                        }
                        
                        // --- 4o for end
                    }
                    //---------3o for end

                }
                
            }

        }
        
        return childrenList; //TODO
    }

    @Override
    public boolean equals(Object obj)
    {
        throw new UnsupportedOperationException("Not Implemented");
    }
    
    
    
    public BackgammonBoard getCopy()
    {
        BackgammonBoard board = new BackgammonBoard();
        
        for (int i = 0; i < board.getBoard().length; i++) {
           board.getBoard()[i]=new LinkedList<Integer>(this.board[i]);
           board.type=type;
           board.currentPlayer=PlayerController.getPlayerWithId(this.currentPlayer.getId());
            
        }
        
        return board;
    }
    
    public void initialiseBoard()
    {
        if(type==PORTES)
        {
            board[1].add(0);
            board[2].add(0);
//            board[2].add(1);
//            board[2].add(1);
//            for (int i = 7; i <13; i++) {
//                board[i].add(1);
//                
//            }
//            
//            for (int i = 13; i <25; i++) {
//                board[i].add(i%2);
//                
//            }
        }//TODO
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
                        //debug
                        System.out.println("Parth porta kai fige");
                        if ((!board[targetPosition].isEmpty())&&board[targetPosition].getLast()==currentPlayer.getNextId())//trwme pouli
                        {
                            System.out.println("to faga");
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
        if(!hasPiece(position))
        {
            return false;
        }
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
       //debug
//        if(currentPlayer.getId()*25==1)
//        {
//            System.out.println(currentPlayer.name+currentPlayer.getId());
//        }
        //debug
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
        
        return (!board[position].isEmpty())&&(board[position].getFirst()==currentPlayer.getId()||board[position].getLast()==currentPlayer.getId());
    }
    
    private boolean hasEnemyDoor(int position)
    {
        if (position==0 || position==25)
        {
            return false;
        }
        else
        {
          return (!board[position].isEmpty())&&(board[position].getLast()==currentPlayer.getNextId()&&board[position].size()>=2);
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

//    @Override
//    public String toString()
//    {
//        return super
//    }

   
    
    
    
}
