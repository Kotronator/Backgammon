/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon;

import java.util.ArrayList;

/**
 *
 * @author TOSHIBA
 */
public class PlayerController {
    
    static ArrayList<Player> players = new ArrayList(2);
    
    public static void addPlayer(String name)
    {
        Player p = new Player(name);
        //debug
        //System.out.println(p.getId());
        players.add(p);
    }
    
    public static Player getPlayerWithId(int id)
    {
        for (Player player : players) {
            if(player.getId()==id)
                return player;
        }
        throw  new UnsupportedOperationException("den uparxei tetoios paukths");
          
    }
    
}
