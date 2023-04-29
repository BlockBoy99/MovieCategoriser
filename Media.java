package MediaTrackerPackage;

public class Media {
    protected String title;
    protected String length;
    protected String currentTime;
    protected String completionStatus;
    protected String colourTag;
    protected String percentageCompletion;
    protected boolean favourite;
    public Media() {
        }





    public String getPercentageCompletion(){
        return percentageCompletion;
    }
    public void setPercentageCompletion(String percentageCompletion) {
        this.percentageCompletion = percentageCompletion;
    }


}
