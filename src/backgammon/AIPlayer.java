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
            HashSet<BackgammonChild> childs;
            childs=board.generateChildren(roll.dice0, roll.dice1);
            v= new Move(null, Double.NEGATIVE_INFINITY);
            for (BackgammonChild child : childs) {
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
                Move diceMove=dice(child.board, depth-1, true);
                 if (lessMove(diceMove,v))
                {
                    v=diceMove;
                    v.moveOrder=child.moveOrder;
                }
            }
            
        }
        return v;
    }
    static int k=0;
    public static Move dice(BackgammonBoard board, int depth,boolean maximaze)
    {  
       
        if (depth==0)
        { 
            return new Move(null,board.evaluate());
            
        }
       
         board.currentPlayer=PlayerController.getPlayerWithId(board.currentPlayer.getNextId());
        Move v=null;
        double sum=0;
        
        for (int i = 1; i <= 6; i++)
            for (int j = 1; j <= 6; j++)
                if(i>=j)
                {
                    Roll roll = new Roll(i, j);
                    if(maximaze)
                    {
                        sum+=max(board,  depth,  roll).value*roll.propability;
                    }
                    else
                    {
                        Move tmp=min(board,  depth,  roll);
                       
                        sum+=tmp.value*roll.propability;
                    }
                }

        
        return new Move(null, sum);
    }
    
    public static Move min(BackgammonBoard board, int depth, Roll roll)
    {
        if(depth==0)
        {
            throw new UnsupportedOperationException("Lathos sto min d=0");
            
        }
        Move v=null;
        
            HashSet<BackgammonChild> childs;
            childs=board.generateChildren(roll.dice0, roll.dice1);
            v= new Move(null,(Double.POSITIVE_INFINITY));
            for (BackgammonChild child : childs) {
                
                
                v=minMove(v, dice(child.board, depth-1,true));
               

            }
        
        
        return v;
    }
    
    private static Move max(BackgammonBoard board, int depth, Roll roll)
    {
         if(depth==0)
         {
             throw new UnsupportedOperationException("Lathos sto max d=0");

         }
         board.currentPlayer=PlayerController.getPlayerWithId(board.currentPlayer.getNextId());
       Move v=null;
        
        {
            HashSet<BackgammonChild> childs;
            childs=board.generateChildren(roll.dice0, roll.dice1);
            v= new Move(null, Double.NEGATIVE_INFINITY);
            for (BackgammonChild child : childs) {
                
                
                v=maxMove(v, dice(child.board, depth-1,false));

            }
        }
    
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

    

    public static class Roll
    {
        public int dice0;
        public int dice1;
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
