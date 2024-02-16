// MatchTree.java
package uk.ac.aber.cs21120.knockout.solution;

import uk.ac.aber.cs21120.knockout.interfaces.IMatchTree;
import uk.ac.aber.cs21120.knockout.interfaces.ITeam;
import uk.ac.aber.cs21120.knockout.interfaces.ITreeNode;

public class MatchTree implements IMatchTree {
    private ITreeNode root;
    private ITreeNode nextMatch;

    // Constructor
    public MatchTree(ITeam[] teams) {
        // Create the tree based on the provided teams
        this.root = buildTree(teams, 0, teams.length - 1);
        this.nextMatch = findNextMatch(root);
    }

    // recursively build the tree
    private ITreeNode buildTree(ITeam[] teams, int start, int end) {
        if (start > end) {
            return null; // No teams left
        }
        if (start == end) {
            return new TreeNode(teams[start], null, null); // Single team, create a leaf node
        }
        // More than one team, create a match node
        ITreeNode left = buildTree(teams, start, (start + end) / 2);
        ITreeNode right = buildTree(teams, (start + end) / 2 + 1, end);
        return new TreeNode(null, left, right);
    }

    @Override
    public ITreeNode getRoot() {
        return root;
    }

    @Override
    public ITreeNode getNextMatch() {
        return nextMatch;
    }

    //find the next match node
    private ITreeNode findNextMatch(ITreeNode node) {
        if (node == null) {
            return null;
        }

        ITreeNode leftChild = node.getLeft();
        ITreeNode rightChild = node.getRight();

        if (node.getTeam()==null && leftChild !=null && rightChild != null && leftChild.getTeam() !=null && rightChild.getTeam() != null ){
            nextMatch = node;
            return nextMatch;
        }

        ITreeNode leftSubTree = findNextMatch(leftChild);
        ITreeNode rightSubTree = findNextMatch(rightChild);

        if(getDepth(leftSubTree, root, 0) > getDepth(rightSubTree, root, 0)){
            nextMatch = leftSubTree;
            return nextMatch;
        }
        else {
            nextMatch = rightSubTree;
            return nextMatch;
        }
    }

    // calculate the depth of a node in the tree
    private int getDepth(ITreeNode n, ITreeNode current, int depth){
        if (current == n)return depth;
        if (current.getLeft() != null){
            int d = getDepth(n, current.getLeft(), depth+1);
            if (d != -1)return d;
        }
        if (current.getRight() != null){
            int d = getDepth(n, current.getRight(), depth+1);
            if (d != -1)return d;
        }
        return -1;
    }

    @Override
    public void setScore(int leftScore, int rightScore) {
        if (nextMatch == null) {
            throw new RuntimeException("No matches to be played.");
        }
        ITreeNode leftNode = nextMatch.getLeft();
        ITreeNode rightNode = nextMatch.getRight();

        // Check for null pointers before accessing child nodes
        if (leftNode != null && rightNode != null) {
            leftNode.setScore(leftScore);
            rightNode.setScore(rightScore);
        }

        // Set the team for the next match based on the scores
        if (leftScore > rightScore) {
            nextMatch.setTeam(leftNode.getTeam());
        }
        else {
            nextMatch.setTeam(rightNode.getTeam());
        }
        nextMatch = findNextMatch(root);
    }
}








