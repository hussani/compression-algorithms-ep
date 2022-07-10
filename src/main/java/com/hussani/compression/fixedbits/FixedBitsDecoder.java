package com.hussani.compression.fixedbits;

import java.util.AbstractMap;
import java.util.Map;

public class FixedBitsDecoder {

    private static final Map<Character, String> charToBinaryMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("a".charAt(0), "00000"),
            new AbstractMap.SimpleEntry<>("b".charAt(0), "00001"),
            new AbstractMap.SimpleEntry<>("c".charAt(0), "00010"),
            new AbstractMap.SimpleEntry<>("d".charAt(0), "00011"),
            new AbstractMap.SimpleEntry<>("e".charAt(0), "00100"),
            new AbstractMap.SimpleEntry<>("f".charAt(0), "00101"),
            new AbstractMap.SimpleEntry<>("g".charAt(0), "00110"),
            new AbstractMap.SimpleEntry<>("h".charAt(0), "00111"),
            new AbstractMap.SimpleEntry<>("i".charAt(0), "01000"),
            new AbstractMap.SimpleEntry<>("j".charAt(0), "01001"),
            new AbstractMap.SimpleEntry<>("k".charAt(0), "01010"),
            new AbstractMap.SimpleEntry<>("l".charAt(0), "01011"),
            new AbstractMap.SimpleEntry<>("m".charAt(0), "01100"),
            new AbstractMap.SimpleEntry<>("n".charAt(0), "01101"),
            new AbstractMap.SimpleEntry<>("o".charAt(0), "01110"),
            new AbstractMap.SimpleEntry<>("p".charAt(0), "01111"),
            new AbstractMap.SimpleEntry<>("q".charAt(0), "10000"),
            new AbstractMap.SimpleEntry<>("r".charAt(0), "10001"),
            new AbstractMap.SimpleEntry<>("s".charAt(0), "10010"),
            new AbstractMap.SimpleEntry<>("t".charAt(0), "10011"),
            new AbstractMap.SimpleEntry<>("u".charAt(0), "10100"),
            new AbstractMap.SimpleEntry<>("v".charAt(0), "10101"),
            new AbstractMap.SimpleEntry<>("w".charAt(0), "10110"),
            new AbstractMap.SimpleEntry<>("x".charAt(0), "10111"),
            new AbstractMap.SimpleEntry<>("y".charAt(0), "11000"),
            new AbstractMap.SimpleEntry<>("z".charAt(0), "11001"),
            new AbstractMap.SimpleEntry<>(",".charAt(0), "11010"),
            new AbstractMap.SimpleEntry<>(".".charAt(0), "11011"),
            new AbstractMap.SimpleEntry<>("!".charAt(0), "11100"),
            new AbstractMap.SimpleEntry<>("?".charAt(0), "11101"),
            new AbstractMap.SimpleEntry<>(" ".charAt(0), "11110"),
            new AbstractMap.SimpleEntry<>(":".charAt(0), "11111")
    );
    private static final Map<String, Character> binaryToCharMap = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("00000", "a".charAt(0)),
            new AbstractMap.SimpleEntry<>("00001", "b".charAt(0)),
            new AbstractMap.SimpleEntry<>("00010", "c".charAt(0)),
            new AbstractMap.SimpleEntry<>("00011", "d".charAt(0)),
            new AbstractMap.SimpleEntry<>("00100", "e".charAt(0)),
            new AbstractMap.SimpleEntry<>("00101", "f".charAt(0)),
            new AbstractMap.SimpleEntry<>("00110", "g".charAt(0)),
            new AbstractMap.SimpleEntry<>("00111", "h".charAt(0)),
            new AbstractMap.SimpleEntry<>("01000", "i".charAt(0)),
            new AbstractMap.SimpleEntry<>("01001", "j".charAt(0)),
            new AbstractMap.SimpleEntry<>("01010", "k".charAt(0)),
            new AbstractMap.SimpleEntry<>("01011", "l".charAt(0)),
            new AbstractMap.SimpleEntry<>("01100", "m".charAt(0)),
            new AbstractMap.SimpleEntry<>("01101", "n".charAt(0)),
            new AbstractMap.SimpleEntry<>("01110", "o".charAt(0)),
            new AbstractMap.SimpleEntry<>("01111", "p".charAt(0)),
            new AbstractMap.SimpleEntry<>("10000", "q".charAt(0)),
            new AbstractMap.SimpleEntry<>("10001", "r".charAt(0)),
            new AbstractMap.SimpleEntry<>("10010", "s".charAt(0)),
            new AbstractMap.SimpleEntry<>("10011", "t".charAt(0)),
            new AbstractMap.SimpleEntry<>("10100", "u".charAt(0)),
            new AbstractMap.SimpleEntry<>("10101", "v".charAt(0)),
            new AbstractMap.SimpleEntry<>("10110", "w".charAt(0)),
            new AbstractMap.SimpleEntry<>("10111", "x".charAt(0)),
            new AbstractMap.SimpleEntry<>("11000", "y".charAt(0)),
            new AbstractMap.SimpleEntry<>("11001", "z".charAt(0)),
            new AbstractMap.SimpleEntry<>("11010", ",".charAt(0)),
            new AbstractMap.SimpleEntry<>("11011", ".".charAt(0)),
            new AbstractMap.SimpleEntry<>("11100", "!".charAt(0)),
            new AbstractMap.SimpleEntry<>("11101", "?".charAt(0)),
            new AbstractMap.SimpleEntry<>("11110", " ".charAt(0)),
            new AbstractMap.SimpleEntry<>("11111", ":".charAt(0))
    );
    private static int NUMBER_OF_BITS = 5;

    public static String compress(final String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output = output + charToBinaryMap.get(input.charAt(i));
        }
        return output;
    }

    public static String decompress(final String input) {
        String output = "";
        String buffer = "";
        for (int i = 0; i < input.length(); i++) {
            buffer = buffer + input.charAt(i);
            if ((i + 1) % NUMBER_OF_BITS != 0) { // keep buffering if not read bites size
                continue;
            }

            output = output + binaryToCharMap.get(buffer);
            buffer = "";
        }
        return output;
    }
}
