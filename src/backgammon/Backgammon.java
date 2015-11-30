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
        int maxDepth=1;
        boolean aiVSai=true;
        if(args.length>0)
            maxDepth=Integer.parseInt(args[0]);
        if(args.length==2&&args[1].equals("true"))
            aiVSai=true;
        PlayerController.addPlayer("Human Player");
        PlayerController.addPlayer("AI");
        // TODO code application logic here 
        backgammon= new BackgammonBoard(PlayerController.getPlayerWithId(0));
        backgammon.initialiseBoard();
        AIPlayer.maxDepth=maxDepth;
        Random r= new Random(System.currentTimeMillis());
        AIPlayer.Roll roll= new AIPlayer.Roll(r.nextInt(6)+1,r.nextInt(6)+1);
        BackgammonFrame window=new graphics.BackgammonFrame(backgammon, roll,"tabli");
        System.out.println(backgammon.isPlayerInBearOffPhase());
        window.repaint();
        while (!backgammon.isTerminal())
        {
            if(!aiVSai)
            {
                if(backgammon.currentPlayer.getId()==0)
                {

                    if(roll.dice0==roll.dice1){

                         boolean valid=false;

                        while (!valid)
                        {  


                            BackgammonBoard tmp= backgammon.getCopy();

                            HashSet<BackgammonChild> set1=backgammon.generateChildren(roll.dice0, roll.dice1);
                            HashSet<BackgammonBoard> set= new HashSet<BackgammonBoard>();
                            for (BackgammonChild x : set1)
                            {
                                set.add(x.board);
                            }

                            if(set.size()==0)
                            {
                                valid=true;
                                backgammon.currentPlayer=PlayerController.getPlayerWithId(backgammon.currentPlayer.getNextId());
                            }

                            int[] pos = new int[4];
                            for (int i = 0; i < 4; i++)
                            {
                                pos[i] = askPosition(roll.dice0);
                                tmp.doMove(pos[i], roll.dice0);
                                BackgammonFrame.boardToDraw=tmp;
                                window.repaint();

                                 try {
                                    Thread.sleep(500);                //1000 milliseconds is one second.
                                } catch(InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }
                            }
        //                    

                            if (set.contains(tmp))
                            {
                                valid=true;
                                for (int i = 0; i < 4; i++)
                                {
                                    backgammon.doMove(pos[i],roll.dice0);
                                }
                                backgammon.currentPlayer=PlayerController.getPlayerWithId(backgammon.currentPlayer.getNextId());

                            }else
                            {
                                valid=false;

                            }
                        }
                         try {
                                    Thread.sleep(500);                //1000 milliseconds is one second.
                                } catch(InterruptedException ex) {
                                    Thread.currentThread().interrupt();
                                }
                        BackgammonFrame.boardToDraw=backgammon;
                        window.repaint();

                    }
                    else
                    {
                        boolean valid=false;

                        while (!valid)
                        {                        


                            HashSet<BackgammonChild> set1=backgammon.generateChildren(roll.dice0, roll.dice1);
                            HashSet<BackgammonBoard> set= new HashSet<BackgammonBoard>();

                            for (BackgammonChild x : set1)
                            {
                                set.add(x.board);
                            }
                            if(set.size()==0)
                            {
                                valid=true;
                                backgammon.currentPlayer=PlayerController.getPlayerWithId(backgammon.currentPlayer.getNextId());
                            }

                            BackgammonBoard tmp= backgammon.getCopy();
                            int dice0=askDice();

                            int pos0 = askPosition(dice0);
                            tmp.doMove(pos0, dice0);
                            BackgammonFrame.boardToDraw=tmp;
                            window.repaint();
                            int dice1=askDice();

                            int pos1 = askPosition(dice1);
                            tmp.doMove(pos1, dice1);
                            BackgammonFrame.boardToDraw=tmp;
                            window.repaint();

                             try {
                                Thread.sleep(400);                //1000 milliseconds is one second.
                            } catch(InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }



                            if (set.contains(tmp))
                            {
                                valid=true;
                                backgammon.doMove(pos0, dice0);
                                backgammon.doMove(pos1, dice1);
                                backgammon.currentPlayer=PlayerController.getPlayerWithId(backgammon.currentPlayer.getNextId());

                            }else
                            {
                                valid=false;

                            }


                             try {
                                Thread.sleep(400);                //1000 milliseconds is one second.
                            } catch(InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                            BackgammonFrame.boardToDraw=backgammon;
                            try {
                                Thread.sleep(400);                //1000 milliseconds is one second.
                            } catch(InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }


                            window.repaint();
                        }
                    }
                }

                roll.dice0=r.nextInt(6)+1;
                roll.dice1=r.nextInt(6)+1;
            }
            try {
                    Thread.sleep(300);                //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            window.repaint();
            Move move=AIPlayer.miniMax(backgammon, AIPlayer.maxDepth, roll);
            backgammon.doMove(move);
            window.repaint();
            try {
                    Thread.sleep(300);                //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            
            roll.dice0=r.nextInt(6)+1;
            roll.dice1=r.nextInt(6)+1;
            window.repaint();
            
        }
        
        int dice0=3, dice1=1;
        boolean showChildren = false;
       
        float start= System.currentTimeMillis();
        
        float end= System.currentTimeMillis();
        
        
        System.out.println("time:"+((double)(end-start))/1000.0);
        
        
        if(showChildren){
            start= System.currentTimeMillis();
            HashSet<BackgammonChild> childrenList=backgammon.generateChildren(dice0, dice1);
            end= System.currentTimeMillis();
            int i=0;
            for (BackgammonChild childrenList1 : childrenList) {
                try {
                    Thread.sleep(20);              
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                new graphics.BackgammonFrame(childrenList1.board,roll,i+
                         " dice:"+childrenList1.moveOrder.get(0).dice+"from"+childrenList1.moveOrder.get(0).position+" & "+
                         " dice:"+childrenList1.moveOrder.get(1).dice+"from"+childrenList1.moveOrder.get(1).position+"v:"+childrenList1.board.evaluate());
                i++;
               
            }
        }
        System.out.println("The winner is:"+PlayerController.getPlayerWithId(backgammon.currentPlayer.getNextId()).name);

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
