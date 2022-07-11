package com.hussani.compression.fixedbits;

import com.hussani.compression.Alphabet;
import com.hussani.compression.Compressor;

public class FixedBitsDecoder implements Compressor {

    public String compress(final String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            output = output + Alphabet.CHAR_TO_BINARY_MAP.get(input.charAt(i));
        }
        return output;
    }

    public String decompress(final String input) {
        String output = "";
        String buffer = "";
        for (int i = 0; i < input.length(); i++) {
            buffer = buffer + input.charAt(i);
            if ((i + 1) % Alphabet.NUMBER_OF_BITS != 0) { // keep buffering if not read bites size
                continue;
            }

            output = output + Alphabet.BINARY_TO_CHAR_MAP.get(buffer);
            buffer = "";
        }
        return output;
    }
}
