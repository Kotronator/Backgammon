/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon;

import graphics.BackgammonFrame;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author tsipiripo
 */
public class Backgammon {

    /**
     * @param args the command line arguments
     */
    private static BackgammonBoard backgammon;
    static Scanner sc= new Scanner(System.in);
    public static void main(String[] args) {
        PlayerController.addPlayer("Stelios");
        PlayerController.addPlayer("AI");
        // TODO code application logic here 
        backgammon= new BackgammonBoard(PlayerController.getPlayerWithId(0));
        backgammon.initialiseBoard();
        AIPlayer.maxDepth=1;
        Random r= new Random(System.currentTimeMillis());
        AIPlayer.Roll roll= new AIPlayer.Roll(r.nextInt(6)+1,r.nextInt(6)+1);
        BackgammonFrame window=new graphics.BackgammonFrame(backgammon, roll,"tabli");
        System.out.println(backgammon.isPlayerInBearOffPhase());
        window.repaint();
        while (!backgammon.isTerminal())
        {
            if(backgammon.currentPlayer.getId()==0)
            {
               
                if(roll.dice0==roll.dice1){
                    BackgammonBoard tmp= backgammon.getCopy();
                    for (int i = 0; i < 4; i++)
                    {
                        int pos0 = askPosition(roll.dice0);
                        
                        tmp.doMove(pos0, roll.dice0);
                    }
                    
                    HashSet<BackgammonChild> set1=backgammon.generateChildren(roll.dice0, roll.dice1);
                    HashSet<BackgammonBoard> set= new HashSet<BackgammonBoard>();
                    for (BackgammonChild x : set1)
                    {
                        set.add(x.board);
                    }
                    if (set.contains(tmp))
                    {
                        System.out.println("\nEkanes thn Tuxh sou");
                    }else
                    {
                        System.out.println("\nTi pas na kaneis");
                    }
                    //backgammon.doMove(pos0, roll.dice0);
                    //backgammon.doMove(pos1, roll.dice1);
                    backgammon.currentPlayer=PlayerController.getPlayerWithId(backgammon.currentPlayer.getNextId());
                    window.repaint();
                    
                    
                }
                else
                {
                    int dice0=askDice();

                    int pos0 = askPosition(dice0);
                    int dice1=askDice();
                    int pos1 = askPosition(dice1);
                    BackgammonBoard tmp= backgammon.getCopy();
                    tmp.doMove(pos0, roll.dice0);
                    tmp.doMove(pos1, roll.dice1);
                    HashSet<BackgammonChild> set1=backgammon.generateChildren(roll.dice0, roll.dice1);
                    HashSet<BackgammonBoard> set= new HashSet<BackgammonBoard>();
                    for (BackgammonChild x : set1)
                    {
                        set.add(x.board);
                    }
                    if (set.contains(tmp))
                    {
                        System.out.println("\nEkanes thn Tuxh sou");
                    }else
                    {
                        System.out.println("\nTi pas na kaneis");
                    }
                    backgammon.doMove(pos0, roll.dice0);
                    backgammon.doMove(pos1, roll.dice1);
                    backgammon.currentPlayer=PlayerController.getPlayerWithId(backgammon.currentPlayer.getNextId());
                    window.repaint();
                   
                }
            }
            roll.dice0=r.nextInt(6)+1;
            roll.dice1=r.nextInt(6)+1;
            Move move=AIPlayer.miniMax(backgammon, AIPlayer.maxDepth, roll);
            backgammon.doMove(move);
            window.repaint();
            try {
                    Thread.sleep(100);                 //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            
            roll.dice0=r.nextInt(6)+1;
            roll.dice1=r.nextInt(6)+1;
            window.repaint();
            
        }
        //window.repaint();
        int dice0=3, dice1=1;
        boolean showChildren = false;
        //AIPlayer.Roll roll = new AIPlayer.Roll(dice0,dice1);
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
                new graphics.BackgammonFrame(childrenList1.board,roll,i+
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
    
    public static int askPosition(int dice)
    {
        System.out.print("Type position to play dice "+dice+":");
        int pos=sc.nextInt();
        return pos;
    }
    
     public static int askDice()
    {
        System.out.print("Type dice to play:");
        int dice=sc.nextInt();
        return dice;
    }
    
    
}
