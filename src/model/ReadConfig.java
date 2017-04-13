package model;

import java.io.*;
import org.json.*;

/**
 * Class to read and parse the JSON config file
 *
 * Created by Martin on 2017-04-11.
 */
public class ReadConfig {
    private static final String configPath = "src/config.txt";
    private StringBuilder sb;
    private String configString;

    public ReadConfig() {
        try{
            BufferedReader reader = new BufferedReader(
                    new FileReader(
                            new File(configPath)));
            sb = new StringBuilder();

            String line;
            while( (line = reader.readLine())!= null){
                sb.append(line);
            }
            configString = sb.toString();
            parseJSON();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseJSON(){
        JSONObject obj = new JSONObject(configString);

        System.out.println("Name is:" + obj.getString("name"));

    }
}
