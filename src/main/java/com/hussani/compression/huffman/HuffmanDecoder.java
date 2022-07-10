package com.hussani.compression.huffman;

import com.hussani.compression.Alphabet;
import com.hussani.compression.datastructure.BinaryTreeNode;

import java.util.PriorityQueue;

public class HuffmanDecoder {

    static int[] frequency(final String input) {
        int[] frequency = new int[Alphabet.ALPHABET_SIZE];

        for (int i = 0; i < input.length(); i++) {
            frequency[Alphabet.charToInt(input.charAt(i))]++;
        }

        return frequency;
    }

    static BinaryTreeNode buildTree(final int[] frequency) {
        final PriorityQueue<BinaryTreeNode> priorityQueue = new PriorityQueue<>((node1, node2) -> {
            if (node1.getFrequency() > node2.getFrequency()) {
                return 1;
            }
            return -1;
        });

        for (int i = 0; i < Alphabet.ALPHABET_SIZE; i++) {
            if (frequency[i] > 0) {
                BinaryTreeNode node = new BinaryTreeNode(Alphabet.intToChar(i), frequency[i], null, null);
                priorityQueue.add(node);
            }
        }

        while (priorityQueue.size() > 1) {
            BinaryTreeNode left = priorityQueue.poll();
            BinaryTreeNode right = priorityQueue.poll();
            BinaryTreeNode node = new BinaryTreeNode(null, left.getFrequency() + right.getFrequency(), left, right);
            priorityQueue.add(node);
        }

        return priorityQueue.poll();
    }

    static String[] buildCodeMap(BinaryTreeNode root, Integer inputLengt) {
        String[] input = new String[Alphabet.ALPHABET_SIZE];
        buildCodeRec(input, root, "");
        return input;

    }

    private static void buildCodeRec(String[] input, BinaryTreeNode root, String code) {
        if (root.getLeft() == null && root.getRight() == null) {
            input[Alphabet.charToInt(root.getCharacter())] = code;
            return;
        }
        buildCodeRec(input, root.getLeft(), code + "0");
        buildCodeRec(input, root.getRight(), code + "1");
    }

    static String buildTreeString(BinaryTreeNode root) {
        StringBuilder output = new StringBuilder();
        buildTreeStringRec(root, output);

        return output.toString();
    }

    private static void buildTreeStringRec(BinaryTreeNode root, StringBuilder output) {
        if (root.getLeft() == null && root.getLeft() == null) {
            output.append("1");
            output.append(Alphabet.CHAR_TO_BINARY_MAP.get(root.getCharacter()));
            return;
        }
        output.append("0");
        buildTreeStringRec(root.getLeft(), output);
        buildTreeStringRec(root.getRight(), output);
    }

    static String buildCodeString(String input, String[] codeMap) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String code = codeMap[Alphabet.charToInt(input.charAt(i))];
            output.append(code);
        }
        return output.toString();
    }

    public static String compreess(final String input) {
        final int[] frequency = frequency(input);
        final BinaryTreeNode tree = buildTree(frequency);
        final String[] binaryMap = buildCodeMap(tree, input.length());

        return buildTreeString(tree) + buildCodeString(input, binaryMap);
    }

    static BinaryTreeNode readCodedTree(String codedTree, int[] position) {
        if (codedTree.charAt(position[0]) == "1".charAt(0)) {
            position[0]++;
            Character character = getCharacter(codedTree, position);
            position[0] += Alphabet.NUMBER_OF_BITS;
            return new BinaryTreeNode(character, 0, null, null);
        }
        position[0]++;

        final BinaryTreeNode left = readCodedTree(codedTree, position);
        final BinaryTreeNode right = readCodedTree(codedTree, position);

        return new BinaryTreeNode(null, 0, left, right);
    }

    static Character getCharacter(String codedTree, int[] position) {
        String buffer = "";
        for (int i = 0; i < Alphabet.NUMBER_OF_BITS; i++) {
            buffer = buffer + codedTree.charAt(position[0]+i);
        }
        return Alphabet.BINARY_TO_CHAR_MAP.get(buffer);
    }

    public static String decompress(final String input) {
        int[] position = new int[]{0}; // hack to share position between scopes

        final BinaryTreeNode root = readCodedTree(input, position);

        StringBuilder output = new StringBuilder();

        while (position[0] < input.length()) {
            BinaryTreeNode node = root;
            while (node.getLeft() != null) {
                if (input.charAt(position[0]) == "0".charAt(0)) {
                    node = node.getLeft();
                } else {
                    node = node.getRight();
                }
                position[0]++;
            }
            output.append(node.getCharacter());
        }
        return output.toString();
    }
}
