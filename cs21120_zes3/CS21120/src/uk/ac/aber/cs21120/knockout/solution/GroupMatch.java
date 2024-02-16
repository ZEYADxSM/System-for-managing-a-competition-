// GroupMatch.java
package uk.ac.aber.cs21120.knockout.solution;

import uk.ac.aber.cs21120.knockout.interfaces.IGroupMatch;
import uk.ac.aber.cs21120.knockout.interfaces.ITeam;

public class GroupMatch implements IGroupMatch {
    private final ITeam team1;
    private final ITeam team2;
    private int team1Score;
    private int team2Score;
    private boolean played;

    // Constructor
    public GroupMatch(ITeam team1, ITeam team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.played = false;
    }

    @Override
    public ITeam getTeam1() {
        return team1;
    }

    @Override
    public ITeam getTeam2() {
        return team2;
    }

    // Calculates points for the specified team in the match
    @Override
    public int getPoints(ITeam team) {
        if (!played) {
            return 0;
        }
        if (team.equals(team1)) {
            return getTeam1Points();
        } else if (team.equals(team2)) {
            return getTeam2Points();
        }
        return 0;
    }

    // Checks if the match has been played
    @Override
    public boolean isPlayed() {
        return played;
    }

    // Sets the scores for both teams in the match
    @Override
    public void setScore(int team1Score, int team2Score) {
        if (played) {
            throw new IllegalStateException("Match has already been played");
        }
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.played = true;
    }

    // Calculates points for team1 based on the match result
    @Override
    public int getTeam1Points() {
        if (!played) {
            throw new IllegalStateException("Match has not been played");
        }
        if (team1Score > team2Score) return 3;
        if (team1Score == team2Score) return 1;
        return 0;
    }

    // Calculates points for team2 based on the match result
    @Override
    public int getTeam2Points() {
        if (!played) {
            throw new IllegalStateException("Match has not been played");
        }
        if (team2Score > team1Score) {
            return 3;
        } else {
            if (team2Score == team1Score) return 1;
            return 0;
        }
    }
}


