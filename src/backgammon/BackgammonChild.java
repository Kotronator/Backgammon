/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon;

/**
 *
 * @author TOSHIBA
 */
public class BackgammonChild 
{
    BackgammonBoard board;
    DiceMove diceMove;

    public class DiceMove
    {
        public static final int DICE_UNCHECKED=-1, DICE_IMPOSSIBLE=0 ;
        
        int[] dice = new int[2];
        int[] position = new int[2];

        public DiceMove()
        {
            dice[0]=DICE_UNCHECKED;
            dice[1]=DICE_UNCHECKED;
            position[0]=0;
            position[1]=0;
        }
        
       
        
    }
}
