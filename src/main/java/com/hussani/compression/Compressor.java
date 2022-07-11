package com.hussani.compression;

public interface Compressor {
    String decompress(String input);

    String compress(String input);
}
