//Main.java
package uk.ac.aber.cs21120.knockout.solution;

import uk.ac.aber.cs21120.knockout.interfaces.*;
import uk.ac.aber.cs21120.knockout.tests.TreePrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner (System.in);

    // determine group finalists
    private static List<ITeam> getGroupFinalists(IGroup group) {
        List<ITeam> groupFinalists = new ArrayList<>();

        // Assuming there is a method to get the table of teams in the group
        ITeam[] groupTable = group.getTable();

        // Assuming the table is sorted by points in descending order
        groupFinalists.add(groupTable[0]);
        groupFinalists.add(groupTable[1]);

        return groupFinalists;
    }
    public static void main(String[] args) {
        // Creating an array of teams
        ITeam[] teams = {
                new Team("Team1"),
                new Team("Team2"),
                new Team("Team3"),
                new Team("Team4"),
                new Team("Team5"),
                new Team("Team6"),
                new Team("Team7"),
                new Team("Team8"),
                new Team("Team9"),
                new Team("Team10"),
                new Team("Team11"),
                new Team("Team12"),
                new Team("Team13"),
                new Team("Team14"),
                new Team("Team15"),
                new Team("Team16")
        };

        // Split teams into groups
        List<ITeam> teamList = Arrays.asList(teams);
        // Shuffle teams randomly
        Collections.shuffle(teamList);

        // Split teams into four groups
        List<List<ITeam>> groupedTeams = new ArrayList<>();
        int groupSize = teamList.size() / 4;
        for (int i = 0; i < teamList.size(); i += groupSize) {
            groupedTeams.add(teamList.subList(i, Math.min(i + groupSize, teamList.size())));
        }

        // Create Group instances
        List<IGroup> groupStageGroups = new ArrayList<>();
        for (List<ITeam> groupTeams : groupedTeams) {
            groupStageGroups.add(new Group(groupTeams.toArray(new ITeam[0])));
        }

        // Play matches in each group
        for (IGroup group : groupStageGroups) {
            IGroupMatch nextMatch = group.getNextMatch();
            while ((nextMatch != null)) {
                System.out.print("Enter score for " + nextMatch.getTeam1().getName() + ": ");
                int leftScore = scanner.nextInt();

                System.out.print("Enter score for " + nextMatch.getTeam2().getName() + ": ");
                int rightScore = scanner.nextInt();

                nextMatch.setScore(leftScore, rightScore);
                nextMatch = group.getNextMatch();
            }
        }

        // Determine finalists from each group
        List<ITeam> finalists = new ArrayList<>();
        for (IGroup group : groupStageGroups) {
            List<ITeam> groupFinalists = getGroupFinalists(group);
            finalists.addAll(groupFinalists);
        }

        // Initialize the knockout stage
        IMatchTree knockoutStage = new MatchTree(finalists.toArray(new ITeam[0]));

        // Main knockout stage loop
        while (true) {
            ITreeNode nextMatch = knockoutStage.getNextMatch();
            if (nextMatch == null) {
                System.out.println("All matches played. Game over!");
                break;
            }

            System.out.println("Next match: " + nextMatch.getLeft().getTeam().getName() +
                    " vs " + nextMatch.getRight().getTeam().getName());

            System.out.print("Enter score for " + nextMatch.getLeft().getTeam().getName() + ": ");
            int leftScore = scanner.nextInt();

            System.out.print("Enter score for " + nextMatch.getRight().getTeam().getName() + ": ");
            int rightScore = scanner.nextInt();

            // Set the scores for the current match
            knockoutStage.setScore(leftScore, rightScore);

            // Print the current state of the tree
            TreePrinter.print(knockoutStage);
        }

        scanner.close();
    }
}




