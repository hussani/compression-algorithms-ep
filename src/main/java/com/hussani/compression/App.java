package com.hussani.compression;

import com.hussani.compression.fixedbits.FixedBitsDecoder;
import com.hussani.compression.huffman.HuffmanDecoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class App 
{
    final static List<String> algorithms = List.of("5bits", "huffman");
    final static List<String> methods = List.of("compression", "decompression");

    static Compressor compressor;
    public static void main( String[] args )
    {
        boolean userError = false;
        if (args.length < 2) {
            printUsage();
            System.exit(1);
        }

        if (!algorithms.contains(args[0])) {
            System.out.println("You must to choose 5bits or huffman compreesion");
            printUsage();
            System.exit(1);
        }

        if (!methods.contains(args[1])) {
            System.out.println("You must to choose to compreess ou to decompress");
            printUsage();
            System.exit(1);
        }

        if (args[0].equals("huffman")) {
            compressor = new HuffmanDecoder();
        } else {
            compressor = new FixedBitsDecoder();
        }


        String input = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while (true) {
                if ((line = reader.readLine()) != null) {
                    input += line;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        String output;

        if (args.length > 2 && args[2].equals("-n")) {
            input = input.toLowerCase();
        }

        if (args[1].equals("compression")) {
            output = compressor.compress(input);
        } else {
            output = compressor.decompress(input);
        }

        System.out.println(output);
    }

    private static void printUsage() {
        System.out.println("Usage");
        System.out.println("The program reads the input of sdtin. Examples:");
        System.out.println("echo \"text input\" | java App <5bits|huffman> <compression|decompression>");
        System.out.println("Or");
        System.out.println("java App <5bits|huffman> <compression|decompression> < inputfile.txt");
    }
}
