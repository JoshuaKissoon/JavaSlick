package com.joshuakissoon.slick.util;

import java.text.DateFormat;
import java.util.Date;

/**
 * A utility class used for Date Utility Operations by Date Popups
 *
 * @author Joshua Kissoon
 * @since 20141215
 */
public class DatePopupUtil
{

    private static final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);

    /**
     * Takes in a date and gets a date string out of it
     *
     * @param date The input date
     *
     * @return String date in the format yyyy-mm-dd
     */
    public static String getDateString(final Date date)
    {
        String dateString = "";

        if (date != null)
        {
            dateString = dateFormat.format(date);
        }

        String[] unformatted = dateString.split("/");

        int day = Integer.valueOf(unformatted[1]);
        String dateDisplay = (day < 10) ? "0" + day : "" + day;

        int month = Integer.valueOf(unformatted[0]);
        String monthDisplay = (month < 10) ? "0" + month : "" + month;

        int year = Integer.valueOf(unformatted[2]);

        if (year > 50)
        {
            return "19" + unformatted[2] + "-" + monthDisplay + "-" + dateDisplay;
        }
        else
        {
            return "20" + unformatted[2] + "-" + monthDisplay + "-" + dateDisplay;
        }
    }

    /**
     * Takes in a date and gets a date string out of it
     *
     * @param date The input date
     *
     * @return String date in the format yyyy-mm
     */
    public static String getDateStringYearMonth(final Date date)
    {
        String dateString = "";

        if (date != null)
        {
            dateString = dateFormat.format(date);
        }

        String[] unformatted = dateString.split("/");

        int month = Integer.valueOf(unformatted[0]);
        String monthDisplay = (month < 10) ? "0" + month : "" + month;

        int year = Integer.valueOf(unformatted[2]);

        if (year > 50)
        {
            return "19" + unformatted[2] + "-" + monthDisplay;
        }
        else
        {
            return "20" + unformatted[2] + "-" + monthDisplay;
        }
    }
}
