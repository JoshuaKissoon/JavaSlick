package com.joshuakissoon.slick.numbers;

/**
 * Helper class with number functions
 *
 * @author Joshua
 * @since 20150101
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

    public static boolean isInteger(String s)
    {
        /* Tests whether the given value is an integer */
        try
        {
            Integer.parseInt(s);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static boolean isFloat(String s)
    {
        /* Tests whether the given value is an integer */
        try
        {
            Float.parseFloat(s);
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static boolean isNumberic(String s)
    {
        return isFloat(s) || isInteger(s);
    }
}
