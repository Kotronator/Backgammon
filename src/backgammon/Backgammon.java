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
        backgammon= new BackgammonBoard(PlayerController.getPlayerWithId(0));
        backgammon.initialiseBoard();
        new graphics.BackgammonFrame(backgammon,"tabli");
        //new graphics.BackgammonFrame(backgammon.getCopy(), "copia");
        long start= System.currentTimeMillis();
        HashSet<BackgammonChild> childrenList=backgammon.generateChildren(1, 6);
        long end= System.currentTimeMillis();
        System.out.println("time:"+(end-start));
        System.out.println("Size"+childrenList.size());
        
        int i=0;
        for (BackgammonChild childrenList1 : childrenList) {
            try {
                Thread.sleep(20);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            new graphics.BackgammonFrame(childrenList1.board,i+
                     " dice:"+childrenList1.moveOrder.get(0).dice+"from"+childrenList1.moveOrder.get(0).position+" & "+
                     " dice:"+childrenList1.moveOrder.get(1).dice+"from"+childrenList1.moveOrder.get(1).position);
            i++;
            //System.out.println("poulia"+childrenList1.board.board[25].size());
        }
        System.out.println("Afta einai");
       
//        {
//             new graphics.BackgammonFrame(childrenList.get(i).board,i+
//                     " dice:"+childrenList.get(i).diceMove.dice[0]+"from"+childrenList.get(i).diceMove.position[0]+" & "+
//                     " dice:"+childrenList.get(i).diceMove.dice[1]+"from"+childrenList.get(i).diceMove.position[1]);
//        }
        
       
        
 
    }
    
    
}
