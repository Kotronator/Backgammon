/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon;

/**
 *
 * @author tsipiripo
 */
public class Player
{
    private static int ID_COUNTER=0;
    
    public String name;
    private int id;

    public Player(String name)
    {
        this.name=name;
        this.id=ID_COUNTER++;
        
    }
    
    public int getId()
    {
        return id;
    }
    
    public int getNextId()
    {
        return (id+1)%ID_COUNTER;
    }
}
