package DatabaseProject.models;

public class ScholarshipRequest {
    private Integer scholarshipID;
    private int userID;
    private ScholarshipStatus status;
    private String destinationUniversity;
    private String field;
    private String startDate;

    public ScholarshipRequest(Integer scholarshipID, int userID, ScholarshipStatus status, String destinationUniversity, String field, String startDate) {
        this.scholarshipID = scholarshipID;
        this.status = status;
        this.userID = userID;
        this.destinationUniversity = destinationUniversity;
        this.field = field;
        this.startDate = startDate;
    }

    public Integer getScholarshipID() {
        return scholarshipID;
    }

    public void setScholarshipID(Integer scholarshipID) {
        this.scholarshipID = scholarshipID;
    }

    public ScholarshipStatus getScholarshipStatus() {
        return status;
    }

    public void setScholarshipStatus(ScholarshipStatus status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDestinationUniversity() {
        return destinationUniversity;
    }

    public void setDestinationUniversity(String destinationUniversity) {
        this.destinationUniversity = destinationUniversity;
    }

    public String getField() {
        return field;
    }

    public void setField(String field){
        this.field = field;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
