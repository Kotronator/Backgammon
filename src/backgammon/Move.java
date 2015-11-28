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
    int value;

    public Move(LinkedList<SingleMove> moveOrder, int value)
    {
        this.value=value;
        this.moveOrder=new LinkedList<>(moveOrder);
    }
     
}
