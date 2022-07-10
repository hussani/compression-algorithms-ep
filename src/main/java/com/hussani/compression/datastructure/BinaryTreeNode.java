package com.hussani.compression.datastructure;

public class BinaryTreeNode {

    final Character character;

    final int frequency;

    final BinaryTreeNode left;

    final BinaryTreeNode right;

    public BinaryTreeNode(Character character, int frequency, BinaryTreeNode left, BinaryTreeNode right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public Character getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }
}
