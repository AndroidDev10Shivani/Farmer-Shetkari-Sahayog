package com.sample.shetkarisahayogfarmer;

public class ComplaintHelperClass {
    String complaint, date, complaintMade, complaintAgainst ;
    long madeId, againstId;

    public ComplaintHelperClass() {
    }

    public ComplaintHelperClass(String complaint, String date, String complaintMade, String complaintAgainst, long madeId, long againstId) {
        this.complaint = complaint;
        this.date = date;
        this.complaintMade = complaintMade;
        this.complaintAgainst = complaintAgainst;
        this.madeId = madeId;
        this.againstId = againstId;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComplaintMade() {
        return complaintMade;
    }

    public void setComplaintMade(String complaintMade) {
        this.complaintMade = complaintMade;
    }

    public String getComplaintAgainst() {
        return complaintAgainst;
    }

    public void setComplaintAgainst(String complaintAgainst) {
        this.complaintAgainst = complaintAgainst;
    }

    public long getMadeId() {
        return madeId;
    }

    public void setMadeId(long madeId) {
        this.madeId = madeId;
    }

    public long getAgainstId() {
        return againstId;
    }

    public void setAgainstId(long againstId) {
        this.againstId = againstId;
    }
}
