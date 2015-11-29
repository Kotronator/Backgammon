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
public class Move
{
    LinkedList<SingleMove> moveOrder= new LinkedList<>();
    double value;

    public Move(LinkedList<SingleMove> moveOrder, double value)
    {
        this.value=value;
        if (moveOrder!=null)
        {
            this.moveOrder=new LinkedList<>(moveOrder);
        }
         this.moveOrder=new LinkedList<>();
    }
     
}
