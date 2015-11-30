/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
    public Player currentPlayer;
    public Move lastMovePlayed= new Move(null, 0);

    public BackgammonBoard(Player currentPlayer)
    {
        this();
        //this.type = type;
        this.currentPlayer = currentPlayer;
    }
    
    
    
    
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

    @Override
    public int hashCode() {
        int hashcode=0;
        for (int i = 0; i < board.length; i++) {
            hashcode+=board[i].hashCode();
            
        }
        return hashcode;
    }
    
    
    
    public HashSet<BackgammonChild> generateChildren(int dice0, int dice1)
    {
        
        HashSet<BackgammonChild> childrenList= new HashSet<>();
        
        boolean isRollDouble=false;
        if(dice1>dice0)
        {
            int temp;
            temp=dice1;
            dice1=dice0;
            dice0=temp;
        }
        else if(dice0==dice1)
        {
            isRollDouble=true;
        }
        
        int firstPiece=currentPlayer.getNextId()*25;
        
        for (int i = 0; i < board.length; i++)
        {
            if(hasPiece(i))
            {
                
                firstPiece=i;
                break;
            }
            
        }
        
        int maxDiceDoublesPlayed=0;//needed for double roll
        boolean canDouble=false,canSingle=false,canBig=false;
        BackgammonChild backgammonChild;//= new BackgammonChild(this);
        if(!isRollDouble)
        {
            for (int i = 25*currentPlayer.getId(); currentPlayer.getId()==0?i<board.length-1:i>0;/*i < board.length;*/ i=backgammonChild.board.getNextPiece(i))
            {
                
                //System.out.println("i="+i);
                 backgammonChild= new BackgammonChild(this);

                if (backgammonChild.board.isValidMove(i, dice0)) 
                {// mporw n pai3w tn megalh zaria
                    //System.out.println("hi"+i+""+dice0);
                    backgammonChild.diceMove.dice[0]=dice0;
                    backgammonChild.diceMove.position[0]=i;
                    backgammonChild.board.doMove(i, dice0);
                    backgammonChild.moveOrder.add(new SingleMove(i, dice0));
                    canSingle=true;
                    canBig=true;
                }
                else
                {
                    backgammonChild.diceMove.dice[0]=BackgammonChild.DiceMove.DICE_IMPOSSIBLE;
                    backgammonChild.diceMove.position[0]=i;
                    if(canBig)//an exdw megalh zaria dn xreiazetai n e3ereunhsw paidia p paizoun me tn mikrh
                    {
                        //continue;
                    }

                }
                BackgammonChild finalChild;
                for (int j = 25*currentPlayer.getId(); currentPlayer.getId()==0?j<board.length-1:j>0; j=finalChild.board.getNextPiece(j)) 
                {
                    
                    //System.out.println("j="+j);
                        finalChild= new BackgammonChild(backgammonChild);
                    if (finalChild.board.isValidMove(j, dice1)) 
                    {// mporw n pai3w tn mikrh zaria
                           
                        finalChild.diceMove.dice[1]=dice1;
                        finalChild.diceMove.position[1]=j;
                        finalChild.board.doMove(j, dice1);
                        //System.out.println("paizw"+i+"+"+j+finalChild.diceMove.dice[0]+finalChild.diceMove.dice[1]);
                        finalChild.moveOrder.add(new SingleMove(j, dice1));
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
                    boolean canBigAfterSmall=false;
                    BackgammonChild finalChild2;
                    if(finalChild.diceMove.dice[0]==BackgammonChild.DiceMove.DICE_IMPOSSIBLE&&finalChild.diceMove.dice[1]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE)
                    {
                        int pid=currentPlayer.getId();
                        for (int k = 25*pid; pid==0?k<board.length-1:k>0; k=k+(pid*(-2)+1))
                        {
                            //System.out.println("bla bla kappa kappa kappa"+k);
                            finalChild2 = new BackgammonChild(finalChild);
                             //System.out.println("k="+k);
                               // backgammonChild= new BackgammonChild(this);

                               if (finalChild2.board.isValidMove(k, dice0)) 
                               {// mporw n pai3w tn megalh zaria
                                   //System.out.println("kappa"+k+""+dice0);
                                   finalChild2.diceMove.dice[0]=dice0;
                                   finalChild2.diceMove.position[0]=k;
                                   finalChild2.board.doMove(k, dice0);
                                   canSingle=true;
                                   canBig=true;
                                   canBigAfterSmall=true;
                                   canDouble=true;
                                   finalChild2.moveOrder.add(new SingleMove(k, dice0));
                                   //break;
                               }
                               else
                               {
                                   finalChild2.diceMove.dice[0]=BackgammonChild.DiceMove.DICE_IMPOSSIBLE;
                                   finalChild2.diceMove.position[0]=k;
                                   if(canBig)//an exdw megalh zaria dn xreiazetai n e3ereunhsw paidia p paizoun me tn mikrh
                                   {
                                       continue;
                                   }

                               }
                               
                            if((canDouble&&finalChild2.diceMove.dice[0]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE&&finalChild2.diceMove.dice[1]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE)
                                ||(!canDouble&&canSingle&&(finalChild2.diceMove.dice[0]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE||finalChild2.diceMove.dice[1]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE))    

                                    )
                            {
                                //if (!finalChild2.board.equals(backgammonChild.board)){//||finalChild2.diceMove.) {
                                    childrenList.add(finalChild2);
                                //}

                            }

                        }
                        
                    }
                    if((canDouble&&finalChild.diceMove.dice[0]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE&&finalChild.diceMove.dice[1]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE)
                        ||(!canDouble&&canSingle&&(finalChild.diceMove.dice[0]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE||finalChild.diceMove.dice[1]!=BackgammonChild.DiceMove.DICE_IMPOSSIBLE))    
                            
                            )
                    {
                        //if (!finalChild.board.equals(backgammonChild.board)){//||finalChild.diceMove.) {
                            childrenList.add(finalChild);
                        //}
                        
                    }

                }

            }
        }else //double roll
        {

            for (int i = 25*currentPlayer.getId(); currentPlayer.getId()==0?i<board.length-1:i>0; i=backgammonChild.board.getNextPiece(i))
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
                    backgammonChild.moveOrder.add(new SingleMove(i, dice0));
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
                BackgammonChild secondChild;
                for (int j = 25*currentPlayer.getId(); currentPlayer.getId()==0?j<board.length-1:j>0; j=secondChild.board.getNextPiece(j)) /////////----------------------------2h apo 4
                {
                    //LinkedList<Integer> board1 = board[j];
                    secondChild= new BackgammonChild(backgammonChild);
                    if (secondChild.board.isValidMove(j, dice0))
                    {
                        secondChild.diceMove.maxPlayed++;
                        secondChild.diceMove.dice[1]=dice0;
                        secondChild.diceMove.position[1]=j;
                        secondChild.board.doMove(j, dice0);
                        secondChild.moveOrder.add(new SingleMove(j, dice0));
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
                    BackgammonChild thirdChild;
                    for (int k = 25*currentPlayer.getId();currentPlayer.getId()==0?k<board.length-1:k>0; k=thirdChild.board.getNextPiece(k))/////////----------------------------
                    {
                        thirdChild= new BackgammonChild(secondChild);
                        //LinkedList<Integer> board1 = board[j];
                        if (thirdChild.board.isValidMove(k, dice0))
                        {
                            thirdChild.diceMove.maxPlayed++;
                            thirdChild.diceMove.dice[2]=dice0;
                            thirdChild.diceMove.position[2]=k;
                            thirdChild.board.doMove(k, dice0);
                            thirdChild.moveOrder.add(new SingleMove(k, dice0));
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
                        BackgammonChild finalChild;
                        for (int w = 25*currentPlayer.getId();currentPlayer.getId()==0?w<board.length-1:w>0; w=thirdChild.board.getNextPiece(w))/////////----------------------------
                        {
                            //LinkedList<Integer> board1 = board[j];
                            finalChild= new BackgammonChild(thirdChild);
                            if (finalChild.board.isValidMove(w, dice0))
                            {
                                finalChild.diceMove.maxPlayed++;
                                finalChild.diceMove.dice[3]=dice0;
                                finalChild.diceMove.position[3]=w;
                                finalChild.board.doMove(w, dice0);
                                finalChild.moveOrder.add(new SingleMove(w, dice0));
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
        ArrayList<BackgammonChild> childrenToRemove = new ArrayList();
        if(!isRollDouble){
            if (canDouble) {
                //System.out.println("afairw ta mapa");
                for (BackgammonChild child : childrenList) {
                    if(child.diceMove.dice[0]<=0||child.diceMove.dice[1]<=0)
                    {
                        //System.err.println(child.diceMove.dice[0]+" "+child.diceMove.position[0]+":"+child.diceMove.dice[1]+" "+child.diceMove.position[1]);
                        childrenToRemove.add(child);
                    }
                }
                while(childrenToRemove.size()>0){
                    childrenList.remove(childrenToRemove.remove(0));

                }

            }
        }else
        {
            //System.out.println("afairw ta mapa me dipla");
                for (BackgammonChild child : childrenList) {
                    if(child.diceMove.maxPlayed<maxDiceDoublesPlayed)
                    {
                        //System.out.println(child.diceMove.dice[0]+" "+child.diceMove.position[0]+":"+child.diceMove.dice[1]+" "+child.diceMove.position[1]);
                        childrenToRemove.add(child);
                    }
                }
                while(childrenToRemove.size()>0){
                    childrenList.remove(childrenToRemove.remove(0));

                }
        }
        
        return childrenList; //TODO
    }

    @Override
    public boolean equals(Object obj)
    {
        if( !(obj instanceof BackgammonBoard))
            return false;
        if(obj==this)
            return true;
        for (int i = 0; i < ((BackgammonBoard)obj).board.length; i++)
         {
            if (!((BackgammonBoard)obj).board[i].equals(this.board[i])) 
            {
                return false;
            }
            
        }
        return true;
    }
    
    
    
    public BackgammonBoard getCopy()
    {
        BackgammonBoard board = new BackgammonBoard();
        
        for (int i = 0; i < board.getBoard().length; i++) {
           board.getBoard()[i]=new LinkedList<Integer>(this.board[i]);
              
        }
        board.type=type;
        board.currentPlayer=PlayerController.getPlayerWithId(this.currentPlayer.getId());
        board.lastMovePlayed=new Move(this.lastMovePlayed.moveOrder, this.lastMovePlayed.value);
        
        return board;
    }
    
    public void initialiseBoard()
    {
        if(type==PORTES)
        {
            //initialiseForOnlySmall();
            //initialiseShouldDoDoor();
            doorsSetup();
        }//TODO
    }
    
    private void initialiseShouldEat()//6.1
    {
         board[0].addLast(0);
            board[1].addLast(1);
            board[6+4].addLast(1);
            board[6+4].addLast(1);
            board[13].addLast(0);
            board[25].addLast(1);
            board[12].addLast(1);
    }
    
    private void initialiseShouldDoDoor()//3.1
    {
        board[1].add(0);
        board[3].add(0);
        board[7].add(0);
        board[24].add(1);
        board[22].add(1);
    }
    
    private void initialiseForOnlyBig()//can both ... should big//3.6
    {   board[1].add(0);
        //board[9].add(0);
         for (int i = 2; i < 4; i++)
         {
            board[i].add(1);
            board[i].add(1);
        }
         for (int i = 5; i < 7; i++)
         {
            board[i].add(1);
            board[i].add(1);
        }
         for (int i = 8; i < 17; i++)
         {
            board[i].add(1);
            board[i].add(1);
        }
         
    }
    
    private void initialiseForOnlySmall()
    //can one ... can only small ... should small 1.2
    //can both should play small first 1.3
    {   board[1].add(0);
        //board[9].add(0);

        for (int i = 3; i < 5; i++)
        {
            board[i].add(1);
            board[i].add(1);
        }
        
    }

    public boolean doMove(Move move)
    {
        for (Iterator<SingleMove> iterator = move.moveOrder.iterator(); iterator.hasNext();)
        {
            SingleMove next = iterator.next();
            if(!doMove(next.position,next.dice))
                throw new UnsupportedOperationException("den ginetai h kinhsh");
            
        }
        currentPlayer=PlayerController.getPlayerWithId(currentPlayer.getNextId());
        return true;
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
                        //System.out.println("Parth porta kai fige");
                        if ((!board[targetPosition].isEmpty())&&board[targetPosition].getLast()==currentPlayer.getNextId())//trwme pouli
                        {
                            //System.out.println("to faga");
                            int piece=board[targetPosition].removeLast();
                            board[currentPlayer.getNextId()*25].addLast(piece);
                        }
                    }
                    board[targetPosition].addLast(currentPlayer.getId());
                    board[position].removeLast();
                    
                    
                }
            lastMovePlayed.moveOrder.add(new SingleMove(position, dice));
            //
            return true;
        }
        return false;
    }

    private boolean isValidMove(int position, int dice)
    {
        if(position<0||position>25||!hasPiece(position))
        {
            return false;
        }
        if((currentPlayer.getId()==0&&position==25)||(currentPlayer.getId()==1&&position==0))
            return false;
        
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
            if((position<=6&&currentPlayer.getId()==1&&dice>=position)||(position>=19&&currentPlayer.getId()==0 && dice>=6-(position % 19)))//ama paw na pazepsw den epitrpetai
                return false;//kinhsh mazematos
//            if(currentPlayer.getId()==1)
//            {
//                if (position>=1&&position<=6&&getBearOffDistanceOfPosition(position)<=dice)
//                {
//                    return false;
//                }
//            }
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
    
    public boolean isPlayerInBearOffPhase()
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
        
        for (int i = startPosition; i <= finishPosition; i++)
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
    
    private boolean hasDoor(int position)
    {
        if (position==0 || position==25)
        {
            return false;
        }
        else
        {
            return (!board[position].isEmpty())&&(board[position].getLast()==currentPlayer.getId()&&board[position].size()>=2);
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

    private int getNextPiece(int i)
    {
//        if (hasPiece(i))
//        {
//            return i;
//        }
//        
        //else
            if (currentPlayer.getId()==0)
            {
                for (int j = i+1; j < 25; j++)
                {
                    if (hasPiece(j))
                    {
                        return j;
                    }
                }
            }
            else
            {
               for (int j =i-1 ; j > 0; j--)
                {
                    if (hasPiece(j))
                    {
                        return j;
                    }
                } 
            }
        return currentPlayer.getNextId()*25+1*(currentPlayer.getId()*(-2)+1);
    }

    boolean isTerminal()
    {
        if (board[0].isEmpty()||board[25].isEmpty())
        {
            return false;
        }
        int greenCounter=0;
        int redCounter=0;
        for (Iterator<Integer> iterator = board[0].iterator(); iterator.hasNext();)
        {
            int piece=iterator.next();
            if(piece==1)
                greenCounter++;
            else
                break;
            
        }
        for (Iterator<Integer> iterator = board[25].iterator(); iterator.hasNext();)
        {
            int piece=iterator.next();
            if(piece==0)
                redCounter++;
            else
                break;
            
        }
        
        return redCounter==15||greenCounter==15;
    }

    public double evaluate()
    {
        //Player temp=currentPlayer;
        //currentPlayer=PlayerController.getPlayerWithId(1);
        int ourDoor=0,enemyDoor=0,ourCaptured=0,enemyCaptured=0,ourCollected=0,enemyCollected = 0;
        for (int i = 1; i < 25; i++)
        {
            if(hasEnemyDoor(i))
                enemyDoor++;
            if(hasDoor(i))
                ourDoor++;
        }
        int start,end;
        if(currentPlayer.getId()==0)
        {
            start=0;
            end=25;
        }
        else
        {
            start=25;
            end=0;
        }
        if (!board[start].isEmpty()&& board[start].getLast()==currentPlayer.getId())
        {
            int i=0,num=0;
            while (i-1>0&&board[start].get(board[start].size()-1-i)==currentPlayer.getId())
            {                
                ourCaptured+=1;
                i++;
            }
            
        }
        if (!board[end].isEmpty()&& board[end].getLast()==currentPlayer.getNextId())
        {
            int i=0,num=0;
            while (i-1>0&&board[end].get(board[end].size()-1-i)==currentPlayer.getNextId())
            {                
                enemyCaptured+=1;
                i++;
            }
            
        }
        
        if (!board[end].isEmpty()&& board[end].getFirst()==currentPlayer.getId())
        {
            
            for (Iterator<Integer> iterator = board[end].iterator(); iterator.hasNext();)
            {
                int piece = iterator.next();
                if (piece==currentPlayer.getNextId())
                {
                    break;
                }
                ourCollected++;
                
            }
                        
        }
        
        if (!board[start].isEmpty()&& board[start].getFirst()==currentPlayer.getNextId())
        {
            
            for (Iterator<Integer> iterator = board[start].iterator(); iterator.hasNext();)
            {
                int piece = iterator.next();
                if (piece==currentPlayer.getId())
                {
                    break;
                }
                enemyCollected++;
                
            }
                        
        }
        
        // currentPlayer=temp;
        if (currentPlayer.getId()==0)
        {
            return -(ourDoor-enemyDoor+enemyCaptured-ourCaptured+ourCollected-enemyCollected);
        }
         else return ourDoor-enemyDoor+enemyCaptured-ourCaptured+ourCollected-enemyCollected;
        //return (ourDoor-enemyDoor-ourCaptured+enemyCaptured);
    }

    private void doorsSetup()
    {
        board[1].add(0);
        board[1].add(0);
        for (int i = 0; i < 5; i++)
        {
            board[6].add(1);
        }
        for (int i = 0; i < 3; i++)
        {
            board[8].add(1);
        }
         for (int i = 0; i < 5; i++)
        {
            board[12].add(0);
        }
          for (int i = 0; i < 5; i++)
        {
            board[13].add(1);
        }
          for (int i = 0; i < 3; i++)
        {
            board[17].add(0);
        }
          for (int i = 0; i < 5; i++)
        {
            board[19].add(0);
        }
        
          for (int i = 0; i < 2; i++)
        {
            board[24].add(1);
        }
    }

   
    
    
    
}
