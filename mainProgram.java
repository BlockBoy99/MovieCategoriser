package MediaTrackerPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class mainProgram {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    static private final Scanner scannerInput = new Scanner(System.in);
    static public ArrayList movies = new ArrayList<Movie>();

    public static void main(String[] args){

        String toImport=DataRetriever.getAllData();

        try{
            File file = new File(System.getProperty("user.home") + "\\Desktop\\MediaDatabaseForMediaIsDatabase.txt");
            Scanner myReader = new Scanner(file);
            int count=0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                count++;
            }
            myReader = new Scanner(file);
            for (int i=0;i<count;i++){
                String toAdd = myReader.nextLine();


                String[] variables = toAdd.split("_");
                Movie temporaryMovie = new Movie("manual");
                temporaryMovie.manualPopulation(variables);
                movies.add(temporaryMovie);
            }
            myReader.close();


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



        System.out.println(" ");
        System.out.println(ANSI_BLUE + "Hello! welcome to the media rater and edit that we have made"+ ANSI_RESET);


        boolean running = true;
        while (running) {



            System.out.println(" ");
            System.out.println("---------------------------------------------");
            System.out.println(" ");
            System.out.println(ANSI_CYAN+"What would you like to do?"+ ANSI_RESET);
            System.out.println(ANSI_PURPLE+"'add'"+ ANSI_RESET+", or "+ANSI_PURPLE+"'a'"+ ANSI_RESET+", will allow you to add a movie");
            System.out.println(ANSI_PURPLE+"'read'"+ ANSI_RESET+", or "+ANSI_PURPLE+"'r'"+ ANSI_RESET+", will allow you to displayed all stored movies");
            System.out.println(ANSI_PURPLE+"'order'"+ ANSI_RESET+", or "+ANSI_PURPLE+"'o'"+ ANSI_RESET+", will allow you to sort all stored movies");
            System.out.println(ANSI_PURPLE+"'specific'"+ ANSI_RESET+", or "+ANSI_PURPLE+"'s'"+ ANSI_RESET+", will allow you to see all details about a specific movie");
            System.out.println(ANSI_PURPLE+"'edit'"+ ANSI_RESET+", or "+ANSI_PURPLE+"'e'"+ ANSI_RESET+", will allow you to edit a movie attribute");
            System.out.println(ANSI_PURPLE+"'delete'"+ ANSI_RESET+", or "+ANSI_PURPLE+"'d'"+ ANSI_RESET+", will allow you to delete a movie");
            System.out.println(ANSI_PURPLE+"'clearFile'"+ ANSI_RESET+", or "+ANSI_PURPLE+"'c'"+ ANSI_RESET+", will clear your file (and delete all of your movies!)");
            System.out.println(ANSI_PURPLE+"'Terminate'"+ ANSI_RESET+" ends the program ");

            String input = scannerInput.nextLine();


            if ("TERMINATE".equals(input.toUpperCase())) {
                running = false;
            }
            else if (input.toUpperCase().charAt(0) == 'A') {
                Movie movieToAdd = new Movie("NormalMode");
                movies.add(movieToAdd);
                System.out.println(movieToAdd.getTitle() + " added!");


                DataSaver.storeMovie(movieToAdd);


            } // ADD

            else if (input.toUpperCase().charAt(0) == 'E') {

                System.out.println("Which movie do you want to edit? Input the number");
                for (int i = 0; i < movies.size(); i++) {
                    Movie loopedMovie = (Movie) movies.get(i);
                    System.out.println(i+1 + ": " + loopedMovie.getTitle());
                }
                int indexOfMovieToEdit = inputValidator(movies.size()) -1;
                Movie movieToEdit = (Movie) movies.get(indexOfMovieToEdit);

                System.out.println("Enter the number for the attribute you want to edit");
                System.out.println(
                            "1. Movie Title: "+ movieToEdit.getTitle()
                        + "\n2. Movie Length: " + movieToEdit.getLength()
                        + "\n3. How much is currently watched: " + movieToEdit.getCurrentTime()
                        + "\n4. Completion status: " + movieToEdit.getCompletionStatus()
                        + "\n5. Favourited: " + movieToEdit.getFavourite()
                );


                int indexOfAttributeToEdit = inputValidator(5);
                switch (indexOfAttributeToEdit) {
                    case 1 -> movieToEdit.inputTitle();
                    case 2 -> movieToEdit.inputLength();
                    case 3 -> movieToEdit.inputCurrentWatchTime();
                    case 4 -> movieToEdit.inputCompletionStatus();
                    case 5 -> movieToEdit.inputFavourite();
                }
                movies.set(indexOfMovieToEdit, movieToEdit);

                // UPDATE TO FILE
                //clears file
                try{
                    FileWriter fileWrite = new FileWriter(System.getProperty("user.home") + "\\Desktop\\MediaDatabaseForMediaIsDatabase.txt");
                    fileWrite.close();
                }  catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //for each movie:

                //things to input
                //take stuff out of file
                DataSaver.clearFile();
                //clear file
                //put new stuff in
                String out = "adf";

                try {
                    File file = new File(System.getProperty("user.home") + "\\Desktop\\MediaDatabaseForMediaIsDatabase.txt");
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw;
                    bw = new BufferedWriter(fw);
                    for (int count = 0; count < movies.size(); count++) {

                        Movie loopedMovie = (Movie) movies.get(count);
                        out = "";
                        out += loopedMovie.title + "_";
                        out += loopedMovie.length + "_";
                        out += loopedMovie.currentTime + "_";
                        out += loopedMovie.completionStatus + "_";
                        out += loopedMovie.favourite + "_";
                        out += loopedMovie.colourTag;



                        try {
                            //System.out.println("appending " + out + "");
                            bw.append(out);
                            bw.newLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new RuntimeException(e);
                        }

                    }
                    bw.close();


                }
                catch (IOException e) {
                    throw new RuntimeException(e);

                }



            } // EDIT

            else if (input.toUpperCase().charAt(0) == 'O'){
                boolean valid = false;
                while(!valid) {
                    valid = true;
                    System.out.println("How would you like to view the movies:" +
                            "\n1: A-Z" +
                            "\n2: longest first" +
                            "\n3: longest watched first" +
                            "\n4: favourited first" +
                            "\n5: Z-A" +
                            "\n6: longest last" +
                            "\n7: not started last" +
                            "\n8: not favourited first");
                    int answer = Integer.parseInt(scannerInput.nextLine());
                    switch (answer) {
                        case 1-> sortBasedOnName();
                        case 2-> sortBasedOnTime();
                        case 3-> sortBasedOnCurrentLength();
                        case 4-> sortBasedOnIfFavourited();
                        case 5-> sortBasedOnNameAscending();
                        case 6-> sortBasedOnTimeAscending();
                        case 7-> sortBasedOnCurrentLengthAscending();
                        case 8-> sortBasedOnIfNotFavourited();
                        default -> valid = false;
                    }
                    if(valid == false){
                        System.out.println("invalid input");
                    }
                }
            }

            else if (input.toUpperCase().charAt(0) == 'R') {

                for (int i = 0; i < movies.size(); i++) {
                    Movie loopedMovie = (Movie) movies.get(i);
                    System.out.println(i+1 + ": " + loopedMovie.getTitle());
                }

            } // LIST ALL AND SEE MORE DETAILS

            else if (input.toUpperCase().charAt(0) == 'S'){
                for (int i = 0; i < movies.size(); i++) {
                    Movie loopedMovie = (Movie) movies.get(i);
                    System.out.println(i+1 + ": " + loopedMovie.getTitle());
                }

                System.out.println("Enter the number for the movie to see more details for");
                int indexOfMovieToView = inputValidator(movies.size()) -1;
                Movie movieToView = (Movie) movies.get(indexOfMovieToView);
                System.out.println(
                        "1. Movie Title: "+ movieToView.getTitle()
                                + "\n2. Movie Length: " + movieToView.getLength()
                                + "\n3. How much is currently watched: " + movieToView.getCurrentTime()
                                + "\n4. Completion status: " + movieToView.getCompletionStatus()
                                + "\n5. Favourited: " + movieToView.getFavourite()
                );
            }

            else if (input.toUpperCase().charAt(0) == 'C') {
                try{
                    FileWriter fileWrite = new FileWriter(System.getProperty("user.home") + "\\Desktop\\MediaDatabaseForMediaIsDatabase.txt");
                    fileWrite.close();
                }  catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } // DROP DATABASE

            else if (input.toUpperCase().charAt(0) == 'D') {
                for (int i = 0; i < movies.size(); i++) {
                    Movie loopedMovie = (Movie) movies.get(i);
                    System.out.println(i + 1 + ": " + loopedMovie.getTitle());
                }

                System.out.println("Enter the number for the movie you want to delete");
                int indexOfMovieToDelete = inputValidator(movies.size()) - 1;
                Movie movieToDelete = (Movie) movies.get(indexOfMovieToDelete);
                System.out.println("Are you really sure that you want to delete " + movieToDelete.getTitle()+ "?(Y/N)");
                String deletion = scannerInput.nextLine();
                if (deletion.toUpperCase().charAt(0) == 'Y'){
                    movies.remove(indexOfMovieToDelete);
                    System.out.println("Movie deleted");
                }
                else {
                    System.out.println("Deletion aborted");
                }
            }

            else{
                System.out.println("that's not one of the options");
            }
        }
    }
    private static int inputValidator(int maxLength){
        boolean valid = false;
        String choice = "";
        while (!valid){
            choice = scannerInput.nextLine();
            if (!isNumeric(choice)){
                System.out.println("That wasn't a number");
                System.out.println("Enter again\n");
            }
            else if (Integer.parseInt(choice) > maxLength || Integer.parseInt(choice) < 0){
                System.out.println("That is not a valid input. That number is outside of the listed values");
                System.out.println("Enter again\n");
            }
            else {
                valid = true;
            }
        }
        return Integer.parseInt(choice);
    }
    private static boolean isNumeric(String number) {
        boolean isnumeric = true;
        try {
            int testNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            isnumeric = false;
        }
        return (isnumeric);
    }



    public static void sortBasedOnName() {
        movies.sort(Comparator.comparing(Movie::getTitle));
    }

    public static void sortBasedOnTime() {
        movies.sort(Comparator.comparing(Movie::getLength));
    }
    public static void sortBasedOnCurrentLength() {
        movies.sort(Comparator.comparing(Movie::getCurrentTime));
    }
    public static void sortBasedOnIfFavourited() {
        movies.sort(Comparator.comparing(Movie::getFavourite));
    }
    public static void reverseList() {
        Collections.reverse(movies);
    }

    public static void sortBasedOnNameAscending() {
        movies.sort(Comparator.comparing(Movie::getTitle));
        reverseList();
    }
    public static void sortBasedOnTimeAscending() {
        movies.sort(Comparator.comparing(Movie::getLength));
        reverseList();
    }
    public static void sortBasedOnCurrentLengthAscending() {
        movies.sort(Comparator.comparing(Movie::getCurrentTime));
        reverseList();
    }
    public static void sortBasedOnIfNotFavourited() {
        movies.sort(Comparator.comparing(Movie::getFavourite));
        reverseList();
    }
}
