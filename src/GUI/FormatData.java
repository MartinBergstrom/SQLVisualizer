package GUI;

/**
 * Created by Martin on 2017-04-12.
 *
 * Class to help with the formatting of the data, in our case we could've simply sent the string as a vector directly form the controller
 * but this could potentially be useful in the future
 */
public class FormatData {
    /**
     * Formats the string sent from our database to fit our GUI representation
     *
     * @param s the string to format
     * @return the formatted string in a row format
     */
    public static Object[] getFormattedData(String s){
        String[] data = s.split(", ");
        return data;
    }

    public static Object[] getFormattedData(int id, String firstname, String surname, String city, int age){
        String[] data = {id+"",firstname,surname,city,age+""};
        return data;
    }
}
