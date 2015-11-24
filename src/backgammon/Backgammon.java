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
public class Backgammon {

    /**
     * @param args the command line arguments
     */
    private static BackgammonBoard backgammon;
    
    public static void main(String[] args) {
        PlayerController.addPlayer("Stelios");
        PlayerController.addPlayer("AI");
        // TODO code application logic here 
        backgammon= new BackgammonBoard();
        backgammon.initialiseBoard();
        new graphics.BackgammonFrame(backgammon,"tabli");
        LinkedList<BackgammonChild> childrenList=backgammon.generateChildren(3, 6);
        System.out.println("Size"+childrenList.size());
        System.out.println("teleiwse to paidi");
        
        for (int i = 0; i < childrenList.size(); i++)
        {
             new graphics.BackgammonFrame(childrenList.get(i).board,i+
                     " dice:"+childrenList.get(i).diceMove.dice[0]+"from"+childrenList.get(i).diceMove.position[0]+" & "+
                     " dice:"+childrenList.get(i).diceMove.dice[1]+"from"+childrenList.get(i).diceMove.position[1]);
        }
        
       
        
 
    }
    
    
}
