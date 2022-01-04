package com.logic.bankwiser.utils;

import java.util.Random;

public class MathUtils {

    /**
     * Accepts a comma delimited string of integers in order to guarantee a unique number.
     * Because of the nature of the ids, and how the bank application operates, even with this
     * amount of ids being available, it is very unlikely a match will be made.
     *
     * @param match a comma delimited string of ids
     * @return a guaranteed unique id
     */
    public static String generateUniqueID(String match) {
        int uniqueID;

        do {
            int min = 10000000;
            int max = 99999999;
            Random rand = new Random();
            uniqueID = rand.nextInt((max - min) + 1) + min;
        } while (match.contains(String.valueOf(uniqueID)));
        return String.valueOf(uniqueID);
    }
}
