package com.hussani.compression.fixedbits;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FixedBitsDecoderTest {

    @ParameterizedTest
    @MethodSource("inputOutputCompressProvider")
    void testCompress(final String input, final String output) {
        assertEquals(output, FixedBitsDecoder.compress(input));
    }

    @ParameterizedTest
    @MethodSource("inputOutputDecompressProvider")
    void testDecompress(final String input, final String output) {
        assertEquals(output, FixedBitsDecoder.decompress(input));
    }

    public static Stream<Arguments> inputOutputCompressProvider() {
        return Stream.of(
                Arguments.of("a", "00000"),
                Arguments.of("hi", "0011101000")
        );
    }

    public static Stream<Arguments> inputOutputDecompressProvider() {
        return Stream.of(
                Arguments.of("00000", "a"),
                Arguments.of("0011101000", "hi")
        );
    }
}