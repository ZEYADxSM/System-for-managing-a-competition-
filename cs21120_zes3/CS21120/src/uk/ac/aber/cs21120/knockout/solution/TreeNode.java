//TreeNode.java
package uk.ac.aber.cs21120.knockout.solution;

import uk.ac.aber.cs21120.knockout.interfaces.ITeam;
import uk.ac.aber.cs21120.knockout.interfaces.ITreeNode;

public class TreeNode implements ITreeNode {
    private ITeam team;
    private ITreeNode left;
    private ITreeNode right;
    private int score;

    public TreeNode(ITeam team, ITreeNode left, ITreeNode right) {
        this.team = team;
        this.left = left;
        this.right = right;
        this.score = 0;
    }

    @Override
    public ITreeNode getLeft() {
        return left;
    }

    @Override
    public ITreeNode getRight() {
        return right;
    }

    @Override
    public void setTeam(ITeam team) {
        this.team = team;
    }

    @Override
    public ITeam getTeam() {
        return team;
    }

    @Override
    public void setScore(int s) {
        this.score = s;
    }

    @Override
    public int getScore() {
        return score;
    }
}
