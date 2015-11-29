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
public class Backgammon {

    /**
     * @param args the command line arguments
     */
    private static BackgammonBoard backgammon;
    
    public static void main(String[] args) {
        PlayerController.addPlayer("Stelios");
        PlayerController.addPlayer("AI");
        // TODO code application logic here 
        backgammon= new BackgammonBoard(PlayerController.getPlayerWithId(1));
        backgammon.initialiseBoard();
        AIPlayer.maxDepth=3;
        new graphics.BackgammonFrame(backgammon,"tabli");
        int dice0=3, dice1=1;
        boolean showChildren = false;
        AIPlayer.Roll roll = new AIPlayer.Roll(dice0,dice1);
        Move move=AIPlayer.miniMax(backgammon, AIPlayer.maxDepth, roll);
        System.out.println("Should Play:"+"pos:"+move.moveOrder.get(0).position+" dice:"+move.moveOrder.get(0).dice+"pos:"+move.moveOrder.get(1).position+" dice:"+move.moveOrder.get(1).dice+"V:"+move.value);
        //new graphics.BackgammonFrame(backgammon.getCopy(), "copia");
        
        //System.out.println("time:"+(end-start));
        //System.out.println("Size"+childrenList.size());
        
        if(showChildren){
            long start= System.currentTimeMillis();
            HashSet<BackgammonChild> childrenList=backgammon.generateChildren(dice0, dice1);
            long end= System.currentTimeMillis();
            int i=0;
            for (BackgammonChild childrenList1 : childrenList) {
                try {
                    Thread.sleep(20);                 //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                new graphics.BackgammonFrame(childrenList1.board,i+
                         " dice:"+childrenList1.moveOrder.get(0).dice+"from"+childrenList1.moveOrder.get(0).position+" & "+
                         " dice:"+childrenList1.moveOrder.get(1).dice+"from"+childrenList1.moveOrder.get(1).position+"v:"+childrenList1.board.evaluate());
                i++;
                //System.out.println("poulia"+childrenList1.board.board[25].size());
            }
        }
        System.out.println("Afta einai");
       
//        {
//             new graphics.BackgammonFrame(childrenList.get(i).board,i+
//                     " dice:"+childrenList.get(i).diceMove.dice[0]+"from"+childrenList.get(i).diceMove.position[0]+" & "+
//                     " dice:"+childrenList.get(i).diceMove.dice[1]+"from"+childrenList.get(i).diceMove.position[1]);
//        }
        
       
        
 
    }
    
    
}
