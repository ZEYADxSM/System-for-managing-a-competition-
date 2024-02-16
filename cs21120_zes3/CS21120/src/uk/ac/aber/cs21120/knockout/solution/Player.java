//Player.java
package uk.ac.aber.cs21120.knockout.solution;

import uk.ac.aber.cs21120.knockout.interfaces.IPlayer;

public class Player implements IPlayer {
    private String name;
    private int position;

    public Player(String name, int position) {
        if (position < 1) {
            throw new RuntimeException("Position must be greater than or equal to 1.");
        }
        this.name = name;
        this.position = position;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPosition() {
        return position;
    }
}