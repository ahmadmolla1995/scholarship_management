package DatabaseProject.models;

public class ScholarshipLog {
    private Integer scholarshipLogID;
    private ScholarshipStatus action;
    private String date;
    private Integer scholarshipID;

    public ScholarshipLog(Integer scholarshipLogID, ScholarshipStatus action, String date, Integer scholarshipID) {
        this.scholarshipLogID = scholarshipLogID;
        this.action = action;
        this.date = date;
        this.scholarshipID = scholarshipID;
    }

    public Integer getScholarshipLogID() {
        return scholarshipLogID;
    }

    public void setScholarshipLogID(Integer scholarshipLogID) {
        this.scholarshipLogID = scholarshipLogID;
    }

    public ScholarshipStatus getAction() {
        return action;
    }

    public void setAction(ScholarshipStatus action) {
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getScholarshipID() {
        return scholarshipID;
    }

    public void setUserID(Integer scholarshipID) {
        this.scholarshipID = scholarshipID;
    }
}

