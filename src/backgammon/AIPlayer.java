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
    private static int maxDepth=1;
    public static Move miniMax(BackgammonBoard board, int depth, Roll roll)
    {
        
//        if(depth==0||child.board.isTerminal())
//        {
//            return new Move(child.moveOrder, child.board.evaluate());
//        }
//        else if(child.board.currentPlayer.getId()==PlayerController.getPlayerWithName("AI").getId())
//        {
//            Move v= new Move(null, new Integer(Integer.MIN_VALUE));
//            //v.v = new Integer(Integer.MIN_VALUE);
//            HashSet<BackgammonChild> childs;
//            for (int i = 1; i <= 6; i++)
//            for (int j = 1; j <= 6; j++)
//            {
//                if(i>=j){
//                    childs=child.board.generateChildren(i,j);
//                    for (BackgammonChild chanceChild : childs) {
//                         miniMax(child, a, b, depth, roll);// gia ton allon TODO orismata
//                        //p.second=child.second;
////                        v=max(v, minimax(child, depth-1, a, b));
////                        if(depth!=maxDepth)
////                        v.movePosition=mancalaBoard.lastBinPlayed;
////                        a=max(a,v);
//                        //System.out.println("v"+v.movePosition+" "+v.v);
//                    }
//           
//                if(b.v<=a.v)
//                    break;
//                }
//            }
//        }
//        else
//        {
//            
//        }
        Move v=null;
        if(board.currentPlayer.getId()==PlayerController.getPlayerWithName("AI").getId())
        {
            HashSet<BackgammonChild> childs;
            childs=board.generateChildren(roll.dice0, roll.dice1);
            v= new Move(null, new Integer(Integer.MIN_VALUE));
            for (BackgammonChild child : childs) {
                
                //p.second=child.second;
                //child.board.currentPlayer=PlayerController.getPlayerWithId(child.board.currentPlayer.getNextId());
                //child.moveOrder.add(new SingleMove(roll.dice0, roll.dice1));
                v=maxMove(v, dice(child, depth,false));

            }
        }else
        {
             HashSet<BackgammonChild> childs;
            childs=board.generateChildren(roll.dice0, roll.dice1);
            v= new Move(null, new Integer(Integer.MAX_VALUE));
            for (BackgammonChild child : childs) {
                
                //p.second=child.second;
                //child.board.currentPlayer=PlayerController.getPlayerWithId(child.board.currentPlayer.getNextId());
                //v=minMove(v, dice(child, depth,true));
                 if (lessMove(dice(child, depth,true),v))
                {
                    v=dice(child, depth,true);
                    v.moveOrder=child.moveOrder;
                }
            }
        }
        
        return v;
    }
    
    public static Move dice(BackgammonChild child, int depth,boolean maximaze)
    {
        Move v= new Move(null, new Integer(Integer.MIN_VALUE));
        int sum=0;
        for (int i = 1; i <= 6; i++)
            for (int j = 1; j <= 6; j++)
                if(i>=j)
                {
                    Roll roll = new Roll(i, j);
                    if(maximaze)//child.board.currentPlayer.getId()==PlayerController.getPlayerWithName("AI").getId())
                    {
                        sum+=max(child.board,  depth-1,  roll).value*roll.propability;
                    }
                    else
                    {
                        sum+=min(child.board,  depth-1,  roll).value*roll.propability;
                    }
                }
        if(depth!=maxDepth)
        {
            v.moveOrder=child.board.lastMovePlayed.moveOrder;
        }
        return new Move(v.moveOrder, sum);
    }
    
    public static Move min(BackgammonBoard board, int depth, Roll roll)
    {
        if(depth==0)//||board.isTerminal())
        {
            
            return new Move (board.lastMovePlayed.moveOrder,board.evaluate());
        }
        Move v=null;
        //if(board.currentPlayer.getId()==PlayerController.getPlayerWithName("AI").getId())
        //{
            HashSet<BackgammonChild> childs;
            childs=board.generateChildren(roll.dice0, roll.dice1);
            v= new Move(null, new Integer(Integer.MAX_VALUE));
            for (BackgammonChild child : childs) {
                
                //p.second=child.second;
                //child.board.currentPlayer=PlayerController.getPlayerWithId(child.board.currentPlayer.getNextId());
                v=minMove(v, dice(child, depth,true));
               

            }
        //}
        if(depth!=maxDepth)
        {
            v.moveOrder=board.lastMovePlayed.moveOrder;
        }
        
        return v;
    }
    
    private static Move max(BackgammonBoard board, int depth, Roll roll)
    {
         if(depth==0)//||board.isTerminal())
         {
            System.out.print(board.lastMovePlayed.moveOrder.size());
            return new Move (board.lastMovePlayed.moveOrder,board.evaluate());
         }
       Move v=null;
        //if(board.currentPlayer.getId()==PlayerController.getPlayerWithName("AI").getId())
        {
            HashSet<BackgammonChild> childs;
            childs=board.generateChildren(roll.dice0, roll.dice1);
            v= new Move(null, new Integer(Integer.MIN_VALUE));
            for (BackgammonChild child : childs) {
                
                //p.second=child.second;
                //child.board.currentPlayer=PlayerController.getPlayerWithId(child.board.currentPlayer.getNextId());
                v=maxMove(v, dice(child, depth,false));

            }
        }
        if(depth!=maxDepth)
        {
            v.moveOrder=board.lastMovePlayed.moveOrder;
        }
        
        return v;
    }
    
    public static Move maxMove(Move a , Move b)
    {
        if(a.value>=b.value)
            return a;
        else return b;
    }
    
    public static Move minMove(Move a , Move b)
    {
        if(a.value<=b.value)
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
        }
        
        
    }
}
