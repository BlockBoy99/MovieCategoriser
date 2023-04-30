package MediaTrackerPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class DataSaver {
    public static void editFile(String stuffToAdd) {
        try {

            File file = new File(System.getProperty("user.home") + "\\Desktop\\MediaDatabaseForMediaIsDatabase.txt");

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("The File exists and so changing to have " + stuffToAdd.substring(0,stuffToAdd.indexOf('_')));
                //


            }
            FileWriter fw = new FileWriter(System.getProperty("user.home") + "\\Desktop\\"+file.getName(),true);
            BufferedWriter bw;
            bw = new BufferedWriter(fw);
            //then we need to read from the file, see if the movie name already exists, and find the position
            String currentContents = DataRetriever.getAllData();
            if(currentContents.contains(stuffToAdd.substring(0,stuffToAdd.indexOf('_')))){
                System.out.println("already exists");
                int placeToDelete = currentContents.lastIndexOf(stuffToAdd.substring(0,stuffToAdd.indexOf('_')));
           //    "clash detected"
                //delete that line

                currentContents.replace(stuffToAdd.substring(0,stuffToAdd.indexOf('_')), "(used)");

                clearFile();
                bw.append(currentContents);
                bw.newLine();
            }
            else{

                bw.append(stuffToAdd);

                bw.newLine();
                bw.close();

            }



        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    // Title,Length,currentWatchTime,completionStatus,favourite,colourTag
    public static void makeNewFile() {
        try {
            File file = new File(System.getProperty("user.home") + "\\Desktop\\MediaDatabaseForMediaIsDatabase.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public static void clearFile(){
        File file = new File(System.getProperty("user.home") + "\\Desktop\\MediaDatabaseForMediaIsDatabase.txt");
        file.delete();
    }
    public static void storeMovie(Movie movie){
        String out = "";
        out += movie.title + "_";
        out += movie.length + "_";
        out += movie.currentTime + "_";
        out += movie.completionStatus + "_";
        out += movie.favourite + "_";
        out += movie.colourTag;
        editFile(out);
    }

    public static void editMovie(Movie movie) throws IOException {
        String out = "";
        out += movie.title + "_";
        out += movie.length + "_";
        out += movie.currentTime + "_";
        out += movie.completionStatus + "_";
        out += movie.favourite + "_";
        out += movie.colourTag;
        try {


                File file = new File(System.getProperty("user.home") + "\\Desktop\\MediaDatabaseForMediaIsDatabase.txt");
        FileWriter fw = new FileWriter(System.getProperty("user.home") + "\\Desktop\\" + file.getName(), true); //why tf are you bugging

        BufferedWriter bw;
        bw = new BufferedWriter(fw);
        //then we need to read from the file, see if the movie name already exists, and find the position
        String currentContents = DataRetriever.getAllData();
        if (currentContents.contains(out.substring(0, out.indexOf('_')))) {
            System.out.println("found the thing that you are trying to edit");

            clearFile();
            bw.append(currentContents);
            bw.newLine();
        }
        else{
            System.out.println("cant find the thing that we are looking for");
        }



        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
