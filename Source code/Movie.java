package MediaTrackerPackage;

import java.util.Scanner;

public class Movie {

    // Lord Scanner
    private static final Scanner inputScanner = new Scanner(System.in);


    // Attributes
    protected String title;
    protected String length;
    protected String currentTime;
    protected String completionStatus;
    protected String colourTag;
    protected String percentageCompletion;
    protected boolean favourite;


    // Constructor
    public Movie(String setting) {
        if (!setting.equals("manual")) {
            inputTitle();
            inputLength();
            inputCurrentWatchTime();
            inputCompletionStatus();
            inputFavourite();
            this.colourTag = "red";
        }

    }

    public void manualPopulation(String[] variables){
        letTitle(variables[0]);
        letLength(variables[1]);
        letCurrentWatchTime(variables[2]);
        letCompletionStatus(variables[3]);
        letPercentageCompletion(variables[4]);
        letFavourite(Boolean.parseBoolean(variables[5]));
        this.colourTag = "red";
    }


    // Complex Setters
    public void inputTitle() {
        System.out.println("What would you like the new movie to be called?");
        String inName=inputScanner.nextLine();
        boolean validName = false;
        while (!validName) {
            if (inName.equals("")) {
                System.out.println("Title is empty");

            }
            else {
                System.out.println("Is this the correct movie title (Y/N) ?: " + inName );
                String temp=inputScanner.nextLine();
                if (temp.toUpperCase().charAt(0) == 'Y') {
                    validName = true;
                }
                else {
                    System.out.println("Then change itr");
                }
            }
            if(!validName){
                System.out.println("Enter another movie title");
                inName = inputScanner.nextLine();
            }

        }
        this.title = inName;

    }
    public void inputLength(){
        System.out.println("How long is the movie in minutes?");
        boolean valid = false;
        String intime=null;
        while (!valid) {
            intime = inputScanner.nextLine();
            if (isNumeric(intime)) {
                int time = Integer.parseInt(intime);
                if (time <= 0) {
                    System.out.println("Runtime length inputted should be more than 0 minutes");
                } else if (time >= 480) {
                    System.out.println("Runtime length inputted should be less than 480 minutes");
                } else {
                    valid = true;
                }
            } else {
                System.out.println("Number is not numerical");
            }
            if (!valid) {
                System.out.println("Please re-enter the runtime");
            }
        }
        this.length = intime;
    }
    public void inputCurrentWatchTime(){ // tests if you are being a silly munchkin
        System.out.println("How much is currently viewed in minutes?");
        String currentWatchTime = null;
        boolean valid = false;

        while(!valid) {
            currentWatchTime = inputScanner.nextLine();
            if (isNumeric(currentWatchTime)){
                if (Integer.parseInt(currentWatchTime)>Integer.parseInt(this.length)){
                    System.out.println("Watch time must be less than total movie length");
                }
                else if (Integer.parseInt(currentWatchTime)<0) {
                    System.out.println("Watch time must be at least 0");
                }
                else{
                    valid=true;
                }
            } else{
                System.out.println("watch time inputted is not a number :(, try again!");
            }

            if (!valid){
                System.out.println("Please re-enter the watch time");

            }

        }
        this.currentTime = currentWatchTime;
    }
    public void inputCompletionStatus(){
        int runTime = Integer.parseInt(this.length);
        int watchTime = Integer.parseInt(this.currentTime);
        String out;
        if(watchTime <= 0){
            out = "not started";
        }
        else if(runTime > watchTime){
            out="still watching";
        }
        else{
            out="completed";
        }
        this.completionStatus = out;
    }
    public void inputFavourite(){
        System.out.println("Is the movie favourited? (true/false)");
        boolean valid = false;
        String favouritedInput = inputScanner.nextLine();
        boolean actuallyBool = Boolean.parseBoolean(favouritedInput);
        this.favourite = Boolean.parseBoolean(String.valueOf(actuallyBool));
    }
    
    
    //setters
    public void letTitle(String title) {
        this.title = title;
    }
    public void letLength(String length) {
        this.length = length;
    }
    public void letCurrentWatchTime(String currentTime) {
        this.currentTime = currentTime;
    }
    public void letCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }
    public void letPercentageCompletion(String percentageCompletion) {
        this.percentageCompletion = percentageCompletion;
    }
    public void letFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    // Type Tests
    private boolean isNumeric(String number) {
        boolean isnumeric = true;
        try {
            int testNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            isnumeric = false;
        }
        return (isnumeric);
    }



    // Getters
    public String getTitle() {
        return this.title;
    }
    public String getLength() {
        return length;
    }
    public String getCurrentTime() {
        return currentTime;
    }
    public String getCompletionStatus() {
        return completionStatus;
    }
    public Boolean getFavourite(){
        return favourite;
    }
    public String getColourTag() {
        return colourTag;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + title + '\'' +
                ", length='" + length + '\'' +
                ", current_time='" + currentTime + '\'' +
                ", completion_status='" + completionStatus + '\'' +
                ", favourite= '" + favourite + '\'' +
                ", colour tag='" + colourTag + '\'' ;


    }


}
