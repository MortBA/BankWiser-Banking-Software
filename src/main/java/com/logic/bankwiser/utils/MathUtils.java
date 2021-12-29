package com.logic.bankwiser.utils;

import java.util.Random;

public class MathUtils {

    public static String generateUniqueID(String match) {
        int uniqueID;
        do {
            int min = 100000;
            int max = 999999;
            Random rand = new Random();
            uniqueID = rand.nextInt((max - min) + 1) + min;
        } while (match.contains(String.valueOf(uniqueID)));
        return String.valueOf(uniqueID);
    }
}
