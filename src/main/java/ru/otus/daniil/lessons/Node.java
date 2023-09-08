package ru.otus.daniil.lessons;

public class Node implements TreeNode {

    private TreeNode left;
    private TreeNode right;
    private final int weight;

    public Node(int weight) {
        this.weight = weight;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getWeight() {
        return weight;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }
}
