/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon;

import graphics.BackgammonFrame;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

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
        backgammon= new BackgammonBoard(PlayerController.getPlayerWithId(0));
        backgammon.initialiseBoard();
        AIPlayer.maxDepth=2;
        Random r= new Random(System.currentTimeMillis());
        BackgammonFrame window=new graphics.BackgammonFrame(backgammon,"tabli");
        while (!backgammon.isTerminal())
        {
            AIPlayer.Roll roll = new AIPlayer.Roll(r.nextInt(6)+1,r.nextInt(6)+1);
            Move move=AIPlayer.miniMax(backgammon, AIPlayer.maxDepth, roll);
            backgammon.doMove(move);
            try {
                    Thread.sleep(50);                 //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            window.repaint();
            
        }
        window.repaint();
        int dice0=3, dice1=1;
        boolean showChildren = false;
        AIPlayer.Roll roll = new AIPlayer.Roll(dice0,dice1);
        float start= System.currentTimeMillis();
        //Move move=AIPlayer.miniMax(backgammon, AIPlayer.maxDepth, roll);
        float end= System.currentTimeMillis();
        //System.out.println("Should Play:"+"(p:"+move.moveOrder.get(0).position+" d:"+move.moveOrder.get(0).dice+")(p:"+move.moveOrder.get(1).position+" d:"+move.moveOrder.get(1).dice+")V:"+move.value);
        //new graphics.BackgammonFrame(backgammon.getCopy(), "copia");
        
        System.out.println("time:"+((double)(end-start))/1000.0);
        //System.out.println("Size"+childrenList.size());
        
        if(showChildren){
            start= System.currentTimeMillis();
            HashSet<BackgammonChild> childrenList=backgammon.generateChildren(dice0, dice1);
            end= System.currentTimeMillis();
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
