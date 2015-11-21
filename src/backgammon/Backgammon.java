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
    public static BackgammonBoard backgammon;
    
    public static void main(String[] args) {
        PlayerController.addPlayer("Stelios");
        PlayerController.addPlayer("AI");
        // TODO code application logic here 
        backgammon= new BackgammonBoard();
        backgammon.initialiseBoard();
        LinkedList<BackgammonChild> childrenList=backgammon.generateChildren(1, 2);
        System.out.println(childrenList.size());
        System.out.println("teleiwse to paidi");
        
        backgammon=childrenList.get(0).board;
        new graphics.BackgammonFrame();
        
        backgammon=childrenList.get(1).board;
        new graphics.BackgammonFrame();
    }
    
    
}
