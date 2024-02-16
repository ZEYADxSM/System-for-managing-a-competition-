//Team.java
package uk.ac.aber.cs21120.knockout.solution;

import uk.ac.aber.cs21120.knockout.interfaces.IPlayer;
import uk.ac.aber.cs21120.knockout.interfaces.ITeam;

import java.util.ArrayList;
import java.util.List;

public class Team implements ITeam {
    private String name;
    private List<IPlayer> players;



    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    @Override
    public void addPlayer(IPlayer p) {
        boolean samePos = false;
       if(players.contains(p)){
           System.out.println("Player already in the team");
       }
       else {
        for (IPlayer player : players){
            if (player.getPosition() == p.getPosition()){
                samePos = true;
            }
        }
        if (!samePos){
            players.add(p);
        }
       }

    }

    @Override
    public IPlayer getPlayerInPosition(int position) {

        for (IPlayer player : players) {
            if (player.getPosition() == position) {
                return player;
            }
        }
        return null;
    }

    @Override
    public List<IPlayer> getPlayers() {
        return new ArrayList<>(players); // Return a copy to prevent external modifications
    }

    @Override
    public String getName() {
        return name;
    }
}