// Group.java
package uk.ac.aber.cs21120.knockout.solution;

import uk.ac.aber.cs21120.knockout.interfaces.IGroup;
import uk.ac.aber.cs21120.knockout.interfaces.IGroupMatch;
import uk.ac.aber.cs21120.knockout.interfaces.ITeam;

import java.util.ArrayList;
import java.util.List;

public class Group implements IGroup {
    private ITeam[] teams;
    private List<IGroupMatch> matches;

    // Constructor
    public Group(ITeam[] teams) {
        // Validate that the group has exactly 4 teams
        if (teams.length != 4) {
            throw new IllegalArgumentException("A group must have exactly 4 teams.");
        }

        this.teams = teams;
        this.matches = new ArrayList<>();

        // Initialize matches for the group
        initializeMatches();
    }

    // initialize matches for the group
    private void initializeMatches() {
        for (int i = 0; i < teams.length; i++) {
            for (int j = i + 1; j < teams.length; j++) {
                IGroupMatch match = new GroupMatch(teams[i], teams[j]);
                matches.add(match);
            }
        }
    }

    // Get the next match that has not been played
    @Override
    public IGroupMatch getNextMatch() {
        for (IGroupMatch match : matches) {
            if (!match.isPlayed()) {
                return match;
            }
        }
        return null;
    }

    // Get the total points of a specific team in the group
    @Override
    public int getPoints(ITeam team) {
        int points = 0;
        for (IGroupMatch match : matches) {
            if (match.isPlayed()) {
                if (match.getTeam1().equals(team)) {
                    points += match.getTeam1Points();
                } else if (match.getTeam2().equals(team)) {
                    points += match.getTeam2Points();
                }
            }
        }
        return points;
    }

    // Get the table of teams in the group based on points (descending order)
    @Override
    public ITeam[] getTable() {
        if (matches.stream().anyMatch(match -> !match.isPlayed())) {
            throw new IllegalStateException("Not all matches have been played.");
        }

        // Sorting teams based on points (descending order)
        ITeam[] sortedTeams = teams.clone();
        for (int i = 0; i < sortedTeams.length - 1; i++) {
            for (int j = 0; j < sortedTeams.length - i - 1; j++) {
                if (getPoints(sortedTeams[j]) < getPoints(sortedTeams[j + 1])) {
                    // Swap teams
                    ITeam temp = sortedTeams[j];
                    sortedTeams[j] = sortedTeams[j + 1];
                    sortedTeams[j + 1] = temp;
                }
            }
        }

        return sortedTeams;
    }
}








