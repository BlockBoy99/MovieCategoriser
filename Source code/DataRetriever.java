package MediaTrackerPackage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DataRetriever {
    public static String getAllData(){
        String out ="";
        try {


            File file = new File(System.getProperty("user.home") + "\\Desktop\\MediaDatabaseForMediaIsDatabase.txt");

            if (file.createNewFile()) {
                System.out.println("this file is new - there wont be anything in it...");
            } else {

                //we have found our file.
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    out += data + "\n";
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return out;
    }
}
