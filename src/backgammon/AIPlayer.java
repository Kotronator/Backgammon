/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon;

import java.util.HashSet;
import java.util.List;

/**
 *
 * @author tsipiripo
 */
public class AIPlayer
{
    public static int maxDepth=1;
    public static Move miniMax(BackgammonBoard board, int depth, Roll roll)
    {
        

        Move v=null;
        if(board.currentPlayer.name.equals("AI"))
        {
            System.out.println("Minimax Entered Max");
            HashSet<BackgammonChild> childs;
            childs=board.generateChildren(roll.dice0, roll.dice1);
            v= new Move(null, Double.NEGATIVE_INFINITY);
            for (BackgammonChild child : childs) {
                
                //p.second=child.second;
                //child.board.currentPlayer=PlayerController.getPlayerWithId(child.board.currentPlayer.getNextId());
                //child.moveOrder.add(new SingleMove(roll.dice0, roll.dice1));
                //v=maxMove(v, dice(child.board, depth-1,false));
                Move diceMove=dice(child.board, depth-1, false);
                 if (greaterMove(diceMove,v))
                {
                    v=diceMove;
                    v.moveOrder=child.moveOrder;
                }

            }
        }else
        {
             HashSet<BackgammonChild> childs;
            childs=board.generateChildren(roll.dice0, roll.dice1);
            v= new Move(null, new Double(Double.POSITIVE_INFINITY));
            for (BackgammonChild child : childs) {
                
                //p.second=child.second;
                //child.board.currentPlayer=PlayerController.getPlayerWithId(child.board.currentPlayer.getNextId());
                //v=minMove(v, dice(child, depth,true));
                Move diceMove=dice(child.board, depth-1, true);
                 if (lessMove(diceMove,v))
                {
                    v=diceMove;
                    v.moveOrder=child.moveOrder;
                }
            }
            
        }
        System.out.println("Minimax Finished");
        return v;
    }
    static int k=0;
    public static Move dice(BackgammonBoard board, int depth,boolean maximaze)
    {  
        board.currentPlayer=PlayerController.getPlayerWithId(board.currentPlayer.getNextId());
        if (depth==0)
        { //System.out.println("EDW EDW"+(k++));
            return new Move(null,board.evaluate());
            
        }
       
        
        Move v=null;// new Move(null, 0);//megalh patata
        double sum=0;
        
        for (int i = 1; i <= 6; i++)
            for (int j = 1; j <= 6; j++)
                if(i>=j)
                {
                    Roll roll = new Roll(i, j);
                    if(maximaze)//child.board.currentPlayer.getId()==PlayerController.getPlayerWithName("AI").getId())
                    {
                        sum+=max(board,  depth,  roll).value*roll.propability;
                    }
                    else
                    {
                        Move tmp=min(board,  depth,  roll);
                        //System.out.println("v:"+tmp.value+" p="+roll.propability);
                        sum+=tmp.value*roll.propability;
                    }
                }
//        if(depth!=maxDepth)
//        {
//            v.moveOrder=child.board.lastMovePlayed.moveOrder;
//        }
        
        return new Move(null, sum);
    }
    
    public static Move min(BackgammonBoard board, int depth, Roll roll)
    {
        if(depth==0)//||board.isTerminal())
        {
            throw new UnsupportedOperationException("Lathos sto min d=0");
            //return new Move (board.lastMovePlayed.moveOrder,board.evaluate());
        }
        Move v=null;
        //if(board.currentPlayer.getId()==PlayerController.getPlayerWithName("AI").getId())
        //{
            HashSet<BackgammonChild> childs;
            childs=board.generateChildren(roll.dice0, roll.dice1);
            v= new Move(null,(Double.POSITIVE_INFINITY));
            for (BackgammonChild child : childs) {
                
                //p.second=child.second;
                //child.board.currentPlayer=PlayerController.getPlayerWithId(child.board.currentPlayer.getNextId());
                v=minMove(v, dice(child.board, depth-1,true));
               

            }
        //}
//        if(depth!=maxDepth)
//        {
//            v.moveOrder=board.lastMovePlayed.moveOrder;
//        }
        
        return v;
    }
    
    private static Move max(BackgammonBoard board, int depth, Roll roll)
    {
         if(depth==0)//||board.isTerminal())
         {
             throw new UnsupportedOperationException("Lathos sto max d=0");
//            System.out.print(board.lastMovePlayed.moveOrder.size());
//            return new Move (board.lastMovePlayed.moveOrder,board.evaluate());
         }
         board.currentPlayer=PlayerController.getPlayerWithId(board.currentPlayer.getNextId());
       Move v=null;
        //if(board.currentPlayer.getId()==PlayerController.getPlayerWithName("AI").getId())
        {
            HashSet<BackgammonChild> childs;
            childs=board.generateChildren(roll.dice0, roll.dice1);
            v= new Move(null, Double.NEGATIVE_INFINITY);
            for (BackgammonChild child : childs) {
                
                //p.second=child.second;
                //child.board.currentPlayer=PlayerController.getPlayerWithId(child.board.currentPlayer.getNextId());
                v=maxMove(v, dice(child.board, depth-1,false));

            }
        }
//        if(depth!=maxDepth)
//        {
//            v.moveOrder=board.lastMovePlayed.moveOrder;
//        }
//        
        //System.out.println("Exited from max with Dices"+roll.dice0+":"+roll.dice1);
        return v;
    }
    
    public static Move maxMove(Move a , Move b)
    {
        if(a.value>b.value)
            return a;
        else return b;
    }
    
    public static Move minMove(Move a , Move b)
    {
        if(a.value<b.value)
            return a;
        else return b;
    }
    
    public static boolean lessMove(Move a , Move b)
    {
        if(a.value<b.value)
            return true;
        else return false;
    }
    public static boolean greaterMove(Move a , Move b)
    {
        if(a.value>b.value)
            return true;
        else return false;
    }

    

    static class Roll
    {
        int dice0;
        int dice1;
        double propability;

        public Roll(int dice0, int dice1)
        {
            this.dice0 = dice0;
            this.dice1 = dice1;
            this.propability = dice0!=dice1?propability=1.0/18.0:1.0/36.0;
            if (propability==0)
            {
                throw new UnsupportedOperationException("P=0");
            }
        }
        
        
    }
}
