package com.joshuakissoon.slick.numbers;

/**
 * Helper class with number functions
 *
 * @author Joshua
 * @since
 */
public class NumberHelper
{

    /**
     * Return ordinal suffix (e.g. 'st', 'nd', 'rd', or 'th') for a given number
     *
     * @param value a number
     *
     * @return Ordinal suffix for the given number
     */
    public static String getOrdinalSuffix(int value)
    {
        int hunRem = value % 100;
        int tenRem = value % 10;

        if (hunRem - tenRem == 10)
        {
            return "th";
        }
        switch (tenRem)
        {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
}
