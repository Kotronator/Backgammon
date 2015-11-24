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
    public BackgammonBoard board;
    public DiceMove diceMove;
    
    
    public BackgammonChild(BackgammonBoard board)
    {
        this.board=board.getCopy();
        diceMove= new DiceMove();
        
    }
    
    public BackgammonChild(BackgammonChild src)
    {
        this.board=src.board.getCopy();
        diceMove= new DiceMove(src.diceMove);
        
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
    
    @Override
        public boolean equals(Object obj)
        {
            if( !(obj instanceof BackgammonChild))
                return false;
            if(obj==this)
                return true;
//            for (int i = 0; i < dice.length; i++)
//            {
//                if(dice[i]!=((BackgammonChild)obj).diceMove.dice[i]||position[i]!=((BackgammonChild)obj).diceMove.position[i])
//                    return false;
//                
//            }
            return board.equals(((BackgammonChild)obj).board);
        }
    
    

    public class DiceMove
    {
        public static final int DICE_UNCHECKED=-1, DICE_IMPOSSIBLE=0 ;
        
        int[] dice = new int[4];
        int[] position = new int[4];
        int maxPlayed=0;

        public DiceMove()
        {
            dice[0]=DICE_UNCHECKED;
            dice[1]=DICE_UNCHECKED;
            dice[2]=DICE_UNCHECKED;
            dice[3]=DICE_UNCHECKED;
            position[0]=0;
            position[1]=0;
            position[2]=0;
            position[3]=0;
        }
        
        public DiceMove(DiceMove src)
        {
            for (int i = 0; i < src.dice.length; i++)
            {
                dice[i]= src.dice[i];
                position[i]= src.position[i];
                
            }
            maxPlayed=src.maxPlayed;
        }
        
        public int getNumOfPlayableDices()
        {
            int counter=0;
            for (int i = 0; i < dice.length; i++)
            {
                if(dice[i]>0);
                    counter++;
            }
            return counter;
        }

        
        
        
       
        
    }
}
