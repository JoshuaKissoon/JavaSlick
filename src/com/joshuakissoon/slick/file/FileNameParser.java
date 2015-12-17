package automngr.file;

/**
 * A class that parses file names
 *
 * @author Joshua Kissoon
 * @since 20141219
 */
public class FileNameParser
{

    /**
     * Method that cleans up a filename by removing:
     * - hyphens
     * - spaces
     * - tildes
     * - exclamation marks
     * - @ symbol
     * - Hash
     * - Dollar sign
     * - Percentage Sign
     * - Question Mark
     *
     * @param inputName The input file name
     *
     * @return String The cleaned filename
     *
     */
    public static String cleanName(final String inputName)
    {
        String cleanedName = new String(inputName);

        cleanedName = cleanedName.replace("-", "_");
        cleanedName = cleanedName.replace(" ", "_");
        cleanedName = cleanedName.replace("~", "_");
        cleanedName = cleanedName.replace("!", "_");
        cleanedName = cleanedName.replace("@", "_");
        cleanedName = cleanedName.replace("#", "_");
        cleanedName = cleanedName.replace("$", "_");
        cleanedName = cleanedName.replace("%", "_");
        cleanedName = cleanedName.replace("?", "_");

        return cleanedName;
    }

    public static String trimName(final String inputName, final int length)
    {
        if(inputName.length() <= length)
        {
            return inputName;
        }
        
        return inputName.substring(inputName.length() - length);
    }
}
