package com.hussani.compression.huffman;

import com.hussani.compression.Alphabet;
import com.hussani.compression.datastructure.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HuffmanDecoderTest {

    @Test
    void frequencyTest() {
        final int[] frequency = new int[Alphabet.ALPHABET_SIZE];

        frequency[Alphabet.charToInt("h".charAt(0))] = 1;
        frequency[Alphabet.charToInt("i".charAt(0))] = 1;

        assertArrayEquals(frequency, HuffmanDecoder.frequency("hi"));
    }

    @Test
    void frequencyHelloTest() {
        final int[] frequency = new int[Alphabet.ALPHABET_SIZE];

        frequency[Alphabet.charToInt("h".charAt(0))] = 1;
        frequency[Alphabet.charToInt("e".charAt(0))] = 1;
        frequency[Alphabet.charToInt("l".charAt(0))] = 2;
        frequency[Alphabet.charToInt("o".charAt(0))] = 1;

        assertArrayEquals(frequency, HuffmanDecoder.frequency("hello"));
    }

    @Test
    void buildAraraquaraTreeTest() {
        final int[] frequency = HuffmanDecoder.frequency("araraquara");


        final BinaryTreeNode partialExpectedResult = new BinaryTreeNode(null,
                10,
                new BinaryTreeNode(null, 5, null, null),
                new BinaryTreeNode("a".charAt(0), 5, null, null)
        );

        final BinaryTreeNode result = HuffmanDecoder.buildTree(frequency);

        assertEquals(partialExpectedResult.getFrequency(), result.getFrequency());
        assertEquals(partialExpectedResult.getRight().getFrequency(), result.getRight().getFrequency());
        assertEquals(partialExpectedResult.getLeft().getFrequency(), result.getLeft().getFrequency());
        assertEquals(partialExpectedResult.getLeft().getCharacter(), result.getLeft().getCharacter());
        assertEquals(partialExpectedResult.getRight().getCharacter(), result.getRight().getCharacter());
    }

    @Test
    void buildAraraquaraMappingArrayTest() {
        String input = "araraquara";
        final int[] frequency = HuffmanDecoder.frequency(input);
        final BinaryTreeNode tree = HuffmanDecoder.buildTree(frequency);
        final String[] result = HuffmanDecoder.buildCodeMap(tree, input.length());

        assertEquals("1", result[0]);
        assertEquals("001", result[16]);
        assertEquals("01", result[17]);
        assertEquals("000", result[20]);
    }

    @Test
    void buildAraraquaraTreeCodeTest() {
        String input = "araraquara";
        final int[] frequency = HuffmanDecoder.frequency(input);
        final BinaryTreeNode tree = HuffmanDecoder.buildTree(frequency);
        final String[] binaryMap = HuffmanDecoder.buildCodeMap(tree, input.length());
        final String result = HuffmanDecoder.buildTreeString(tree);

        assertEquals("000110100110000110001100000", result);
    }

    @Test
    void buildCodeAraraquaraTest() {
        String input = "araraquara";
        final int[] frequency = HuffmanDecoder.frequency(input);
        final BinaryTreeNode tree = HuffmanDecoder.buildTree(frequency);
        final String[] binaryMap = HuffmanDecoder.buildCodeMap(tree, input.length());
        String result = HuffmanDecoder.buildCodeString(input, binaryMap);
        assertEquals("10110110010001011", result);
    }

    @Test
    void compressAraraquaraTest() {
        String input = "araraquara";
        String expected = "00011010011000011000110000010110110010001011";
        String result = new HuffmanDecoder().compress(input);
        assertEquals(expected, result);
    }

    @Test
    void expandTreeTest() {
        String treeCodedString = "000110100110000110001100000";
        BinaryTreeNode resultNode = HuffmanDecoder.readCodedTree(treeCodedString, new int[]{0});
        assertEquals("a".charAt(0), resultNode.getRight().getCharacter());
        assertEquals("u".charAt(0), resultNode.getLeft().getLeft().getLeft().getCharacter());
    }

    @Test
    void decompressAraraquaraTest() {
        String expected = "araraquara";
        String input = "00011010011000011000110000010110110010001011";
        String result = new HuffmanDecoder().decompress(input);
        assertEquals(expected, result);
    }
}