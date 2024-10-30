package com.g07;

import java.io.File;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No filename provided");
            printUsage();
            return;
        }
        if (args.length < 2) {
            System.out.println("No algorithms provided");
            printUsage();
            return;
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("No such file exists");
            return;
        }

        for (int i = 1; i < args.length; i++) {
            try {
                String hash = HashTools.encrypt(file, args[i]);
                System.out.println(args[i] + " = " + hash);
            }
            catch (NoSuchAlgorithmException e) {
                System.out.println("No such algorithm as " + args[i]);
            }
        }
    }

    private static void printUsage() {
        System.out.println("\nUsage:\n\tfilehash <filename> [algorithms...]");
    }
}