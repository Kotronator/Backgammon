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
        LinkedList<BackgammonChild> childrenList=backgammon.generateChildren(1, 2);
        System.out.println(childrenList.size());
        System.out.println("teleiwse to paidi");
        new graphics.BackgammonFrame(backgammon,"tabli");
        
        new graphics.BackgammonFrame(childrenList.get(0).board,"0");
        
       
        new graphics.BackgammonFrame(childrenList.get(1).board,"1");
    }
    
    
}
